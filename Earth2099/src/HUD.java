import java.awt.*;
import java.awt.geom.Line2D;

public class HUD {

    //variables estaticas por que nunca deben cambiar de su valor que ya estan
    public static int nivel = 1;
    public static int vida = 100;
    public static String balas;
    public static String nombreArma;
    public static int puntuacion = 0;
    public int x;
    public int y;

    public static Arma arma1;
    public static Arma arma2;

    public static int armaIndex = 1;

    // cuando tenemos 15 muertos, es cuando queremos cambiar de nivel
    public HUD(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Arma getArma1()
    {
        return arma1;
    }
    public static void setIndex(int a)
    {
        armaIndex = a;
    }

    public static Arma getArma2()
    {
        return arma2;
    }

    public static void setArma1(Arma a)
    {
        arma1 = a;
    }
    public static void setArma2(Arma a)
    {
        arma2 = a;
    }
    //aquí dibujamos el grafico que aparece en pantalla
    public void render(Graphics2D g) {


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

        g.setColor(Color.black);
        g.setStroke(new BasicStroke(6));
        if(armaIndex == 0) {
            g.drawRect(x  +22, y + 172, 80, 80);
            g.setColor(Color.WHITE);
            g.drawRect(x  +20, y + 170, 80, 80);
        }else{
            g.drawRect(x + 127, y + 172, 80, 80);
            g.setColor(Color.WHITE);
            g.drawRect(x + 125, y + 170, 80, 80);

        }
        g.drawImage(arma1.getPortada(),x + 25,y + 180,null);
        g.drawImage(arma2.getPortada(),x+ 135,y+ 180,null);


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