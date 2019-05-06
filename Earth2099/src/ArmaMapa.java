import java.awt.*;
import java.awt.image.BufferedImage;

public class ArmaMapa extends Entidad {

    private BufferedImage imagen;
    public ArmaMapa(int x, int y, int ancho, int altura, String nombre,BufferedImage imagen,Handler handler, Game main) {
        super(x, y, ancho, altura, nombre, handler, main);
        this.imagen = imagen;

    }

    public void actualizar()
    {
        //a
    }
    public String getArma()
    {
        return nombre;
    }
    public void render(Graphics2D g)
    {
        if(enCamara()) {
            g.drawImage(imagen,x,y,null);
        }
    }


}
