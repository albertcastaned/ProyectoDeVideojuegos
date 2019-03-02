import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Pelota {
	private int x;
	private int y;
	private int velX;
	private int velY;
	public boolean fuera;
	private Jugador jug;
	private Game game;
	private Rectangle r,p;
	public Pelota(int x,int y,int velX,int velY, Jugador jug,Game game)
	{
		fuera = false;
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.jug = jug;
		this.game = game;
	}
	
	public void render(Graphics g)
	{

		g.setColor(Color.BLUE);
		g.fillOval(x,y,30,30);
		
	}
	
	public void actualizar()
	{
	    r = new Rectangle(x,y,30,30);
	    p = new Rectangle(jug.getX(),jug.getY(),30,30);
	    
	    if (r.intersects(p))
	    {
	    	game.detener();
	    	game.gameOverMsg();
	    }
		if(x > 810 || x < -10 || y > 810 || y < -10)
		{
			Random rand = new Random();
			int type = rand.nextInt(2);
			if(type==0)
			{
				rand = new Random();
				int pos = rand.nextInt(800);
				x = pos;
				y = 0;
				velY = 10;
				velX = 0;
				
			}else if(type==1)
			{
				rand = new Random();
				int pos = rand.nextInt(800);
				x = 0;
				y = pos;
				velX = 10;
				velY =0;
			}
		}
		x +=velX;
		y +=velY;
		
	}
	

}
