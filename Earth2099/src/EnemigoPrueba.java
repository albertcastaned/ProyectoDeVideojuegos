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
	private boolean lastimado;

	public EnemigoPrueba(int x, int y, int ancho, int altura, String nombre, int vidaMax, int dano, Handler handler,Main main) {
		super(x, y, ancho, altura, nombre, vidaMax, dano, handler,main);
		lastimado = false;
		timer = new Timer(3000,buscarJugador);
		p = main.getPlayerPath(x, y);
		System.out.println("le" + p.getLength());
		pathIndex = 0;
		px = p.getStep(pathIndex).getX() * 80;
		py = p.getStep(pathIndex).getY() * 80;
		timer.start();
	}


	public void checarColision() {
		//Obtener Entidades del Handler
		ListIterator <Entidad> iterator = handler.listaEntidades.listIterator();
		while (iterator.hasNext())
		{
			Entidad aux = iterator.next();
			

			//Colision con un Enemigo
			if (aux instanceof Bala)
			{
				if (chocandoEn(x, y, aux))
				{
					velX = 8;
					handler.quitarObjeto(aux);
				}

			}

		}
		
	}
	public void seguirJugador2()
	{
		int aux1 = Math.abs(px - x);
		int aux2 = Math.abs(py - y);
		if(8 >= (aux1 + aux2))
		{
			System.out.println(pathIndex);
			if(pathIndex >= p.getLength() - 1)
			{
			

				p = main.getPlayerPath(x, y);
				pathIndex = 0;

			}else {
			pathIndex++;
			px = p.getStep(pathIndex).getX() * 80;
			py = p.getStep(pathIndex).getY() * 80;
			}
		}
		int dx = px -x;
		int dy = py - y;
		double direction = Math.atan2(dy, dx);
		
		x = (int) (x + (4 * Math.cos(direction)));
		y = (int) (y + (4 * Math.sin(direction)));
		
	}


	//Actualizarlo
	@Override
	public void actualizar() {

		velX =0;
		if(p==null)
			return;
		if(pathIndex != p.getLength())
		{
		seguirJugador2();
		checarColision();
		x += velX;
		System.out.println(x);
		}


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

	@Override
	public void atacar(int x, int y) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void disparar(int x, int y) {
		// TODO Auto-generated method stub
		
	}



}
