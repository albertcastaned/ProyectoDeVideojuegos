import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ListIterator;

// Clase que define el comportamiento del jugador
public class Player extends Chracter{

	
	private boolean up,down;
	private Game game;
	
	// Constructor que recibe los atributos de un GameObject
	public Player(double x, double y, int width, int height, Color color, Handler handler,Game game) {
		super(x, y, width, height, color, handler);
		// La velocidad al rebotar por defecto es de -8
		// Es -8 dado a que al rebotar, la pelota se mueve a arriba (negativo)
		velY = 0;
		up = false;
		down = false;
		this.game = game;
		}
	
	// Método que nos ayuda a actualizar al jugador
	@Override
	public void tick() 
	{	
		// Se revisan las colisiones
		colision();
		
		
		// Se actualiza la posición en x y en y con respecto a su velocidad
		y += velY;
	}
	
	// Método que se encarga de detectar las colisiones
	@Override
	public void colision() 
	{	
		// Se genera un iterador para revisar todos los objetos
		ListIterator <GameObject> iterator = handler.obj.listIterator();
		while (iterator.hasNext())
		{
			// Se crea un objeto auxiliar
			GameObject aux = iterator.next();
			

			// Si el auxiliar es un enemigo
			if (aux instanceof Bloque)
			{
				// Si hace contacto con el enemigo en el eje de las x al sumarle la velocidad
				if (placeMeeting(x, y, aux))
				{
					// Se detecta si va a la derecha o a la izquierda el jugador
					int sign = velX > 0? 1: -1;
					// Mientras aún no esté a un pixel de lejanía
					while(!placeMeeting(x + sign, y, aux))
						// Se acerca de pixel en pixel a la dirección correspondiente
						x += sign;
					//GameOver
					game.stop();
				}
			}
		}
	}

	// Método que lee las teclas que han sido presionadas
	public void keyPressed(int key) {
		// Si es escape, cierra el juego
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		// Si es la flecha izquierda, vuelve la velocidad en x -3
		if (key == KeyEvent.VK_W) {up=true;velY = -9;}
		// Si es la flecha derecha, vuelve la velocidad en x +3
		if (key == KeyEvent.VK_S) {down=true;velY = 9;}
		

	}

	public void keyReleased(int key) {
		 if (key == KeyEvent.VK_W) {
             up = false;
             if (down) {
            	 velY = 9;
             } else {
            	 velY = 0;
             }
         }
         if (key == KeyEvent.VK_S) {
             down = false;
             if (up) {
            	 velY = -9;
             } else {
            	 velY = 0;
             }
         }
	}
	public void keyTyped(int key) {
		
	}

}
