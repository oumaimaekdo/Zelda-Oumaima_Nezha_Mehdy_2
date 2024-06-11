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
        ChargementMap(MapPossible.test);

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

    public void ChargementMap(MapInt mapInt){
        this.sol=mapInt.getCarte();
        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();
        if(this.champ==null)
            this.champ = new Champ(LongueurInt,LargeurInt,sol);
        else
            this.champ.setChamp(mapInt.getLongueur(),mapInt.getLargeur(),sol);
        this.tailleTuile=champ.gettT();
        map.setPrefTileHeight(tailleTuile);
        map.setPrefTileWidth(tailleTuile);
        map.setPrefHeight(LargeurInt*tailleTuile);
        map.setPrefWidth(LongueurInt*tailleTuile);
        mapInt.CreationMap(map);
    }

    public void mouseclicked(MouseEvent mouseEvent) {
        univers.requestFocus();
    }

    public void keyPressed(KeyEvent keyEvent) {
        clavier.handle(keyEvent);
        if(keyEvent.getCode().toString().equals("P")){
            map.getChildren().clear();
            ChargementMap(MapPossible.test2);
        }
    }
    public void keyReleased(KeyEvent e){
        clavier.toucheRelaché(e);
    }
}