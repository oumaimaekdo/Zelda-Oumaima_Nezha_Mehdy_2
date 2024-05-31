package oumaima_nezha_mehdy.zelda.controleur;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

import oumaima_nezha_mehdy.zelda.Univers.*;

public class Controleur implements Initializable {


    @FXML
    private BorderPane fenetre;
    @FXML
    private TilePane map;
    @FXML
    private Pane univers;

    private Champ champ;

    @FXML
    private HBox vueInventaire;

    private int[] sol;

    @FXML
    private Pane vueActeur;
    @FXML
    private Pane vueArmes;

    @FXML
    private VueArmes vueArc;
    private Armes arc;
    @FXML
    private Pane vueArcPane ;

    private VueActLink linkControl;
    private VueArmes armesControl;

    private int tailleTuile;

    private int LongueurInt;

    private int LargeurInt;

    private Clavier clavier;

    @FXML
    private Pane vueArmesInventaire;

    @FXML
    private ImageView vueArbres; //creer plutôt un pain avec les elements traversable


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapInt mapInt = MapPossible.test2;
        this.tailleTuile=64;
        this.sol=mapInt.getCarte();
        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();
        this.champ = new Champ(LongueurInt,LargeurInt,sol);
        this.arc = new Arc();
        this.vueArc = new VueArmes(new Image("file:src/main/resources/images/arc.gif"),arc);
        vueArc.getArmeVue().xProperty().setValue(200);
        vueArc.getArmeVue().yProperty().setValue(100);
        vueArc.getArmeVue().fitWidthProperty().setValue(100);
        vueArc.getArmeVue().fitHeightProperty().setValue(100);
        this.vueArbres.setImage(new Image("file:src/main/resources/images/Bloc/forest.png"));
        vueArbres.setY(250);
        vueArbres.setX(175);


        map.setPrefTileHeight(tailleTuile);
        map.setPrefTileWidth(tailleTuile);
        map.setPrefHeight(LargeurInt*tailleTuile);
        map.setPrefWidth(LongueurInt*tailleTuile);
        CreationMap();
        champ.afficherMap();
        this.linkControl=new VueActLink(vueActeur,champ,tailleTuile,vueArmes,vueInventaire,vueArmesInventaire);
        this.clavier =new Clavier(vueActeur,linkControl,vueInventaire);
        this.champ.getLink().getXProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateX(univers.getPrefWidth()/2-champ.getLink().getX());
        });
        this.champ.getLink().getYProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateY(univers.getPrefHeight()/2-champ.getLink().getY());
        });
        this.univers.setTranslateX(univers.getPrefWidth()/2-champ.getLink().getX());
        this.univers.setTranslateY(univers.getPrefHeight()/2-champ.getLink().getY());


    }

    public Clavier getClavier() {
        return clavier;
    }

    public void CreationMap() {
        int[] carte = this.sol;
        Image eau = new Image("file:src/main/resources/images/Bloc/Eau.jpg");
        Image terre = new Image("file:src/main/resources/images/Bloc/Herbe.jpg");
        Image arbre = new Image("file:src/main/resources/images/Bloc/arbre.png");
        Image maison = new Image("file:src/main/resources/images/Bloc/maison.png");
        Image pont = new Image("file:src/main/resources/images/pont.png");


        for (int i = 0; i < carte.length; i++) {
            ImageView imageView = new ImageView();
            map.getChildren().add(imageView);
            imageView.setId(String.valueOf(i));
            imageView.setFitHeight(tailleTuile);
            imageView.setFitWidth(tailleTuile);
            double col = i % LargeurInt;
            double lig = Math.floor(i/LongueurInt);
            double x = col * LargeurInt;
            double y = lig * LongueurInt;
            imageView.setX(x);
            imageView.setY(y);
            switch (carte[i]) {
                case 0:
                    imageView.setImage(terre);
                    break;
                case 1:
                    imageView.setImage(arbre);
                    break;
                case 2:
                    imageView.setImage(eau);
                    break;
                case 3:
                    imageView.setImage(maison);
                    break;
                case 4:
                    imageView.setImage(pont);
                    break;

            }
        }
    }

    public void mouseclicked(MouseEvent mouseEvent) {
        univers.requestFocus();
    }

    public void keyPressed(KeyEvent keyEvent) {
        clavier.handle(keyEvent);
        System.out.println(vueInventaire.lookup("#case1").getId());
    }
}