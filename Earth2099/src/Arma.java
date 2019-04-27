import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	protected Handler handler;
	
	//Timer que cambia puedeDisparar en cierto tiempo determinado
	protected Timer timer;
	
	public Arma(String s,int d, int n, int m, int t,Handler handler,Game main)
	{
		nombre = s;
		danio = d;
		numBalas = n;
		maxBalas = m;
		tiempoDisparar = t;
		this.handler = handler;
		puedeDisparar = true;
		this.main = main;
		timer = new Timer(tiempoDisparar,timerPoderDisparar);

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
			// TODO Auto-generated method stub
			puedeDisparar = true;
			timer.stop();
		}
    };
	
}
