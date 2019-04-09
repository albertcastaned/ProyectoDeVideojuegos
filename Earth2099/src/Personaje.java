import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.Timer;


//Clase que el jugador controla
public class Personaje extends Entidad{
	
	//Velocidad que tendra el jugador
	private int velocidad;
	
	//Que direccion/tecla esta activa
	private boolean arriba = false,abajo =false ,izquierda = false,derecha = false;
	
	//Vida actual y vida maxima
	private int vida;
	private int vidaMaxima;
	
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
	
	private int tiempoPuedeSerLastimado = 1000; 
	
	
	public Personaje(int x, int y, int ancho, int altura, String nombre, Handler handler,Game main) {
		super(x,y,ancho,altura,nombre,handler,main);
		vidaMaxima = 100;
		vida = vidaMaxima;

		//Agregar armas
		armas = new Arma[3];
		armas[0] = new Metralleta("Metralleta",10,50,50,50,handler,main);
		armas[1] = new Escopeta("Escopeta",30,10,50,100,handler,main);
		armas[2] = new Escopeta("Escopeta 2",50,50,50,500,handler,main);
		
		indexArma = 0;
		puntuacion = 0;
		armasActuales = 0;
		velocidad = 10;
		puedeSerLastimado = true;
		puedeSerLastimadoTimer = new Timer(tiempoPuedeSerLastimado,timerPoderDisparar);
		
	}
	
	//Timer
    ActionListener timerPoderDisparar = new ActionListener(){


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			puedeSerLastimado = true;
			puedeSerLastimadoTimer.stop();
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
	
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getVidaMaxima() {
		return vidaMaxima;
	}

	public void setVidaMaxima(int vidaMaxima) {
		this.vidaMaxima = vidaMaxima;
	}

	public int getIndexArma() {
		return indexArma;
	}

	public void setIndexArma(int indexArma) {
		this.indexArma = indexArma;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	
//Cambiar posicion de arma del arreglo entre 1 y 3, se usa en el scroll del mouse
	public void cambiarArma(int num)
	{
		if(indexArma == 2 && num == 1)
		{
			indexArma = 0;
			return;
		}if(indexArma == 0 && num == -1)
		{
			indexArma = 2;
			return;
		}
		indexArma  += num;
	}
	
	//Asignar una posicion del arreglo de armas, se usa en las teclas 1,2,y 3
	public void asignarArma(int num)
	{
		if(armas[indexArma] != null)
			indexArma = num;
	}
	
	//Llamar metodo recargar de la arma actual
	public void recargar()
	{
		armas[indexArma].recargar(10);
	}
	
	//Llamar metodo disparar de la arma actual
	public synchronized void disparar(int mx, int my)
	{
		armas[indexArma].disparar(x, y, mx, my);
		
	}
	
	//Actualizar
	@Override
	public synchronized void actualizar() {
		//Asignar direccion dependiendo de las teclas
		asignarDireccion();
		
		//Checar si esta colisionando con otra entidad
		checarColision();
		
		//Cambiar posicion
		x += velX;
		x = clamp(x,0,8000 - 30);
		y += velY;
		y = clamp(y,0,8000 - 30);
	    //Si tiene un powerup, que tome su efecto
	    if(powerup != null){
	      if(!powerup.agregarAtributos(this)){
	        powerup = null;
	      }
	    }
	}

	//Dibujar
	@Override
	public synchronized void render(Graphics g) {
		g.setColor(Color.WHITE);
    	int camPosX = (int) -main.getCamaraXOffset();
    	int camPosY = (int) -main.getCamaraYOffset();
		//Dibujar texto de informacion de prueba
		g.drawString("Vida: " + vida, camPosX + 20, camPosY + 20);
		g.drawString("Nombre de arma: " + armas[indexArma].getNombre(), camPosX + 20,camPosY + 40);
		g.drawString("Numero de balas: " + armas[indexArma].getBalas(), camPosX + 20, camPosY + 60);
		g.drawString("Puede disparar: " + String.valueOf(armas[indexArma].getPuedeDisparar()), camPosX + 20, camPosY + 80);
		g.setColor(Color.GREEN);
		
		//Dibujar al personaje por el momento un circulo
		g.fillOval(x,y,ancho,altura);
		
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
				//Agregar aqui timer para que no recibir daño cada frame
				if(puedeSerLastimado && chocandoEn(x,y,aux))
				{
					vida-=5;
					puedeSerLastimado = false;
					puedeSerLastimadoTimer.start();
					if(vida <= 0)
						System.exit(0);
				}
				if(vida <= 0)
					System.exit(0);
			}
		      if(aux instanceof PowerUp){
		        if(chocandoEn(x, y, aux)){
		          this.powerup = ((PowerUp)aux);
		          
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
	
	
}
