package oumaima_nezha_mehdy.zelda.Vue;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.Ennemi;

public class VuePointsDeVie {

    private Pane vuePointsDeVie;  // Le conteneur principal pour les éléments de la vue des points de vie
    public ProgressBar barreDeVieLink;  // Accessible publiquement pour que le contrôleur puisse les lier
    public ProgressBar barreDeVieEnnemi;  // Accessible publiquement pour que le contrôleur puisse les lier
    private Champ champ;
    private IntegerProperty xProperty, yProperty;
    private Acteur link;
    private Ennemi ennemi;


    public VuePointsDeVie(Pane vuePointsDeVie, Champ c) {
        this.vuePointsDeVie = vuePointsDeVie;
        this.champ = c;
        this.xProperty =new SimpleIntegerProperty();
        this.yProperty =new SimpleIntegerProperty();
        initialiserVue();
        //vuePointsDeVie.setX
    }

    private void initialiserVue() {
        barreDeVieLink = new ProgressBar();
        barreDeVieLink.setPrefWidth(200);
        //barreDeVieLink.setLayoutX();

        link = champ.getLink();
        ennemi = champ.getListEnnemi().get(0);

        barreDeVieEnnemi = new ProgressBar();
        barreDeVieEnnemi.setPrefWidth(200);

        barreDeVieLink.progressProperty().bind(link.vieProperty(3));
        barreDeVieEnnemi.progressProperty().bind(ennemi.vieProperty(3));

        barreDeVieLink.setLayoutX(link.getX());
        barreDeVieLink.setLayoutY(link.getY()+2);

        barreDeVieEnnemi.setLayoutX(ennemi.getX());
        barreDeVieEnnemi.setLayoutY(ennemi.getY()+2);

        vuePointsDeVie.getChildren().addAll(barreDeVieLink, barreDeVieEnnemi);

        //((HBox) vuePointsDeVie).getChildren().addAll(barreDeVieLink, barreDeVieEnnemi);
    }

    public IntegerProperty getXProperty(){return xProperty;}
    public IntegerProperty getYProperty(){return yProperty;}

    public void setX(int x){this.xProperty.setValue(x);}
    public void setY(int y){this.yProperty.setValue(y);}

    public Pane getVuePointsDeVie() {
        return vuePointsDeVie;
    }
}
