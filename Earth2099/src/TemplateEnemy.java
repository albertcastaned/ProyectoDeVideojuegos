import java.awt.Color;
import java.awt.Graphics;
import java.util.ListIterator;

//Templete de los tipos de enemigos que habra
public abstract class TemplateEnemy extends Entidad{
	
	//Vida actual, como no pueden recuperar vida no necesitamos vida maxima
	protected int vida;
	//Daño que hacen
	protected int dano;
	
	//Velocidad a la que el enemigo se mueve
	protected int velocidad;
	
	//Arma que portan si es que lo hacen
	private Arma arma;
	private boolean useArma;
	

	public TemplateEnemy(int x, int y,int ancho, int altura, String nombre, int vidaMax,int dano, int velocidad,Handler handler,Game main) {
		super(x,y,ancho,altura,nombre,handler,main);
		vida = vidaMax;
		this.dano = dano;
		this.velocidad = velocidad;

	}
	

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;

	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public Arma getArma() {
		return arma;
	}

	public void setArma(Arma arma) {
		this.arma = arma;
	}

	
	public boolean isUseArma() {
		return useArma;
	}

	public void setUseArma(boolean useArma) {
		this.useArma = useArma;
	}

	@Override
	public synchronized void render(Graphics g) {
		if(enCamara())
		{
		g.setColor(Color.RED);
		g.fillOval((int)x,(int)y,ancho,altura);
		}
		
	}
	

	public abstract void atacar(int x, int y);
	
	public abstract void disparar(int x, int y);
	
	public void checarColision()
	{
		//Obtener Entidades del Handler
		ListIterator <Entidad> iterator = handler.listaEntidades.listIterator();
		while (iterator.hasNext())
		{
			Entidad aux = iterator.next();
			

			//Colision con un Enemigo
			if (aux instanceof Bala)
			{
				if (chocandoEn(x, y, aux))
				{	//Quitar vida aqui
					
					
					
					//Si choca con bala eliminar bala y cambiar posicion de enemigo
					velX = aux.getVelX();
					velY = aux.getVelY();
					handler.quitarObjeto(aux);
				}

			}

		}
		
	}


	@Override
	public String toString() {
		return "TemplateEnemy [vida=" + vida + ", dano=" + dano + ", arma=" + arma + ", useArma=" + useArma + ", x=" + x + ", y=" + y + ", velX=" + velX + ", velY=" + velY + ", ancho=" + ancho + ", altura="
				+ altura  + ", nombre=" + nombre + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		else {
			return false;
		}
		
	}
	
	

}
