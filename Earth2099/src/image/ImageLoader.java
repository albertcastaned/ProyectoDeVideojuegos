package image;


// Carga las librerías que se van a utilizar
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

// Clase que se encargará de cargar las imágenes ocupadas por el juego
public class ImageLoader {

	// Método que regresa una imágen dependiendo de si existe o no la ruta
	public static BufferedImage loadImage(String path)
	{
		// Intenta cargar la imágen
		try {
			// Regresa la imágen dependiendo de su ruta
			return ImageIO.read(ImageLoader.class.getResource(path));
		// En caso de que no logre encontrar la imágen
		} catch (IOException e) {
			// Imprime el error por el cuál no se ha podido cargar la imágen
			e.printStackTrace();
			// Dado a que no podemos cargar el juego sin imágenes, lo cierra
			System.exit(1);
		}
		// En caso de que nada lo anterior suceda, nos regresa nulo (muy raro que llegue aquí)
		return null;
	}
	
}