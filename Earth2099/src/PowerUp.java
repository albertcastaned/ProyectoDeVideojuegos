import java.awt.Graphics;
import java.awt.image.BufferedImage;

//Clase entidad que cambiara atributos al personaje, se generaran al azar por el escenario
public abstract class PowerUp extends Entidad {
	
	//Duracion del cambio de atributos
	private int duracion;
	
	public PowerUp() {
		super();
		this.duracion = 20;
	}
	
	public PowerUp(int posX, int posY, int ancho, int altura, String nombre, Handler handler,Main main) {
		super(posX,posY,ancho,altura,nombre,handler,main);
	}
	
	
	//Cada subclase tendra efectos distintos
	public abstract void agregarAtributos();
	

	//Dibujar
	public void render(Graphics g) {
		g.fillOval(x, y, ancho, altura);
	}

	
	

	@Override
	public String toString() {
		return "PowerUp [duracion=" + duracion +  ", x=" + x + ", y=" + y + ", velX=" + velX + ", velY="
				+ velY + ", ancho=" + ancho + ", altura=" + altura + ", nombre=" + nombre + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		
		return false;
	}
	
	
	
	

}
