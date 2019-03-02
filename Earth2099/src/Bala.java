import java.awt.Color;
import java.awt.Graphics;

public class Bala extends Entidad{

	protected boolean activo;
	protected int tamaño;
	public Bala(int x, int y, int mousePosX, int mousePosY, int tamaño)
	{
		
		super(x,y,10,10,"Bala");
		activo = true;

		this.tamaño = tamaño;
		float dirX = mousePosX - x;
		float dirY = mousePosY - y;
		
		float dirLength = (float) Math.sqrt(dirX*dirX + dirY*dirY);
		velX = (int) (30 * dirX/dirLength);
		velY = (int) (30 * dirY/dirLength);

		
	}
	public boolean getActivo()
	{
		return activo;
	}
	public synchronized void actualizar(){
		
		if(!activo)
			return;
		x += velX;
		y += velY;
		if(x < 0 || x > 1200 || y < 0 || y > 1200/16 * 9)
			activo = false;
	}
	
	public synchronized void render(Graphics g)
	{
		if(!activo)
			return;
		g.setColor(Color.BLUE);
		g.fillOval((int)x, (int)y, tamaño, tamaño);
	}

}
