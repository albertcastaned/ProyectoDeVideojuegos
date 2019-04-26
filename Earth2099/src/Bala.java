import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ListIterator;

import image.Assets;

//Clase bala que se movera a velocidad constante en una direccion hasta chocar con algun solido o fuera del escenario
public class Bala extends Entidad{

	//Tamaño de la bala
	protected int tamanio;
	protected float angulo;
	protected BufferedImage img;
	public Bala(int x, int y, int mousePosX, int mousePosY, int tamanio,Handler handler,Game main)
	{
		
		super(x,y,tamanio,tamanio,"Bala",handler,main);

		this.tamanio = tamanio;
		
		//Obtener velocidad x y velocidad y utilizando la hipotenusa respecto a ellas
		float dirX = mousePosX - x;
		float dirY = mousePosY - y;
		
		float dirLength = (float) Math.sqrt(dirX*dirX + dirY*dirY);
		velX = (int) (30 * dirX/dirLength);
		velY = (int) (30 * dirY/dirLength);

		angulo = getAngulo(new Point(mousePosX,mousePosY));
		
		img = Assets.bala;
		
	}
	
	public float getAngulo(Point destino) {
	    float angulo = (float) Math.toDegrees(Math.atan2(destino.y - 500, destino.x - 500));

	    if(angulo < 0)
	    	angulo += 360;
	    return angulo;
	}

	
	//Metodo actualizar que se llama a seguido
	public void actualizar(){
		
		//Checar si esta colisionando con alguna entidad
		checarColision();
		x += velX;
		y += velY;
		
		//Eliminar si esta fuera de la camara
		if(x < -main.getCamaraXOffset() || x > (-main.getCamaraXOffset() + Game.getVentanaAncho()) || y < -main.getCamaraYOffset() || y > (-main.getCamaraYOffset() + Game.getVentanaAltura()))
			handler.quitarObjeto(this);
	}
	public void checarColision() {
		//Obtener Entidades del Handler
		ListIterator <Entidad> iterator = handler.listaEntidades.listIterator();
		while (iterator.hasNext())
		{
			Entidad aux = iterator.next();
			
			//Colision con un Bloque solido
			if (aux instanceof BloqueColision)
			{
				if (chocandoEn(x+velX, y, aux))
				{
					handler.quitarObjeto(this);
				}
				if (chocandoEn(x, y+velY, aux))
				{
					handler.quitarObjeto(this);

				}

			}
			//Colision con un Enemigo
			if (aux instanceof TemplateEnemy)
			{
				if (chocandoEn(x, y, aux))
				{

					((TemplateEnemy) aux).setVida(((TemplateEnemy) aux).getVida() - 50);
					if(((TemplateEnemy) aux).getVida() <= 0)
						handler.quitarObjeto(aux);
					aux.setVelX(velX/2);
					aux.setVelY(velY/2);
					
					handler.quitarObjeto(this);
				}

			}

		}
		
	}
	
	//Dibujar la bala
	public  void render(Graphics2D g2d)
	{
		g2d.drawImage(img,x,y,null);
		
	}



}
