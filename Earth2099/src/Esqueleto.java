import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ListIterator;

import javax.swing.Timer;

//Enemigo que puede tirar huesos hacia el jugador
public class Esqueleto extends TemplateEnemy {
	//Index de la lista de pasos a seguir del Path
	private int pathIndex;
	//Camino que sigue
	private volatile Path p;
	//Posicion del index de la lista de Path
	private int px, py;
	
	//Timer para tirar un proyectil al jugador
	private Timer timerTirarHueso;
	
	
	
	//Esta siguiendo al jugador o no
	private boolean siguiendo;
	public Esqueleto(int x, int y, int ancho, int altura, String nombre, int vidaMax, int dano, int velocidad,
			Handler handler, Game main) {
		super(x, y, ancho, altura, nombre, vidaMax, dano, velocidad, handler, main);
		siguiendo = false;
		timerTirarHueso = new Timer(2000,tirarHueso);
		
	}
	
	//Timer
    ActionListener tirarHueso = new ActionListener(){


		@Override
		public void actionPerformed(ActionEvent e) {
			int destinoX,destinoY;
			//Obtener posicion de el mouse respecto a la posicion de la camara
			destinoX = main.getJugadorX();
			destinoY = main.getJugadorY();
			
			//Agregar bala del enemigo al handler
			handler.agregarObjeto(new BalaEnemigo(x,y,destinoX,destinoY,20,10,handler,main));
		}
    };
    

   
	public void seguirJugador()
	{
		//Obtener distancia de el punto buscado a la actual
		int distanciaAPuntoX = Math.abs(px - x);
		int distanciaAPuntoY = Math.abs(py - y);
		//Si la distancia es igual o menor a 8
		if(8 >= (distanciaAPuntoX + distanciaAPuntoY))
		{
			//Si esta al final de la lista del camino
			if(pathIndex >= p.getLength() - 1)
			{
				//Obtener distancia al jugador
				int distanciaJugadorX = Math.abs(main.getJugadorX() - x);
				int distanciaJugadorY = Math.abs(main.getJugadorY() - y);
				
				//Si esta a 600 o mas de distancia dejar de seguirlo
				if(1200 <= (distanciaJugadorX + distanciaJugadorY))
				{
				siguiendo = false;
				
				}else {
				//Sino crear un nuevo camino
				p = main.obtenerCamino(x, y);
				if(p==null)
				{
					siguiendo = false;
					timerTirarHueso.stop();

				}
				else
					pathIndex = 0;

				}
				//255 255 1100 0000 0
				//255 255 1111 1110 0000 0000
			}else {
			//Obtener nueva posicion de la lista
			pathIndex++;
			px = p.getStep(pathIndex).getX() * 80;
			py = p.getStep(pathIndex).getY() * 80;
			}
		}
		//Calcular velocidad y angulo para llegar al punto
		int dx = px -x;
		int dy = py - y;
		double direction = Math.atan2(dy, dx);
		x = (int) (x + (velocidad * Math.cos(direction)));
		y = (int) (y + (velocidad * Math.sin(direction)));
		
	}

	@Override
	public void actualizar() {
		//Si tiene 0 de vida morir
		
		if(vida <= 0)
		{
			timerTirarHueso.stop();
			handler.quitarObjeto(this);
		}

		if(siguiendo)
		{	
			if(p==null)
			{	
				return;
			}
			if(pathIndex != p.getLength())
			{
				seguirJugador();
			}
			
			
		}
		else {
			//Checar si esta a menos de 1000 de distancia para empezar a seguir
			int distanciaJugadorX = Math.abs(main.getJugadorX() - x);
			int distanciaJugadorY = Math.abs(main.getJugadorY() - y);
			if(800 >= (distanciaJugadorX + distanciaJugadorY))
			{

				p = main.obtenerCamino(x, y);
				if(p!=null)
				{
				pathIndex = 0;
				px = p.getStep(pathIndex).getX() * 80;
				py = p.getStep(pathIndex).getY() * 80;
				siguiendo = true;
				timerTirarHueso.start();
				timerTirarHueso.setRepeats(true);

				}
			}
		}
		//Checar colision y aumentar posicion respecto a la velocidad 
		if(velX != 0)
		{
			int sign = velX<0?-1:1;
			velX+=sign;
		}
		if(velY != 0)
		{
			int sign = velY<0?-1:1;
			velY+=sign;
		}
		x += velX;
		y += velY;
		velX = 0;
		velY = 0;
	}
	
	//Dibujar la bala
	@Override
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillOval((int)x, (int)y, ancho, altura);
	}
}


