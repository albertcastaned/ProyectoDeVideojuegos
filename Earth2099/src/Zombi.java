import java.awt.Color;
import java.awt.Graphics2D;

//Enemigo prueba del Template, luego se agregaran enemigos con comportamientos diferentes
public class Zombi extends TemplateEnemy{
	//Index de la lista de pasos a seguir del Path
	private int pathIndex;
	//Camino que sigue
	private volatile Path p;
	//Posicion del index de la lista de Path
	private int px, py;
	
	
	//Esta siguiendo al jugador o no
	private boolean siguiendo;

	public Zombi(int x, int y, int ancho, int altura, String nombre, int vidaMax, int dano, int velocidad,Handler handler,Game main) {
		super(x, y, ancho, altura, nombre, vidaMax, dano, velocidad,handler,main);
		siguiendo = false;

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
				if(1200 <= (distanciaJugadorX + distanciaJugadorY))
				{
				siguiendo = false;
				
				}else {
				//Sino crear un nuevo camino
				p = main.obtenerCamino(x, y);
				if(p==null)
					siguiendo = false;
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
		x = (int) (x + (velocidad * Math.cos(direction)));
		y = (int) (y + (velocidad * Math.sin(direction)));
		
	}


	//Actualizarlo
	@Override
	public void actualizar() {
		

		
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
		//Inicar velocidad en 0
		velX = 0;
		velY = 0;

		}











}
