import image.Assets;

public class CartuchoBala extends PowerUp {
    public CartuchoBala(int posX, int posY, int ancho, int altura, String nombre, Handler handler,Game main) {
        super(posX,posY,ancho,altura,nombre,handler,main);
        this.duracion = 0; //CYCLES
        img = Assets.cartucho;
    }

    public boolean agregarAtributos(Personaje personaje){
        if(this.counter <= this.duracion){
            personaje.sumarBalas(20);

        }
        this.counter += 1;
        return this.counter <= this.duracion;
    }

    public void actualizar(){}
}
