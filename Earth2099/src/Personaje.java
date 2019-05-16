import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;
import java.util.Random;

import javax.swing.Timer;

import image.Assets;


//Clase que el jugador controla
public class Personaje extends Entidad{
	
	//Sprites / Animaciones
	private static image.AnimationSprite imagen,abajoAnimacion,abajoDerechaAnimacion,derechaAnimacion,
	arribaDerechaAnimacion,arribaAnimacion,arribaIzquierdaAnimacion,izquierdaAnimacion,abajoIzquierdaAnimacion;

	
	private int imageOffsetX;
	private int imageOffsetY;
	
	//Angulo del jugador al Mouse
	private float angulo;

	//Posicion del mouse
	private Point posicionMouse;
	//Velocidad que tendra el jugador
	private int velocidad;
	
	//Que direccion/tecla esta activa
	private boolean arriba = false,abajo =false ,izquierda = false,derecha = false;
	
	//Vida actual y vida maxima
	private int vida;

	
	//Arreglo de armas con maximo 3
	private Arma[] armas;
	
	//Posicion actual en el arreglo de armas
	private int indexArma;
	
	//Puntuacion del jugado
	private int puntuacion;
	
	//Numero de armas actuales
	private int armasActuales;
	
	//Powerup equipado
	private PowerUp powerup;
	
	//Variables para controlar el tiempo en el que puede ser lastimado el jugador
	private boolean puedeSerLastimado;
	private Timer puedeSerLastimadoTimer;
	private Timer esquivando;
	private Timer puedeEsquivarTimer;
	private int tiempoPuedeSerLastimado = 1000; 
	
	private boolean puedeEsquivar = true;
	private boolean puedeVolverAEsquivar = true;

	//Contador de enemigos muertos

	public Personaje(int x, int y, int ancho, int altura, String nombre, Handler handler,Game main) {
		super(x,y,ancho,altura,nombre,handler,main);
		vida = HUD.getVida();
		
		//Poscicion Mouse
		posicionMouse = new Point();
		posicionMouse.x = x;
		posicionMouse.y = y;
		//Agregar armas
		armas = new Arma[2];
		armas[0] = HUD.getArma1();
		armas[1] = HUD.getArma2();

		indexArma = 0;
		puntuacion = 0;
		armasActuales = 2;
		velocidad = 9;
		puedeSerLastimado = true;
		puedeSerLastimadoTimer = new Timer(tiempoPuedeSerLastimado,timerPoderDisparar);
		esquivando = new Timer(600,esquivandoTimer);
		puedeEsquivarTimer = new Timer(600,volverAEsquivarListener);


		image.SpriteBuilder builder = new image.SpriteBuilder(Assets.pAbajo,76,96);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);

		abajoAnimacion = new image.AnimationSprite((int)x,(int)y,builder.build());
		abajoAnimacion.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.pAD,76,96);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);

		abajoDerechaAnimacion = new image.AnimationSprite((int)x,(int)y,builder.build());
		abajoDerechaAnimacion.setAnimSpd(10);
		
		
		builder = new image.SpriteBuilder(Assets.pDerecha,76,96);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);

		derechaAnimacion = new image.AnimationSprite((int)x,(int)y,builder.build());
		derechaAnimacion.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.pArribaDerecha,76,96);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);

		arribaDerechaAnimacion = new image.AnimationSprite((int)x,(int)y,builder.build());
		arribaDerechaAnimacion.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.pArriba,76,96);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);

		arribaAnimacion = new image.AnimationSprite((int)x,(int)y,builder.build());
		arribaAnimacion.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.pArribaIzquierda,76,96);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);

		arribaIzquierdaAnimacion = new image.AnimationSprite((int)x,(int)y,builder.build());
		arribaIzquierdaAnimacion.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.pIzquierda,76,96);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);

		izquierdaAnimacion = new image.AnimationSprite((int)x,(int)y,builder.build());
		izquierdaAnimacion.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.pAbajoIzquierda,76,96);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);

		abajoIzquierdaAnimacion = new image.AnimationSprite((int)x,(int)y,builder.build());
		abajoIzquierdaAnimacion.setAnimSpd(10);
		
		imagen = abajoAnimacion;
		
	}
	
	//Timer
    ActionListener timerPoderDisparar = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			puedeSerLastimado = true;
			puedeSerLastimadoTimer.stop();
		}
	};
	//Timer
	ActionListener esquivandoTimer = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			puedeVolverAEsquivar = false;
			puedeEsquivar = true;
			puedeEsquivarTimer.start();
			esquivando.stop();

		}
    };

	//Timer
	ActionListener volverAEsquivarListener = new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			puedeVolverAEsquivar = true;
			puedeEsquivarTimer.stop();
		}
	};
    
	public static int clamp(int val, int min, int max) {
	    return Math.max(min, Math.min(max, val));
	}
	//Asignar direcciones, se llaman desde el KeyInput
	public void setAbajo(boolean aux)
	{
		abajo = aux;
	}
	public void setArriba(boolean aux)
	{
		arriba = aux;
	}
	public void setIzquierda(boolean aux)
	{
		izquierda = aux;
	}
	public void setDerecha(boolean aux)
	{
		derecha = aux;
	}
	
	//Dependiendo de la direccion asignar velocidad
	public void asignarDireccion()
	{
		velX = 0;
		velY = 0;
		if(arriba)
			velY = -velocidad;
		if(abajo)
			velY = velocidad;
		if(izquierda)
			velX = -velocidad;
		if(derecha)
			velX = velocidad;
	}

	public void esquivar()
	{
		if(!puedeEsquivar ||(velX == 0 && velY == 0) || !puedeVolverAEsquivar)
			return;
		puedeEsquivar = false;


		velX = (int)(velX * 1.8);
		velY = (int)(velY * 1.8);
		esquivando.start();

	}
	public void cambiarNivel()
	{
		main.resetear();
	}
	
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {

		this.vida = vida;
		if(vida >= 100)
			this.vida = 100;
	}


	
