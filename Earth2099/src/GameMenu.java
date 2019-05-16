import java.awt.*;

import java.awt.image.BufferStrategy;
import java.util.ArrayList;

public class GameMenu extends GameState implements Runnable{
	private static final long serialVersionUID = 1L;

	private Graphics g;
	private BufferStrategy bs;

  private MenuController menuController;
  private ArrayList<MenuOption> options;

  public GameMenu(){
    corriendo = false;
    this.windowTitle = "Earth 2099 - Menu";
    this.VentanaAncho = Toolkit.getDefaultToolkit().getScreenSize().width;
    this.VentanaAltura = Toolkit.getDefaultToolkit().getScreenSize().height;
      MiCanvas canvas = MiCanvas.getCanvas(this.windowTitle, this);
  }

  public synchronized void start(){
    if (corriendo) return;

    //Empezar el thread
    thread = new Thread(this);
    thread.start();
    corriendo = true;

    options = new ArrayList<>();

    options.add(new StartOption(this, 150));
    options.add(new ExitOption(this, 200));

    menuController = new MenuController(options);

    this.addKeyListener(menuController);
  }

  public synchronized void stop(){
    try{
        corriendo = false;
      thread.join();
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  synchronized void actualizar(){
  }

  synchronized void dibujar(){
        bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }
        try {
        	//Dibujar el fondo
        	g = bs.getDrawGraphics();

        	//Convertir a Graphics2D para utilizar el metodo translate
        	Graphics2D g2d = (Graphics2D)g;
        	
        	g2d.setColor(Color.BLACK);
        	g2d.fillRect(0,0,VentanaAncho,VentanaAltura);
          menuController.paint(g2d);
        }finally {
        	g.dispose();
        }

      bs.show();
  }

   void startGame(){
      Main.setState(this, new Game());
   }
   void exitGame(){
     System.exit(0);
   }

   public int getJugadorX(){return 0;}
   public int getJugadorY(){return 0;}
   public double getCamaraXOffset(){return 0;}
   public double getCamaraYOffset(){return 0;}

}
