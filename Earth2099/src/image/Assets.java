package image;

// Se importan las librerías a ocupar
import java.awt.image.BufferedImage;

public class Assets {
	
	// Aquí se cargan todas las imágenes que se requieran en el juego
	// Es estático y público dado a que requerimos acceder a ellas desde varias clases
	/*
	 * Ejemplo de BufferedImage con varias imágenes:
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
	
	//Personaje
	public static BufferedImage pAbajo;
	public static BufferedImage pAD;
	public static BufferedImage pDerecha;
	public static BufferedImage pArribaDerecha;
	public static BufferedImage pArriba;
	public static BufferedImage pArribaIzquierda;
	public static BufferedImage pIzquierda;
	public static BufferedImage pAbajoIzquierda;
	
	//Armas
	public static BufferedImage pArmaArriba;
	public static BufferedImage pArmaAbajo;
	public static BufferedImage pArmaDerecha;
	public static BufferedImage pArmaIzquierda;
	public static BufferedImage pArmaAD;
	public static BufferedImage pArmaAI;
	public static BufferedImage pArmaArD;
	public static BufferedImage pArmaArI;

	
	public static BufferedImage bala;



	// En éste método se inicializan todas las imágenes que se van a ocupar
	public static void init()
	{
		// Se hace uso de la clase ImageLoader para cargar la imágen 
		// (la clase debió ser creada previamente)
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
		pAbajo = ImageLoader.loadImage("/SpritesPersonaje/pAbajo.png");
		pAD = ImageLoader.loadImage("/SpritesPersonaje/pAD.png");
		pDerecha = ImageLoader.loadImage("/SpritesPersonaje/pDerecha.png");
		pArribaDerecha = ImageLoader.loadImage("/SpritesPersonaje/pArribaD.png");
		pArriba = ImageLoader.loadImage("/SpritesPersonaje/pArriba.png");
		pArribaIzquierda = ImageLoader.loadImage("/SpritesPersonaje/pArribaI.png");
		pIzquierda = ImageLoader.loadImage("/SpritesPersonaje/pIzquierda.png");
		pAbajoIzquierda = ImageLoader.loadImage("/SpritesPersonaje/pAbajoIzquierda.png");

		pArmaArriba = ImageLoader.loadImage("/SpritesArmas/mg_up.png");
		pArmaAbajo = ImageLoader.loadImage("/SpritesArmas/mg_down.png");
		pArmaDerecha = ImageLoader.loadImage("/SpritesArmas/mg_sideL.png");
		pArmaIzquierda = ImageLoader.loadImage("/SpritesArmas/mg_side.png");
		pArmaAD = ImageLoader.loadImage("/SpritesArmas/mg_diagdown.png");
		pArmaAI = ImageLoader.loadImage("/SpritesArmas/mg_diagdownL.png");
		pArmaArD = ImageLoader.loadImage("/SpritesArmas/mg_diagup.png");
		pArmaArI = ImageLoader.loadImage("/SpritesArmas/mg_diagupLeft.png");
		
		bala = ImageLoader.loadImage("/bulletc.png");



		
	}
}
