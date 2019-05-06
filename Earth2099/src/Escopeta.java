import image.Assets;

import java.util.Random;

//Subclase que dispara las BalaOla
public class Escopeta extends Arma {
	public Escopeta(String s, int d, int n, int m, int t, Game main) {
		super(s, d, n, m, t,main);
		armaAbajo = Assets.pArmaAbajoEsc;
		armaArriba = Assets.pArmaArribaEsc;
		armaDerecha = Assets.pArmaDerechaEsc;
		armaIzquierda = Assets.pArmaIzquierdaEsc;
		armaDerechaAbajo = Assets.pArmaADEsc;
		armaDerechaArriba = Assets.pArmaArDEsc;
		armaIzquierdaAbajo = Assets.pArmaAIEsc;
		armaIzquierdaArriba = Assets.pArmaArIEsc;
		actual = armaAbajo;
	}

	@Override
	public void disparar(int x, int y, int mx, int my,float angulo) {

		//Si ha pasado el tiempo para poder disparar y tiene balas
		if(numBalas > 0 && getPuedeDisparar())
		{
			
		//Empezar timer
		puedeDisparar = false;
		timer.start();

		//Agregar al handler
		Random ran = new Random();
		handler.agregarObjeto(new BalaEscopeta(x,y,mx + ran.nextInt(160) + 30,my + ran.nextInt(160) + 30,angulo,handler,main));
		handler.agregarObjeto(new BalaEscopeta(x,y,mx - ran.nextInt(160) + 30,my - ran.nextInt(160) + 30,angulo,handler,main));
		handler.agregarObjeto(new BalaEscopeta(x,y,mx,my,angulo,handler,main));

		//Quitar una bala al arma
		numBalas-=3;
		
	
		}
	}


}
