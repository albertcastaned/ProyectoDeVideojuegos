import java.awt.Graphics;

import java.awt.Rectangle;
//Superclase que define a todos los objetos que pueden interactuar con otros y se pueden mover
public abstract class Entidad {
	//Posicion, velocidad, dimensiones
	protected int x,y,velX,velY,ancho,altura;
	protected String nombre;
	
	//Referencia al Handler
	protected Handler handler;
	
	//Referencia a la clase principal
	protected Main main;
	//Constructor default
	public Entidad() {
		x = 0;
		y = 0;
		velX = 0;
		velY = 0;
		ancho = 0;
		altura = 0;
	}
	
	//Constructor descriptivo
	public Entidad(int x, int y,int ancho, int altura,String nombre,Handler handler,Main main)
	{
		this.x = x;
		this.y = y;
		velX = 0;
		velY = 0;
		this.ancho = ancho;
		this.altura = altura;
		this.nombre = nombre;
		this.handler = handler;
		this.main = main;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public synchronized void setVelX(int ex)
	{
		velX= ex;
	}
	public synchronized void setVelY(int ye)
	{
		velY= ye;
	}
	
	//Regresa dimensiones de Rectangulo del objeto para las Colisiones
	public Rectangle getDimension()
	{
		return (new Rectangle(getX(),getY(),ancho,altura));
	}
	
	//Regresa booleano si la entidad esta chocando con otro
	public boolean chocandoEn (double x, double y, Entidad obj)
	{
		if ((new Rectangle((int)x, (int)y, ancho, altura)).intersects(obj.getDimension())) 
			return true;
		return false;
	}
	
	//Metodos abstractos
	public abstract void actualizar();
	
	public abstract void render(Graphics g);
}