import java.awt.Graphics;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

// Clase encargada de manejar a los objetos
public class Handler {
	// Se hace una lista de objetos
	/*
	 *  CopyOnWriteArrayList ayuda a que no importe que se intenten correr varios procesos
	 *  a la vez sobre éste, siempre podrá ser modificada la lista
	 */	
	public CopyOnWriteArrayList <GameObject> obj;
	private int newSpd;
	// Constructor del Handler
	public Handler()
	{
		// Se instancia como una nueva lista de objetos
		obj = new CopyOnWriteArrayList <GameObject>();
		newSpd=3;
	}
	
	// Método encargado de acutalizar los objetos contenidos en el Handler
	public void tick()
	{
		// Se hace un iterador de la lista (se puede hacer con un for each)
		ListIterator <GameObject> iterator = obj.listIterator();
		boolean reset = false;
		// Se actualizan todos los objetos
		while (iterator.hasNext())
		{
			GameObject aux = iterator.next();
			if(aux.getX() < 0)
			{
				removeObj(aux);
				reset= true;
			}else {
			aux.tick();
			}
		}
		if(reset)
			resetear();
	}
	
	public void resetear()
	{

		newSpd++;
		Random ran = new Random();
		int pos = ran.nextInt(5);
		for(int i=0;i<5;i++)
		{
			if(i != pos)
			addObj(new Bloque(800,96*i,newSpd,this));
			
		}
	}
	// Método encargado de renderizar los objetos contenidos en el Hanlder
	public void render(Graphics g)
	{
		// Se hace un iterador de la lista (se puede hacer con un for each)
		ListIterator <GameObject> iterator = obj.listIterator();
		// Se renderizan todos los objetos
		while (iterator.hasNext())
		{
			GameObject aux = iterator.next();
			aux.paint(g);
		}
	}
	// Método para añadirle objetos al Handler
	public void addObj(GameObject obj)
	{
		// Le añade el objeto a la lista
		this.obj.add(obj);
	}
	// Método para remover los objetos del Handler
	public void removeObj(GameObject obj)
	{
		// Le remueve el objeto a la lista
		this.obj.remove(obj);
	}
}
