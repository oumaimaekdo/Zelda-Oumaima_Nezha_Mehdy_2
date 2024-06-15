package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

import oumaima_nezha_mehdy.zelda.Vue.ListObsBloc;
import oumaima_nezha_mehdy.zelda.Vue.VueVillage2;
import oumaima_nezha_mehdy.zelda.modele.Armes.EpeeDeFer;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;
import oumaima_nezha_mehdy.zelda.Vue.VueActLink;
import oumaima_nezha_mehdy.zelda.Vue.VueSbir;

public class Controleur implements Initializable {

    public Pane VueBloc;
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
        this.mapInt3 = MapPossible.collision;
        this.mapInt4 = MapPossible.village2;
        this.monde = MapVillage2.monde;


        ChargementMap(mapInt4,"debut",MapPossible.collision.getCarte());
        //this.vueVillage2 = new VueVillage2(mapVIllage2,champ,mapInt4,"volcanLand");
        //this.vueVillage2 = new VueVillage2(mapVIllage2,champ,mapInt4,"iceLand");
        //this.vueVillage2 = new VueVillage2(mapVIllage2,champ,monde,"monde");

        this.linkControl = new VueActLink(vueActeur, champ, champ.gettT(), vueArmes, vueInventaire, vueArmesInventaire);
        this.sbirControl = new VueSbir(vueSbir, champ, champ.gettT());
        sbirControl.getSbir1().deplacementAleatoire();

        this.clavier = new Clavier(vueActeur, linkControl, vueInventaire);
        setUpListeners();



    }

    private void setUpListeners() {
        this.champ.getLink().getXProperty().addListener((observable, oldValue, newValue) -> {
            this.univers.setTranslateX(univers.getPrefWidth() / 2 - champ.getLink().getX());
            if(vueVillage2.getNom().equals("debut")&&champ.getLink().getY()/64==14 && champ.getLink().getX()/64==10) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                linkControl.getLink().setX(660);
                linkControl.getLink().setY(50);
                ChargementMap(mapInt4, "foret", MapVillage2.collision.getCarte());
            }

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
        Coffre c = new Coffre(new EpeeDeFer(this.champ),"CléNormal",this.champ);
    }


    public void mouseclicked(MouseEvent mouseEvent) {
        univers.requestFocus();
    }

    public void keyPressed(KeyEvent keyEvent) {
        clavier.handle(keyEvent);
        if(vueVillage2.getNom().equals("debut")) {
            if (keyEvent.getCode().toString().equals("P")) {
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                linkControl.getLink().setX(660);
                linkControl.getLink().setY(50);
                ChargementMap(mapInt4, "foret", MapVillage2.collision.getCarte());
            }
        }
        else if(vueVillage2.getNom().equals("foret")){
            if (keyEvent.getCode().toString().equals("P")) {

                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                linkControl.getLink().setX(60);
                linkControl.getLink().setY(590);
                ChargementMap(mapInt4, "iceLand", MapVillage2.collision.getCarte());
            } if(keyEvent.getCode().toString().equals("N")){
                mapVIllage2.getChildren().clear();
                LayerSup.getChildren().clear();
                linkControl.getLink().setX(670);
                linkControl.getLink().setY(930);
                ChargementMap(mapInt4, "debut", MapVillage2.collision.getCarte());
            }
        }
    }

    public void keyReleased(KeyEvent e){
        clavier.toucheRelaché(e);
    }


}
