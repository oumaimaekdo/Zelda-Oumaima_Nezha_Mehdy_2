package oumaima_nezha_mehdy.zelda.Vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.Ennemi;
import oumaima_nezha_mehdy.zelda.modele.Univers.Sbir;

import java.util.HashMap;
import java.util.Map;

public class VueSbir {

    private Pane vuePointsDeVie;
    @FXML
    private Pane vueSbir;
    private Champ champ;
    private int tailleTuile;
    private Map<Ennemi, ImageView> ennemiImageViewMap;
    public ProgressBar barreDeVieEnnemi;

    public VueSbir(Pane pane, Champ c, int tailleTuile, Pane vuePointsDeVie) {
        vueSbir = pane;
        this.vuePointsDeVie = vuePointsDeVie;
        this.champ = c;
        this.tailleTuile = tailleTuile;
        this.ennemiImageViewMap = new HashMap<>();
        this.barreDeVieEnnemi = new ProgressBar();
        this.barreDeVieEnnemi.setPrefWidth(70);
        this.barreDeVieEnnemi.setPrefHeight(15);
        this.barreDeVieEnnemi.setStyle("-fx-accent: red;");
        creerBarreDeVie(champ.getSbir());

        for (Ennemi e : champ.getListEnnemi()) {
            creerSbir("file:src/main/resources/images/sbire-simple.png", e);
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> supprimerSbir(e)));
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        }
    }

    public void creerSbir(String path, Ennemi s) {
        ImageView r = new ImageView();
        Image image = new Image(path);
        r.setImage(image);
        r.setFitWidth(35);
        r.setFitHeight(35);
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
                vueSbir.getChildren().remove(barreDeVieEnnemi);
                this.ennemiImageViewMap.remove(ennemi);
            }
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
        vueSbir.getChildren().add(barreDeVieEnnemi);
    }


}
