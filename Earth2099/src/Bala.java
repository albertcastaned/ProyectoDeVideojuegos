import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ListIterator;

import image.Assets;

//Clase bala que se movera a velocidad constante en una direccion hasta chocar con algun solido o fuera del escenario
public class Bala extends Entidad{


	protected static BufferedImage img = Assets.bala;
	public Bala(int x, int y, int mousePosX, int mousePosY, float angulo, Handler handler,Game main)
	{
	
		super(x,y,img.getWidth(),img.getHeight(),"Bala",handler,main);

		
		//Obtener velocidad x y velocidad y utilizando la hipotenusa respecto a ellas
		float dirX = mousePosX - x;
		float dirY = mousePosY - y;
		
		float dirLength = (float) Math.sqrt(dirX*dirX + dirY*dirY);
		velX = (int) (30 * dirX/dirLength);
		velY = (int) (30 * dirY/dirLength);

		img = Assets.bala;
		float finalAngulo = (float) Math.toRadians(angulo);
		AffineTransform tx = new AffineTransform();
		tx.rotate(finalAngulo,img.getWidth()/2,img.getHeight()/2);
		
		AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);
		
		img = op.filter(img, null);
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
			if (aux instanceof AguaColision)
			{
				if (chocandoEn(x, y, aux))
				{
					handler.quitarObjeto(this);
				}


			}
			//Colision con un Enemigo
			if (aux instanceof TemplateEnemy)
			{
				if (chocandoEn(x, y, aux))
				{
					
					((TemplateEnemy) aux).setVida(((TemplateEnemy) aux).getVida() - 20);
					((TemplateEnemy) aux).setKnockbackX(velX / 2);
					((TemplateEnemy) aux).setKnockbackY(velY / 2);
					((TemplateEnemy) aux).activateTimer();
					if(((TemplateEnemy) aux).getVida() <= 0)
					{
						//Si es esqueleto parar el timer de tirar hueso
						if(aux instanceof Esqueleto)
							((Esqueleto) aux).stopTimer();
						handler.quitarObjeto(aux);
					}
					
					
					handler.quitarObjeto(this);
					break;
				}

			}

		}
		
	}
	
	//Dibujar la bala
	public  void render(Graphics2D g2d)
	{
		g2d.drawImage(img,x,y,null);
		if(Game.getDebug())
		{
		g2d.setColor(Color.RED);
		g2d.drawRect(x, y, ancho, altura);
		}
		
	}



}
