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

import oumaima_nezha_mehdy.zelda.Vue.VueChamp;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapInt mapInt = MapPossible.test3;
        MapInt mapInt2 = MapPossible.test4;
        MapInt mapInt3 = MapPossible.collision;

        this.champ = new Champ(mapInt.getLongueur(), mapInt.getLargeur(), MapPossible.collision.getCarte());
        this.vueChamp = new VueChamp(map1,LayerSup,armesMap,champ,mapInt,mapInt2,mapInt3);

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
