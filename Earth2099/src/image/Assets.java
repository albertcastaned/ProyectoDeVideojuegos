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
	public static BufferedImage tree;
	public static BufferedImage lavaTile;
	public static BufferedImage rockTile;
	public static BufferedImage rock;
	public static BufferedImage desertTile;
	public static BufferedImage cactus;
	public static BufferedImage snowTile;
	public static BufferedImage snowTree;
	public static BufferedImage iceTile;



	// En �ste m�todo se inicializan todas las im�genes que se van a ocupar
	public static void init()
	{
		// Se hace uso de la clase ImageLoader para cargar la im�gen 
		// (la clase debi� ser creada previamente)
		grassTile = ImageLoader.loadImage("/grassTile.png");
		waterTile = ImageLoader.loadImage("/waterTile.png");
		tree = ImageLoader.loadImage("/Tree.png");
		lavaTile = ImageLoader.loadImage("/lavaTile.png");
		rockTile = ImageLoader.loadImage("/rockTile.png");
		rock = ImageLoader.loadImage("/rock.png");
		desertTile = ImageLoader.loadImage("/desertTile.png");
		cactus = ImageLoader.loadImage("/cactus.png");
		snowTile = ImageLoader.loadImage("/snowTile.png");
		iceTile = ImageLoader.loadImage("/iceTile.png");
		snowTree = ImageLoader.loadImage("/snowTree.png");





		
	}
}
