import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tree {
	private int x,y;
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
	public int getY() {
		// TODO Auto-generated method stub
		return y;
	}
	
}
