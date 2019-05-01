import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tree extends Entidad{
	private BufferedImage img;
	
	public Tree(int x, int y, BufferedImage img,Handler handler, Game main)
	{
		super(x,y,30,30,"Tree",handler,main);
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
