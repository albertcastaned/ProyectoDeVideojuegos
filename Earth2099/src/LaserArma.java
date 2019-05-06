import image.Assets;

public class LaserArma extends Arma{

    public LaserArma(String s, int d, int n, int m, int t,Game main)
    {
        super(s, d, n, m, t,main);
        armaAbajo = Assets.pArmaAbajoLaser;
        armaArriba = Assets.pArmaArribaLaser;
        armaDerecha = Assets.pArmaDerechaLaser;
        armaIzquierda = Assets.pArmaIzquierdaLaser;
        armaDerechaAbajo = Assets.pArmaADLaser;
        armaDerechaArriba = Assets.pArmaArDLaser;
        armaIzquierdaAbajo = Assets.pArmaAILaser;
        armaIzquierdaArriba = Assets.pArmaArILaser;
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
            Bala bal = new BalaLaser(x,y,mx,my,angulo,handler,main);

            //Agregar al handler
            handler.agregarObjeto(bal);

            //Quitar una bala al arma
            numBalas--;

        }
    }

}
