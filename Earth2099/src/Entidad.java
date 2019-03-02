import java.awt.Graphics;

public abstract class Entidad {
	protected int x,y,velX,velY,ancho,altura;
	protected boolean activo;
	protected String nombre;
	
	public Entidad(int x, int y,int ancho, int altura,String nombre)
	{
		this.x = x;
		this.y = y;
		velX = 0;
		velY = 0;
		this.ancho = ancho;
		this.altura = altura;
		activo = true;
		this.nombre = nombre;
	}
	
	public synchronized void setVelX(int x)
	{
		velX= x;
	}
	public synchronized void setVelY(int y)
	{
		velY= y;
	}
	
	public abstract void actualizar();
	
	public abstract void render(Graphics g);
}
