import java.awt.*;


public abstract class GameState extends Canvas implements Runnable{

  protected static final long serialVersionUID = 1L;
  protected volatile int VentanaAncho, VentanaAltura;
  protected String windowTitle;

  protected GameState(){
  }

  protected GameState(String windowTitle, MiCanvas canvas){
    this.windowTitle = windowTitle;
    corriendo = false;
    canvas.setState(this);
  }

	//Variables para Thread
	protected Thread thread;
	protected volatile boolean corriendo;

  public abstract void start();
  public abstract void stop();

  abstract void actualizar();
  abstract void dibujar();


  public int getVentanaAncho(){
    return VentanaAncho;
  }
  public int getVentanaAltura(){
    return VentanaAltura;
  }

  public void setCorriendo(boolean corriendo){

    this.corriendo = corriendo;
  }

  // Reset
  public void resetear(){
    stop();
    start();
  }

  // Run
  public synchronized void run(){
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
        actualizar();
        dibujar();

        ticks ++;
        delta --;
      }
      
      if (timer >= 1000000000)
      {

        ticks = 0;
        timer = 0;
      }
    }
		stop();
  }

  public String getWindowTitle(){
    return windowTitle;
  }


  // Game Methods
  public abstract int getJugadorX();
  public abstract int getJugadorY();
  public abstract double getCamaraXOffset();
  public abstract double getCamaraYOffset();

}
