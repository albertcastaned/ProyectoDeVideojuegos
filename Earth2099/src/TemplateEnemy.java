import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

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
	protected boolean useArma = false;
	protected int knockbackX = 0,knockbackY = 0;
	protected Timer timerKnockback;

	//Animacion
	protected image.AnimationSprite imagen,imgArriba,imgAbajo,imgDerecha,imgIzquierda;
	public TemplateEnemy(int x, int y,int ancho, int altura, String nombre, int vidaMax,int dano, int velocidad,Handler handler,Game main) {
		super(x,y,ancho,altura,nombre,handler,main);
		vida = vidaMax;
		this.dano = dano;
		this.velocidad = velocidad;
		timerKnockback = new Timer(30,resetKnockback);

	}
	public void setKnockbackX(int a)
	{
		knockbackX = a;
	}
	public void setKnockbackY(int a)
	{
		knockbackY = a;
	}
	public void activateTimer()
	{
		timerKnockback.start();
	}
	//Timer
    ActionListener resetKnockback = new ActionListener(){


		@Override
		public void actionPerformed(ActionEvent e) {
			knockbackX = 0;
			knockbackY = 0;
			timerKnockback.stop();
		}
    };
	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;

	}
	
	public void render(Graphics2D g) {
		if(enCamara())
		{
			imagen.render(g);
			if(vida<100) {
				g.setColor(Color.black);
				g.fillRect(x - 34, imagen.getY() - 12, 104, 14);
				g.setColor(Color.red);
				g.fillRect(x - 32, imagen.getY() - 10, 100, 10);
				g.setColor(Color.green);
				g.fillRect(x - 32, imagen.getY() - 10, vida, 10);
			}
			if(main.getDebug())
			{
				g.setColor(Color.RED);
				g.drawRect(x, y, ancho, altura);
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
