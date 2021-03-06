import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ListIterator;

// Clase que define el comportamiento del jugador
public class Player extends Chracter{

	
	private boolean up,down;
	private Game game;
	private AnimationSprite imagen;
	
	// Constructor que recibe los atributos de un GameObject
	public Player(double x, double y, int width, int height, Color color, Handler handler,Game game) {
		super(x, y, width, height, color, handler);
		velY = 0;
		velX = 0;
		up = false;
		down = false;
		this.game = game;
		
		SpriteBuilder builder = new SpriteBuilder("/Textures/bat.png",48,64);
		builder.addImage(0, 2);
		builder.addImage(1, 2);
		builder.addImage(2, 2);
		imagen = new AnimationSprite((int)x,(int)y,builder.build());
		imagen.setAnimSpd(10);

		}
	
	// M�todo que nos ayuda a actualizar al jugador
	@Override
	public void tick() 
	{	
		// Se revisan las colisiones
		colision();
		
		imagen.update();
		// Se actualiza la posici�n en x y en y con respecto a su velocidad
		if(y >= 0 && up)
		y += velY;
		else if(y <= 450 && down)
		y += velY;
		
		imagen.setY((int)y);
		imagen.setX((int)x);
	}
	
	// M�todo que se encarga de detectar las colisiones
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
				if (placeMeeting(x + 10, y + 32, aux))
				{
					// Se detecta si va a la derecha o a la izquierda el jugador
					int sign = velX > 0? 1: -1;
					// Mientras a�n no est� a un pixel de lejan�a
					while(!placeMeeting(x + sign, y, aux))
						// Se acerca de pixel en pixel a la direcci�n correspondiente
						x += sign;
					//GameOver
					game.stop();
				}
			}
		}
	}

	
	// M�todo que lee las teclas que han sido presionadas
	public void keyPressed(int key) {
		// Si es escape, cierra el juego
		if (key == KeyEvent.VK_ESCAPE) System.exit(1);
		// Si es la flecha w, vuelve la velocidad en y -9
		if (key == KeyEvent.VK_W) {
			up=true;
				velY = -9;
			}
		// Si es la flecha s, vuelve la velocidad en y +9
		if (key == KeyEvent.VK_S) {
			down=true;velY = 9;
			}
		

		

	}
	@Override
	public Rectangle getBounds() 
	{
		//Colision mas precisa para esta imagen quitando el espacio blanco de las celdas de la imagen del murcielago
		return (new Rectangle(imagen.getX() + 10, imagen.getY() + 32, 26, 10));
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

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		imagen.render(g);
	}
	
	

}
