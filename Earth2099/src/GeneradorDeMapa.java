import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
//Super clase de los generadores de nivel
public abstract class GeneradorDeMapa {
	
	public abstract int[][] placeTile();
	
	//Algoritmo libre de uso usado de Internet para crear imagenes con ruido
	protected static OpenSimplexNoise simplexNoise;
	//Imagen auxiliar para restar color para forma de isla
	protected BufferedImage substract;
	protected BufferedImage image;
	protected int waterLevel;
	protected int TileImageWidth = 800/8, TileImageHeight = 800/8;
	
	protected GameMap map;
	
	public GeneradorDeMapa(GameMap map)
	{
		this.map = map;
		try {
			substract = ImageIO.read(ForestGenerator.class.getResource("/substract.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		
		//Crear ruido con una semilla aleatoria
		Random ran = new Random();
		int rand = ran.nextInt(1000000);
		simplexNoise = new OpenSimplexNoise(rand);
		double scale = 0.0063;
		
		//Insertar pixeles a la imagen
		image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < 800; y++)
		{
			for (int x = 0; x < 800; x++)
			{
				
				double value = sumOctave(10,x,y,0.5,scale, -60,255);
			
				int aux = substract.getRGB(x, y);
				int red = (aux & 0x00ff0000) >> 16;
				
				int rgb = 0x010101 * ((int)(value) - red);
				image.setRGB(x, y, rgb);
				
			}
		}
		//Guardar imagen para pruebas
		try {
			ImageIO.write(image, "png", new File("resultNoise.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static double sumOctave(double iterations,double x,double y,double persistence,double scale,double low,double high)
	{
		double maxAmp = 0;
		double amp = 1;
		double freq = scale;
		double newNoise = 0;
		
		for(int i =0; i < iterations;i++)
		{
			newNoise += simplexNoise.eval(x * freq ,y * freq) * amp;
			maxAmp += amp;
			amp *= persistence;
			freq *= 2;
		}
		
		newNoise /= maxAmp;
		newNoise = newNoise * (high-low)/2 + (high + low) / 2;
		return newNoise;
	}
	protected abstract void generarAdornos(Game main);


}
