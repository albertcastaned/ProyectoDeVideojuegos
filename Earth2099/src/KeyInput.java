import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//Clase que detecta teclas y asigna direccion al personaje
public class KeyInput implements KeyListener{
	private Personaje personaje;

	public KeyInput(Personaje personaje)
	{

		this.personaje = personaje;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		int key = e.getKeyCode();
		
		//Teclas VK_W -> Tecla W
        if(key == KeyEvent.VK_W) 
        	personaje.setArriba(true);
        
        if(key == KeyEvent.VK_S)
        	personaje.setAbajo(true);
        
        if(key == KeyEvent.VK_A)
        	personaje.setIzquierda(true);
        
        if(key == KeyEvent.VK_D)
        	personaje.setDerecha(true);
        
        if(key == KeyEvent.VK_1)
        	personaje.asignarArma(0);
        
        if(key == KeyEvent.VK_2)
        	personaje.asignarArma(1);
        
        if(key == KeyEvent.VK_3)
        	personaje.asignarArma(2);

        
        if(key == KeyEvent.VK_P)
        	Game.setDebug();
        if(key == KeyEvent.VK_Z)
        	personaje.cambiarNivel();
		if(key == KeyEvent.VK_SHIFT)
			personaje.esquivar();
		if(key == KeyEvent.VK_SPACE)
			personaje.agarrarArma();
        if(key == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
	
	public void cambiarPersonaje(Personaje p)
	{
		personaje = p;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		 int key = e.getKeyCode();
		 if (key == KeyEvent.VK_W) 
             personaje.setArriba(false);
         
         if (key == KeyEvent.VK_S) 
             personaje.setAbajo(false);
         
         if (key == KeyEvent.VK_A)
        	 personaje.setIzquierda(false);

         if (key == KeyEvent.VK_D)
        	 personaje.setDerecha(false);
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	