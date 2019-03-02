import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionBalas {
	private List<Bala> balas;
	
	public CollectionBalas()
	{
		balas = new ArrayList<Bala>();
	}
	
	public synchronized void CrearBala(Bala bal)
	{

		if(balas!=null)
			balas.add(bal);
	}
	
	
	public synchronized void actualizar()
	{
		Iterator<Bala> itr = balas.iterator();
		while(itr.hasNext())
		{
			Bala bal = itr.next();
			if(!bal.getActivo())
			{
				itr.remove();
			}else
			bal.actualizar();
		}
	}
	
	public synchronized void render(Graphics g)
	{
		Iterator<Bala> itr = balas.iterator();
		while(itr.hasNext())
		{
			Bala bal = itr.next();
			if(!bal.getActivo())
			{
				itr.remove();
			}
			else {
			bal.render(g);
			//bal.actualizar();
			}
		}
	}
}
