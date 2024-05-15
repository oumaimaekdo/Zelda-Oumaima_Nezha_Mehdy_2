package oumaima_nezha_mehdy.zelda.controleur;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.controlsfx.control.spreadsheet.Grid;
import oumaima_nezha_mehdy.zelda.Main;
import oumaima_nezha_mehdy.zelda.Univers.*;

public class Controleur implements Initializable {

    @FXML
    private TilePane map;

    private Champ champ;


    private int[] sol;

    @FXML
    private Pane vueActeur;

    private LinkController linkControl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        map.setPrefTileHeight(65);
        map.setPrefTileHeight(65);
        CreationMap();
        champ.afficherMap();

            this.map.setOnKeyPressed(e -> {
                System.out.println(e.getCode());
            });
        this.linkControl=new LinkController(vueActeur,champ);

    }




    public void CreationMap() {
        int[] carte = this.sol;
        for (int i = 0; i < carte.length; i++) {

                Rectangle rectangle = new Rectangle(65, 65);
                map.getChildren().add(rectangle);
                rectangle.setId(String.valueOf(i));
                double col = i % 10;
                double lig = Math.floor(i/10);
                double x = col * 10;
                double y = lig * 10;
                rectangle.setX(x);
                rectangle.setY(y);
                switch (carte[i]) {
                    case 0:
                        rectangle.setFill(Color.GREEN);
                        break;
                    case 1:
                        rectangle.setFill(Color.BLACK);
                        break;
                    case 2:
                        rectangle.setFill(Color.BLUE);
                        break;
                }
        }
    }
    public void touchePressé(String event){
        linkControl.touchePressé(event);
    }

}

