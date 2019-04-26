import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

//Templete de los tipos de enemigos que habra
public abstract class TemplateEnemy extends Entidad{
	
	//Vida actual, como no pueden recuperar vida no necesitamos vida maxima
	protected int vida;
	//Daño que hacen
	protected int dano;
	protected int knockbackX = 0,knockbackY = 0;

	//Velocidad a la que el enemigo se mueve
	protected int velocidad;
	
	//Arma que portan si es que lo hacen
	private Arma arma;
	protected boolean useArma = false;
	

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

	
	
	
	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		g.setColor(Color.BLUE);
		g.fillOval((int)x, (int)y, ancho, altura);
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
