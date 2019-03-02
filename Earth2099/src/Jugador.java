import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

//Clase de prueba para probar el game loop
public class Jugador extends Entidad{

	private Arma[] armas;
	private int armaIndex = 1;
	public Jugador(int x, int y, CollectionBalas colBalas)
	{

		super(x,y,30,30,"Personaje"); 
		armas = new Arma[3];
		armas[0] = new Metralleta("Metralleta",10,50,50,50,colBalas);
		armas[1] = new Escopeta("Escopeta",30,10,50,100,colBalas);
		armas[2] = new Escopeta("Escopeta 2",50,50,50,500,colBalas);
		
	}


	public synchronized void actualizar()
	{
		x+= velX;
		y+= velY;

	}
	
	public synchronized void disparar(int mx, int my)
	{

		armas[armaIndex].disparar(x, y, mx, my);

		
	}
	
	public void cambiarArma(int num)
	{
		if(armaIndex == 2 && num == 1)
		{
			armaIndex = 0;
			return;
		}if(armaIndex == 0 && num == -1)
		{
			armaIndex = 2;
			return;
		}
		armaIndex  += num;
	}
	public void asignarArma(int num)
	{
		if(armas[armaIndex] != null)
			armaIndex = num;
	}
	
	public void recargar()
	{
		armas[armaIndex].recargar(10);
	}

	public synchronized void render(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.drawString("Nombre de arma: " + armas[armaIndex].getNombre(), 20, 20);
		g.drawString("Numero de balas: " + armas[armaIndex].getBalas(), 20, 40);
		g.drawString("Puede disparar: " + String.valueOf(armas[armaIndex].getPuedeDisparar()), 20, 60);
		g.setColor(Color.RED);

		g.fillOval(x,y,ancho,altura);
	}




}
