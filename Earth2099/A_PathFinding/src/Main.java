import java.awt.*;
import java.awt.List;
import java.util.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;


//Clase principal que incializa las demas, y controla el Game Loop
public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	//Iniciar dimensiones de ventana
	private static final int VentanaAncho = 800, VentanaAltura = 800;
	
	//Variables para Thread
	private Thread thread;
	private boolean corriendo;
	
	//Instancia Jugador
	private Enemy enemy;
	
	//Variables para render de Imagen
	private Graphics g;
	private BufferStrategy bs;
	private GameMap map;
	private Path path;
	
	//Iniciar
	public static void main(String args[])
	{
		new Main();
	}
	
	
	//Iniciar juego
	public Main()
	{
		corriendo = false;

		new MiCanvas(VentanaAncho,VentanaAltura, "A* Pathfinding", this);
		

	}

	//Inicar el thread
	public synchronized void start()
	{	
		//Detener si ya se esta corriendo
		if (corriendo) return;
		
		//Empezar el thread
		thread = new Thread(this);
		thread.start();
		corriendo = true;
		
		//Crear instancias iniciales

		map = new GameMap();
		AStarSearch a = new AStarSearch(map);

		path = a.findPath(0, 0,0,9);
		enemy = new Enemy(0,0,80,80,path);
		System.out.println(path.getLength());

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

			} 

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

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
	private void actualizar()
	{	
		enemy.update();
	}
	private void dibujar()
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
        	
        	g2d.setColor(Color.WHITE);
        	g2d.clearRect(0, 0, VentanaAncho, VentanaAltura);
        	g.fillRect(0,0,VentanaAncho,VentanaAltura);
        	
    		for (int x=0;x<map.getWidthInTiles();x++) {
    			for (int y=0;y<map.getHeightInTiles();y++) {
					g.setColor(Color.BLUE);
    				g.drawRect(x*80, y*80, 80, 80);
    				
    				if(path.contains(x,y))
    				{
    					g.setColor(Color.RED);
        				g.fillRect(x*80, y*80, 80, 80);
    				}
    				if(map.blocked(x, y))
    				{
    					g.setColor(Color.BLACK);
        				g.fillRect(x*80, y*80, 80, 80);
    				}

    			}
    		}
    		g.setColor(Color.BLUE);
    		enemy.render(g);

        	

        	//colBalas.actualizar();
        }finally {
        	g.dispose();
        }

		bs.show();
	}
	
	

	
	//Regresa dimensiones de la ventana 
	public static int getVentanaAncho() {
		return VentanaAncho;
	}

	public static int getVentanaAltura() {
		return VentanaAltura;
	}

}
