import image.Assets;

import java.util.Random;

public class Lanzallamas extends Arma {

    public Lanzallamas(String s, int d, int n, int m, int t, Game main) {
        super(s, d, n, m, t, main);
        armaAbajo = Assets.pArmaAbajoFuego;
        armaArriba = Assets.pArmaArribaFuego;
        armaDerecha = Assets.pArmaDerechaFuego;
        armaIzquierda = Assets.pArmaIzquierdaFuego;
        armaDerechaAbajo = Assets.pArmaADFuego;
        armaDerechaArriba = Assets.pArmaArDFuego;
        armaIzquierdaAbajo = Assets.pArmaAIFuego;
        armaIzquierdaArriba = Assets.pArmaArIFuego;
        actual = armaAbajo;
    }
    @Override
    public void disparar(int x, int y, int mx, int my,float angulo) {

        //Si ha pasado el tiempo para poder disparar y tiene balas
        if(numBalas > 0 && getPuedeDisparar())
        {

            //Empezar timer
            puedeDisparar = false;
            timer.start();

            //Agregar al handler
            Random ran = new Random();
            handler.agregarObjeto(new BalaFuego(x,y,mx + ran.nextInt(80) + 30,my + ran.nextInt(80) + 30,angulo,handler,main));
            handler.agregarObjeto(new BalaFuego(x,y,mx - ran.nextInt(80) + 30,my - ran.nextInt(80) + 30,angulo,handler,main));
            handler.agregarObjeto(new BalaFuego(x,y,mx + ran.nextInt(90) + 50,my + ran.nextInt(90) + 60,angulo,handler,main));
            handler.agregarObjeto(new BalaFuego(x,y,mx - ran.nextInt(90) + 50,my - ran.nextInt(90) + 60,angulo,handler,main));
            handler.agregarObjeto(new BalaFuego(x,y,mx + ran.nextInt(100) + 60,my + ran.nextInt(100) + 60,angulo,handler,main));
            handler.agregarObjeto(new BalaFuego(x,y,mx - ran.nextInt(100) + 60,my - ran.nextInt(100) + 60,angulo,handler,main));
            handler.agregarObjeto(new BalaFuego(x,y,mx,my,angulo,handler,main));

            //Quitar una bala al arma
            numBalas-=7;
            if(numBalas <0)
                numBalas =0;


        }
    }
}

