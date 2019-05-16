import image.Assets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;


//Clase que crea instancias balas en un intervalo de determinado, 
public abstract class Arma {

	protected String nombre;
	
	protected Game main;
	
	//Daño que hace 
	protected int danio;
	
	//Numero de balas que tiene actualmente
	protected int numBalas;
	
	//Numero de balas que pueden cargar maximo
	protected int maxBalas;
	
	//El tiempo que tarda en poder volver a disparar
	protected int tiempoDisparar;
	
	//Booleano si puede disparar en un cierto tiempo
	protected boolean puedeDisparar;
	
	//Referencia al Handler
	protected static Handler handler;
	
	//Timer que cambia puedeDisparar en cierto tiempo determinado
	protected Timer timer;

	//Imagenes
	protected BufferedImage armaAbajo;
	protected BufferedImage armaArriba;
	protected BufferedImage armaIzquierda;
	protected BufferedImage armaDerecha;
	protected BufferedImage armaIzquierdaAbajo;
	protected BufferedImage armaIzquierdaArriba;
	protected BufferedImage armaDerechaArriba;
	protected BufferedImage armaDerechaAbajo;

	protected BufferedImage actual;

	
	public Arma(String s,int d, int n, int m, int t,Game main)
	{
		nombre = s;
		danio = d;
		numBalas = n;
		maxBalas = m;
		tiempoDisparar = t;
		handler = main.getHandler();
		puedeDisparar = true;
		this.main = main;
		timer = new Timer(tiempoDisparar,timerPoderDisparar);

	}
	public BufferedImage getPortada()
	{
		return armaDerechaAbajo;
	}

	public void cambiarImagen(int id)
	{
		switch(id)
		{
			case 0:
				actual = armaArriba;
				break;
			case 1:
				actual = armaAbajo;
				break;
			case 2:
				actual = armaDerecha;
				break;
			case 4:
				actual = armaIzquierda;
				break;
			case 5:
				actual = armaDerechaAbajo;
				break;
			case 6:
				actual = armaDerechaArriba;
				break;
			case 7:
				actual = armaIzquierdaAbajo;
				break;
			case 8:
				actual = armaIzquierdaArriba;
				break;
		}
	}

	public BufferedImage obtenerImagen()
	{
		return actual;
	}
	//Obtener string formato numBalas/maxBalas
	public String getBalas()
	{
		return String.valueOf(numBalas) + "/"+  String.valueOf(maxBalas);
	}
	
	//Metodo abstracto que cambiara dependiendo del tipo de arma
	public abstract void disparar(int x, int y, int mx, int my,float angulo);
	
	
	public String getNombre()
	{
		return nombre;
	}
	
	
	public boolean getPuedeDisparar()
	{
       return puedeDisparar;
	}
	
	//Sumar balas si son menores al maximo
	public void recargar(int n)
	{
		
		numBalas +=n;
		if(numBalas > maxBalas)
			numBalas = maxBalas;
	}
	
	//Timer
    ActionListener timerPoderDisparar = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			puedeDisparar = true;
			timer.stop();
		}
    };
	
}
