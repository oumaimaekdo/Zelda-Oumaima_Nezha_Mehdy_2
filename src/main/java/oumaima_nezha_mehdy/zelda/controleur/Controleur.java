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
    private TilePane map1;
    @FXML
    private TilePane map2;
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
        MapInt mapInt2 = MapPossible.test2;
        this.sol=mapInt.getCarte();
        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();
        this.champ = new Champ(LongueurInt,LargeurInt,sol);
        this.tailleTuile=champ.gettT();
        this.arc = new Arc();
        map1.setPrefTileHeight(tailleTuile);
        map1.setPrefTileWidth(tailleTuile);
        map1.setPrefHeight(LargeurInt*tailleTuile);
        map1.setPrefWidth(LongueurInt*tailleTuile);
        map2.setPrefTileHeight(tailleTuile);
        map2.setPrefTileWidth(tailleTuile);
        map2.setPrefHeight(LargeurInt*tailleTuile);
        map2.setPrefWidth(LongueurInt*tailleTuile);
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
        Image maison55 = new Image("file:src/main/resources/images/MapDebut/55.png");
        Image maison56 = new Image("file:src/main/resources/images/MapDebut/56.png");
        Image maison57 = new Image("file:src/main/resources/images/MapDebut/57.png");
        Image maison58 = new Image("file:src/main/resources/images/MapDebut/58.png");
        Image maison59 = new Image("file:src/main/resources/images/MapDebut/59.png");
        Image maison60 = new Image("file:src/main/resources/images/MapDebut/60.png");
        Image maison61 = new Image("file:src/main/resources/images/MapDebut/61.png");
        Image maison62 = new Image("file:src/main/resources/images/MapDebut/62.png");
        Image maison63 = new Image("file:src/main/resources/images/MapDebut/63.png");
        Image maison64 = new Image("file:src/main/resources/images/MapDebut/64.png");
        Image maison65 = new Image("file:src/main/resources/images/MapDebut/65.png");
        Image maison66 = new Image("file:src/main/resources/images/MapDebut/66.png");
        Image tronc67 = new Image("file:src/main/resources/images/MapDebut/67.png");
        Image arbrecote68 = new Image("file:src/main/resources/images/MapDebut/68.png");




        for (int i = 0; i < carte.length; i++) {
            ImageView imageView = new ImageView();
            ImageView imageView2 = new ImageView();

            map1.getChildren().add(imageView);
            map2.getChildren().add(imageView2);
            imageView.setId(String.valueOf(i));
            imageView.setFitHeight(tailleTuile);
            imageView.setFitWidth(tailleTuile);
            imageView2.setId(String.valueOf(i));
            imageView2.setFitHeight(tailleTuile);
            imageView2.setFitWidth(tailleTuile);
            double col = i % LargeurInt;
            double lig = Math.floor(i/LongueurInt);
            double x = col * LargeurInt;
            double y = lig * LongueurInt;
            imageView.setX(x);
            imageView.setY(y);
            imageView2.setX(x);
            imageView2.setY(y);
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
                case 55:
                    imageView.setImage(maison55);
                    break;
                case 56:
                    imageView.setImage(maison56);
                    break;
                case 57:
                    imageView.setImage(maison57);
                    break;
                case 58:
                    imageView.setImage(maison58);
                    break;
                case 59:
                    imageView2.setImage(maison59);
                    break;
                case 60:
                    imageView2.setImage(maison60);
                    break;
                case 61:
                    imageView2.setImage(maison61);
                    break;
                case 62:
                    imageView2.setImage(maison62);
                    break;
                case 63:
                    imageView2.setImage(maison63);
                    break;
                case 64:
                    imageView2.setImage(maison64);
                    break;
                case 65:
                    imageView2.setImage(maison65);
                    break;
                case 66:
                    imageView2.setImage(maison66);
                    break;
                case 67:
                    imageView.setImage(tronc67);
                    break;
                case 68:
                    imageView2.setImage(arbrecote68);
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