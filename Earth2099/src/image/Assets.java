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

	//Armas
	public static BufferedImage pArmaArribaLaser;
	public static BufferedImage pArmaAbajoLaser;
	public static BufferedImage pArmaDerechaLaser;
	public static BufferedImage pArmaIzquierdaLaser;
	public static BufferedImage pArmaADLaser;
	public static BufferedImage pArmaAILaser;
	public static BufferedImage pArmaArDLaser;
	public static BufferedImage pArmaArILaser;

	//Armas
	public static BufferedImage pArmaArribawavy;
	public static BufferedImage pArmaAbajowavy;
	public static BufferedImage pArmaDerechawavy;
	public static BufferedImage pArmaIzquierdawavy;
	public static BufferedImage pArmaADwavy;
	public static BufferedImage pArmaAIwavy;
	public static BufferedImage pArmaArDwavy;
	public static BufferedImage pArmaArIwavy;

	//Armas
	public static BufferedImage pArmaArribaEsc;
	public static BufferedImage pArmaAbajoEsc;
	public static BufferedImage pArmaDerechaEsc;
	public static BufferedImage pArmaIzquierdaEsc;
	public static BufferedImage pArmaADEsc;
	public static BufferedImage pArmaAIEsc;
	public static BufferedImage pArmaArDEsc;
	public static BufferedImage pArmaArIEsc;

	//Armas
	public static BufferedImage pArmaArribaFuego;
	public static BufferedImage pArmaAbajoFuego;
	public static BufferedImage pArmaDerechaFuego;
	public static BufferedImage pArmaIzquierdaFuego;
	public static BufferedImage pArmaADFuego;
	public static BufferedImage pArmaAIFuego;
	public static BufferedImage pArmaArDFuego;
	public static BufferedImage pArmaArIFuego;
	//Bala
	public static BufferedImage bala;
	public static BufferedImage balaHueso;
	public static BufferedImage laser;
	public static BufferedImage wavy;
	
	//Enemigos
	public static BufferedImage zombieEsqueletoSheet;
	
	public static BufferedImage fantasmaSheet;


	//Power-ups
	public static BufferedImage escudo;

	//Cursor
	public static BufferedImage cursor;

	//Vida
	public static BufferedImage vida;

	//Fire
	public static BufferedImage fire;

	public static BufferedImage cartucho;

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

		//Metralleta
		pArmaArriba = ImageLoader.loadImage("/SpritesArmas/mg_up.png");
		pArmaAbajo = ImageLoader.loadImage("/SpritesArmas/mg_down.png");
		pArmaDerecha = ImageLoader.loadImage("/SpritesArmas/mg_sideL.png");
		pArmaIzquierda = ImageLoader.loadImage("/SpritesArmas/mg_side.png");
		pArmaAD = ImageLoader.loadImage("/SpritesArmas/mg_diagdown.png");
		pArmaAI = ImageLoader.loadImage("/SpritesArmas/mg_diagdownL.png");
		pArmaArD = ImageLoader.loadImage("/SpritesArmas/mg_diagup.png");
		pArmaArI = ImageLoader.loadImage("/SpritesArmas/mg_diagupLeft.png");

		//Laser
		pArmaArribaLaser = ImageLoader.loadImage("/SpritesArmas/laser_up.png");
		pArmaAbajoLaser = ImageLoader.loadImage("/SpritesArmas/laser_down.png");
		pArmaDerechaLaser = ImageLoader.loadImage("/SpritesArmas/laser_side.png");
		pArmaIzquierdaLaser = ImageLoader.loadImage("/SpritesArmas/laser_sideL.png");
		pArmaADLaser = ImageLoader.loadImage("/SpritesArmas/laser_diagdown.png");
		pArmaAILaser = ImageLoader.loadImage("/SpritesArmas/laser_diagdownL.png");
		pArmaArDLaser = ImageLoader.loadImage("/SpritesArmas/laser_diagup.png");
		pArmaArILaser = ImageLoader.loadImage("/SpritesArmas/laser_diagupL.png");

		//Wavy
		pArmaArribawavy = ImageLoader.loadImage("/SpritesArmas/matter_up.png");
		pArmaAbajowavy = ImageLoader.loadImage("/SpritesArmas/matter_down.png");
		pArmaDerechawavy = ImageLoader.loadImage("/SpritesArmas/matter_side.png");
		pArmaIzquierdawavy = ImageLoader.loadImage("/SpritesArmas/matter_sideL.png");
		pArmaADwavy = ImageLoader.loadImage("/SpritesArmas/matter_diagdown.png");
		pArmaAIwavy = ImageLoader.loadImage("/SpritesArmas/matter_diagdownL.png");
		pArmaArDwavy = ImageLoader.loadImage("/SpritesArmas/matter_diagup.png");
		pArmaArIwavy = ImageLoader.loadImage("/SpritesArmas/matter_diagupL.png");

		//Escopeta
		pArmaArribaEsc = ImageLoader.loadImage("/SpritesArmas/shot_up.png");
		pArmaAbajoEsc = ImageLoader.loadImage("/SpritesArmas/shot_down.png");
		pArmaDerechaEsc = ImageLoader.loadImage("/SpritesArmas/shot_side.png");
		pArmaIzquierdaEsc = ImageLoader.loadImage("/SpritesArmas/shot_sideL.png");
		pArmaADEsc = ImageLoader.loadImage("/SpritesArmas/shot_diagdown.png");
		pArmaAIEsc = ImageLoader.loadImage("/SpritesArmas/shot_diagdownL.png");
		pArmaArDEsc = ImageLoader.loadImage("/SpritesArmas/shot_diagup.png");
		pArmaArIEsc = ImageLoader.loadImage("/SpritesArmas/shot_diagupL.png");

		//Fuego
		pArmaArribaFuego = ImageLoader.loadImage("/SpritesArmas/flamethrower_up.png");
		pArmaAbajoFuego = ImageLoader.loadImage("/SpritesArmas/flamethrower_down.png");
		pArmaDerechaFuego = ImageLoader.loadImage("/SpritesArmas/flamethrower_side.png");
		pArmaIzquierdaFuego = ImageLoader.loadImage("/SpritesArmas/flamethrower_sideL.png");
		pArmaADFuego = ImageLoader.loadImage("/SpritesArmas/flamethrower_diagdown.png");
		pArmaAIFuego = ImageLoader.loadImage("/SpritesArmas/flamethrower_diagdownL.png");
		pArmaArDFuego = ImageLoader.loadImage("/SpritesArmas/flamethrower_diagup.png");
		pArmaArIFuego = ImageLoader.loadImage("/SpritesArmas/flamethrower_diagupL.png");

		bala = ImageLoader.loadImage("/bulletc.png");
		laser = ImageLoader.loadImage("/laser.png");

		zombieEsqueletoSheet = ImageLoader.loadImage("/ZombieYEsqueleto.png");
		
		fantasmaSheet = ImageLoader.loadImage("/ghost.png");
		balaHueso = ImageLoader.loadImage("/huesoProyectil.png");
		escudo = ImageLoader.loadImage("/shieldPowerup.png");
		wavy = ImageLoader.loadImage("/wavy.png");

		cursor = ImageLoader.loadImage("/1crosshair.png");
		vida = ImageLoader.loadImage("/vida.png");

		fire = ImageLoader.loadImage("/fire.png");
		cartucho = ImageLoader.loadImage("/cartucho.png");
	}
}
