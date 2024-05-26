package oumaima_nezha_mehdy.zelda.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.util.Duration;
import oumaima_nezha_mehdy.zelda.Univers.*;

public class Controleur implements Initializable {

    @FXML
    private TilePane map;
    @FXML
    private Pane univers;

    private Champ champ;


    private int[] sol;

    @FXML
    private Pane vueActeur;

    private VueActLink linkControl;

    private int tailleTuile;

    private int LongueurInt;

    private int LargeurInt;

    private Clavier clavier;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MapInt mapInt = MapPossible.test;
        this.tailleTuile=64;
        this.sol=mapInt.getCarte();
        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();
        this.champ = new Champ(LongueurInt,LargeurInt,sol);
        map.setPrefTileHeight(tailleTuile);
        map.setPrefTileWidth(tailleTuile);
        map.setPrefHeight(LargeurInt*tailleTuile);
        map.setPrefWidth(LongueurInt*tailleTuile);
        CreationMap();
        champ.afficherMap();
        this.linkControl=new VueActLink(vueActeur,champ,tailleTuile);
        this.clavier =new Clavier(vueActeur,linkControl);
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
                        imageView.setImage(terre);
                        break;
                    case 2:
                        imageView.setImage(eau);
                        break;
                }
        }
    }


    public void mouseclicked(MouseEvent mouseEvent) {
        univers.requestFocus();
    }

    public void keyPressed(KeyEvent keyEvent) {
        clavier.touchePressé(keyEvent);
    }


    public void keyReleased(KeyEvent keyEvent) {
        clavier.toucheRelaché(keyEvent);
    }
}

