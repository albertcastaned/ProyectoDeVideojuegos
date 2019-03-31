
public class Fantasma extends TemplateEnemy{
	//Esta siguiendo al jugador o no
	private boolean siguiendo;
	
	public Fantasma(int x, int y, int ancho, int altura, String nombre, int vidaMax, int dano, int velocidad, Handler handler,
			Game main) {
		super(x, y, ancho, altura, nombre, vidaMax, dano, velocidad, handler, main);
		siguiendo = false;
	}
	public void seguirJugador()
	{

		//Calcular velocidad y angulo para llegar al punto
		int dx = main.getJugadorX() -x;
		int dy = main.getJugadorY() - y;
		double direction = Math.atan2(dy, dx);
		x = (int) (x + (velocidad * Math.cos(direction)));
		y = (int) (y + (velocidad * Math.sin(direction)));
		
	}
	@Override
	public void atacar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disparar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	public void actualizar()
	{	velX=0;
		velY=0;
		checarColision();
		if(siguiendo)
		{
		seguirJugador();
		}else {
			//Checar si esta a menos de 2000 de distancia para empezar a seguir
			int distanciaJugadorX = Math.abs(main.getJugadorX() - x);
			int distanciaJugadorY = Math.abs(main.getJugadorY() - y);
			if(2000>= (distanciaJugadorX + distanciaJugadorY))
			{
				siguiendo = true;
			}
		}
		x+=velX;
		y+=velY;
	}

}