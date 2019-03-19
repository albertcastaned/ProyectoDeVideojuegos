import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.Timer;

//Enemigo prueba del Template, luego se agregaran enemigos con comportamientos diferentes
public class EnemigoPrueba extends TemplateEnemy{
	private int pathIndex;
	private Path p;
	private int px, py;
	private Timer timer;
	public EnemigoPrueba(int x, int y, int ancho, int altura, String nombre, int vidaMax, int dano, Handler handler,Main main) {
		super(x, y, ancho, altura, nombre, vidaMax, dano, handler,main);

		timer = new Timer(3000,buscarJugador);
		p = main.getPlayerPath(x, y);
		pathIndex = 0;
		px = p.getStep(pathIndex).getX() * 80;
		py = p.getStep(pathIndex).getY() * 80;
		timer.start();
	}

	//Como seguira al jugador
	@Override
	public void seguirJugador(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	//Como lo atacara
	@Override
	public void atacar(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	//Como lo disparara si es que tiene arma
	@Override
	public void disparar(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	
	
	//Actualizarlo
	@Override
	public void actualizar() {

		velX = 0;
		velY = 0;

		if(p==null)
			return;
		if(pathIndex != p.getLength())
		{
		moveTowardsPath();
		x+=velX;
		y+=velY;
		}


		
		

	}
	
	public void moveTowardsPath() 
	{
			int signDirX = 0;
			int signDirY = 0;
			signDirX = (x==px)?0:(x<px)?1:-1;
			signDirY = (y==py)?0:(y<py)?1:-1;

			if(x == px && y == py)
			{
				if(pathIndex >= (p.getLength() -1))
				{
					///x = px;
					//dy = py;
					p = main.getPlayerPath(x, y);
					pathIndex = 0;
					timer.restart();
					return;
				}else {
				pathIndex++;

				px = p.getStep(pathIndex).getX() * 80;
				py = p.getStep(pathIndex).getY() * 80;
				}
			}

			velX=4 * signDirX;
			
			velY=4 * signDirY;
			

	}
	//Timer
    ActionListener buscarJugador = new ActionListener(){


		@Override
		public void actionPerformed(ActionEvent e) {

			p = main.getPlayerPath(x, y);
			pathIndex = 0;
			timer.restart();
			

		}
    };

}
