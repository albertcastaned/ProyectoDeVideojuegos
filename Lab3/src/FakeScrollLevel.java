import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FakeScrollLevel extends LevelController {

	private BufferedImage img;
	 int b1_xOff;
	 int b2_xOff;
	
	public FakeScrollLevel(int width,int height,BufferedImage background)
	{
		super(width,height);
		img = background;

		b1_xOff = 0;
		b2_xOff = b1_xOff + img.getWidth();
	}
	
	public void update()
	{
		b1_xOff-=5;
		b2_xOff-=5;
		if(b1_xOff <= -img.getWidth())
			b1_xOff = img.getWidth();
		if(b2_xOff <= -img.getWidth())
			b2_xOff = img.getWidth();
	}
	
	public void render(Graphics g)
	{
		g.drawImage(img, b1_xOff,0,null);
		g.drawImage(img, b2_xOff,0,null);

	}
}