//Cambiar posicion de arma del arreglo entre 1 y 3, se usa en el scroll del mouse
	public void cambiarArma(int num)
	{
		if(indexArma + num >= 0 && indexArma + num < armasActuales) {
			indexArma += num;
			HUD.setIndex(indexArma);
		}
	}
	
	//Asignar una posicion del arreglo de armas, se usa en las teclas 1,2,y 3
	public void asignarArma(int num)
	{

		if(num < armasActuales && num >= 0) {
			indexArma = num;
			HUD.setIndex(indexArma);
		}
	}

	
	//Llamar metodo disparar de la arma actual
	public void disparar(int mx, int my)
	{
		armas[indexArma].disparar(x+imageOffsetX, y+imageOffsetY, mx, my,angulo);

		
	}
	public float getAngulo(Point destino) {
	    float angulo = (float) Math.toDegrees(Math.atan2(destino.y - MiCanvas.getCanvas().getState().getVentanaAltura()/2, destino.x - MiCanvas.getCanvas().getState().getVentanaAncho()/2));

	    if(angulo < 0)
	    	angulo += 360;
	    return angulo;
	}
	public void sumarBalas(int a)
	{
		armas[indexArma].recargar(a);
	}
	//Posicion del mouse
	public void cambiarPosicionMouse(int a,int b)
	{
		posicionMouse.x = a;
		posicionMouse.y = b;
	}
	//Actualizar
	@Override
	public void actualizar() {


		//Si ha matado mas de 30 enemigos
		//Asignar direccion dependiendo de las teclas
		if (puedeEsquivar) {
			asignarDireccion();
		}


		//Checar si esta colisionando con otra entidad
		checarColision();
		if (!puedeEsquivar)
		{
			if(velY == 0 && velX == 0)
			{
				puedeEsquivar = true;
				puedeEsquivarTimer.stop();
			}else{
				Random ran = new Random();
				if(ran.nextInt(100) < 50) {
					int aux = ran.nextInt(40);
					int aux2 = ran.nextInt(40);
					int aux3 = ran.nextInt(2) == 0 ? ran.nextInt(30) : -ran.nextInt(30);
					int aux4 = ran.nextInt(2) == 0 ? ran.nextInt(30) : -ran.nextInt(40);
					handler.agregarObjeto(new SlideEffect(x + aux3, y + aux4, 5 + aux, 5 + aux2, "Effecto", handler, main));
				}

			}
		}
		angulo = getAngulo(posicionMouse);

		if(angulo < 100 && angulo > 70)
		{
			imagen = abajoAnimacion;
			armas[indexArma].cambiarImagen(1);
			imageOffsetX = -5;
			imageOffsetY = 30;
		}else if(angulo < 70 && angulo > 20)
		{
			imagen = abajoDerechaAnimacion;
			armas[indexArma].cambiarImagen(5);
			imageOffsetX = 10;
			imageOffsetY = 20;
		}
		else if(angulo > -10 && angulo < 20)
		{
			imagen = derechaAnimacion;
			armas[indexArma].cambiarImagen(2);
			imageOffsetX = 10;
			imageOffsetY = 0;

		}else if((angulo < -10 && angulo > 0) || (angulo < 365 && angulo > 280))
		{	imagen = arribaDerechaAnimacion;
			armas[indexArma].cambiarImagen(6);
			imageOffsetX = 10;
			imageOffsetY = -20;

		}else if(angulo < 280 && angulo > 240)
		{
			imagen = arribaAnimacion;
			armas[indexArma].cambiarImagen(0);
			imageOffsetX = 0;
			imageOffsetY = -40;

		}else if(angulo < 240 && angulo > 200)
		{
			imagen = arribaIzquierdaAnimacion;
			armas[indexArma].cambiarImagen(8);
			imageOffsetX = -50;
			imageOffsetY = -20;

		}
		else if(angulo < 200 && angulo > 170)
		{
			imagen = izquierdaAnimacion;
			armas[indexArma].cambiarImagen(4);
			imageOffsetX = -50;
			imageOffsetY = 0;

		}
		else if(angulo < 170 && angulo > 100)
		{
			imagen = abajoIzquierdaAnimacion;
			armas[indexArma].cambiarImagen(7);
			imageOffsetX = -50;
			imageOffsetY = 20;
		}
		HUD.cambiarBalas(armas[indexArma].getBalas());
		HUD.cambiarNombreArma(armas[indexArma].getNombre());
		//Animar
		if(velX != 0 || velY != 0)
			imagen.update();
		//Cambiar posicion
		x += velX;
		x = clamp(x,0,8000 - 30);
		y += velY;
		y = clamp(y,0,8000 - 60);
	    //Si tiene un powerup, que tome su efecto
	    if(powerup != null){
	      if(!powerup.agregarAtributos(this)){
	        powerup = null;
	      }
	    }
		imagen.setY((int)y - 40);
		imagen.setX((int)x - 25);
	}
	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return (y-30) + 96;
	}
	

	public void agarrarArma() {
		//Lista de entidades
		ListIterator<Entidad> iterator = handler.listaEntidades.listIterator();
		while (iterator.hasNext()) {
			Entidad aux = iterator.next();

			if (aux instanceof ArmaMapa) {
				if (armasActuales < 3 && chocandoEn(x, y, aux)) {
					switch (((ArmaMapa) aux).getArma()) {
						case "Metralleta":
							armas[indexArma] = new Metralleta("Metralleta", 50, 60, 60, 100, main);
							if(indexArma==0)
							{
								HUD.setArma1(new Metralleta("Metralleta", 50, 60, 60, 100, main));
							}else{
								HUD.setArma2(new Metralleta("Metralleta", 50, 60, 60, 100, main));
							}
							break;
						case "Escopeta":
							armas[indexArma] = new Escopeta("Escopeta", 10, 40, 40, 1000, main);
							if(indexArma==0)
							{
								HUD.setArma1(new Escopeta("Escopeta", 10, 40, 40, 1000, main));
							}else{
								HUD.setArma2(new Escopeta("Escopeta", 10, 40, 40, 1000, main));
							}
							break;
						case "Laser":
							armas[indexArma] = new LaserArma("Laser", 30, 20, 20, 500, main);
							if(indexArma==0)
							{
								HUD.setArma1( new LaserArma("Laser", 30, 20, 20, 500, main));
							}else{
								HUD.setArma2( new LaserArma("Laser", 30, 20, 20, 500, main));
							}
							break;
						case "Wavy":
							armas[indexArma] = new WavyArma("Wavy", 50, 30, 30, 300, main);
							if(indexArma==0)
							{
								HUD.setArma1(  new WavyArma("Wavy", 50, 30, 30, 300, main));
							}else{
								HUD.setArma2(  new WavyArma("Wavy", 50, 30, 30, 300, main));
							}
							break;
						case "Lanzallamas":
							armas[indexArma] = new Lanzallamas("Lanzallamas",120,120,120,700,main);
							if(indexArma==0)
							{
								HUD.setArma1(new Lanzallamas("Lanzallamas",120,120,120,700,main));
							}else{
								HUD.setArma2(new Lanzallamas("Lanzallamas",120,120,120,700,main));
							}
							break;

					}
					if(armasActuales < 1)
						armasActuales++;
					main.bajarCountFactoryArmas();
					handler.quitarObjeto(aux);
				}
			}
		}
	}
	//Checar colision
	public void checarColision() {
		
		//Lista de entidades
		ListIterator <Entidad> iterator = handler.listaEntidades.listIterator();
		while (iterator.hasNext())
		{
			Entidad aux = iterator.next();
			
			//Si es un bloque solido, asignar velocidad a cero
			if (aux instanceof BloqueColision)
			{
				if (chocandoEn(x+velX, y, aux))
				{
					int sign = velX > 0? 1: -1;
					//Acercarse a la pared
					while (!chocandoEn(x+sign, y, aux))
						x+=sign;
					//Si se atora, salir
					while(chocandoEn(x,y,aux))
					{
						x-=sign;
					}
					velX = 0;
				}
				if (chocandoEn(x, y+velY, aux))
				{
					int sign = velY > 0? 1: -1;
					//Acercarse a la pared
					while (!chocandoEn(x, y+sign, aux))
						y+=sign;
					
					//Si se atora, salir
					while(chocandoEn(x,y,aux))
					{
						y-=sign;
					}
					velY = 0;
				}

			}
			
			//Si es un enemigo, reducir vida
			if(aux instanceof TemplateEnemy)
			{

				//No hacer daño cuando esta esquivando
				if(powerup instanceof Invisibilidad)
					continue;
				if(!puedeEsquivar)
				{
					if(chocandoEn(x,y,aux)) {

						((TemplateEnemy) aux).setVida(((TemplateEnemy) aux).getVida() - 5);
						handler.agregarObjeto(new TextoFlotante(aux.getX(), aux.getY(), 30, 30, "dmg", "-5", 1, handler, main));
						if(((TemplateEnemy) aux).getVida() <= 0)
						{
							//Si es esqueleto parar el timer de tirar hueso
							if(aux instanceof Esqueleto)
								((Esqueleto) aux).stopTimer();
							if(aux instanceof EsqueletoLider)
								((EsqueletoLider) aux).stopTimer();
							Random ran = new Random();
							int auxNum = ran.nextInt(30)+15;

							handler.agregarObjeto(new TextoFlotante(x,y,30,30,"dmg","+" + auxNum,3,handler,main));
							HUD.sumarPuntos(auxNum);
							handler.quitarObjeto(aux);
							main.bajarCountFactoryEnemigo();
							main.sumarMuerto();
						}

					}
				}


				if(puedeSerLastimado && chocandoEn(x,y,aux))
				{
					vida-=5;
					handler.agregarObjeto(new TextoFlotante(x,y,30,30,"dmg","-5",1,handler,main));

					puedeSerLastimado = false;
					puedeSerLastimadoTimer.start();

				}

			}
			if(aux instanceof BalaEnemigo)
			{
				if (chocandoEn(x, y, aux)) {
					//No hacer daño cuando esta esquivando
					if(!puedeEsquivar || powerup instanceof Invisibilidad)
						continue;
					setVida(getVida() - 10);
					handler.agregarObjeto(new TextoFlotante(x,y,30,30,"dmg","-10",1,handler,main));

					handler.quitarObjeto(aux);

				}
			}
		      if(aux instanceof PowerUp){
		        if(chocandoEn(x, y, aux)){
		          this.powerup = ((PowerUp)aux);
					main.bajarCountFactoryPowerUp();
		          handler.quitarObjeto(aux);
		        }
		      }

		}
		
		
		
	}
	
	@Override
	public String toString() {
		return "Personaje [Vida=" + vida + ", Puntuacion =" + puntuacion + ", Armas =" + armasActuales + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else {
			return false;
		}
		
	}

	@Override
	public void render(Graphics2D g) {
		
    	int camPosX = (int) -main.getCamaraXOffset();
    	int camPosY = (int) -main.getCamaraYOffset();


		if(angulo < 365 && angulo > 200)
		{
			g.drawImage(armas[indexArma].obtenerImagen(),x + imageOffsetX,y + imageOffsetY,null);
			imagen.render(g);
		}else {
			imagen.render(g);
			g.drawImage(armas[indexArma].obtenerImagen(),x + imageOffsetX ,y + imageOffsetY,null);
		}

		if(powerup instanceof Invisibilidad)
		{
			g.setColor(new Color(129, 241, 116, 90));
			g.fillOval(x- 40,y - 40,altura+50,altura+50);
		}
		if(!puedeEsquivar)
		{
			g.setColor(new Color(142,195,255, 90));
			g.fillOval(x- 40,y - 40,altura+50,altura+50);

		}



		g.drawImage(Assets.cursor,posicionMouse.x + camPosX - 20,posicionMouse.y + camPosY - 20,null);
		if(main.getDebug())
		{
			g.setColor(Color.RED);
			g.drawRect(x, y, ancho, altura);
		}
    	
	}
	
}
