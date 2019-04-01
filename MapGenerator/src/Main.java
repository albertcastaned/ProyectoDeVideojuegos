import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
public class Main extends Canvas implements Runnable{
	private BufferStrategy bs;
	private Graphics g;
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private static BufferedImage grassTile;
	private static BufferedImage treeTile;
	private ArrayList<Tree> trees;
	//Variables para Thread
	private Thread thread;
	private boolean corriendo;
	private JButton generate;
	private boolean generating;
		public static void main(String args[])
		{
			new Main();
		}
		public Main()
		{
			generating = false;
			trees = new ArrayList<Tree>();
			try {
				 grassTile = ImageIO.read(Main.class.getResource("/Tiles/grassTile1.png"));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			try {
				 treeTile = ImageIO.read(Main.class.getResource("/Tiles/Tree.png"));
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
	        frame = new JFrame("Map generator");
	        frame.add(this);

	        generate = new JButton("Generate");

	        frame.add(generate,BorderLayout.SOUTH);
	        frame.pack();
	        frame.setPreferredSize(new Dimension(1500,900));
	        frame.setMaximumSize(new Dimension(1500,900));
	        frame.setMinimumSize(new Dimension(1500,900));

	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setResizable(false);
	        frame.setLocationRelativeTo(null);
	        generate.addActionListener(new ActionListener() {
	        	@Override
	        	public void actionPerformed(ActionEvent e)
	        	{
	    			generating = true;

	        		trees = new ArrayList<Tree>();
	        		generateTrees();
	        	}
	        });


	        frame.setVisible(true);
	        frame.setFocusable(false);
			frame.requestFocus();
			generateTrees();
			start();

		}
		public void generateTrees()
		{
			Random ran = new Random();
			float entire_size = 1500 - (-1500);
			float cell_size = entire_size/20;
			for(int i=0;i < 20;i++)
			{
				float x_min = -1500 + cell_size * i;
				float x_max = -1500 + cell_size*(i+1);
				
				x_min +=5;
				x_max -= 5;
				
				for(int j=0;j < 20;j++)
				{
					float y_min = -1500 + cell_size * j;
					float y_max = -1500 + cell_size*(j+1);
					
					y_min +=5;
					y_max -= 5;
					int randX = ran.nextInt(50);
					int randY = ran.nextInt(50);

					int x = (int) Remap((float)randX,(float)0,(float)50,(float)x_min,(float)x_max);
					int y = (int) Remap((float)randY,(float)0,(float)50,(float)y_min,(float)y_max);
					
					trees.add(new Tree(x,y,treeTile));
					System.out.println("Arbol creado en: " + x + y);
					
				}
			}
			Collections.sort(trees, new Comparator<Tree>(){
				   public int compare(Tree o1, Tree o2){
				      return o1.getY() - o2.getY();
				   }
				});
			generating = false;
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
			
			
			g.clearRect(0, 0, 1500, 900);
			for(int i=0;i<=50;i++)
			{
				for(int j=0;j<=50;j++)
				{
					g.drawImage(grassTile,i*30,j*30,null);
				}
			}
			
			Iterator<Tree> treeIt = trees.iterator();
			while(treeIt.hasNext())
			{
				if(generating)
					break;
				treeIt.next().render(g);
			}
			
			}finally{
			g.dispose();
			}
			bs.show();
			
			
		}
		
		float Remap(float x, float t1, float t2, float s1, float s2)
		{
			// "Yellow" is a "normalized" value between 0 and 1
			float yellow = (x - t1)/(t2 - t1);

			// "Green" is the result!
			float green = yellow*(s2 - s1) + s1;

			return green;
		}

		//Detener el thread
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
