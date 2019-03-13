import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Bloques {
	private ArrayList<Bloque> bloques;
	private Jugador jug;
	private Game game;
	private int spdAumentar = 0;
	public Bloques(Jugador jug, Game game){
		Random ran = new Random();
		int pos = ran.nextInt(5);
		bloques = new ArrayList<Bloque>();
		this.jug = jug;
		this.game = game;
		for(int i=0;i<5;i++)
		{
			if(i != pos)
			bloques.add(new Bloque(800,80*i,jug,8));
			
		}

		
		
	}
	public void draw(Graphics2D g)
	{
		Iterator<Bloque> itr = bloques.iterator();
		while(itr.hasNext())
		{
			Bloque aux = itr.next();
			aux.draw(g);
		}
	}
	public void resetear()
	{
		Random ran = new Random();
		int pos = ran.nextInt(5);
		bloques = new ArrayList<Bloque>();
		spdAumentar++;
		for(int i=0;i<5;i++)
		{
			if(i != pos)
			bloques.add(new Bloque(800,80*i,jug,8 + spdAumentar));
			
		}
	}
	public void update()
	{
		Iterator<Bloque> itr = bloques.iterator();
		while(itr.hasNext())
		{
			Bloque aux = itr.next();
			aux.update();
			if(aux.getGameOver())
			{
				
				game.detener();
				break;
			}
			if(aux.getX() < -32)
			{
				resetear();
				break;
			}
		}
	}

}
