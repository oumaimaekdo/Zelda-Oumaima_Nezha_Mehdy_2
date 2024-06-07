package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Bloc;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

public class VueBlocImpassable extends Bloc {

    @FXML
    private ImageView vueArbres;

    @FXML
    private final Pane elementsImpassable;
    private int tT;
    private Champ champ;

    public VueBlocImpassable(Pane pane, Champ c, int tailleTuile,double x, double y) {
        super(false, x, y);
        this.elementsImpassable = pane;
        creerForet("file:src/main/resources/images/Bloc/forest.png");
        this.tT = tailleTuile;
        this.champ = c;

    }

    public void creerForet(String path){
        ImageView r = new ImageView();
        Image Image = new Image(path);
        r.setImage(Image);
        r.setFitWidth(1000);
        r.setFitHeight(1000);
        elementsImpassable.getChildren().add(r);
        r.setY(250);
        r.setX(175);
        this.vueArbres=r;
    }




}
