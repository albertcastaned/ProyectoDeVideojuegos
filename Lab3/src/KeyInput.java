import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// Clase encargada de leer la entrada de teclado del usuario
public class KeyInput implements KeyListener{
	
	
	// Se crea a quién va a ser el escuchador de las teclas
	Player player;
	// Se hace el constructor, pasándole al escuchador
	public KeyInput(Player player) 
	{
		this.player = player;
	}
	// Si una tecla es presionada
	@Override
	public void keyPressed(KeyEvent e) {
		// Se obtiene el código entero de la tecla
		int key = e.getKeyCode();
		// Se le pasa el código al método de tecla presionada del escuchador
		player.keyPressed(key);
	}
	// Si una tecla es liberada
	@Override
	public void keyReleased(KeyEvent e) {
		// Se obtiene el código entero de la tecla
		int key = e.getKeyCode();
		// Se le pasa el código al método de tecla liberada del escuchador
		player.keyReleased(key);
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

}
