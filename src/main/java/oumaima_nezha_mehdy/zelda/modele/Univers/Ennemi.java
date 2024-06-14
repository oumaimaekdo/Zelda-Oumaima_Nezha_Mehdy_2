package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Ennemi extends Acteur {
    private Timeline mouvementTimeline;

    public Ennemi(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
        initialiserMouvement();
    }

    private void initialiserMouvement() {
        mouvementTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> seDirigerVersLink()));
        mouvementTimeline.setCycleCount(Timeline.INDEFINITE);
        mouvementTimeline.play();
    }

    private void seDeplacerAleatoirement() {
        int chanceDeDeplacement = (int) (Math.random() * 100) + 1;
        int chanceDeNouvelleDirection = (int) (Math.random() * 100) + 1;
        int directionAleatoire;
        String nouvelleDirection = "";

        if (chanceDeDeplacement <= 75) { //75% de chance de se déplacer
            if (chanceDeNouvelleDirection <= 75) { //75% de chance de changer de direction
                directionAleatoire = (int) (Math.random() * 4) + 1;
                nouvelleDirection = switch (directionAleatoire) {
                    case 1 -> "nord";
                    case 2 -> "est";
                    case 3 -> "sud";
                    case 4 -> "ouest";
                    default -> "null";
                };
            }
            this.seDeplacer(nouvelleDirection);
        }
    }

    private void seDirigerVersLink() {
        Acteur link = this.getChamp().getLink();
        if (link == null) {
            return;
        }

        int linkX = link.getX();
        int linkY = link.getY();
        int deltaX = linkX - this.getX();
        int deltaY = linkY - this.getY();

        String direction = "";
        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (deltaX > 0) {
                direction = "est";
            } else {
                direction = "ouest";
            }
        } else {
            if (deltaY > 0) {
                direction = "sud";
            } else {
                direction = "nord";
            }
        }

        this.seDeplacer(direction);
    }
}
