public class Main{

  public static void setState(GameState oldState, GameState newState){
    oldState.setCorriendo(false);
    MiCanvas.getCanvas().setState(newState);
  }
  public static void main(String args[]){
     new GameMenu();
  }
}
