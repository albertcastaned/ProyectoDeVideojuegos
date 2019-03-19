import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

// La clase Window nos genera una ventana con un canvas
public class Window {
	// Se declaran las variables a utilizar
	private JFrame frame;
	private int width, height;
	private String title;
	
	public static Canvas canvas;
	
	// Se crea el constructor de la clase, se le pasa el título y el tamaño de la ventana
	public Window(String title, int width, int height)
	{
		this.title = title;
		this.width = width;
		this.height = height;
		// Se llama al método que en sí crea la ventana
		createWindow();
	}
	
	// Método que crea a la ventana y al canvas
	public void createWindow()
	{
		// Se instancia un JFrame y se le da el título
		frame = new JFrame(title);
		
		// Se le da el tamaño preferido, máximo y mínimo, todos con el mismo width y height
		frame.setSize(width, height);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension (width, height));
		
		// Se hace que el frame pueda recibir el foco
		frame.setFocusable(true);
		// Para que se pueda cerrar con la cruz por defecto de una aplicación
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Para que no se pueda modificar el tamaño de la ventana
		frame.setResizable(false);
		// Hace que el frame aparezca al centro de la pantalla
		frame.setLocationRelativeTo(null);
		// Vuelve la ventana visible
		frame.setVisible(true);
		
		// Se instancia al canvas
		canvas = new Canvas();
		
		// Se le da el tamaño preferido, máximo y mínimo, todos con el mismo width y height
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension (width, height));
		
		// No es requerido que el canvas tenga el foco en ningún momento
		canvas.setFocusable(false);
		
		// Añade el canvas al frame
		frame.add(canvas);
		// Hace que el frame se adapte al canvas
		frame.pack();
		// El frame pide el foco al terminar de ser creado
		frame.requestFocus();
	}
	
	public Canvas getCanvas() { return canvas; }
	public JFrame getFrame() { return frame; }
}
