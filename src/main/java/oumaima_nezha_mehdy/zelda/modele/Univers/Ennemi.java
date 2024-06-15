package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Ennemi extends Acteur {
    private Timeline mouvementTimeline;

    public Ennemi(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
        initialiserMouvement();
        setVie(1000);
    }

    private void initialiserMouvement() {
        mouvementTimeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> seDirigerVersLink()));
        mouvementTimeline.setCycleCount(Timeline.INDEFINITE);
        mouvementTimeline.play();
    }

    public void seDirigerVersLink() {
        Acteur link = this.getChamp().getLink();
        if (link == null) {
            return;
        }

        int linkX = link.getX();
        int linkY = link.getY();
        int deltaX = linkX - this.getX();
        int deltaY = linkY - this.getY();

        String direction = "";
        if(!estmort()){
            if (!estEnCollisionAvec(getChamp().getLink())) {
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
            } else {
                direction = "ouest";
            }
        }else{
            getChamp().mortActeur(this);
        }

    }

    public boolean estmort(){
        return getVie()<=0;
    }

}
