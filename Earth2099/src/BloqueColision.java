
import java.awt.Color;
import java.awt.Graphics2D;
//Clase creada como prueba de Colision, luego se remplazara por objetos dibujados del Escenario
public class BloqueColision extends Entidad {

	
	public BloqueColision(int x,int y, int ancho, int altura,Handler handler,Game main)
	{
		super(x,y,ancho,altura,"Bloque" ,handler,main);
	}
	
	//Actualizar, aunque no hace pero para cumplir con lo abstracto
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

	//Dibujar
	public void render(Graphics2D g) {
		if(Game.getDebug())
		{
			g.setColor(Color.RED);
			g.drawRect(x, y, ancho, altura);
		}
	}

}
