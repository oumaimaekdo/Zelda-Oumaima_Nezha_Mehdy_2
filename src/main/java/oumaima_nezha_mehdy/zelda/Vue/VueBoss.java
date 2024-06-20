package oumaima_nezha_mehdy.zelda.Vue;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.Ennemi;

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

    public VueBoss(Pane pane, Champ c, int tailleTuile) {
        vueBoss = pane;
        this.champ = c;
        this.tailleTuile = tailleTuile;
        this.ennemiImageViewMap = new HashMap<>();
        this.barreDeVieEnnemi = new ProgressBar();
        this.barreDeVieEnnemi.setPrefWidth(70);
        this.barreDeVieEnnemi.setStyle("-fx-accent: red;");

        barreDeVieEnnemi.progressProperty().bind(champ.getSbir().vieProperty().divide(100));

        barreDeVieEnnemi.setLayoutX(600 + champ.getLink().getX() - champ.getSbir().getX());
        barreDeVieEnnemi.setLayoutY(-450 + champ.getLink().getY() - champ.getLink().getY());

        ChangeListener<Number> xlistener = (obs, old, nouv) -> {
            barreDeVieEnnemi.setLayoutX(600 + champ.getSbir().getX() - champ.getLink().getX());
        };
        ChangeListener<Number> ylistener = (obs, old, nouv) -> {
            barreDeVieEnnemi.setLayoutY(-450 + champ.getSbir().getY() - champ.getLink().getY());
        };

        champ.getSbir().getXProperty().addListener(xlistener);
        champ.getSbir().getYProperty().addListener(ylistener);

        vuePointsDeVie.getChildren().add(barreDeVieEnnemi);


        for (Ennemi e : champ.getListEnnemi()) {
            creerSbir("file:src/main/resources/images/volcanorax-attaque.png", e);
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

    public void supprimerSbir(Ennemi ennemi) {
        if (ennemi.estmort()) {
            ImageView imageView = this.ennemiImageViewMap.get(ennemi);
            if (imageView != null) {
                vueBoss.getChildren().remove(imageView);
                this.ennemiImageViewMap.remove(ennemi);
            }
        }
    }
}
