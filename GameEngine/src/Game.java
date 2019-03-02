import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

//Cambiado de JPanel a Canvas para evitar Flickering
public class Game extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int ALTURA = 800;
	private static int ANCHO = 800;
	private BufferStrategy buffer;
	private Graphics g;

	private Thread thread;
	private volatile boolean corriendo = false;
	private volatile boolean gameOver = false;
	
	private Jugador jugador;
	private Pelotas pelotas;
	
	public static void main(String args[])
	{
		new Game();
	}
	public Game()
	{

		readyForTermination();
		setFocusable(true);
		requestFocus();
		new MiCanvas(ALTURA,ANCHO, "Laboratorio 1", this);



	}
	

	
	public void empezar()
	{
		if(thread==null || !corriendo)
		{
			jugador = new Jugador(ALTURA,ANCHO);
			pelotas = new Pelotas(ALTURA,ANCHO,jugador,this);
			thread = new Thread(this);
			thread.start();
			this.setVisible(true);
			corriendo = true;
			
			this.addKeyListener(new KeyListener() {

				@Override
				public void keyPressed(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyCode() == KeyEvent.VK_UP)
						jugador.arriba = true;
					if(e.getKeyCode() == KeyEvent.VK_DOWN)
						jugador.abajo = true;
					if(e.getKeyCode() == KeyEvent.VK_LEFT)
						jugador.izquierda = true;
					if(e.getKeyCode() == KeyEvent.VK_RIGHT)
						jugador.derecha = true;
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					if(e.getKeyCode() == KeyEvent.VK_UP)
						jugador.arriba = false;

					
					if(e.getKeyCode() == KeyEvent.VK_DOWN)
						jugador.abajo = false;
					if(e.getKeyCode() == KeyEvent.VK_LEFT)
						jugador.izquierda = false;
					if(e.getKeyCode() == KeyEvent.VK_RIGHT)
						jugador.derecha = false;
				}

				@Override
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
				}
				
			});
			

		}
	}
	
	public void detener()
	{
		corriendo = false;
		System.exit(0);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		corriendo = true;
		while(corriendo)
		{
			if(gameOver)
				corriendo = false;
			
			gameActualizar();
			gameRender();
			
			try {
				Thread.sleep(20);
			}catch(InterruptedException e)
			{
				System.exit(0);
			}
			
		}

		
	}
	
	public synchronized void gameActualizar() 
	{
		if(!gameOver)
		{
			
			pelotas.actualizar();
			jugador.actualizar();
		}
		
	}
	public synchronized void gameRender()
	{	
		buffer = getBufferStrategy();
        if(buffer == null){
            createBufferStrategy(3);
            return;
        }
        
        try {
        	g = buffer.getDrawGraphics();
        	g.setColor(Color.WHITE);
        	g.fillRect(0,0,ALTURA,ANCHO);
			jugador.render(g);
			pelotas.render(g);
        }finally {
        	g.dispose();
        }

		buffer.show();

	}
	
	public void gameOverMsg()
	{
		System.out.println("Game Over");
	}
	public void readyForTermination()
	{
		
	}

	
}