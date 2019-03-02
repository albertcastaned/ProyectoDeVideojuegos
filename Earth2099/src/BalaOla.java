
public class BalaOla extends Bala {
	private int dx;
	public BalaOla(int x, int y, int mousePosX, int mousePosY, int tamaño) {
		super(x, y, mousePosX, mousePosY, tamaño);
		// TODO Auto-generated constructor stub
	}
	
	public synchronized void actualizar(){
		
		if(!activo)
			return;
		x += velX;
		y += velY + (Math.sin(dx/4) * 20);
		if(x < 0 || x > 1200 || y < 0 || y > 1200/16 * 9)
			activo = false;
		dx++;
	}

}
