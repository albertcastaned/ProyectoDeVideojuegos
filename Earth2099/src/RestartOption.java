public class RestartOption extends MenuOption{
  public RestartOption(GameMenu gameMenu, int y){
    super("Restart game", gameMenu, y);
  }

  public void apply(){
    this.gameMenu.startGame();
  }
}
