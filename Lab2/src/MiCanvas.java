import javax.swing.*;
import java.awt.*;

//Canvas dentro de ventana
public class MiCanvas extends Canvas {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	//Iniciar ventana
	public MiCanvas(int ancho, int altura, String titulo, Game main)
	{
        frame = new JFrame(titulo);
        frame.setPreferredSize(new Dimension(ancho,altura));
        frame.setMaximumSize(new Dimension(ancho,altura));
        frame.setMinimumSize(new Dimension(ancho,altura));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(main);
        
        frame.setVisible(true);
        frame.setFocusable(false);
        main.empezar(); 
	}

}
