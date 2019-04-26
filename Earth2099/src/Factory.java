import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;


//Superclase de otros factories que creara entidades cada cierto tiempo, la forma en la que se crean sera definida en las subclases
public abstract class Factory {
	
	
	protected Timer timer;
	protected int map[][];
	public Factory(int tiempo,int map[][])
	{
		timer = new Timer(tiempo,timerCrearEntidad);
		this.map = map;
		timer.start();
	}
	public abstract void crear();
	
	//Timer
    ActionListener timerCrearEntidad = new ActionListener(){


		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			crear();
			timer.restart();
		}
    };
}
