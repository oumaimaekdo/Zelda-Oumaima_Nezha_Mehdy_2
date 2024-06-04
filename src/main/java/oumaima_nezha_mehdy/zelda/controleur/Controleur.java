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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapInt mapInt = MapPossible.test3;
        this.sol=mapInt.getCarte();
        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();
        this.champ = new Champ(LongueurInt,LargeurInt,sol);
        this.tailleTuile=champ.gettT();
        this.arc = new Arc();

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
        Image herbeDroit = new Image("file:src/main/resources/images/MapDebut/13.png");
        Image herbe = new Image("file:src/main/resources/images/MapDebut/herbe2.png");
        Image herbeBas = new Image("file:src/main/resources/images/MapDebut/23.png");
        Image herbeGauche = new Image("file:src/main/resources/images/MapDebut/11.png");
        Image herbeHaut = new Image("file:src/main/resources/images/MapDebut/1.png");
        Image terre = new Image("file:src/main/resources/images/MapDebut/54.png");
        Image coin24 = new Image("file:src/main/resources/images/MapDebut/24.png");
        Image coin22 = new Image("file:src/main/resources/images/MapDebut/22.png");
        Image coin17 = new Image("file:src/main/resources/images/MapDebut/17.png");
        Image coin28 = new Image("file:src/main/resources/images/MapDebut/28.png");
        Image coin0 = new Image("file:src/main/resources/images/MapDebut/0.png");
        Image coin16 = new Image("file:src/main/resources/images/MapDebut/16.png");
        Image coin2 = new Image("file:src/main/resources/images/MapDebut/2.png");
        Image coin27 = new Image("file:src/main/resources/images/MapDebut/27.png");
        Image buisson33 = new Image("file:src/main/resources/images/MapDebut/33.png");
        Image buisson34 = new Image("file:src/main/resources/images/MapDebut/34.png");
        Image buisson35 = new Image("file:src/main/resources/images/MapDebut/35.png");
        Image buisson36 = new Image("file:src/main/resources/images/MapDebut/36.png");
        Image maison5 = new Image("file:src/main/resources/images/MapDebut/5.png");
        Image maison6 = new Image("file:src/main/resources/images/MapDebut/6.png");
        Image maison7 = new Image("file:src/main/resources/images/MapDebut/7.png");
        Image maison8 = new Image("file:src/main/resources/images/MapDebut/8.png");
        Image maison9 = new Image("file:src/main/resources/images/MapDebut/9.png");
        Image maison10 = new Image("file:src/main/resources/images/MapDebut/10.png");
        Image maison14 = new Image("file:src/main/resources/images/MapDebut/14.png");
        Image maison15 = new Image("file:src/main/resources/images/MapDebut/15.png");

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
                case 12:
                    imageView.setImage(herbe);
                    break;
                case 54:
                    imageView.setImage(terre);
                    break;
                case 13:
                    imageView.setImage(herbeDroit);
                    break;
                case 11:
                    imageView.setImage(herbeGauche);
                    break;
                case 23:
                    imageView.setImage(herbeBas);
                    break;
                case 1:
                    imageView.setImage(herbeHaut);
                    break;
                case 24:
                    imageView.setImage(coin24);
                    break;
                case 22:
                    imageView.setImage(coin22);
                    break;
                case 17:
                    imageView.setImage(coin17);
                    break;
                case 28:
                    imageView.setImage(coin28);
                    break;
                case 0:
                    imageView.setImage(coin0);
                    break;
                case 16:
                    imageView.setImage(coin16);
                    break;
                case 2:
                    imageView.setImage(coin2);
                    break;
                case 27:
                    imageView.setImage(coin27);
                    break;
                case 33:
                    imageView.setImage(buisson33);
                    break;
                case 34:
                    imageView.setImage(buisson34);
                    break;
                case 35:
                    imageView.setImage(buisson35);
                    break;
                case 36:
                    imageView.setImage(buisson36);
                    break;
                case 5:
                    imageView.setImage(maison5);
                    break;
                case 6:
                    imageView.setImage(maison6);
                    break;
                case 7:
                    imageView.setImage(maison7);
                    break;
                case 8:
                    imageView.setImage(maison8);
                    break;
                case 9:
                    imageView.setImage(maison9);
                    break;
                case 10:
                    imageView.setImage(maison10);
                    break;
                case 14:
                    imageView.setImage(maison14);
                    break;
                case 15:
                    imageView.setImage(maison15);
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