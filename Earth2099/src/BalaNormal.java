import java.util.Random;

//Tipo de Bala con movimiento normal
public class BalaNormal extends Bala{

	public BalaNormal(int x, int y, int mousePosX, int mousePosY, float angulo,Handler handler,Game main) {
		super(x, y, mousePosX, mousePosY, angulo, handler,main);
		Random ran = new Random();
		velX+= (ran.nextInt(4)%2==0)?ran.nextInt(10):-ran.nextInt(10);
		velY+= (ran.nextInt(4)%2==0)?ran.nextInt(10):-ran.nextInt(10);
		
	}

}
