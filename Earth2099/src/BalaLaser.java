import image.Assets;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ListIterator;
import java.util.Random;

public class BalaLaser extends Bala{
    public BalaLaser(int x, int y, int mousePosX, int mousePosY, float angulo,Handler handler,Game main) {
        super(x, y, mousePosX, mousePosY, angulo, handler,main);
        img = Assets.laser;
        float finalAngulo = (float) Math.toRadians(angulo);
        AffineTransform tx = new AffineTransform();
        tx.rotate(finalAngulo,img.getWidth()/2,img.getHeight()/2);

        AffineTransformOp op = new AffineTransformOp(tx,AffineTransformOp.TYPE_BILINEAR);

        img = op.filter(img, null);
    }
    @Override
    public void checarColision() {
        //Obtener Entidades del Handler
        ListIterator<Entidad> iterator = handler.listaEntidades.listIterator();
        while (iterator.hasNext())
        {
            Entidad aux = iterator.next();

            //Colision con un Enemigo
            if (aux instanceof TemplateEnemy)
            {
                if (chocandoEn(x, y, aux))
                {

                    ((TemplateEnemy) aux).setVida(((TemplateEnemy) aux).getVida() - 10);
                    ((TemplateEnemy) aux).setKnockbackX(velX / 2);
                    ((TemplateEnemy) aux).setKnockbackY(velY / 2);
                    ((TemplateEnemy) aux).activateTimer();
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
                }

            }

        }

    }
}
