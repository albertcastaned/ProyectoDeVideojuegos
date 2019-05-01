import java.util.Random;

import image.Assets;

//Enemigo que puede pasar por colisiones
public class Fantasma extends TemplateEnemy{
	//Esta siguiendo al jugador o no
	private boolean siguiendo;
	
	public Fantasma(int x, int y, int ancho, int altura, String nombre, int vidaMax, int dano, int velocidad, Handler handler,
			Game main) {
		super(x, y, ancho, altura, nombre, vidaMax, dano, velocidad, handler, main);
		siguiendo = false;
		Random ran = new Random();
		
		velocidad += ran.nextInt(10);
		image.SpriteBuilder builder = new image.SpriteBuilder(Assets.fantasmaSheet,70,97);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);


		imgAbajo = new image.AnimationSprite((int)x,(int)y,builder.build());
		imgAbajo.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.fantasmaSheet,70,97);
		builder.addImage(0, 1);
		builder.addImage(1, 1);
		builder.addImage(2, 1);


		imgIzquierda = new image.AnimationSprite((int)x,(int)y,builder.build());
		imgIzquierda.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.fantasmaSheet,70,97);
		builder.addImage(0, 2);
		builder.addImage(1, 2);
		builder.addImage(2, 2);


		imgDerecha = new image.AnimationSprite((int)x,(int)y,builder.build());
		imgDerecha.setAnimSpd(10);
		
		builder = new image.SpriteBuilder(Assets.fantasmaSheet,70,97);
		builder.addImage(0, 3);
		builder.addImage(1, 3);
		builder.addImage(2, 3);


		imgArriba = new image.AnimationSprite((int)x,(int)y,builder.build());
		imgArriba.setAnimSpd(10);
		imagen = imgAbajo;
		
		
	}
	public void seguirJugador()
	{

		//Calcular velocidad y angulo para llegar al punto
		int dx = main.getJugadorX() -x;
		int dy = main.getJugadorY() - y;
		double direction = Math.atan2(dy, dx);
		velX = (int) (velocidad * Math.cos(direction));
		velY = (int) (velocidad * Math.sin(direction));
		
	}


	public void actualizar()
	{	velX=0;
		velY=0;
		if(siguiendo)
		{
		seguirJugador();
		if(velX < 0)
		{
			imagen = imgIzquierda;
		}else if(velX > 0){
			imagen = imgDerecha;
		}
		else if(velY < 0)
		{
			imagen = imgArriba;
		}else if(velY > 0)
		{
			imagen = imgAbajo;
		}
		

		if(velX != 0 || velY != 0)
			imagen.update();
		x += velX + knockbackX;
		y += velY + knockbackY;
		//Inicar velocidad en 0
		velX = 0;
		velY = 0;
		
		imagen.setY((int)y - 40);
		imagen.setX((int)x - 20);
		}else {
			//Checar si esta a menos de 2000 de distancia para empezar a seguir
			int distanciaJugadorX = Math.abs(main.getJugadorX() - x);
			int distanciaJugadorY = Math.abs(main.getJugadorY() - y);
			if(1500>= (distanciaJugadorX + distanciaJugadorY))
			{
				siguiendo = true;
			}
		}
		


	}

}
