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

public class VueSbir {

    @FXML
    private Pane vueSbir;
    private Ennemi sbir1;
    private Champ champ;
    private int tailleTuile;

    @FXML
    private ImageView vueSbir1;
    private Image SbirNord;
    private Image SbirSud;
    private Image SbirEst;
    private Image SbirOuest;

    public VueSbir(Pane pane, Champ c, int tailleTuile) {
        vueSbir = pane;
        this.champ = c;
        this.sbir1 = champ.getSbir();
        this.tailleTuile = tailleTuile;
        creerSbir("file:src/main/resources/images/squeletteMarcheEst.gif", sbir1);
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> supprimerSbir(sbir1)));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
        this.vueSbir1 = r;
    }

    public Ennemi getSbir() {
        return this.sbir1;
    }

    public void updateChamp(Champ champ) {
        this.champ = champ;
    }

    public void supprimerSbir(Ennemi s){
        if(s.estmort()){
            vueSbir.getChildren().remove(vueSbir1);
        }
    }

}
