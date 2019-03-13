import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

// Clase que designa el comportamiento de los personajes del juego
public abstract class Chracter extends GameObject{
	
	// Es un juego de plataformas, por lo que tiene gravedad, velocidad en x y en y
	protected double velX;
	protected double velY;
	// Constructor que pide los valores del Game object 
	public Chracter(double x, double y, int width, int height, Color color, Handler handler) {
		super(x, y, width, height, color, handler);
		// Se asigna la velocidad en x a que por defecto sea de 3
		velX = 3;
	}

	// Pinta al personaje
	@Override
	public void paint(Graphics g) 
	{		
		// Se le asigna el color que el personaje tiene
		g.setColor(color);
		// Los personajes en éste caso son óvalos, así que los dibujamos
		g.fillOval(getX(),getY(), width, height);
	}

	// Todo personaje tiene un comportamiento diferente, así que el tick sigue siendo abstracto
	public abstract void tick();
	
	// Todos los personajes en éste juego tienen colisión
	public abstract void colision();
	
	// Obtenemos los bordes del personaje
	@Override
	public Rectangle getBounds() 
	{
		return (new Rectangle(getX(), getY(), width, height));
	}
	
	// Place meeting nos ayuda a revisar si el personaje colisiona con otro objeto
	public boolean placeMeeting (double x, double y, GameObject obj)
	{
		// revisa si el rectángulo del otro objeto intersecta con el rectángulo del personaje
		if ((new Rectangle((int)x, (int)y, width, height)).intersects(obj.getBounds())) 
			return true;
		return false;
	}
	
	// A partir de aquí son los setters y getters
	public double getVelX()
	{
		return velX;
	}
	public double getVelY()
	{
		return velY;
	}

	public void setVelX(double velX)
	{
		this.velX = velX;
	}
	public void setVelY(double velY)
	{
		this.velY = velY;
	}

}
