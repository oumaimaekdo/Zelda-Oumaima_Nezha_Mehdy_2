package oumaima_nezha_mehdy.zelda.Vue.Acteurs;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs.Ennemi;

import java.util.HashMap;
import java.util.Map;

public class VueBoss{

    private Pane vuePointsDeVie;
    @FXML
    private Pane vueBoss;
    private Champ champ;
    private int tailleTuile;
    private Map<Ennemi, ImageView> ennemiImageViewMap;
    public ProgressBar barreDeVieEnnemi;


    public VueBoss(Pane pane, Champ c, int tailleTuile, Pane vuePointsDeVie) {
        vueBoss = pane;
        this.champ = c;
        this.tailleTuile = tailleTuile;
        this.vuePointsDeVie =vuePointsDeVie;
        this.ennemiImageViewMap = new HashMap<>();
        this.barreDeVieEnnemi = new ProgressBar();
        this.barreDeVieEnnemi.setPrefWidth(70);
        this.barreDeVieEnnemi.setPrefHeight(15);
        this.barreDeVieEnnemi.setStyle("-fx-accent: red;");
        creerBarreDeVie(champ.getBoss());

        creerBoss("file:src/main/resources/images/volcanorax-attaque.png", champ.getBoss());
        for (Ennemi e : champ.getListEnnemi()) {

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> supprimerBoss(e)));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public void creerBarreDeVie(Acteur a){

        NumberBinding progressBinding = Bindings.createDoubleBinding(
                () -> a.vieProperty().get()/ (double) 100,a.vieProperty(),a.maxVieProperty()
        );
        barreDeVieEnnemi.progressProperty().bind(progressBinding);
        barreDeVieEnnemi.translateXProperty().bind(a.getXProperty().subtract(20));
        barreDeVieEnnemi.translateYProperty().bind(a.getYProperty().subtract(20));
        barreDeVieEnnemi.setId(a.getNom());
        barreDeVie();

    }

    public void barreDeVie(){
        vueBoss.getChildren().add(barreDeVieEnnemi);
        //barreDeVieEnnemi.translateXProperty().setValue(0);
        //barreDeVieEnnemi.translateYProperty().setValue(0);
    }

    public void creerBoss(String path, Ennemi s) {
        ImageView r = new ImageView();
        Image image = new Image(path);
        r.setImage(image);
        r.setFitWidth(30);
        r.setFitHeight(30);
        vueBoss.getChildren().add(r);
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

    public void supprimerBoss(Ennemi ennemi) {
        if (ennemi.estmort()) {
            ImageView imageView = this.ennemiImageViewMap.get(ennemi);
            if (imageView != null) {
                vueBoss.getChildren().remove(imageView);
                this.ennemiImageViewMap.remove(ennemi);
            }
        }
    }
}
