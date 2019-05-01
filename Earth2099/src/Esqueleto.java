import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Timer;

import image.Assets;

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
	
	
	private int attackPoint;

	//Esta siguiendo al jugador o no
	private boolean siguiendo;
	public Esqueleto(int x, int y, int ancho, int altura, String nombre, int vidaMax, int dano, int velocidad,
			Handler handler, Game main) {
		super(x, y, ancho, altura, nombre, vidaMax, dano, velocidad, handler, main);
		siguiendo = false;
		timerTirarHueso = new Timer(1000,tirarHueso);
		Random ran = new Random();
		attackPoint = ran.nextInt(8);
		velocidad += ran.nextInt(10);
		
		image.SpriteBuilder builder = new image.SpriteBuilder(Assets.zombieEsqueletoSheet,96,96);
		builder.addImage(9, 0);
		builder.addImage(10, 0);
		builder.addImage(11, 0);


		imgAbajo = new image.AnimationSprite((int)x,(int)y,builder.build());
		imgAbajo.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.zombieEsqueletoSheet,96,96);
		builder.addImage(9, 1);
		builder.addImage(10, 1);
		builder.addImage(11, 1);


		imgIzquierda = new image.AnimationSprite((int)x,(int)y,builder.build());
		imgIzquierda.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.zombieEsqueletoSheet,96,96);
		builder.addImage(9, 2);
		builder.addImage(10, 2);
		builder.addImage(11, 2);


		imgDerecha = new image.AnimationSprite((int)x,(int)y,builder.build());
		imgDerecha.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.zombieEsqueletoSheet,96,96);
		builder.addImage(9, 3);
		builder.addImage(10, 3);
		builder.addImage(11, 3);


		imgArriba = new image.AnimationSprite((int)x,(int)y,builder.build());
		imgArriba.setAnimSpd(10);
		imagen = imgAbajo;
		
	}
	public float getAngulo(Point destino) {
	    float angulo = (float) Math.toDegrees(Math.atan2(destino.y - 500, destino.x - 500));

	    if(angulo < 0)
	    	angulo += 360;
	    return angulo;
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
			handler.agregarObjeto(new BalaEnemigo(x,y,destinoX,destinoY,getAngulo(new Point(destinoX,destinoY)),handler,main));
		}
    };
    

    public void stopTimer()
    {
    	timerTirarHueso.stop();
    }
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
				if(1600 <= (distanciaJugadorX + distanciaJugadorY))
				{
				siguiendo = false;
				
				}else {
				//Sino crear un nuevo camino
				p = main.obtenerCamino(x, y,attackPoint);
				if(p==null)
				{
					siguiendo = false;
					timerTirarHueso.stop();

				}
				else
					pathIndex = 0;

				}

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
		velX = (int) ((velocidad * Math.cos(direction)));
		velY = (int) ((velocidad * Math.sin(direction)));
		
	}

	@Override
	public void actualizar() {
		//Inicar velocidad en 0
		velX = 0;
		velY = 0;
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
			
			if(velX < 0)
			{
				imagen = imgIzquierda;
			}else if(velX > 0){
				imagen = imgDerecha;
			}
			else if(velY < 0)
			{
				imagen = imgArriba;
			}else if(velY > 0)
			{
				imagen = imgAbajo;
			}
			

			if(velX != 0 || velY != 0)
				imagen.update();
			x += velX + knockbackX;
			y += velY + knockbackY;

			imagen.setY((int)y - 60);
			imagen.setX((int)x - 30);
		}
		else {
			//Checar si esta a menos de 1000 de distancia para empezar a seguir
			int distanciaJugadorX = Math.abs(main.getJugadorX() - x);
			int distanciaJugadorY = Math.abs(main.getJugadorY() - y);
			if(1600 >= (distanciaJugadorX + distanciaJugadorY))
			{

				p = main.obtenerCamino(x, y,attackPoint);
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

		
	}
	

}


