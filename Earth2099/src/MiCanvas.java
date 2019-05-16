import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//Crear ventana y Canvas en la cual se mostrara todo
public class MiCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
  private GameState main;
  private static MiCanvas instance;


	//Iniciar ventana
	private MiCanvas(String titulo, GameState main)
	{
      frame = new JFrame(titulo);
      frame.setMinimumSize(new Dimension(main.getVentanaAncho(),main.getVentanaAltura()));
      // Transparent 16 x 16 pixel cursor image.
      BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

      // Create a new blank cursor.
      Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
      cursorImg, new Point(0, 0), "blank cursor");

      // Set the blank cursor to the JFrame.
      frame.getContentPane().setCursor(blankCursor);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      frame.setState(Frame.NORMAL);
        

      frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
      frame.setUndecorated(true);
      frame.setVisible(true);
      frame.setFocusable(true);
      frame.requestFocus();
      frame.add(main);


      //Empezar el thread ya que se inicio la ventana
      main.start(); 

      this.main = main;
	}

  public static MiCanvas getCanvas(String titulo, GameState main){
    if(instance == null){
      instance = new MiCanvas(titulo, main);
    }
    return instance;
  }

  public static MiCanvas getCanvas(){
    return instance;
  }

  public GameState getState(){
    return main;
  }
	
	public static int getWindowWidth()
	{
		return frame.getWidth();
	}
	
	public static int getWindowHeight()
	{
		return frame.getHeight();
	}

  public void setState(GameState main){
      this.frame.dispose();

      this.frame = new JFrame(main.getWindowTitle());
      this.frame.setMinimumSize(new Dimension(main.getVentanaAncho(),main.getVentanaAltura()));
      // Transparent 16 x 16 pixel cursor image.
      BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

      // Create a new blank cursor.
      Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
              cursorImg, new Point(0, 0), "blank cursor");

      // Set the blank cursor to the JFrame.
      this.frame.getContentPane().setCursor(blankCursor);
      this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.frame.setResizable(false);
      this.frame.setLocationRelativeTo(null);
      this.frame.setState(Frame.NORMAL);


      this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
      this.frame.setUndecorated(true);
      this.frame.setVisible(true);
      this.frame.setFocusable(true);
      this.frame.requestFocus();
      this.frame.add(main);

      
    //Empezar el thread ya que se inicio la ventana
    main.start();
    this.main = main;
  }

}
