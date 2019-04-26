import java.util.Random;

public class PowerUpFactory extends Factory{

	private static int countPowerUps = 0;
	private Game game;
	public PowerUpFactory(int tiempo, int[][] map,Game game) {
		super(tiempo, map);
		this.game = game;
	}

	public static void bajarCountPowerUps()
	{
		countPowerUps-=1;
	}
	@Override
	public void crear() {
		// TODO Auto-generated method stub
		//Numero maximo de enemigos 30
		if(countPowerUps >= 10)
			return;

		Random ran = new Random();
		int x = ran.nextInt(99);
		int y = ran.nextInt(99);
		
		//esta Bloqueado
		if(map[x][y] == 1)
			return;
		else
		{
			//Tipo de enemigo basado en porcentaje de 1 al 100
			int tipoPowerUp = ran.nextInt(100);
			if(tipoPowerUp <= 60)
			{
				Game.getHandler().agregarObjeto(new Invisibilidad(x * 80,y * 80,80,80,"Invisibilidad",Game.getHandler(),game));
			//else if(tipoEnemigo < 60 && tipoEnemigo <= 80)
				//Game.getHandler().agregarObjeto(new Esqueleto(x * 80,y * 80,80,80,"Esqueleto",100,5,8,Game.getHandler(),game));
			//else
				//Game.getHandler().agregarObjeto(new Fantasma(x * 80,y * 80,80,80,"Fantasma",100,5,8,Game.getHandler(),game));

			System.out.println("FACTORY NUEVO POWERUP EN X = " + x * 80 + " Y = " + y * 80);
			countPowerUps++;
			}
		}
		
				
		
	}

}
