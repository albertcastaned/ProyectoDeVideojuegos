import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tree extends Entidad{
	private BufferedImage img;
	
	public Tree(int x, int y, BufferedImage img)
	{
		this.x = x;
		this.y = y;
		this.img = img;
	}
	public void render(Graphics g)
	{
		g.drawImage(img,x,y,null);
	}
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}
	
}
