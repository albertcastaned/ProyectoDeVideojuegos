import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//Clase que detecta teclas y agrega velocidad al jugador prueba
public class KeyInput implements KeyListener{
	private Jugador jugador;

	private boolean up,down,left,right;
	public KeyInput(Jugador jugador )
	{
		this.jugador = jugador;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {up=true;jugador.setVelY(-9);}
        if(key == KeyEvent.VK_S){down=true; jugador.setVelY(9);}
        if(key == KeyEvent.VK_A) {left=true;jugador.setVelX(-9);}
        if(key == KeyEvent.VK_D){right=true; jugador.setVelX(9);}
        if(key == KeyEvent.VK_1)
       	 jugador.asignarArma(0);
        if(key == KeyEvent.VK_2)
       	 jugador.asignarArma(1);
        if(key == KeyEvent.VK_3)
       	 jugador.asignarArma(2);
        
        if(key == KeyEvent.VK_R)
       	 jugador.recargar();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		 int key = e.getKeyCode();
		 if (key == KeyEvent.VK_W) {
             up = false;
             if (down) {
            	 jugador.setVelY(9);
             } else {
            	 jugador.setVelY(0);
             }
         }
         if (key == KeyEvent.VK_S) {
             down = false;
             if (up) {
            	 jugador.setVelY(-9);
             } else {
            	 jugador.setVelY(0);
             }
         }
         if (key == KeyEvent.VK_A){
                 left = false;
                 if (right) {
                	 jugador.setVelX(9);
                 } else {
                	 jugador.setVelX(0);
                 }
         }
             if (key == KeyEvent.VK_D){
                 right = false;
                 if (left) {
                	 jugador.setVelX(-9);
                 } else {
                	 jugador.setVelX(0);
                 }
             }


}
	

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
