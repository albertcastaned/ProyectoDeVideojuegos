import java.awt.Color;
import java.awt.Graphics;

//Templete de los tipos de enemigos que habra
public abstract class TemplateEnemy extends Entidad{
	
	//Vida actual, como no pueden recuperar vida no necesitamos vida maxima
	private int vida;
	//Daño que hacen
	private int dano;
	
	//Arma que portan si es que lo hacen
	private Arma arma;
	private boolean useArma;
	

	public TemplateEnemy(int x, int y,int ancho, int altura, String nombre, int vidaMax,int dano, Handler handler,Main main) {
		super(x,y,ancho,altura,nombre,handler,main);
		vida = vidaMax;
		this.dano = dano;

	}
	
	public void checarColision()
	{
		
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

		g.setColor(Color.RED);
		g.fillOval((int)x,(int)y,ancho,altura);
		
	}
	
	public void actualizar()
	{
		
	}

	public abstract void atacar(int x, int y);
	
	public abstract void disparar(int x, int y);


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
