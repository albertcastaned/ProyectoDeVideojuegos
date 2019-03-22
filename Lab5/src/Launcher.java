// Clase encargada de ejecutar el juego
public class Launcher {

	public static void main(String[] args) {
		// Se crea el juego, se le da el t�tulo y el tama�o de ventana
		Game game = new Game("Laboratorio 5", 720, 480);
		// Se inicia el juego
		game.start();
	}

}
