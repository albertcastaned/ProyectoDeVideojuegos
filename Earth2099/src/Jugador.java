import java.awt.Color;
import java.awt.Graphics;

//Clase de prueba para probar el game loop
public class Jugador {
	private int x,y,velX,velY;
	private CollectionBalas colBalas;
	public Jugador(int x, int y, CollectionBalas colBalas)
	{

		this.x = x;
		this.y = y;
		velX = 0;
		velY = 0;
		this.colBalas = colBalas;
	}

	public synchronized void setVelX(int x)
	{
		velX= x;
	}
	public synchronized void setVelY(int y)
	{
		velY= y;
	}
	public synchronized void actualizar()
	{
		x+= velX;
		y+= velY;

	}
	
	public synchronized void disparar(int mx, int my)
	{

		Bala bal = new Bala(x,y,mx,my);
		colBalas.CrearBala(bal);
		
	}

	public synchronized void dibujar(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x,y,30,30);
	}




}
