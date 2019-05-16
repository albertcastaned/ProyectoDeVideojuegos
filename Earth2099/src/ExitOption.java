public class ExitOption extends MenuOption{
  public ExitOption(GameMenu gameMenu, int y){
    super("Exit game", gameMenu, y);
  }

  public void apply(){
    this.gameMenu.exitGame();
  }
}
