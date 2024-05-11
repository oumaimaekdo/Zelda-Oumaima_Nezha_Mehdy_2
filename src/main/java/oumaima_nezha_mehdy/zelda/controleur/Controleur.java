package oumaima_nezha_mehdy.zelda.controleur;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.controlsfx.control.spreadsheet.Grid;
import oumaima_nezha_mehdy.zelda.Univers.*;

public class Controleur implements Initializable {

    @FXML
    private GridPane map;

    private Champ champ;

    private Rectangle[][] tabRectangle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.champ = new Champ(30, 20);
        CreationMap();
        champ.afficherMap();

        this.map.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
        });
    }



    public void CreationMap() {
        int[][] carte = champ.getChamp();
        tabRectangle = new Rectangle[carte.length][carte[0].length];
        for (int y = 0; y < carte.length; y++) {
            for (int x = 0; x < carte[y].length; x++) {
                Rectangle rectangle = new Rectangle(20, 20);
                rectangle.setId(String.valueOf(x+y));
                rectangle.setWidth(65);
                rectangle.setHeight(65);
                map.add(rectangle, x, y);
                tabRectangle[y][x] = rectangle;
                switch (carte[y][x]) {
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
    }

    public void touchePressé(String key){
        System.out.println("\n \n \n" );
        System.out.println(key);
        switch (key) {
            case"Z":champ.getLink().seDeplacer("nord");
                break;
            case "Q":champ.getLink().seDeplacer("ouest");
                break;
            case "S":champ.getLink().seDeplacer("sud");
                break;
            case "D":champ.getLink().seDeplacer("est");
                break;
        }

        champ.afficherMap();
        System.out.println(champ.getLink().getX()+","+champ.getLink().getY());
        tabRectangle[1][1].setFill(Color.VIOLET);
        raffraichir();
    }

    public void raffraichir() {
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
    }



}

