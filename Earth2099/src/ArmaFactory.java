import image.Assets;

import java.util.Random;

public class ArmaFactory extends Factory{

    //Referencia al main
    private Game game;
    public ArmaFactory(int tiempo, int[][] map,Game game) {
        super(tiempo, map);
        this.game = game;
    }



    //Crea las instancias
    @Override
    public void crear() {
        //Numero maximo de enemigos 30

        if (countMax >= 10 || !game.getCorriendo())
            return;


        //Posicion de la matriz actual
        Random ran = new Random();
        int x = ran.nextInt(99);
        int y = ran.nextInt(99);

        //esta Bloqueado
        if (map[x][y] == 1)
            return;
        else {
            //Tipo de enemigo basado en porcentaje de 1 al 100
            int tipoPowerUp = ran.nextInt(100) + 1;
            if (tipoPowerUp <= 20) {
                game.getHandler().agregarObjeto(new ArmaMapa(x * 80, y * 80, 80, 80, "Metralleta", Assets.pArmaAD, game.getHandler(), game));
                System.out.println("FACTORY NUEVA ARMA EN X = " + x * 80 + " Y = " + y * 80);
                countMax++;
            }
            if (tipoPowerUp > 20 && tipoPowerUp <= 40) {
                game.getHandler().agregarObjeto(new ArmaMapa(x * 80, y * 80, 80, 80, "Wavy", Assets.pArmaAIwavy, game.getHandler(), game));
                System.out.println("FACTORY NUEVA ARMA EN X = " + x * 80 + " Y = " + y * 80);
                countMax++;
            }
            if (tipoPowerUp > 40 && tipoPowerUp <= 60) {
                game.getHandler().agregarObjeto(new ArmaMapa(x * 80, y * 80, 80, 80, "Laser", Assets.pArmaADLaser, game.getHandler(), game));
                System.out.println("FACTORY NUEVA ARMA EN X = " + x * 80 + " Y = " + y * 80);
                countMax++;
            }
            if (tipoPowerUp > 60 && tipoPowerUp <= 80) {
                game.getHandler().agregarObjeto(new ArmaMapa(x * 80, y * 80, 80, 80, "Escopeta", Assets.pArmaADEsc, game.getHandler(), game));
                System.out.println("FACTORY NUEVA ARMA EN X = " + x * 80 + " Y = " + y * 80);
                countMax++;
            }
            if (tipoPowerUp > 80 && tipoPowerUp <= 100) {
                game.getHandler().agregarObjeto(new ArmaMapa(x * 80, y * 80, 80, 80, "Lanzallamas", Assets.pArmaADFuego, game.getHandler(), game));
                System.out.println("FACTORY NUEVA ARMA EN X = " + x * 80 + " Y = " + y * 80);
                countMax++;
            }
        }

    }
}





