public class Invisibilidad extends PowerUp{

	public Invisibilidad(int posX, int posY, int ancho, int altura, String nombre, Handler handler,Game main) {
		super(posX,posY,ancho,altura,nombre,handler,main);
    this.duracion = 600; //CYCLES
	}

  public boolean agregarAtributos(Personaje personaje){
    if(this.counter <= this.duracion){
      personaje.setVida(personaje.getVidaMaxima());
    }
    this.counter += 1;
    return this.counter <= this.duracion;
  }

  public void actualizar(){}

}
