package oumaima_nezha_mehdy.zelda.Univers;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;

public class Volcanorax extends Ennemi {

    private Acteur link;
    private DeplacementBFS deplacementBFS;
    private int[] anciennePositionLink;
    private ArrayList<int[]> cheminActuel;

    public Volcanorax(int x, int y, Champ m, Acteur link) {
        super("Volcanorax", x, y, m);
        this.link = link;
        this.deplacementBFS = new DeplacementBFS();
    }

    public Volcanorax(Champ m, Acteur link) {
        super("Volcanorax", m);
        this.link = link;
        this.deplacementBFS = new DeplacementBFS();
    }

    @Override
    public void deplacer() {

        int[] nouvellePositionLink = {link.getX() / 64, link.getY() / 64};

        // Recalculer le chemin seulement si Link a bougé
        if (anciennePositionLink == null || !Arrays.equals(anciennePositionLink, nouvellePositionLink)) {
            System.out.println("Link a bougé vers (" + nouvellePositionLink[0] + ", " + nouvellePositionLink[1] + ")");
            cheminActuel = deplacementBFS.deplacementBFS(nouvellePositionLink[0], nouvellePositionLink[1], super.champ, this);
            anciennePositionLink = nouvellePositionLink;
        }

        // Suivre le chemin actuel s'il est valide
        if (cheminActuel != null && !cheminActuel.isEmpty()) {
            suivreChemin(cheminActuel);
        } else {
            System.out.println("Aucun chemin trouvé pour Volcanorax.");
        }
    }

    @Override
    public void attaquer(Acteur cible) {

    }

    @Override
    public Color getColor() {
        return null;
    }
}
