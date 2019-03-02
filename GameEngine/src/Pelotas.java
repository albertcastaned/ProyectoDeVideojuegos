import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Pelotas {
	private List<Pelota> pelotas;
	
	public Pelotas(int ancho,int altura, Jugador jug,Game game)
	{


		pelotas = new ArrayList<Pelota>();
		for(int i=0;i<10;i++)
		{
			Random rand = new Random();
			int type = rand.nextInt(2);
			if(type==0)
			{
				rand = new Random();
				int pos = rand.nextInt(ancho);
				pelotas.add(new Pelota(pos,0, 0, 10,jug,game));
				
			}else if(type==1)
			{
				rand = new Random();
				int pos = rand.nextInt(altura);
				pelotas.add(new Pelota(0,pos, 10, 0,jug,game));
			}
		}

	}
	
	public void actualizar()
	{
		
		Iterator<Pelota> it = pelotas.iterator();
		while(it.hasNext())
		{
			Pelota aux = it.next();
			aux.actualizar();
		}
		
	}
	
	public void render(Graphics g)
	{

		Iterator<Pelota> it2 = pelotas.iterator();
		while(it2.hasNext())
		{
			Pelota aux = it2.next();
			aux.render(g);
			
		}
	}


}
