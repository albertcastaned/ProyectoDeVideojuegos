import java.awt.Graphics2D;
import java.awt.Rectangle;
//Superclase que define a todos los objetos que pueden interactuar con otros y se pueden mover
public abstract class Entidad {
	//Posicion, velocidad, dimensiones
	protected int x,y,velX,velY,ancho,altura;
	protected String nombre;
	
	//Referencia al Handler
	protected Handler handler;
	//Referencia a la clase principal
	protected Game main;

	
	//Constructor descriptivo
	public Entidad(int x, int y,int ancho, int altura,String nombre,Handler handler,Game main)
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

	public int getAltura()
	{
		return altura;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public int getDepth()
	{
		return y + altura;
	}
	public void setVelX(int ex)
	{
		velX= ex;
	}
	public void setVelY(int ye)
	{
		velY= ye;
	}
	public int getVelX()
	{
		return velX;
	}
	
	public int getVelY()
	{
		return velY;
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
	
	//Si esta adentro de la camara, dibujar
	public boolean enCamara()
	{
		if(x < -main.getCamaraXOffset() + Game.getVentanaAncho() + 200 && x > -main.getCamaraXOffset() - 200 && y < -main.getCamaraYOffset() + Game.getVentanaAltura() + 200 && y > -main.getCamaraYOffset() - 280)
			return true;
		else
			return false;
	}
	//Metodos abstractos
	public abstract void actualizar();
	
	public abstract void render(Graphics2D g);
}
