import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Bloque extends Chracter {

	private int spd;
	public Bloque(int x, int y,int spd,Handler handler){
		super(x,y,32,96,Color.RED,handler);
		this.spd = spd;

		
	}
	
	public void draw(Graphics2D g)
	{
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 32, 96);
	}
	public void tick()
	{
		x-=spd;

	}

	
	@Override
	public void paint(Graphics g) 
	{		
		// Se le asigna el color que el personaje tiene
		g.setColor(color);
		// Los personajes en �ste caso son �valos, as� que los dibujamos
		g.fillRect(getX(),getY(), width, height);

	}
	@Override
	public void colision() {
		// TODO Auto-generated method stub
		
	}
}