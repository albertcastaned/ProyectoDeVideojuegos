
public class Metralleta extends Arma{
	
	public Metralleta(String s,int d, int n, int m, int t,CollectionBalas col)
	{
		super(s,d,n,m,t,col);
	}

	@Override
	public void disparar(int x, int y, int mx, int my) {
		// TODO Auto-generated method stub
		if(numBalas > 0 && getPuedeDisparar())
		{
		puedeDisparar = false;
		timer.start();
		Bala bal = new BalaNormal(x,y,mx,my,daño);
		col.CrearBala(bal);
		numBalas--;
		

		}
	}

}
