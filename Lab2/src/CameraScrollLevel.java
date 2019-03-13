import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CameraScrollLevel extends LevelController{

	
	private BufferedImage img;
	private int nBacks;
	private int camX;

	
	public CameraScrollLevel(int width,int height)
	{
		super(width,height);
		try
		{
			img = (BufferedImage)ImageIO.read(getClass().getClassLoader().getResource("./sback.png"));
			} catch (IOException e) {
				e.printStackTrace();
					}
		camX = 0;
		nBacks = 32;

		
	}
	
	public void update()
	{
		camX+=5;

	}
	
	public void render(Graphics2D g)
	{
		g.translate(-camX, 0);
		for(int i=0;i<nBacks;i++)
		{
			g.drawImage(img, i*img.getWidth(),0,null);
		}
	}


	
	
	
}
