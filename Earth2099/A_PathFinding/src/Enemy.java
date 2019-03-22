import java.awt.Graphics;
import java.util.ArrayList;

public class Enemy {

	private int x,y,ancho,altura,velX,velY,px,py;
	
	private int pathIndex;
	private Path p;
	private ArrayList steps;
	public Enemy(int x, int y, int ancho, int altura,Path p)
	{
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.altura = altura;
		velX = 0;
		velY = 0;
		pathIndex = 0;
		this.p = p;
		this.steps = p.getSteps();
		px = p.getStep(pathIndex).getX() * 80;
		py = p.getStep(pathIndex).getY() * 80;
	}
	
	
	public void moveTowardsPath() 
	{
			velX = 0;
			velY = 0;
			int signDirX = 0;
			int signDirY = 0;
			signDirX = (x==px)?0:(x<px)?1:-1;
			signDirY = (y==py)?0:(y<py)?1:-1;

			if(x == px && y == py)
			{
				if(pathIndex >= (p.getLength() -1))
				{
					x = px;
					y = py;
					return;
				}else {
				pathIndex++;
				System.out.println(pathIndex);

				px = p.getStep(pathIndex).getX() * 80;
				py = p.getStep(pathIndex).getY() * 80;
				}
			}

			velX=4 * signDirX;
			
			velY=4 * signDirY;
			

	}
	
	public void update() {
		if(pathIndex != p.getLength())
		{
		moveTowardsPath();
		x+=velX;
		y+=velY;
		}
	}
	
	public void render(Graphics g)
	{
		g.fillOval(x,y,ancho,altura);
	}
	
	
}
