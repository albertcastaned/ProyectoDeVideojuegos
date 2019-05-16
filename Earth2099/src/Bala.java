import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ListIterator;
import java.util.Random;

import image.Assets;

//Clase bala que se movera a velocidad constante en una direccion hasta chocar con algun solido o fuera del escenario
public class Bala extends Entidad{


	protected BufferedImage img;
	public Bala(int x, int y, int mousePosX, int mousePosY, float angulo, Handler handler,Game main)
	{
	
		super(x,y,30,30,"Bala",handler,main);

		
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
					
					((TemplateEnemy) aux).setVida(((TemplateEnemy) aux).getVida() - 10);
					((TemplateEnemy) aux).setKnockbackX(velX / 2);
					((TemplateEnemy) aux).setKnockbackY(velY / 2);
					((TemplateEnemy) aux).activateTimer();
					handler.agregarObjeto(new TextoFlotante(aux.getX(),aux.getY(),30,30,"dmg","-10",1,handler,main));

					if(((TemplateEnemy) aux).getVida() <= 0)
					{
						//Si es esqueleto parar el timer de tirar hueso
						if(aux instanceof Esqueleto)
							((Esqueleto) aux).stopTimer();
						if(aux instanceof EsqueletoLider)
							((EsqueletoLider) aux).stopTimer();
						Random ran = new Random();
						int auxNum = ran.nextInt(30)+15;

						handler.agregarObjeto(new TextoFlotante(x,y,30,30,"dmg","+" + auxNum,3,handler,main));
						HUD.sumarPuntos(auxNum);
						handler.quitarObjeto(aux);
						main.bajarCountFactoryEnemigo();
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
		if(main.getDebug())
		{
		g2d.setColor(Color.RED);
		g2d.drawRect(x, y, ancho, altura);
		}
		
	}



}
