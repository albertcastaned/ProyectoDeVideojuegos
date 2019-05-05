import image.Assets;

public class Vida extends PowerUp {
    public Vida(int posX, int posY, int ancho, int altura, String nombre, Handler handler,Game main) {
        super(posX,posY,ancho,altura,nombre,handler,main);
        this.duracion = 0; //CYCLES
        img = Assets.vida;
    }

    public boolean agregarAtributos(Personaje personaje){
        if(this.counter <= this.duracion){
            personaje.setVida(personaje.getVida() + 20);
            handler.agregarObjeto(new TextoFlotante(personaje.getX(),personaje.getY(),30,30,"dmg","+20",2,handler,main));

        }
        this.counter += 1;
        return this.counter <= this.duracion;
    }

    public void actualizar(){}
}
