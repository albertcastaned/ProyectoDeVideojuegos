import java.util.ListIterator;

public class BalaEnemigo extends Bala{

	public BalaEnemigo(int x, int y, int mousePosX, int mousePosY, float angulo, Handler handler, Game main) {
		super(x, y, mousePosX, mousePosY, angulo, handler, main);
		
	}
	
	//Cambiar colision para que detecte al jugador y no al enemigo
	@Override
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
			
			if(aux instanceof Personaje)
			{
				if (chocandoEn(x, y, aux))
				{
				((Personaje) aux).setVida(((Personaje) aux).getVida() - 10);
				handler.quitarObjeto(this);
				}
			}


		}
		
	}
	

	

}
