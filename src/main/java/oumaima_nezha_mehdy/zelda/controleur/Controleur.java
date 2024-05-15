package oumaima_nezha_mehdy.zelda.controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

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

    private Acteur link2;

    @FXML
    private Pane vueActeur;

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
            this.link2 = new Acteur("newlink",0,0,champ);
            creerSprite(link2);


    }

    public void creerSprite(Acteur a){
        Circle r=new Circle(3);
        r.setFill (Color.RED);
        vueActeur.getChildren().add(r);
        r.setId(a.getId());
        r.setTranslateX(a.getX());
        r.setTranslateY(a.getY());
        r.translateXProperty().bind(a.getXProperty());
        r.translateYProperty().bind(a.getYProperty());
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

    public void touchePressé(String key){
        System.out.println("\n \n \n" );
        System.out.println(key);
        switch (key) {
            case"Z" :
            case "UP":this.link2.seDeplacer("nord");
                break;
            case "Q":
            case "LEFT":this.link2.seDeplacer("ouest");
                break;
            case "S":
            case "DOWN":this.link2.seDeplacer("sud");
                break;
            case "D":
            case "RIGHT":this.link2.seDeplacer("est");
                break;
        }

        champ.afficherMap();
        System.out.println(link2.getX()+","+link2.getY());
        for(int i=0;i<champ.getChamp().length ;i++)
            if(champ.getChamp()[i]==1)
            System.out.println(i);
    }

  /*  public void raffraichir() {
        int[][] carte = champ.getChamp();
        for (int y = 0; y < carte.length; y++) {
            for (int x = 0; x < carte[y].length; x++) {
                switch (carte[y][x]) {
                    case 0:
                        tabRectangle[y][x].setFill(Color.GREEN);
                        break;
                    case 1:
                        tabRectangle[y][x].setFill(Color.BLACK);
                        break;
                    case 2:
                        tabRectangle[y][x].setFill(Color.BLUE);
                        break;
                }
            }

    }

}*/

}

