//Subclase que dispara las BalaOla
public class Escopeta extends Arma {
	public Escopeta(String s, int d, int n, int m, int t, Handler handler,Main main) {
		super(s, d, n, m, t, handler,main);
		
	}

	
	public void disparar(int x, int y, int mx, int my) {

		//Si ha pasado el tiempo para poder disparar y tiene balas
		if(numBalas > 0 && getPuedeDisparar())
		{
			
		//Empezar timer
		puedeDisparar = false;
		timer.start();
		
		//Crear tipo de bala de esta arma
		Bala bal = new BalaOla(x,y,mx,my,danio,handler,main);
		
		//Agregar al handler
		handler.agregarObjeto(bal);
		
		//Quitar una bala al arma
		numBalas--;
		
	
		}
	}

}
