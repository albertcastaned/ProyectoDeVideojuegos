import image.Assets;

import java.util.ListIterator;
import java.util.Random;

//Tipo de bala que se mueve en un tipo de movimiento en onda
public class BalaOla extends Bala {
	private double dx;
	private int dir;
	public BalaOla(int x, int y, int mousePosX, int mousePosY,float angulo,Handler handler,Game main) {
		super(x, y, mousePosX, mousePosY, angulo,handler,main);
		// TODO Auto-generated constructor stub
		dx = 0;
		Random ran = new Random();
		int randomDir = ran.nextInt(2);
		dir = randomDir == 1 ? 1 : -1;
		img = Assets.wavy;
	}
	
	public void actualizar(){
		//Checar colision
		checarColision();
		
		x += velX + (Math.sin(dx/4) * velY ) * dx/10 * dir;
		//Agregar valor de seno de dx que se incrementa constantemente
		y += velY + (Math.sin(dx/4) * velX ) * dx/10 * dir;

		dx+= (double)dir;
		//Eliminar si esta fuera de la camara
		if(x < -main.getCamaraXOffset() || x > (-main.getCamaraXOffset() + Game.getVentanaAncho()) || y < -main.getCamaraYOffset() || y > (-main.getCamaraYOffset() + Game.getVentanaAltura()))
			handler.quitarObjeto(this);

	}
	@Override
	public void checarColision() {
		//Obtener Entidades del Handler
		ListIterator<Entidad> iterator = handler.listaEntidades.listIterator();
		while (iterator.hasNext())
		{
			Entidad aux = iterator.next();

			//Colision con un Enemigo
			if (aux instanceof TemplateEnemy)
			{
				if (chocandoEn(x, y, aux))
				{

					((TemplateEnemy) aux).setVida(((TemplateEnemy) aux).getVida() - 30);
					((TemplateEnemy) aux).setKnockbackX(velX / 2);
					((TemplateEnemy) aux).setKnockbackY(velY / 2);
					((TemplateEnemy) aux).activateTimer();
					handler.agregarObjeto(new TextoFlotante(aux.getX(),aux.getY(),30,30,"dmg","-30",1,handler,main));

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
						Game.bajarCountFactoryEnemigo();
						Game.sumarMuerto();

					}
				}

			}

		}

	}
}
