import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import image.Assets;
//Super clase de los generadores de nivel
public class GeneradorDeMapa {
		
	//Algoritmo libre de uso usado de Internet para crear imagenes con ruido
	private static OpenSimplexNoise simplexNoise;
	//Imagen auxiliar para restar color para forma de isla
	private BufferedImage substract;
	private BufferedImage image;
	private int waterLevel;
	private int TileImageWidth = 800/8, TileImageHeight = 800/8;
	
	private GameMap map;
	
	private int probabilidadDecoracion;
	
	private BufferedImage decoracionImagen;
	
	private int xImagenOffset = 0, yImagenOffset = 0;
	
	public GeneradorDeMapa(GameMap map,int tipoDeNivel)
	{
		this.map = map;
		try {
			substract = ImageIO.read(Game.class.getResource("/substract.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		switch(tipoDeNivel)
		{
		
		
		//Nivel tipo bosque
		case 1:
			waterLevel = 150;
			probabilidadDecoracion = 15;
			decoracionImagen = Assets.tree;
			xImagenOffset = -35;
			yImagenOffset = -60;
			break;
		case 2:
			waterLevel = 80;
			probabilidadDecoracion = 5;
			decoracionImagen = Assets.rock;
			xImagenOffset = 0;
			yImagenOffset = 60;
			break;
		
		case 3:
			waterLevel = 240;
			probabilidadDecoracion = 10;
			decoracionImagen = Assets.cactus;
			xImagenOffset = 0;
			yImagenOffset = 0;
			break;
		case 4:
			waterLevel = 240;
			probabilidadDecoracion = 10;
			decoracionImagen = Assets.snowTree;
			xImagenOffset = -35;
			yImagenOffset = - 50;
			break;
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
	//Generar objetos de decoracion
	public void generarAdornos(Game main)
	{
		Random ran = new Random();
		for(int x=0;x < 100 - 1;x++)
		{
			
			for(int y=0;y < 100 - 1;y++)
			{
				
				if(ran.nextInt(100) < probabilidadDecoracion)
				{
				if(!map.blocked(x, y))
				{
				Game.getHandler().agregarObjeto(new Decoracion(x*80 + xImagenOffset,y*80 + yImagenOffset,decoracionImagen,Game.getHandler(),main));
				map.setBloqueado(x, y + 1);
				map.setTipo(x, y+1);
				}
				}
				
			}
		}

	}
	//Basandose en la imagen creada, poner bloques dependiendo del color que se creo del ruido
	public int[][] placeTile()
	{	
		int mat[][];
		mat = new int[TileImageWidth][TileImageHeight];
		for (int y = 0; y < TileImageHeight - 1; y++)
		{
			for (int x = 0; x < TileImageWidth - 1; x++)
			{
				int aux = image.getRGB(x*8, y*8);
				int red = (aux & 0x00ff0000) >> 16;
				if(red <= waterLevel)
				{
					mat[x][y] = 0;
				}else {
					mat[x][y] = 1;

				}
				
			}
		}
		return mat;
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


}
