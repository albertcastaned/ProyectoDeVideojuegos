import java.awt.Color;
import java.awt.Graphics;

public class Jugador {
	
	private int x;
	private int y;
	public boolean arriba,abajo,izquierda,derecha;
	
	public Jugador(int ancho, int altura)
	{
		x = ancho/2;
		y = altura/2;
	}
	
	
	public void render(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x,y,30,30);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void actualizar()
	{
		int velX = 0,velY = 0;
		if(arriba)
			velY=-20;
		if(abajo)
			velY=20;
		if(izquierda)
			velX=-20;
		if(derecha)
			velX=20;
		
		x+=velX;
		y+=velY;
	}

}