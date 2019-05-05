import java.awt.*;
import java.util.Random;

public class TextoFlotante extends Entidad{
    private int cycles;
    private String dmg;
    private int tipo;
    public TextoFlotante(int x, int y, int ancho, int altura, String nombre, String dmg, int tipo,Handler handler, Game main)
    {
        super(x,y,ancho,altura,nombre,handler,main);
        Random ran = new Random();
        int num = ran.nextInt(80);
        this.x = (num%2==0)?x+num:x-num;
        cycles = 0;
        this.dmg = dmg;
        this.tipo = tipo;

    }
    @Override
    public void actualizar()
    {
        if(cycles < 80) {
            y -= 3;
            cycles++;
        }else
           handler.quitarObjeto(this);
    }

    @Override
    public void render(Graphics2D g)
    {
        Font tr = new Font("TimesRoman", Font.BOLD, 33);
        g.setFont(tr);
        g.setColor(Color.BLACK);
        g.drawString(dmg,x+2,y+2);
        tr = new Font("TimesRoman", Font.BOLD, 32);
        g.setFont(tr);

        switch(tipo) {
            case 1:
                g.setColor(Color.RED);
                break;
            case 2:
                g.setColor(Color.GREEN);
                break;
            case 3:
                g.setColor(Color.YELLOW);
                break;
        }
        g.drawString(dmg,x,y);
    }
}
