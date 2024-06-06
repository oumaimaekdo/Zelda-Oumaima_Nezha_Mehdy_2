package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public abstract class Ennemi extends Acteur {

    public Champ champ;

    public Ennemi(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
        this.champ = m;
    }

    public Ennemi(String nom, Champ m) {
        super(nom, m);
    }

    public abstract void deplacer();
    public abstract void attaquer(Acteur cible);

    public void suivreChemin(ArrayList<int[]> chemin) {
        if (chemin == null || chemin.isEmpty()) {
            return;
        }
        // Déplacer vers la première étape du chemin
        int[] pas = chemin.remove(0);
        int nouveauX = pas[0] * 64;
        int nouveauY = pas[1] * 64;
        System.out.println("Déplacement vers (" + nouveauX + ", " + nouveauY + ")");
        this.setX(nouveauX);
        this.setY(nouveauY);
    }

    public abstract Color getColor();
}
