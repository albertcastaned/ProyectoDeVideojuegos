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


	//Iniciar ventana
	public MiCanvas(String titulo, Game main)
	{
        frame = new JFrame(titulo);
		frame.setMinimumSize(new Dimension(800,800));
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
        
        frame.add(main);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setFocusable(false);
		frame.requestFocus();

        
        //Empezar el thread ya que se inicio la ventana
        main.start(); 
	}
	
	public static int getWindowWidth()
	{
		return frame.getWidth();
	}
	
	public static int getWindowHeight()
	{
		return frame.getHeight();
	}
	


}
