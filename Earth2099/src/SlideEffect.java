import java.awt.*;

public class SlideEffect extends Entidad {
    private int alpha;
    public SlideEffect(int x, int y, int ancho, int altura, String nombre, Handler handler, Game main)
    {
        super(x,y,ancho,altura,nombre,handler,main);
        alpha = 100;

    }
    public void actualizar(){
        if(alpha > 0) {
            y -= 1;
            alpha--;
        }else
            handler.quitarObjeto(this);
    }

    public void render(Graphics2D g)
    {

        g.setColor(new Color(252,253,255,alpha));
        g.fillOval(x,y,40,40);
        g.setColor(new Color(0,0,0,alpha));
        g.drawOval(x,y,40,40);
    }
}
