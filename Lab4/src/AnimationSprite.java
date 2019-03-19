import java.awt.Graphics;

public class AnimationSprite {
	
	private int imageIndex;
	private CachedSprite sprite;
	
	
	private boolean reachedEnd;
	private int animSpd;
	private int animCount;
	private int topCount;
	
	private int sX, sY;
	
	private int width, height;
	
	public AnimationSprite(int x, int y, CachedSprite sprite)
	{
		imageIndex = 0;
		this.sprite = sprite;
		System.out.println("Sprite count: " + sprite.size());
		sX = x;
		sY = y;
		
	}
	public AnimationSprite(int x, int y, CachedSprite sprite, int width, int height)
	{
		imageIndex = 0;
		this.sprite = sprite;
		System.out.println("Sprite count: " + sprite.size());
		sX = x;
		sY = y;
		this.width = width;
		this.height = height;
	}
	
	public int getWidth()
	{
		return width;
	}
	public int getHeight()
	{
		return height;
	}
	public void setX(int x)
	{
		sX = x;
	}
	public void setY(int y)
	{
		sY = y;
	}
	public int getX()
	{
		return sX;
	}
	public int getY()
	{
		return sY;
	}
	
	
	public void setAnimSpd(int animSpd)
	{
		this.animSpd = animSpd;
		if(animSpd != 0)
		{
			topCount = 60 / animSpd;
			animCount = 0;
		}
		reachedEnd = false;
	}
	
	public void update()
	{
		if(animSpd > 0)
		{
			if(animCount < topCount)
			{
				animCount++;
				reachedEnd = false;
			}else {
				animCount = 0;
				imageIndex = (imageIndex + 1) % sprite.size();
				reachedEnd = true;
			}
		}
	}
	
	public void render(Graphics g)
	{
			if(width!=0)
			g.drawImage(sprite.get(imageIndex), sX,sY,width,height,null);
			else
			g.drawImage(sprite.get(imageIndex), sX,sY,null);

	}
	
	public boolean hasReachedEnd()
	{
		return reachedEnd;
	}
	
	public void reset()
	{
		imageIndex = 0;
		animCount = 0;
	}


}
