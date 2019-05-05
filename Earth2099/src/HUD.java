import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

    //variables estaticas por que nunca deben cambiar de su valor que ya estan
    public static int nivel = 1;
    public static int vida = 100;
    public static int muertos;
    public static String balas;
    public static String nombreArma;
    public static int puntuacion = 0;
    public int x;
    public int y;

    // cuando tenemos 15 muertos, es cuando queremos cambiar de nivel
    public HUD(int x, int y) {
        this.x = x;
        this.y = y;
    }


    //aquí dibujamos el grafico que aparece en pantalla
    public void render(Graphics g) {

        g.setFont(new Font("TimesRoman",Font.BOLD,26));
        g.setColor(new Color(46, 46, 46));
        g.fillRect(x+20, y+20, 610, 60);
        g.setColor(Color.BLACK);
        g.fillRect(x+15, y+15, 610, 60);
        g.setColor(Color.RED);
        g.fillRect(x+20, y+20, 600, 50);
        g.setColor(new Color(61,251, 98));
        g.fillRect(x+20, y+20, vida*6, 50);
        g.setColor(Color.BLACK);

        g.drawString(vida + " / 100",x + 15 + 610/2 - 50 + 2,y + 15 + 60/2 + 10 + 2);
        g.setColor(Color.WHITE);
        g.drawString(vida + " / 100",x + 15 + 610/2 - 50,y + 15 + 60/2 + 10);

        g.setFont(new Font("TimesRoman",Font.BOLD,32));

        g.setColor(Color.BLACK);
        g.drawString(nombreArma + ": " + balas + " Balas ",x + 17 ,y + 122);
        g.setColor(Color.WHITE);
        g.drawString(nombreArma + ": " + balas + " Balas ",x + 15 ,y + 120);

        g.setColor(Color.BLACK);
        g.drawString("Score: " + puntuacion,x + Game.getVentanaAncho() - 320 + 2 ,y + 60 + 2);
        g.setColor(Color.WHITE);
        g.drawString("Score: " + puntuacion,x + Game.getVentanaAncho() - 320,y + 60);


    }

    public static void cambiarBalas(String b)
    {
        balas = b;
    }

    public static void cambiarNombreArma(String b)
    {
        nombreArma = b;
    }
    public static void sumarPuntos(int a)
    {
        puntuacion+=a;
    }
    public static int getNivel() {
        return nivel;
    }

    public static void setNivel(int nivel) {
        HUD.nivel = nivel;
    }

    public static int getVida() {
        return vida;
    }

    public static void setVida(int vida) {
        HUD.vida = vida;
    }

    public static int getMuertos() {
        return muertos;
    }

    public static void setMuertos(int muertos) {
        HUD.muertos = muertos;
    }

    public int getx() {
        return x;
    }

    public void setx(int x) {
        this.x = x;
    }

    public int gety() {
        return y;
    }

    public void sety(int y) {
        this.y = y;
    }


}