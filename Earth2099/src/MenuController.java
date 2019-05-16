import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class MenuController implements KeyListener{
  private MenuOption currentOption;
  private int currentOptionIndex;
  private ArrayList<MenuOption> options;

  public MenuController(ArrayList<MenuOption> options){
    this.options = options;
    this.currentOptionIndex = 0;
    this.currentOption = this.options.get(this.currentOptionIndex);
    this.currentOption.select();
    
  }

  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();

    this.currentOption.unselect();

    switch(key){
      case KeyEvent.VK_W:
        this.currentOptionIndex = (this.currentOptionIndex + 1) % this.options.size();
        break;
      case KeyEvent.VK_S:
        if(this.currentOptionIndex == 0){
          this.currentOptionIndex = this.options.size();
        }

        this.currentOptionIndex = (this.currentOptionIndex - 1) % this.options.size();
        break;

    }
    this.currentOption = this.options.get(this.currentOptionIndex);
    this.currentOption.select();
  }

  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    switch(key){
      case KeyEvent.VK_ENTER:
      case KeyEvent.VK_SPACE:
      case KeyEvent.VK_D:
        System.out.println("APPLIED OPTION");
        this.currentOption.apply();
        break;
    }
  }

  public void keyTyped(KeyEvent e){
  }

  public void paint(Graphics2D g){
    options.forEach((opt) -> opt.paint(g));
  }
}
