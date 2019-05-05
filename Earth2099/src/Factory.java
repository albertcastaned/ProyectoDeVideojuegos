import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


//Superclase de otros factories que creara entidades cada cierto tiempo, la forma en la que se crean sera definida en las subclases
public abstract class Factory {
	
	
	protected Timer timer;
	protected int map[][];
	
	protected int countMax = 0;
	public Factory(int tiempo,int map[][])
	{
		timer = new Timer(tiempo,timerCrearEntidad);
		this.map = map;
		timer.start();
		timer.setRepeats(true);
		System.out.println("Nuevo factory");

	}
	
	public void bajarCount()
	{
		countMax--;
	}
	public abstract void crear();
	
	public void cambiarMap(int m[][])
	{
		System.out.println("Nuevo mapa");

		map = m;
		
		countMax = 0;
	}
	//Timer
    ActionListener timerCrearEntidad = new ActionListener(){


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			crear();
			
		}
    };
}
