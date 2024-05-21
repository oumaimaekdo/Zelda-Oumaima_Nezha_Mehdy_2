package oumaima_nezha_mehdy.zelda.controleur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

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

    private double tailleTuile;

    private Clavier clavier;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tailleTuile=64;
        this.sol = new int[]{   0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 2, 2,
                0, 0, 2, 2, 1, 2, 2, 2, 2, 0,
                0, 2, 2, 2, 1, 2, 2, 0, 0, 0,
                2, 2, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.champ = new Champ(10, 10,sol);
        map.setPrefTileHeight(tailleTuile);
        map.setPrefTileHeight(tailleTuile);
        CreationMap();
        champ.afficherMap();
        this.linkControl=new VueActLink(vueActeur,champ,tailleTuile);
        this.clavier =new Clavier(vueActeur,linkControl);


    }

    public void keyPressed(KeyEvent e){
        clavier.handle(e);
    }




    public void CreationMap() {
        int[] carte = this.sol;
        Image eau = new Image("file:src/main/resources/images/asset2.jpg");
        Image terre = new Image("file:src/main/resources/images/asset.jpg");
        for (int i = 0; i < carte.length; i++) {
            ImageView imageView = new ImageView();
            map.getChildren().add(imageView);
            imageView.setId(String.valueOf(i));
            imageView.setFitHeight(tailleTuile);
            imageView.setFitWidth(tailleTuile);
            double col = i % 10;
            double lig = Math.floor(i/10);
            double x = col * 10;
            double y = lig * 10;
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


}



