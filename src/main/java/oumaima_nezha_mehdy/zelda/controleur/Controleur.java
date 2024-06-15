package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import oumaima_nezha_mehdy.zelda.Vue.VueVillage2;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;
import oumaima_nezha_mehdy.zelda.Vue.VueActLink;
import oumaima_nezha_mehdy.zelda.Vue.VueSbir;

public class Controleur implements Initializable {


    @FXML
    private TilePane LayerSup;
    @FXML
    private TilePane TresorPnj;

    @FXML
    private TilePane mapVIllage2;

    @FXML
    private Pane univers;
    @FXML
    private HBox vueInventaire;

    @FXML
    private Pane vueActeur;
    @FXML
    private Pane vueArmes;
    @FXML
    private Pane vueSbir;
    @FXML
    private Pane vueArmesInventaire;

    private Champ champ;
    private VueActLink linkControl;
    private VueSbir sbirControl;
    private Clavier clavier;
    private VueVillage2 vueVillage2;

    private MapInt mapInt;
    private MapInt mapInt2;
    private MapInt mapInt3;
    private MapInt mapInt4;
    private MapInt monde;

    private int[] sol;
    private int LongueurInt;

    private int LargeurInt;

    private int tailleTuile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mapInt4 = MapPossible.village2;


        ChargementMap(mapInt4,MapPossible.pnjETtresors,"debut",MapPossible.collision.getCarte());

        this.linkControl = new VueActLink(vueActeur, champ, champ.gettT(), vueArmes, vueInventaire, vueArmesInventaire);
        this.sbirControl = new VueSbir(vueSbir, champ, champ.gettT());

        this.clavier = new Clavier(vueActeur, linkControl, vueInventaire);
        setUpListeners();



    }

    private void setUpListeners() {
        this.champ.getLink().getXProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateX(univers.getPrefWidth() / 2 - champ.getLink().getX());
            if(vueVillage2.getNom().equals("debut")&&champ.getLink().getY()/64==14 && champ.getLink().getX()/64==10) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                TresorPnj.getChildren().clear();
                linkControl.getLink().setX(660);
                linkControl.getLink().setY(50);
                ChargementMap(mapInt4,MapPossible.pnjETtresors, "foret", MapPossible.collisionForet.getCarte());
            }
            else if(vueVillage2.getNom().equals("foret") &&champ.getLink().getY()/64==0 && champ.getLink().getX()/64==10) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                TresorPnj.getChildren().clear();
                linkControl.getLink().setX(670);
                linkControl.getLink().setY(900);
                ChargementMap(mapInt4,MapPossible.pnjETtresors, "debut", MapPossible.collision.getCarte());
            }
            else if(vueVillage2.getNom().equals("foret") &&champ.getLink().getY()/64==10 && champ.getLink().getX()/64==19) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                TresorPnj.getChildren().clear();
                linkControl.getLink().setX(60);
                linkControl.getLink().setY(590);
                ChargementMap(mapInt4,MapPossible.pnjETtresors, "iceLand", MapPossible.collision.getCarte());
            }
            else if(vueVillage2.getNom().equals("iceLand") &&champ.getLink().getY()/64==9 && champ.getLink().getX()/64==0) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                TresorPnj.getChildren().clear();
                linkControl.getLink().setX(1180);
                linkControl.getLink().setY(590);
                ChargementMap(mapInt4,MapPossible.pnjETtresors, "foret", MapPossible.collisionForet.getCarte());
            }

        });
        this.champ.getSbir().getYProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateY(univers.getPrefHeight() / 2 - champ.getLink().getY());
        });
        this.champ.getLink().getYProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateY(univers.getPrefHeight() / 2 - champ.getLink().getY());
        });
        this.univers.setTranslateX(univers.getPrefWidth() / 2 - champ.getLink().getX());
        this.univers.setTranslateY(univers.getPrefHeight() / 2 - champ.getLink().getY());

    }

    public void ChargementMap(MapInt mapInt,MapInt carteTresorPnj,String nomMap,int[] collision){
        this.sol= collision;
        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();
        if(this.champ==null)
            this.champ = new Champ(LongueurInt,LargeurInt,sol);
        else
            this.champ.setChamp(mapInt.getLongueur(),mapInt.getLargeur(),sol);
        this.tailleTuile=champ.gettT();
        mapVIllage2.setPrefTileHeight(tailleTuile);
        mapVIllage2.setPrefTileWidth(tailleTuile);
        mapVIllage2.setPrefHeight(LargeurInt*tailleTuile);
        mapVIllage2.setPrefWidth(LongueurInt*tailleTuile);
        this.vueVillage2 = new VueVillage2(mapVIllage2,LayerSup,TresorPnj,champ,mapInt4,MapPossible.LayerSup,carteTresorPnj,nomMap);
    }


    public void mouseclicked(MouseEvent mouseEvent) {
        univers.requestFocus();
    }

    public void keyPressed(KeyEvent keyEvent) {
        clavier.handle(keyEvent);
        System.out.println(vueInventaire.lookup("#case1").getId());
        System.out.println(linkControl.getLink().getX()/64 + " ......." + linkControl.getLink().getY()/64);
    }

    public void keyReleased(KeyEvent e){
        clavier.toucheRelaché(e);
    }


}
