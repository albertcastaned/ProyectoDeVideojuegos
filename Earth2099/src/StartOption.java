public class StartOption extends MenuOption{
  public StartOption(GameMenu gameMenu, int y){
    super("Start game", gameMenu, y);
  }

  public void apply(){
    this.gameMenu.startGame();
  }
}
