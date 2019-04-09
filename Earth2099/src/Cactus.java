import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cactus extends Entidad{
	private BufferedImage img;
	
	public Cactus(int x, int y, BufferedImage img,Handler handler, Game main)
	{
		super(x,y,30,30,"Cactus",handler,main);
		this.img = img;
	}
	public void render(Graphics g)
	{
		if(enCamara())
		g.drawImage(img,x,y,null);
	}
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	
}
