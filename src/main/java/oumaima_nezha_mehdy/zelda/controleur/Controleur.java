package oumaima_nezha_mehdy.zelda.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.util.Duration;
import org.controlsfx.control.spreadsheet.Grid;
import oumaima_nezha_mehdy.zelda.Univers.*;

public class Controleur implements Initializable {

    @FXML
    private TilePane map;
    @FXML
    private Pane vueActeur;

    private Champ champ;

    private int[] sol;

    private Acteur link2;
    private Ennemi e;
    //private Rectangle[][] tabRectangle;

    private Timeline gameLoop;

    private int temps;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.sol = new int[]{
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 2, 2, 2, 0, 0, 0, 0,
                2, 1, 2, 2, 2, 2, 2, 1, 2, 2,
                3, 3, 3, 2, 2, 2, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3};

        this.champ = new Champ(10, 10, sol);

        map.setPrefTileHeight(65);
        map.setPrefTileHeight(65);

        CreationMap();
        champ.afficherMap();

        this.map.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
        });

        this.e = new Ennemi("sbire de feu", 100, 100, champ);
        this.link2 = new Acteur("newlink", 0, 0, champ);

        creerSprite(link2);
        creerSpriteEnnemi(e);

        this.map.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
        });

        initGameLoop();

    }

    public void creerSpriteEnnemi(Acteur a) {

        Circle r = new Circle(3);
        r.setFill(Color.GREEN); // Vous pouvez changer la couleur ou utiliser une image spécifique pour l'ennemi
        vueActeur.getChildren().add(r);
        r.setId(e.getId());
        r.setTranslateX(e.getX());
        r.setTranslateY(e.getY());
        r.translateXProperty().bind(e.getXProperty());
        r.translateYProperty().bind(e.getYProperty());
    }


    public void creerSprite(Acteur a) {


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
                    rectangle.setFill(Color.DARKGREY);
                    break;
                case 1:
                    rectangle.setFill(Color.BLACK);
                    break;
                case 2:
                    rectangle.setFill(Color.BLUE);
                    break;
                case 3:
                    rectangle.setFill(Color.LIGHTBLUE);
                    break;
            }
        }
    }



    public void touchePressé(String key){
        System.out.println("\n \n \n" );
        System.out.println(key);
        switch (key) {
            case "Z":
            case "UP":
                this.link2.seDeplacer("nord");
                break;
            case "Q":
            case "LEFT":
                this.link2.seDeplacer("ouest");
                break;
            case "S":
            case "DOWN":
                this.link2.seDeplacer("sud");
                break;
            case "D":
            case "RIGHT":
                this.link2.seDeplacer("est");
                break;
        }

        champ.afficherMap();
        System.out.println(link2.getX()+","+link2.getY());

        //raffraichir();
    }

    private void initGameLoop() {

        temps = 0;
        int nbFramesParSeconde = 1;

        // Création de la KeyFrame avec l'événement à exécuter à chaque frame
        KeyFrame kf = new KeyFrame(
                Duration.seconds(1.0 / nbFramesParSeconde), // Durée entre chaque frame
                event -> {
                    // Code exécuté à chaque frame
                    //jeuMisaJour();
                    deplacerEnnemi();
                    renduDuJeu();
                }
        );

        // Création de la Timeline (boucle de jeu) et ajout de la KeyFrame
        gameLoop = new Timeline(kf);
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        gameLoop.play();
    }

    // Méthode appelée à chaque frame pour mettre à jour l'état du jeu
/*
    private void jeuMisaJour() {

        temps++;
        if (temps >= 100) {
            System.out.println("Fin de la boucle de jeu.");
            gameLoop.stop();
        }
        deplacerEnnemi();
    }

 */

    private void deplacerEnnemi() {


        if (e.getX() < link2.getX()) {
            e.seDeplacer("est");
        } else if (e.getX() > link2.getX()) {
            e.seDeplacer("ouest");
        }


        if (e.getY() < link2.getY()) {
            e.seDeplacer("sud");
        } else if (e.getY() > link2.getY()) {
            e.seDeplacer("nord");
        }


        if (e.getX() == link2.getX() && e.getY() == link2.getY()) {
            System.out.println("collision !");
        }



        /*
        //il s'approche du link

        if (e.getX() > link2.getX()) {
            e.seDeplacer("est");
        } else if (e.getX() < link2.getX()) {
            e.seDeplacer("ouest");
        }

        if (e.getY() > link2.getY()) {
            e.seDeplacer("sud");
        } else if (e.getY() < link2.getY()) {
            e.seDeplacer("nord");
        }

        if(e.getX() == link2.getX() && e.getY() == link2.getY()){
            System.out.println("collision ! ");
        }

         */




        //l'ennemi se déplace pas correctement
        //ArrayList<int[]> chemin = e.deplacementBFS(link2.getX() / 64, link2.getY() / 64);
        //e.suivreChemin(chemin);
    }

    // Méthode appelée à chaque frame pour mettre à jour l'affichage du jeu

    private void renduDuJeu() { System.out.println("Frame : " + temps); }



}

