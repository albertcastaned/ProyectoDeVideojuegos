
//Enemigo prueba del Template, luego se agregaran enemigos con comportamientos diferentes
public class EnemigoPrueba extends TemplateEnemy{

	public EnemigoPrueba(int x, int y, int ancho, int altura, String nombre, int vidaMax, int dano, Handler handler,Main main) {
		super(x, y, ancho, altura, nombre, vidaMax, dano, handler,main);
		// TODO Auto-generated constructor stub
	}

	//Como seguira al jugador
	@Override
	public void seguirJugador(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	//Como lo atacara
	@Override
	public void atacar(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	//Como lo disparara si es que tiene arma
	@Override
	public void disparar(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	//Actualizarlo
	@Override
	public void actualizar() {
		// TODO Auto-generated method stub
		
	}

}
