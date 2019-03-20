import java.awt.Color;
import java.awt.Graphics;
//Clase creada como prueba de Colision, luego se remplazara por objetos dibujados del Escenario
public class BloqueColision extends Entidad {

	
	public BloqueColision(int x,int y, int ancho, int altura,Handler handler,Main main)
	{
		super(x,y,ancho,altura,"Bloque" ,handler,main);
	}
	
	//Actualizar, aunque no hace pero para cumplir con lo abstracto
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	//Dibujar
	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(Color.YELLOW);
		g.fillRect(x,y,ancho,altura);
	}

}