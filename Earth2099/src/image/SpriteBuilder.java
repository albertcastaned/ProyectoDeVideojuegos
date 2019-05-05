package image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteBuilder {
	
	private BufferedImage spriteSheet;
	private List<BufferedImage> images;
	
	private final int cellW, cellH;
	
	public SpriteBuilder(BufferedImage pAbajo, int cellW, int cellH)
	{
		spriteSheet = pAbajo;

		
		images = new ArrayList<>();
		this.cellW = cellW;
		this.cellH = cellH;
	}
	
	public SpriteBuilder addImage(int i, int j)
	{
		images.add(spriteSheet.getSubimage(i*cellW,j*cellH,cellW,cellH));
		return this;
	}
	
	public CachedSprite build()
	{
		return new CachedSprite(images);
	}

}
