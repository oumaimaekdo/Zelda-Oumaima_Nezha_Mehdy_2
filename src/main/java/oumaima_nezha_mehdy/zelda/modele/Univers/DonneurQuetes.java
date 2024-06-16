package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class DonneurQuetes extends Acteur{
    private Timeline verifTimeline;

    public DonneurQuetes(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
        verificationLink();
    }
    public boolean linkAutour() {
        Acteur link = getChamp().getLink();
        boolean present = false;
        int rayon = 30;
            if ((this.getY() - rayon <= link.getY() && link.getY() <= this.getY() + rayon) && (this.getX() - rayon <= link.getX() && link.getX() <= this.getX() + rayon)){
                present = true;
            }
        System.out.println("Link est present");
        return present;
    }

    private void verificationLink() {
        verifTimeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> linkAutour()));
        verifTimeline.setCycleCount(Timeline.INDEFINITE);
        verifTimeline.play();
    }
}
