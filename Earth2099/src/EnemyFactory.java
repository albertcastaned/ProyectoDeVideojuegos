import java.util.Random;

public class EnemyFactory extends Factory{
	
	private Game game;
	public EnemyFactory(int tiempo,int map[][],Game game) {
		super(tiempo,map);
		this.game = game;
	}
	

	@Override
	public void crear() {
		//Numero maximo de enemigos 15
		if(countMax >= 15 || !game.getCorriendo())
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
				int tipoEnemigo = ran.nextInt(100) + 1;
				if(tipoEnemigo <= 30)
					game.getHandler().agregarObjeto(new Zombi(x * 80,y * 80,40,40,"Zombi",100,5,12,game.getHandler(),game));
				else if(tipoEnemigo > 30 && tipoEnemigo <= 60)
					game.getHandler().agregarObjeto(new Esqueleto(x * 80,y * 80,40,40,"Esqueleto",100,5,10,game.getHandler(),game));
				else if(tipoEnemigo > 60 && tipoEnemigo <= 80)
					game.getHandler().agregarObjeto(new Fantasma(x * 80,y * 80,30,30,"Fantasma",100,5,10,game.getHandler(),game));
				else
					game.getHandler().agregarObjeto(new EsqueletoLider(x * 80,y * 80,40,40,"Esqueleto Lider",100,5,9,game.getHandler(),game));
				//System.out.println("FACTORY NUEVO ENEMIGO EN X = " + x * 80 + " Y = " + y * 80);
				i++;
				countMax++;
				System.out.println("Nuevo enemigo");
			}
				
		}
	}

}
