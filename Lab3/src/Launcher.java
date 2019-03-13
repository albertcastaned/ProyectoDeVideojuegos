// Clase encargada de ejecutar el juego
public class Launcher {

	public static void main(String[] args) {
		// Se crea el juego, se le da el título y el tamaño de ventana
		Game game = new Game("Laboratorio 3", 720, 480);
		// Se inicia el juego
		game.start();
	}

}
