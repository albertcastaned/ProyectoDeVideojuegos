import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	int x,y;
	private BufferedImage img;
	public Tile(int x,int y,BufferedImage img)
	{
		this.x = x;
		this.y = y;
		this.img = img;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(img,x,y,null);
	}
}
