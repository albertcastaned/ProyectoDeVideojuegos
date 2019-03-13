import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bloque {
	private int x;
	private int y;
	private Jugador jug;
	private boolean gameOver = false;
	private int spd;
	public Bloque(int x, int y, Jugador jug,int spd){
		this.x = x;
		this.y = y;
		this.jug = jug;
		this.spd = spd;

		
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 80);
	}
	public void update()
	{
		checkCollision();
		x-=spd;

	}
	public int getX()
	{
		return x;
	}
	
	public void checkCollision()
	{
		   Rectangle bloque = new Rectangle(x+10,y,32,80);
		   Rectangle jugador = new Rectangle(jug.getX(),jug.getY(),32,32);
		    if (bloque.intersects(jugador))
		    {

		       gameOver = true;
		    }
	}
	public boolean getGameOver()
	{
		return gameOver;
	}
}
