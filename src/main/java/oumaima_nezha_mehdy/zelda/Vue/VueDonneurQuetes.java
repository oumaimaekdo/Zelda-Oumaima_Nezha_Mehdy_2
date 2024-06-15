package oumaima_nezha_mehdy.zelda.Vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.DonneurQuetes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Ennemi;

import java.util.ArrayList;

public class VueDonneurQuetes {

    @FXML
    private Pane vueDonneurQuetes;
    private DonneurQuetes Arthur;
    private DonneurQuetes Vanessa;
    private Champ champ;
    private int tailleTuile;

    @FXML
    private ImageView DonneurQuetes;

    public VueDonneurQuetes(Pane pane, Champ c, int tailleTuile) {
        vueDonneurQuetes = pane;
        this.champ = c;
        this.Arthur = champ.getArthur();
        this.Vanessa = champ.getVanessa();
        this.tailleTuile = tailleTuile;
        creerDonneurQuetes("file:src/main/resources/images/Personnages/-1.png", Arthur);
        creerDonneurQuetes("file:src/main/resources/images/Personnages/-2.png",Vanessa);
        /*Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> supprimerSbir(champ.getListEnnemi())));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/
    }

    public void creerDonneurQuetes(String path, DonneurQuetes s) {
        ImageView r = new ImageView();
        Image image = new Image(path);
        r.setImage(image);
        r.setFitWidth(30);
        r.setFitHeight(30);
        vueDonneurQuetes.getChildren().add(r);
        r.setId(s.getId());
        r.setTranslateX(s.getX());
        r.setTranslateY(s.getY());
        r.translateXProperty().bind(s.getXProperty());
        r.translateYProperty().bind(s.getYProperty());
        this.DonneurQuetes = r;
    }

    public DonneurQuetes getDonneurQuetes() {
        return this.Arthur;
    }

    public void updateChamp(Champ champ) {
        this.champ = champ;
    }

    /*public void supprimerSbir(ArrayList<Ennemi> ennemi){
        for(Ennemi e : ennemi){
            if(e.estmort()){
                vueSbir.getChildren().remove(vueSbir1);
            }
        }

    }*/
}
