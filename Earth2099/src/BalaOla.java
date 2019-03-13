import java.util.Random;

//Tipo de bala que se mueve en un tipo de movimiento en onda
public class BalaOla extends Bala {
	private double dx;
	private int dir;
	public BalaOla(int x, int y, int mousePosX, int mousePosY, int tamanio,Handler handler,Main main) {
		super(x, y, mousePosX, mousePosY, tamanio,handler,main);
		// TODO Auto-generated constructor stub
		dx = 0;
		Random ran = new Random();
		int randomDir = ran.nextInt(2);
		dir = randomDir == 1 ? 1 : -1;
	}
	
	public synchronized void actualizar(){
		//Checar colision
		checarColision();
		
		x += velX;
		//Agregar valor de seno de dx que se incrementa constantemente
		y += velY + (Math.sin(dx/4) * 20);
		
		
		dx+= (double)dir;

	}

}
