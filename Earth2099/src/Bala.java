import java.awt.Graphics;

public class Bala {
	private double posX;
	private double posY;
	private double velX;
	private double velY;
	private boolean activo;
	
	public Bala(int x, int y, int mousePosX, int mousePosY)
	{
		activo = true;
		posX = x;
		posY = y;
		
		float dirX = mousePosX - x;
		float dirY = mousePosY - y;
		
		float dirLength = (float) Math.sqrt(dirX*dirX + dirY*dirY);
		velX = 30 * dirX/dirLength;
		velY = 30 * dirY/dirLength;

		
	}
	public boolean getActivo()
	{
		return activo;
	}
	public synchronized void actualizar(){
		if(!activo)
			return;
		posX += velX;
		posY += velY;
		if(posX < 0 || posX > 1200 || posY < 0 || posY > 1200/16 * 9)
			activo = false;
	}
	
	public synchronized void render(Graphics g)
	{
		if(!activo)
			return;
		g.fillOval((int)posX, (int)posY, 10, 10);
	}

}
