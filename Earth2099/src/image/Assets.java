package image;

// Se importan las librer�as a ocupar
import java.awt.image.BufferedImage;

public class Assets {
	
	// Aqu� se cargan todas las im�genes que se requieran en el juego
	// Es est�tico y p�blico dado a que requerimos acceder a ellas desde varias clases
	/*
	 * Ejemplo de BufferedImage con varias im�genes:
	 * public static BufferedImage background, player, cloud, enemy...; 
	*/
	public static BufferedImage waterTile;
	public static BufferedImage grassTile;
	
	// En �ste m�todo se inicializan todas las im�genes que se van a ocupar
	public static void init()
	{
		// Se hace uso de la clase ImageLoader para cargar la im�gen 
		// (la clase debi� ser creada previamente)
		grassTile = ImageLoader.loadImage("/grassTile.png");
		waterTile = ImageLoader.loadImage("/waterTile.png");

		
	}
}
