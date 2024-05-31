package oumaima_nezha_mehdy.zelda.Univers;

import java.util.Random;

public class Sbire extends Ennemi{

    Random random = new Random();
    public Sbire(String nom, int x, int y, Champ m){
        super(nom,x,y,m);
    }

    public Sbire(String nom, Champ m){
        super(nom,m);
    }


    public void deplacementAleatoire() {

        Random rand = new Random();
        int direction = rand.nextInt(4); // Génère un nombre aléatoire entre 0 et 3 inclus
        switch (direction) {
            case 0:
                seDeplacer("nord");
                break;
            case 1:
                seDeplacer("sud");
                break;
            case 2:
                seDeplacer("ouest");
                break;
            case 3:
                seDeplacer("est");
                break;
        }
    }
}
