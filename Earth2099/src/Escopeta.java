
public class Escopeta extends Arma {

	public Escopeta(String s, int d, int n, int m, int t, CollectionBalas col) {
		super(s, d, n, m, t, col);
	}

	
	public void disparar(int x, int y, int mx, int my) {

		if(numBalas > 0 && getPuedeDisparar())
		{
		puedeDisparar = false;
		timer.start();
		Bala bal = new BalaOla(x,y,mx,my,daño);
		col.CrearBala(bal);
		numBalas--;
		
	
		}
	}

}
