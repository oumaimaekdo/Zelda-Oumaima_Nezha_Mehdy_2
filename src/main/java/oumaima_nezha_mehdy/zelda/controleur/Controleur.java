package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import oumaima_nezha_mehdy.zelda.Vue.*;
import oumaima_nezha_mehdy.zelda.modele.Armes.Arc;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Armes.Bombe;
import oumaima_nezha_mehdy.zelda.modele.Armes.EpeeDeFer;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;

public class Controleur implements Initializable {

    public Pane VueBloc;

    @FXML
    private TilePane LayerSup;


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
    private Pane vueBoss;
    @FXML
    private Pane vueSbir;
    @FXML
    private Pane vueDonneurQuetes;
    @FXML
    private Pane vueArmesInventaire;
    @FXML
    private Pane vuePointsDeVie;

    private Champ champ;
    //private VuePointsDeVie pointsDeVieControl;
    private VueActLink linkControl;
    private VueBoss bossControl;
    private VueSbir sbirControl;
    private VueSbir sbirControl2;
    private VueDonneurQuetes donneurQuetesControl;
    private Clavier clavier;
    private VueVillage2 vueVillage2;


    private MapInt mapInt4;


    private int[] sol;
    private int LongueurInt;

    private int LargeurInt;

    private int tailleTuile;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mapInt4 = MapPossible.village2;


        ChargementMap(mapInt4,"debut",MapPossible.collision.getCarte());

        this.linkControl = new VueActLink(vueActeur, champ, champ.gettT(), vueArmes, vueInventaire, vueArmesInventaire);
        this.sbirControl = new VueSbir(vueSbir, champ, champ.gettT(),vuePointsDeVie);
        this.bossControl = new VueBoss(vueBoss, champ, champ.gettT(),vuePointsDeVie);
        this.clavier = new Clavier(vueActeur, linkControl, vueInventaire);

        //this.pointsDeVieControl = new VuePointsDeVie(vuePointsDeVie,champ);

        setUpListeners();


    }

    private void setUpListeners() {
        this.champ.getLink().getXProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateX(univers.getPrefWidth() / 2 - champ.getLink().getX());
            if(vueVillage2.getNom().equals("debut")&&champ.getLink().getY()/64==14 && champ.getLink().getX()/64==10) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                this.donneurQuetesControl = new VueDonneurQuetes(vueDonneurQuetes,champ, champ.gettT());
                this.bossControl = new VueBoss(vueBoss, champ, champ.gettT(),vuePointsDeVie);
                linkControl.getLink().setX(660);
                linkControl.getLink().setY(50);
                ChargementMap(mapInt4, "foret", MapPossible.collisionForet.getCarte());
            }
            else if(vueVillage2.getNom().equals("foret") &&champ.getLink().getY()/64==0 && champ.getLink().getX()/64==10) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                vueSbir.getChildren().clear();
                vueDonneurQuetes.getChildren().clear();
                linkControl.getLink().setX(670);
                linkControl.getLink().setY(900);
                ChargementMap(mapInt4, "debut", MapPossible.collision.getCarte());
            }
            else if(vueVillage2.getNom().equals("foret") &&champ.getLink().getY()/64==10 && champ.getLink().getX()/64==19) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                vueDonneurQuetes.getChildren().clear();
                linkControl.getLink().setX(60);
                linkControl.getLink().setY(590);
                ChargementMap(mapInt4, "iceLand", MapPossible.collisionvillageGlace.getCarte());
            }
            else if(vueVillage2.getNom().equals("iceLand") &&champ.getLink().getY()/64==9 && champ.getLink().getX()/64==0) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                this.donneurQuetesControl = new VueDonneurQuetes(vueDonneurQuetes,champ, champ.gettT());
                linkControl.getLink().setX(1180);
                linkControl.getLink().setY(590);
                ChargementMap(mapInt4, "foret", MapPossible.collisionForet.getCarte());
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

    public void ChargementMap(MapInt mapInt,String nomMap,int[] collision){
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
        this.vueVillage2 = new VueVillage2(mapVIllage2,LayerSup,champ,mapInt4,MapPossible.LayerSup,nomMap);
        this.champ.getListBloc().addListener(new ListObsBloc(VueBloc));
        ArrayList<Armes> coffreContenu = new ArrayList<>(Arrays.asList(new Bombe(champ),new EpeeDeFer(champ),new Arc(champ)));
        Coffre c = new Coffre(coffreContenu,"CléNormal",this.champ);

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
