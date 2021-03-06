import image.Assets;

//Subclase que dispara BalaNormal
public class Metralleta extends Arma{
	
	public Metralleta(String s,int d, int n, int m, int t,Game main)
	{
		super(s,d,n,m,t,main);
		armaAbajo = Assets.pArmaAbajo;
		armaArriba = Assets.pArmaArriba;
		armaDerecha = Assets.pArmaDerecha;
		armaIzquierda = Assets.pArmaIzquierda;
		armaDerechaAbajo = Assets.pArmaAD;
		armaDerechaArriba = Assets.pArmaArD;
		armaIzquierdaAbajo = Assets.pArmaAI;
		armaIzquierdaArriba = Assets.pArmaArI;
		actual = armaAbajo;

	}

	@Override
	public void disparar(int x, int y, int mx, int my,float angulo) {
		// TODO Auto-generated method stub

		//Si ha pasado el tiempo para poder disparar y tiene balas
		if(numBalas > 0 && getPuedeDisparar())
		{
		//Empezar timer
		puedeDisparar = false;
		timer.start();
		
		//Crear tipo de bala de esta arma
		Bala bal = new BalaNormal(x,y,mx,my,angulo,handler,main);
		
		//Agregar al handler
		handler.agregarObjeto(bal);
		
		//Quitar una bala al arma
		numBalas--;

		}
	}

}
