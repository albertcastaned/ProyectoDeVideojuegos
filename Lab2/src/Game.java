import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


//Alberto Castañeda Arana 
//A01250647
//Laboratorio 2 Proyecto de Desarollo de Videojuegos


//Cambiado de JPanel a Canvas para evitar Flickering, y BufferStrategy para Image


//Se utilizo FakeScrollLevel para este juego, ya que el CameraScrollLevel hace que
//el jugador se mueva junto a la camara y se pierda de vista
public class Game extends Canvas implements Runnable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int ANCHO = 800;
	private static int ALTURA = ANCHO/2;

	private BufferStrategy buffer;
	private Graphics2D g;

	private Thread thread;
	private volatile boolean corriendo = false;
	private volatile boolean gameOver = false;
	private FakeScrollLevel fakeScroll;
	private CameraScrollLevel camScroll;
	private Jugador player;
	private Bloques bloques;

	//Iniciar juego
	public static void main(String args[])
	{
		new Game();
	}
	public Game()
	{
		
		readyForTermination();
		setFocusable(true);
		requestFocus();
		//Crear mi canvas
		new MiCanvas(ANCHO,ALTURA, "Laboratorio 2", this);
		//Asignar KeyListener para controlar a personajes
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

				if(e.getKeyCode() == KeyEvent.VK_UP)
					player.setUp(true);
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
					player.setDown(true);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_UP)
					player.setUp(false);
				if(e.getKeyCode() == KeyEvent.VK_DOWN)
					player.setDown(false);
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		


	}
	

	//Empezar thread e iniciar instancias
	public void empezar()
	{
		if(thread==null || !corriendo)
		{
			fakeScroll = new FakeScrollLevel(ANCHO,ALTURA);
			//camScroll = new CameraScrollLevel(ANCHO,ALTURA/2);
			player = new Jugador(100,100);
			bloques = new Bloques(player,this);

			thread = new Thread(this);
			thread.start();
			this.setVisible(true);
			corriendo = true;
		}
	}
	//Detener thread
	public void detener()
	{
		corriendo = false;
		gameOver = true;
		gameOverMsg();
	}
	
	//Game loop
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
	
	//Actualizar entidades
	public synchronized void gameActualizar() 
	{
		if(!gameOver)
		{
			
			//camScroll.update();
			fakeScroll.update();
			player.update();
			bloques.update();
		}	
		
	}
	//Renderizar entidades
	public synchronized void gameRender()
	{	
		buffer = getBufferStrategy();
        if(buffer == null){
            createBufferStrategy(3);
            return;
        }
        
        try {
        	g = (Graphics2D)buffer.getDrawGraphics();
        	g.setColor(Color.WHITE);
        	g.fillRect(0,0,ANCHO,ALTURA);
        	fakeScroll.render(g);
			//camScroll.render(g);
			player.draw(g);
			bloques.draw(g);
        }finally {
        	g.dispose();
        }

		buffer.show();

	}
	//Mensajes game over
	public void gameOverMsg()
	{
		System.out.println("Game Over");
		//System.exit(0);
	}
	public void readyForTermination()
	{
		
	}

	
}