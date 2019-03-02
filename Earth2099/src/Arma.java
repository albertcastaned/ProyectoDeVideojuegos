import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public abstract class Arma {

	protected String nombre;
	protected int daño;
	protected int numBalas;
	protected int maxBalas;
	protected int tiempoDisparar;
	protected boolean puedeDisparar;
	protected CollectionBalas col;
	protected Timer timer;
	
	public Arma(String s,int d, int n, int m, int t,CollectionBalas col)
	{
		nombre = s;
		daño = d;
		numBalas = n;
		maxBalas = m;
		tiempoDisparar = t;
		this.col = col;
		puedeDisparar = true;
		timer = new Timer(tiempoDisparar,timerPoderDisparar);

	}
	
	public String getBalas()
	{
		return String.valueOf(numBalas) + "/"+  String.valueOf(maxBalas);
	}
	public abstract void disparar(int x, int y, int mx, int my);
	
	public String getNombre()
	{
		return nombre;
	}
	

	public boolean getPuedeDisparar()
	{
       return puedeDisparar;
	}
	
	public void recargar(int n)
	{
		
		numBalas +=n;
		if(numBalas > maxBalas)
			numBalas = maxBalas;
	}
	
    ActionListener timerPoderDisparar = new ActionListener(){


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			puedeDisparar = true;
			timer.stop();
		}
    };
	
}
