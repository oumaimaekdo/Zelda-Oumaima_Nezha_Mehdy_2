package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import oumaima_nezha_mehdy.zelda.Main;
import oumaima_nezha_mehdy.zelda.Vue.VueChamp;
import oumaima_nezha_mehdy.zelda.Vue.VueVillage2;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;
import oumaima_nezha_mehdy.zelda.Vue.VueActLink;
import oumaima_nezha_mehdy.zelda.Vue.VueSbir;

public class Controleur implements Initializable {

    @FXML
    private TilePane map1;
    @FXML
    private TilePane LayerSup;
    @FXML
    private TilePane armesMap;

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
    private VueChamp vueChamp;
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
        this.mapInt = MapPossible.test3;
        this.mapInt2 = MapPossible.test4;
        this.mapInt3 = MapPossible.collision;
        this.mapInt4 = MapPossible.village2;
        this.monde = MapVillage2.monde;

        this.champ = new Champ(mapInt.getLongueur(), mapInt.getLargeur(), MapPossible.collision.getCarte());
        //this.champ = new Champ(mapInt4.getLongueur(), mapInt4.getLargeur(), MapVillage2.collision.getCarte());
        this.vueChamp = new VueChamp(map1,LayerSup,armesMap,champ,mapInt,mapInt2,mapInt3);
        //this.vueVillage2 = new VueVillage2(mapVIllage2,champ,mapInt4,"volcanLand");
        //this.vueVillage2 = new VueVillage2(mapVIllage2,champ,mapInt4,"iceLand");
        //this.vueVillage2 = new VueVillage2(mapVIllage2,champ,monde,"monde");

        this.linkControl = new VueActLink(vueActeur, champ, champ.gettT(), vueArmes, vueInventaire, vueArmesInventaire, armesMap,vueChamp);
        this.sbirControl = new VueSbir(vueSbir, champ, champ.gettT());
        sbirControl.getSbir1().deplacementAleatoire();

        this.clavier = new Clavier(vueActeur, linkControl, vueInventaire);
        setUpListeners();

    }

    private void setUpListeners() {
        this.champ.getLink().getXProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateX(univers.getPrefWidth() / 2 - champ.getLink().getX());
        });
        this.champ.getLink().getYProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateY(univers.getPrefHeight() / 2 - champ.getLink().getY());
        });
        this.univers.setTranslateX(univers.getPrefWidth() / 2 - champ.getLink().getX());
        this.univers.setTranslateY(univers.getPrefHeight() / 2 - champ.getLink().getY());
    }


    public void mouseclicked(MouseEvent mouseEvent) {
        univers.requestFocus();
    }

    public void keyPressed(KeyEvent keyEvent) {
        clavier.handle(keyEvent);
        System.out.println(vueInventaire.lookup("#case1").getId());
    }

    public void keyReleased(KeyEvent e){
        clavier.toucheRelaché(e);
    }


}
