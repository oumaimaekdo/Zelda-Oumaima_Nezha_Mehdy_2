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

import oumaima_nezha_mehdy.zelda.Vue.VueBlocImpassable;
import oumaima_nezha_mehdy.zelda.Vue.VueSbir;
import oumaima_nezha_mehdy.zelda.modele.Armes.Arc;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;
import oumaima_nezha_mehdy.zelda.Vue.VueActLink;
import oumaima_nezha_mehdy.zelda.Vue.VueArmes;

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
    private Pane vueSbir;

    @FXML
    private VueArmes vueArc;
    private Armes arc;
    @FXML
    private Pane vueArcPane ;

    private VueActLink linkControl;
    private VueSbir sbirControl;

    private VueArmes armesControl;

    private int tailleTuile;

    private int LongueurInt;

    private int LargeurInt;

    private Clavier clavier;

    @FXML
    private Pane vueArmesInventaire;

    @FXML
    private Pane elementsImpassable; //creer plutôt un pain avec les elements traversable

    private VueBlocImpassable test;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapInt mapInt = MapPossible.test2;
        this.sol=mapInt.getCarte();
        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();
        this.champ = new Champ(LongueurInt,LargeurInt,sol);
        this.tailleTuile=champ.gettT();
        this.arc = new Arc();
        this.test = new VueBlocImpassable(elementsImpassable,champ,tailleTuile,250,175);



        map.setPrefTileHeight(tailleTuile);
        map.setPrefTileWidth(tailleTuile);
        map.setPrefHeight(LargeurInt*tailleTuile);
        map.setPrefWidth(LongueurInt*tailleTuile);
        CreationMap();
        this.linkControl=new VueActLink(vueActeur,champ,tailleTuile,vueArmes,vueInventaire,vueArmesInventaire);
        this.sbirControl = new VueSbir(vueSbir,champ,tailleTuile);
        sbirControl.getSbir1().deplacementAleatoireX();
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