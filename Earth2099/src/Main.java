import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	//Iniciar dimensiones de ventana
	private static final int VentanaAncho = 1200, VentanaAltura = VentanaAncho/16 * 9;
	private Thread thread;
	private boolean corriendo;
	private Jugador jugador;
	private Graphics g;
	private BufferStrategy bs;
	private CollectionBalas colBalas;

	public static void main(String args[])
	{
		new Main();
	}
	//Iniciar juego
	public Main()
	{
		corriendo = false;

		new MiCanvas(VentanaAncho,VentanaAltura, "Earth 2099", this);
		

	}
	//Inicar el thread
	public synchronized void start()
	{	

		thread = new Thread(this);
		thread.start();
		corriendo = true;
		colBalas = new CollectionBalas();
		jugador = new Jugador(10,10,colBalas);
		this.addKeyListener(new KeyInput(jugador));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				


			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				

				jugador.disparar(e.getX(),e.getY());
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}
			
			
		});
		this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				// TODO Auto-generated method stub

			      	e.consume(); 
			        int notches = e.getWheelRotation();
			        while (notches > 0) {
			            jugador.cambiarArma(1);
			            notches--;
			        }
			        while (notches < 0) {
			            jugador.cambiarArma(-1);
			            notches++;
			        }
			}


			
		});


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
	//Llamar metodos actualizar y dibujar de cada objeto 60 veces por segundo para movimiento fluido
	@Override
	public synchronized void run() {
		long tiempo = System.nanoTime();
		double ticksPorSegundo = 60.0;
		double ns = 1000000000/ticksPorSegundo;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(corriendo)
		{
			long now = System.nanoTime();
			delta += (now - tiempo)/ns;
			tiempo = now;
			while(delta>=1)
			{
				actualizar();
				delta--;
			}
			if(corriendo)
				dibujar();
			frames++;
			
			if(System.currentTimeMillis()-timer>1000)
			{
				timer+=1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}
	public void resetear()
	{
		stop();
		start();
	}
	private void actualizar()
	{	

		//Se llamara aqui metodo actualizar de cada entidad
		jugador.actualizar();
		colBalas.actualizar();
	}
	private void dibujar()
	{
		//Se llamara aqui metodo dibujar de cada entidad
        bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        try {
        	g = bs.getDrawGraphics();
        	g.setColor(Color.BLACK);
        	g.fillRect(0,0,VentanaAncho,VentanaAltura);
        	jugador.render(g);
        	colBalas.render(g);
        	g.setColor(Color.WHITE);

        	g.drawString("CONTROLES:",20,200);
        	g.drawString("W A S D para moverse",20, 220);
        	g.drawString("1 , 2 , 3 para cambiar de arma",20, 240);
        	g.drawString("R para recargar",20, 260);
        	g.drawString("Click para disparar a direccion de mouse",20, 280);


        	//colBalas.actualizar();
        }finally {
        	g.dispose();
        }

		bs.show();
	}
	

}
