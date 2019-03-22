

public class LevelController {

	protected int width;
	protected int height;
	
	public LevelController(int width,int height)
	{
		this.width = width;
		this.height = height;
		
		AudioPlayer.get().setMusicVolume(1f);
		AudioPlayer.get().playBackMusic("cowboys_music");
		AudioPlayer.get().setEffectVolume(0.6f);
		AudioPlayer.get().playEffectSound("bats");

	}
	

}