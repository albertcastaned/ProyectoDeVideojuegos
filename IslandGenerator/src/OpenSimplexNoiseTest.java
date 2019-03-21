/*
 * OpenSimplex Noise sample class.
 */

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class OpenSimplexNoiseTest extends Canvas implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	private static final int WIDTHTILE = WIDTH/8;
	private static final int HEIGHTILE = HEIGHT/8;
	private static OpenSimplexNoise noise;
	private JFrame frame;
	private JButton generate;
	private Thread thread;
	private BufferedImage image;
	private BufferedImage water;

	private BufferedImage tile;
	private BufferStrategy bs;
	private Graphics g;
	private boolean corriendo;
	private BufferedImage test;
	private ArrayList tiles;
	public static void main(String[] args) throws IOException
	{	
		new OpenSimplexNoiseTest();
	}

	public OpenSimplexNoiseTest() throws IOException
	{
		try {
			test = ImageIO.read(OpenSimplexNoiseTest.class.getResource("/test.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			tile = ImageIO.read(OpenSimplexNoiseTest.class.getResource("/grassTile1.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		try {
			water = ImageIO.read(OpenSimplexNoiseTest.class.getResource("/wata.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
        frame = new JFrame("Map generator");
        frame.add(this);
        tiles = new ArrayList();
        generate = new JButton("Generate");

        frame.add(generate,BorderLayout.SOUTH);
        frame.pack();
        frame.setPreferredSize(new Dimension(800,800));
        frame.setMaximumSize(new Dimension(800,800));
        frame.setMinimumSize(new Dimension(800,800));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        generate.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e)
        	{

        		try {
					generateMap();
					placeTile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });


        frame.setVisible(true);
        frame.setFocusable(false);
		frame.requestFocus();
		generateMap();
		placeTile();
		start();
	
	
	}
	public synchronized void stop()
	{
		try {
			thread.join();
			corriendo = false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public synchronized void start()
	{	
		//Detener si ya se esta corriendo
		if (corriendo) return;
		
		//Empezar el thread
		thread = new Thread(this);
		thread.start();
		corriendo = true;
	}
	public void render()
	{

		bs = getBufferStrategy();
		if (bs == null)
		{
			createBufferStrategy(3);
			return;
		}
		try {
		g = bs.getDrawGraphics();
		
		
		g.clearRect(0, 0, 800, 800);
		g.drawImage(image,0,0,null);
		for(int i = 0; i < tiles.size();i++)
		{
			((Tile) tiles.get(i)).render(g);
		}
		}finally{
		g.dispose();
		}
		bs.show();
		
		
	}
	public void generateMap() throws IOException
	{
		Random ran = new Random();
		int rand = ran.nextInt(1000000);
		noise = new OpenSimplexNoise(rand);
		double scale = 0.0063;
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < HEIGHT; y++)
		{
			for (int x = 0; x < WIDTH; x++)
			{
				
				double value = sumOctave(10,x,y,0.5,scale, -60,255);
			
				int aux = test.getRGB(x, y);
				int red = (aux & 0x00ff0000) >> 16;
				
				int rgb = 0x010101 * ((int)(value) - red);
				image.setRGB(x, y, rgb);
				
			}
		}
		ImageIO.write(image, "png", new File("noise.png"));
	}
	
	public void placeTile()
	{	tiles.clear();
		for (int y = 0; y < WIDTHTILE; y++)
		{
			for (int x = 0; x < HEIGHTILE; x++)
			{
				int aux = image.getRGB(x*8, y*8);
				int red = (aux & 0x00ff0000) >> 16;
				if(red < 180)
				{
					tiles.add(new Tile(x*8,y*8,tile));
				}else {
					tiles.add(new Tile(x*8,y*8,water));

				}
				//int test = test.getRGB(x,y);
				
			}
		}
	}
	public static double sumOctave(double iterations,double x,double y,double persistence,double scale,double low,double high)
	{
		double maxAmp = 0;
		double amp = 1;
		double freq = scale;
		double newNoise = 0;
		
		for(int i =0; i < iterations;i++)
		{
			newNoise += noise.eval(x * freq ,y * freq) * amp;
			maxAmp += amp;
			amp *= persistence;
			freq *= 2;
		}
		
		newNoise /= maxAmp;
		newNoise = newNoise * (high-low)/2 + (high + low) / 2;
		return newNoise;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int fps = 60, ticks = 0;
		double timePerTick = 1000000000 / fps, delta = 0;
		long now, lastTime = System.nanoTime(), timer = 0;
		
		while (corriendo)
		{
			now = System.nanoTime();
			delta += (now-lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1)
			{
				render();

				ticks ++;
				delta --;
			}
			
			if (timer >= 1000000000)
			{
				System.out.println("Ticks and frames: " + ticks);

				ticks = 0;
				timer = 0;
			}
		}
stop();
	}
}