import image.Assets;

import java.util.Random;



//Factory creadora de los power-ups
public class PowerUpFactory extends Factory{
	
	//Referencia al main
	private Game game;
	public PowerUpFactory(int tiempo, int[][] map,Game game) {
		super(tiempo, map);
		this.game = game;
	}


	
	//Crea las instancias
	@Override
	public void crear()
	{
		//Numero maximo de enemigos 30
		
		if(countMax >= 10)
			return;
		//Posicion de la matriz actual
		Random ran = new Random();
		int x = ran.nextInt(99);
		int y = ran.nextInt(99);
		
		//esta Bloqueado
		if(map[x][y] == 1) {
			return;
		} else
		{
			//Tipo de enemigo basado en porcentaje de 1 al 100
			int tipoPowerUp = ran.nextInt(100) + 1;
			if(tipoPowerUp <= 40)
			{
				int aux = ran.nextInt(100) + 1;
				if(aux <= 33) {
					Game.getHandler().agregarObjeto(new Invisibilidad(x * 80, y * 80, 80, 80, "Invisibilidad", Game.getHandler(), game));
				}else if(aux > 33 && aux <= 66){
					Game.getHandler().agregarObjeto(new Vida(x * 80, y * 80, 80, 80, "Vida", Game.getHandler(), game));
				}else {
					Game.getHandler().agregarObjeto(new CartuchoBala(x * 80, y * 80, 80, 80, "Cartucho balas", Game.getHandler(), game));

				}
			}
			System.out.println("FACTORY NUEVO POWERUP EN X = " + x * 80 + " Y = " + y * 80);
			countMax++;

		}
	}

}


