import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Decoracion extends Entidad{
	private BufferedImage img;
	public Decoracion(int x, int y, BufferedImage img,Handler handler, Game main)
	{
		super(x,y,30,30,"Decoracion",handler,main);
		this.img = img;
	}
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub

	}

	public void render(Graphics2D g) {
		// TODO Auto-generated method stub
		if(enCamara())
		{
		g.drawImage(img,x,y,null);
		
		}
	}
	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return img.getHeight() + y;
	}

}
