import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;

//Clase bala que se movera a velocidad constante en una direccion hasta chocar con algun solido o fuera del escenario
public class Bala extends Entidad{

	//Tamaño de la bala
	protected int tamanio;
		
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

		
	}
	
	

	
	//Metodo actualizar que se llama a seguido
	public synchronized void actualizar(){
		
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
					((TemplateEnemy) aux).setVida(((TemplateEnemy) aux).getVida());
					handler.quitarObjeto(this);
				}

			}

		}
		
	}
	
	//Dibujar la bala
	public synchronized void render(Graphics g)
	{
		g.setColor(Color.BLUE);
		g.fillOval((int)x, (int)y, tamanio, tamanio);
	}

}
