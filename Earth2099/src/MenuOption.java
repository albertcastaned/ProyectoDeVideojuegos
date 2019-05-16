import java.awt.*;

public abstract class MenuOption {
  protected Color defaultColor = Color.gray;
  protected Color selectedColor = Color.white;
  protected Color textColor = defaultColor;
  protected GameMenu gameMenu;
  protected String optionText;
  protected int x=-1, y, textWidth=-1;

  protected MenuOption(String optionText, GameMenu gameMenu, int y){
    this.optionText = optionText;
    this.gameMenu = gameMenu;
    this.y = y;
  }

  public abstract void apply();

  public void paint(Graphics2D g){
    if(textWidth == -1 || x == -1){
      textWidth = g.getFontMetrics().stringWidth(this.optionText);
      x = textWidth / 2;
    }
    g.setColor(textColor);
    g.drawString(this.optionText, gameMenu.getVentanaAncho()/2 - x, y);
  }

  public void select(){
    textColor = selectedColor;
  }

  public void unselect(){
    textColor = defaultColor;
  }
}
