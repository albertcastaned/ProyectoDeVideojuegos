import java.util.Random;

public class EnemyFactory extends Factory{
	
	private Game game;
	private static int enemyCount = 0;
	public EnemyFactory(int tiempo,int map[][],Game game) {
		super(tiempo,map);
		// TODO Auto-generated constructor stub
		this.game = game;
	}
	
	public static void bajarCountEnemigo()
	{
		enemyCount--;
	}
	@Override
	public void crear() {
		//Numero maximo de enemigos 30
		if(enemyCount >= 15)
			return;
		int i = 0;
		while(i < 3)
		{
		
			Random ran = new Random();
			int x = ran.nextInt(99);
			int y = ran.nextInt(99);
			
			//esta Bloqueado
			if(map[x][y] == 1)
				continue;
			else
			{
				//Tipo de enemigo basado en porcentaje de 1 al 100
				int tipoEnemigo = ran.nextInt(100);
				if(tipoEnemigo <= 60)
					Game.getHandler().agregarObjeto(new Zombi(x * 80,y * 80,80,80,"Zombi",100,5,8,Game.getHandler(),game));
				else if(tipoEnemigo < 60 && tipoEnemigo <= 80)
					Game.getHandler().agregarObjeto(new Esqueleto(x * 80,y * 80,80,80,"Esqueleto",100,5,8,Game.getHandler(),game));
				else
					Game.getHandler().agregarObjeto(new Fantasma(x * 80,y * 80,80,80,"Fantasma",100,5,8,Game.getHandler(),game));

				//System.out.println("FACTORY NUEVO ENEMIGO EN X = " + x * 80 + " Y = " + y * 80);
				i++;
				enemyCount++;
			}
				
		}
	}

}
