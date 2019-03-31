import java.awt.*;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import image.Assets;


//Clase principal que incializa las demas, y controla el Game Loop
public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	//Iniciar dimensiones de ventana
	private static final int VentanaAncho = 1280, VentanaAltura = VentanaAncho;
	
	//Variables para Thread
	private Thread thread;
	private boolean corriendo;
	
	//Instancia Jugador
	private Personaje personaje; 
	
	//Variables para render de Imagen
	private Graphics g;
	private BufferStrategy bs;
	
	//Clase que guarda los tiles del juego, y la informacion de cada una
	private GameMap map;
	
	
	//Algoritmo A* Pathfinding utilizado para la busqueda de caminos optimos para el enemigo
	private AStarSearch a;
	
	//Camara
	private Camara camara;
	
	//Objeto Handler para guardar en lista entidades que interactuan con otros
	private Handler handler;
	
	
	//Iniciar
	public static void main(String args[])
	{
		new Game();
	}
	
	//Iniciar juego
	public Game()
	{
		corriendo = false;
		new MiCanvas(VentanaAncho,VentanaAltura, "Earth 2099", this);
	}
	
	//Inicar el thread
	public synchronized void start()
	{	
		//Detener si ya se esta corriendo
		if (corriendo) return;
		
		// Inicializa todas las imágenes (se encuentran en Assets)
		/*
		 * Hacer uso de Assets nos da la ventaja de no tener que estar cargando
		 * las imágenes cada que sean ocupadas, sino que solo se cargan una vez
		 * y son referenciadas cuando se requieran usar.
		 */
		Assets.init();
		
		//Empezar el thread
		thread = new Thread(this);
		thread.start();
		corriendo = true;
		
		//Crear instancias iniciales
		camara = new Camara(0,0);
		handler = new Handler();
		
		//Crear mapa que tendra los tiles
		map = new GameMap();
		personaje = new Personaje(4000,4000,30,30,"Personaje",handler,this);
		
		//Crear algoritmo de busqueda
		a = new AStarSearch(map);
		handler.agregarObjeto(personaje);
		
		//Crear colisiones basadas en donde hay tiles bloqueados
		for (int x=0;x<map.getWidthInTiles();x++) {
			for (int y=0;y<map.getHeightInTiles();y++) {
				if(map.blocked(x, y))
				{
					handler.agregarObjeto(new BloqueColision(80*x,80*y,80,80,handler,this));
				}

			}
		}

		handler.agregarObjeto(new Zombi(4333,4000,80,80,"Zombi 1",100,5,8,handler,this));
		handler.agregarObjeto(new Zombi(4333,4000,80,80,"Zombi 2",100,5,8,handler,this));
		handler.agregarObjeto(new Fantasma(4333,4000,80,80,"Fantasma 1",100,5,2,handler,this));
		handler.agregarObjeto(new Invisibilidad(4000,4000,80,80,"Invisibilidad",handler,this));
		
	
		//Agregar KeyListener al jugador
		this.addKeyListener(new KeyInput(personaje));

		//Agregar MouseListener al Canvas
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				


			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			//Llamar funcion disparar del jugador pasando la posicion actual del Mouse
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
				int x,y;
				//Obtener posicion de el mouse respecto a la posicion de la camara
				x = e.getX() - (int)camara.getxOffset();
				y = e.getY() - (int)camara.getyOffset();
				personaje.disparar(x,y);
			} 

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			
			
		});
		//Agregar mouse listener de scroll de Mouse para cambiar de armas
		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub
					
			      	e.consume(); 
			        int notches = e.getWheelRotation();
			        while (notches > 0) {
			        	personaje.cambiarArma(1);
			            notches--;
			        }
			        while (notches < 0) {
			        	personaje.cambiarArma(-1);
			            notches++;
			        }
			}


			
		});


	}

	//Detener el thread
	public synchronized void stop()
	{
		try {
			thread.join();
			corriendo = false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	//Llamar los metodos actualizar y render 60 veces por segundo y mantener el loop constante.
	@Override
	public synchronized void run() {
				int fps = 60, ticks = 0;
				double timePerTick = 1000000000 / fps, delta = 0;
				long now, lastTime = System.nanoTime(), timer = 0;
				
				while (corriendo)
				{
					now = System.nanoTime();
					delta += (now-lastTime) / timePerTick;
					timer += now - lastTime;
					lastTime = now;
					
					if (delta >= 1)
					{
						actualizar();
						dibujar();

						ticks ++;
						delta --;
					}
					
					if (timer >= 1000000000)
					{
						System.out.println("Ticks and frames: " + ticks);

						ticks = 0;
						timer = 0;
					}
				}
		stop();
		
	}
	//Reiniciar juego y thread
	public void resetear()
	{
		stop();
		start();
	}
	//Llamar los metodos actualizar de cada Entidad
	private synchronized void actualizar()
	{	
		for(int i = 0; i < handler.listaEntidades.size();i++)
		{
			if(handler.listaEntidades.get(i) instanceof Personaje)
			{
				camara.actualizar((Personaje) handler.listaEntidades.get(i));
			}
		}
		//Se llamara aqui metodo actualizar de cada entidad en el Handler
		handler.actualizar();
	}
	private synchronized void dibujar()
	{
		//Crear u obtener BufferStrategy
        bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        try {
        	//Dibujar el fondo
        	g = bs.getDrawGraphics();
        	//Convertir a Graphics2D para utilizar el metodo translate
        	Graphics2D g2d = (Graphics2D)g;
        	
        	g2d.setColor(Color.BLACK);
        	g2d.fillRect(0,0,VentanaAncho,VentanaAltura);
        	
        	
        	//Mover el canvas a la posicion de la camara
        	g2d.translate(camara.getxOffset(), camara.getyOffset()); //COMENZAR CAMARA
        	
        	//Todo los objetos que se moveran junto a la camara deben ponerse en medio
        	
        	////////////////////////////////////////////////////////////////////////////
        	//Se llamara aqui metodo render de cada entidad
        	//Dibujar los tiles
    		for (int x=0;x<map.getWidthInTiles();x++) {
    			for (int y=0;y<map.getHeightInTiles();y++) {
    				if(map.blocked(x, y))
    				{
    					g2d.drawImage(Assets.waterTile,x*80,y*80,null);
    				}else {
    					g2d.drawImage(Assets.grassTile,x*80,y*80,null);

    				}

    			}
    		}
        	handler.render(g2d);
        	

    		
        	//Obtener posicion actual de la camara
        	int camPosX = (int) -camara.getxOffset();
        	int camPosY = (int) -camara.getyOffset();
        	
        	g2d.setColor(Color.WHITE);
        	
        	//Dibujar texto de informacion
        	g.drawString("CONTROLES:", camPosX + 20, camPosY + 200);
        	g.drawString("W A S D para moverse", camPosX + 20, camPosY +  220);
        	g.drawString("1 , 2 , 3 para cambiar de arma", camPosX + 20, camPosY + 240);
        	g.drawString("R para recargar", camPosX + 20, camPosY + 260);
        	g.drawString("Click para disparar a direccion de mouse", camPosX + 20, camPosY + 280);
        	
        	////////////////////////////////////////////////////////////////////////////
        	g2d.translate(-camara.getxOffset(), -camara.getyOffset()); //TERMINAR CAMARA


        	
        	//Dibujar texto de informacion de prueba



        }finally {
        	g.dispose();
        }

		bs.show();
	}
	
	
	//Regresa posicion de la camara para los objetos que no tienen referencia a el
	public double getCamaraXOffset()
	{
		return camara.getxOffset();
	}
	
	public double getCamaraYOffset()
	{
		return camara.getyOffset();
	}

	
	//Regresa dimensiones de la ventana 
	public static int getVentanaAncho() {
		return VentanaAncho;
	}

	public static int getVentanaAltura() {
		return VentanaAltura;
	}
	//Calcular posicion del tile en proporcion a las dimensiones de los tiles (Por ahora 80x80)
	public int getTilePosX(int x)
	{
		return (int) Math.floor(x/80);
	}
	public int getTilePosY(int y)
	{
		return (int) Math.floor(y/80);
	}
	
	
	//Calcular camino al jugador
	public Path obtenerCamino(int x, int y)
	{
		
		//Nueva busqueda
		a.clear();
		
		//Iniciar camino nulo
		Path path = null;
		
		//Si el jugador esta cerca ir a posicion directamente para ataque
		if((Math.abs(x - personaje.getX()) + Math.abs(y - personaje.getY())) < 300)
		{
			path = a.findPath(getTilePosX(x),getTilePosY(y),getTilePosX(personaje.getX()),getTilePosY(personaje.getY()));
			return path;
		}
		
		//Mientras no se encuentre un camino valido
		while(path==null)
		{
			
		//Crear una posicion basada en la del jugador al azar
		//Esto se usa para que el enemigo utilice diferentes caminos y ataque desde diferentes lados
		Random rand = new Random();
		int attackPoint = rand.nextInt(7);
		int ex = 0, ye = 0;
		switch(attackPoint)
		{
		//Atacar a derecha
		case 0:
			ex = getTilePosX(personaje.getX() + 80);
			ye = getTilePosY(personaje.getY());
			break;
		//Atacar a izquierda
		case 1:
			ex = getTilePosX(personaje.getX() - 80);
			ye = getTilePosY(personaje.getY());
			break;
		//Atacar a arriba
		case 2:
			ex = getTilePosX(personaje.getX());
			ye = getTilePosY(personaje.getY() + 80);
			break;
		//Atacar a abajo
		case 3:
			ex = getTilePosX(personaje.getX());
			ye = getTilePosY(personaje.getY() - 80);
			break;
		//Atacar a arriba derecha
		case 4:
			ex = getTilePosX(personaje.getX() + 80);
			ye = getTilePosY(personaje.getY() + 80);
			break;
			
		//Atacar a abajo izquierda
		case 5:
			ex = getTilePosX(personaje.getX() - 80);
			ye = getTilePosY(personaje.getY() - 80);
			break;
		//Atacar a abajo derecha
		case 6:
			ex = getTilePosX(personaje.getX()+ 80);
			ye = getTilePosY(personaje.getY() - 80);
			break;
		//Atacar a arriba izquierda
		case 7:
			ex = getTilePosX(personaje.getX() - 80);
			ye = getTilePosY(personaje.getY() + 80);
			break;
			
		}
		
		//Minimo posicion de matriz 0 , y maximo 99 para que no se salga de las dimensiones de la matriz
		ex = clamp(ex,0,99);
		ye = clamp(ye,0,99);
		
		//Calcular camino de posicion del jugador pasado hacia la posicion creada
		path = a.findPath(getTilePosX(x),getTilePosY(y),ex,ye);
		
		}
		//Regresar camino una vez que sea valido
		return path;

	}
	
	//Funcion para regresar valor con un minimo y un maximo posible
	public static int clamp(int val, int min, int max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	//Regresa posicion de jugador para llamarlo desde cualquier clase
	public int getJugadorX()
	{
		return personaje.getX();
	}
	public int getJugadorY()
	{
		return personaje.getY();
	}
	
}
