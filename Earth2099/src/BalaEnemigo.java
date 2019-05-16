import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ListIterator;

import image.Assets;

public class BalaEnemigo extends Bala{
	protected image.AnimationSprite imagen;
	
	public BalaEnemigo(int x, int y, int mousePosX, int mousePosY, float angulo, Handler handler, Game main) {
		super(x, y, mousePosX, mousePosY, angulo, handler, main);
		//Obtener velocidad x y velocidad y utilizando la hipotenusa respecto a ellas
		float dirX = mousePosX - x;
		float dirY = mousePosY - y;
		
		float dirLength = (float) Math.sqrt(dirX*dirX + dirY*dirY);
		velX = (int) (15 * dirX/dirLength);
		velY = (int) (15 * dirY/dirLength);

		image.SpriteBuilder builder = new image.SpriteBuilder(Assets.balaHueso,64,64);
		builder.addImage(0, 0);
		builder.addImage(1, 0);
		builder.addImage(2, 0);
		builder.addImage(3, 0);
		
		imagen = new image.AnimationSprite((int)x,(int)y,builder.build());
		imagen.setAnimSpd(20);

	}
	
	//Cambiar colision para que detecte al jugador y no al enemigo
	@Override
	public void checarColision() { }
	
	
	
	//Metodo actualizar que se llama a seguido
	@Override
	public void actualizar(){
		
		//Checar si esta colisionando con alguna entidad
		checarColision();
		x += velX;
		y += velY;
		imagen.update();
		imagen.setY((int)y);
		imagen.setX((int)x);
		//Eliminar si esta fuera de la camara
		if(x < -main.getCamaraXOffset() || x > (-main.getCamaraXOffset() + MiCanvas.getCanvas().getState().getVentanaAncho()) || y < -main.getCamaraYOffset() || y > (-main.getCamaraYOffset() + MiCanvas.getCanvas().getState().getVentanaAltura()))
			handler.quitarObjeto(this);
	}
	//Dibujar la bala
	@Override
	public  void render(Graphics2D g2d)
	{
		if(enCamara())
		{
			imagen.render(g2d);
			if(main.getDebug())
			{
				g2d.setColor(Color.RED);
				g2d.drawRect(x, y, ancho, altura);
			}
		}
		
	}

	

}
