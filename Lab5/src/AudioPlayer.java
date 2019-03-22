import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	private static AudioPlayer instance;
	private HashMap<String,Clip> audios;
	private float musicVol;
	private float effectVol;
	
	private String currentMusic;
	
	
	private AudioPlayer()
	{
		audios = new HashMap<String,Clip>();
		musicVol = 1;
		effectVol = 1;
	}
	
	public static AudioPlayer get()
	{
		if(instance==null)
		{
			instance = new AudioPlayer();
		}
		return instance;
	}
	public static float clamp(float val, float min, float max) {
	    return Math.max(min, Math.min(max, val));
	}
	
	public void playBackMusic(String audioName)
	{
		Clip clip = getSoundClip(audioName);
		clip.setFramePosition(0);
		setVolume(clip,musicVol);
		clip.start();	
		currentMusic = audioName;
		}
	
	public void stopBackMusic()
	{
		if(currentMusic!=null)
		{
			Clip clip = audios.get(currentMusic);
			clip.stop();
			currentMusic = null;
		}
	}
	
	public void playEffectSound(String audioName)
	{
		Clip clip = getSoundClip(audioName);
		clip.setFramePosition(0);
		setVolume(clip,effectVol);
		clip.start();
		currentMusic = audioName;

	}
	
	public void setMusicVolume(float vol)
	{
		musicVol = vol;
		musicVol = clamp(musicVol,0,1);
		if(currentMusic!=null)
		setVolume(getSoundClip(currentMusic),musicVol);
		
	}
	public void setEffectVolume(float vol)
	{
		effectVol = vol;
	}
	public float getMusicVolume()
	{
		return musicVol;
	}
	public float getEffectVolume()
	{
		return effectVol;
	}
	private Clip getSoundClip(String name)
	{
		Clip clip = null;
		clip = audios.get(name);
		AudioInputStream stream = null;

		if(clip!=null)
		{
			return clip;
		}else {
			try {
				clip = AudioSystem.getClip();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
				//Crear carpeta Sounds en res y poner musica alli
				File file = new File(path + "/res/Sounds/" + name + ".wav");
				stream = AudioSystem.getAudioInputStream(file);
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				clip.open(stream);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			audios.put(name,clip);
		}
		return clip;
	}
	private void setVolume(Clip clip, float volume)
	{
		FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = range*volume + gainControl.getMinimum();
		gainControl.setValue(gain);
	}
}
