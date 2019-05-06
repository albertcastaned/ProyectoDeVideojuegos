import image.Assets;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ListIterator;
import java.util.Random;

public class BalaFuego extends Bala{
    public BalaFuego(int x, int y, int mousePosX, int mousePosY, float angulo, Handler handler,Game main)
    {

        super(x,y,mousePosX,mousePosY,angulo,handler,main);


        //Obtener velocidad x y velocidad y utilizando la hipotenusa respecto a ellas
        float dirX = mousePosX - x ;
        float dirY = mousePosY - y ;

        float dirLength = (float) Math.sqrt(dirX*dirX + dirY*dirY);
        velX = (int) (15 * dirX/dirLength);
        velY = (int) (15 * dirY/dirLength);

        img = Assets.fire;
        float finalAngulo = (float) Math.toRadians(angulo);
        AffineTransform tx = new AffineTransform();
        tx.rotate(finalAngulo,img.getWidth()/2,img.getHeight()/2);

        AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);

        img = op.filter(img, null);
    }

    public float getAngulo(Point destino) {
        float angulo = (float) Math.toDegrees(Math.atan2(destino.y - 500, destino.x - 500));

        if(angulo < 0)
            angulo += 360;
        return angulo;
    }

    //Metodo actualizar que se llama a seguido
    public void actualizar(){

        //Checar si esta colisionando con alguna entidad
        checarColision();
        x += velX;
        y += velY;

        //Eliminar si esta fuera de la camara
        if(x < -main.getCamaraXOffset() || x > (-main.getCamaraXOffset() + Game.getVentanaAncho()) || y < -main.getCamaraYOffset() || y > (-main.getCamaraYOffset() + Game.getVentanaAltura()))
            handler.quitarObjeto(this);
    }

    public void checarColision() {
        //Obtener Entidades del Handler
        ListIterator<Entidad> iterator = handler.listaEntidades.listIterator();
        while (iterator.hasNext())
        {
            Entidad aux = iterator.next();

            //Colision con un Bloque solido
            if (aux instanceof AguaColision)
            {
                if (chocandoEn(x, y, aux))
                {
                    handler.quitarObjeto(this);
                }


            }
            //Colision con un Enemigo
            if (aux instanceof TemplateEnemy)
            {
                if (chocandoEn(x, y, aux))
                {

                    ((TemplateEnemy) aux).setVida(((TemplateEnemy) aux).getVida() - 10);

                    handler.agregarObjeto(new TextoFlotante(aux.getX(),aux.getY(),30,30,"dmg","-10",1,handler,main));

                    if(((TemplateEnemy) aux).getVida() <= 0)
                    {
                        //Si es esqueleto parar el timer de tirar hueso
                        if(aux instanceof Esqueleto)
                            ((Esqueleto) aux).stopTimer();
                        if(aux instanceof EsqueletoLider)
                            ((EsqueletoLider) aux).stopTimer();
                        Random ran = new Random();
                        int auxNum = ran.nextInt(30)+15;

                        handler.agregarObjeto(new TextoFlotante(x,y,30,30,"dmg","+" + auxNum,3,handler,main));
                        HUD.sumarPuntos(auxNum);
                        handler.quitarObjeto(aux);
                        Game.bajarCountFactoryEnemigo();
                        Game.sumarMuerto();

                    }


                    handler.quitarObjeto(this);
                    break;
                }

            }

        }

    }
}
