import java.awt.Graphics;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

//Clase que guarda las entidades en una lista e itera sobre ella
public class Handler {
	
	//Tipo de ArrayList que permite escribir y leer a la vez
	public CopyOnWriteArrayList <Entidad> listaEntidades;
	
	public Handler()
	{
		//Crear lista
		listaEntidades = new CopyOnWriteArrayList<Entidad>();
	}

	//Llamado desde actualizar del main, actualiza cada entidad
	public void actualizar()
	{
		//Iterar
		Iterator<Entidad> itr = listaEntidades.iterator();
		while(itr.hasNext())
		{
			Entidad ent = itr.next();
			
			//Llamar actualizar de la entidad
			ent.actualizar();
		}
	}
	
	//Llamado desde actualizar del maim, dibuja cada entidad
	public void render(Graphics g)
	{
		//Iterar
		Iterator<Entidad> itr = listaEntidades.iterator();
		while(itr.hasNext())
		{
			Entidad ent = itr.next();
			/*
			if(ent instanceof Personaje)
			{
				if(ent.getVelX() !=0 || ent.getVelY() != 0)
				{
					 Collections.sort(listaEntidades, new Comparator<Entidad>() {
					        public int compare(Entidad o1, Entidad o2) {
					            return Integer.compare(o2.getY(), o1.getY());
					        }
					    });			
				}
			}
			*/
			
			//Dibujar entidad
			ent.render(g);
		}
	}
	
	//Agregar entidad a la lista
	public void agregarObjeto(Entidad obj)
	{
		System.out.println("Objeto agregado al Handler");
		listaEntidades.add(obj);
	}
	
	//Eliminar objeto de la lista
	public void quitarObjeto(Entidad obj)
	{
		// Le remueve el objeto a la lista
		listaEntidades.remove(obj);
		System.out.println("Objeto eliminado de Handler");
	}
}
