package oumaima_nezha_mehdy.zelda.Vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.Ennemi;

import java.util.HashMap;
import java.util.Map;

public class VueSbir {

    @FXML
    private Pane vueSbir;
    private Champ champ;
    private int tailleTuile;
    private Map<Ennemi, ImageView> ennemiImageViewMap;

    public VueSbir(Pane pane, Champ c, int tailleTuile) {
        vueSbir = pane;
        this.champ = c;
        this.tailleTuile = tailleTuile;
        this.ennemiImageViewMap = new HashMap<>();
        for (Ennemi e : champ.getListEnnemi()) {
            creerSbir("file:src/main/resources/images/squeletteMarcheEst.gif", e);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> supprimerSbir(e)));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public void creerSbir(String path, Ennemi s) {
        ImageView r = new ImageView();
        Image image = new Image(path);
        r.setImage(image);
        r.setFitWidth(30);
        r.setFitHeight(30);
        vueSbir.getChildren().add(r);
        r.setId(s.getId());
        r.setTranslateX(s.getX());
        r.setTranslateY(s.getY());
        r.translateXProperty().bind(s.getXProperty());
        r.translateYProperty().bind(s.getYProperty());
        this.ennemiImageViewMap.put(s, r);  // Associez l'ennemi à son ImageView
    }

    public void updateChamp(Champ champ) {
        this.champ = champ;
    }

    public void supprimerSbir(Ennemi ennemi) {
        if (ennemi.estmort()) {
            ImageView imageView = this.ennemiImageViewMap.get(ennemi);
            if (imageView != null) {
                vueSbir.getChildren().remove(imageView);
                this.ennemiImageViewMap.remove(ennemi);
            }
        }
    }
}
