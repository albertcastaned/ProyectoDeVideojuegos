import java.awt.Color;
import java.awt.Graphics2D;

public class Jugador {

	private int x,y;
	private int spd = 0;
	private boolean up,down;
	
	
	public Jugador(int x, int y)
	{
		this.x = x;
		this.y = y;
		spd = 20;
		up = false;
		down = false;
		
	}
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	public void setUp(boolean aux)
	{
		up = aux;
	}
	
	public void setDown(boolean aux)
	{
		down = aux;
	}
	

	public void draw(Graphics2D g)
	{
		g.setColor(Color.blue);
		g.fillRect(x,y,32,32);
	}
	
	public void update()
	{
		if(up && y > 0)
		y-=spd;
		else if(down && y < 340)
		y+=spd;
	}
	public void setSpd(int sdp)
	{
		this.spd = sdp;
	}
	
}
