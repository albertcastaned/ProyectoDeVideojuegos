import image.Assets;

public class WavyArma extends Arma {
    public WavyArma(String s, int d, int n, int m, int t, Handler handler,Game main) {
        super(s, d, n, m, t, handler,main);
        armaAbajo = Assets.pArmaAbajowavy;
        armaArriba = Assets.pArmaArribawavy;
        armaDerecha = Assets.pArmaDerechawavy;
        armaIzquierda = Assets.pArmaIzquierdawavy;
        armaDerechaAbajo = Assets.pArmaADwavy;
        armaDerechaArriba = Assets.pArmaArDwavy;
        armaIzquierdaAbajo = Assets.pArmaAIwavy;
        armaIzquierdaArriba = Assets.pArmaArIwavy;
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

            //Crear tipo de bala de esta arma
            Bala bal = new BalaOla(x,y,mx,my,angulo,handler,main);

            //Agregar al handler
            handler.agregarObjeto(bal);

            //Quitar una bala al arma
            numBalas--;


        }
    }


}


