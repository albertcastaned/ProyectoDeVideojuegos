
import java.util.Random;

import image.Assets;

public class SnowGenerator extends GeneradorDeMapa{
	public SnowGenerator(GameMap map)
	{		
		super(map);
		waterLevel = 240;
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
	

	@Override
	protected void generarAdornos(Game main) {
		// TODO Auto-generated method stub
		Random ran = new Random();
		for(int x=0;x < 100 - 1;x++)
		{
			
			for(int y=0;y < 100 - 1;y++)
			{
				
				if(ran.nextInt(100) < 10)
				{
				if(!map.blocked(x, y))
				{
				Game.getHandler().agregarObjeto(new Tree(x*80 - 35 ,y*80 - 50,Assets.snowTree,Game.getHandler(),main));
				map.setBloqueado(x, y + 1);
				map.setTipo(x, y+1);
				}
				}
				
			}
		}
	}

}