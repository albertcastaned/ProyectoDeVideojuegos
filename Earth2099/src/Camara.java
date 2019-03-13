
//Clase que representa la porcion mostrada en la ventana del escenario
public class Camara {
	//Posicion respecto al origen
	private double xOffset, yOffset;
	
	
	public Camara(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}


	public double getxOffset() {
		return xOffset;
	}



	public double getyOffset() {
		return yOffset;
	}

//Funcion que suaviza el movimiento entre punto a y b en una velocidad alpha
	public double lerp(double point1, double point2, double alpha)
	{
	    return point1 + alpha * (point2 - point1);
	}
	//Cambiar la posicion de la camara basandose en la posicion actual del jugador
	public void actualizar(Personaje p)
	{
		xOffset = (int) lerp(xOffset,-p.getX() +(Main.getVentanaAncho()/2),(float) 0.1);
		yOffset = (int) lerp(yOffset,-p.getY() + (Main.getVentanaAltura()/2),(float) 0.1);

	}
	
	
	
}
