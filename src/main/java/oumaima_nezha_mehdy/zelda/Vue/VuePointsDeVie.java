package oumaima_nezha_mehdy.zelda.Vue;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        barreDeVieLink.setPrefWidth(100);
        //barreDeVieLink.setLayoutX();

        link = champ.getLink();
        ennemi = champ.getListEnnemi().get(0);

        barreDeVieEnnemi = new ProgressBar();
        barreDeVieEnnemi.setPrefWidth(100);



        barreDeVieLink.progressProperty().bind(link.vieProperty().divide(100));
        barreDeVieEnnemi.progressProperty().bind(ennemi.vieProperty().divide(100));

        barreDeVieLink.setLayoutX(600);
        barreDeVieLink.setLayoutY(-450);

        barreDeVieEnnemi.setLayoutX(600 + link.getX() - ennemi.getX());
        barreDeVieEnnemi.setLayoutY(-450 + link.getY() - ennemi.getY());

        ChangeListener<Number> xlistener = (obs, old, nouv) -> {
            barreDeVieEnnemi.setLayoutX(600 + ennemi.getX() - link.getX());
        };
        ChangeListener<Number> ylistener = (obs, old, nouv) -> {
            barreDeVieEnnemi.setLayoutY(-450 + ennemi.getY() - link.getY());
        };

        link.getXProperty().addListener(xlistener);
        link.getYProperty().addListener(ylistener);
        ennemi.getXProperty().addListener(xlistener);
        ennemi.getYProperty().addListener(ylistener);

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
