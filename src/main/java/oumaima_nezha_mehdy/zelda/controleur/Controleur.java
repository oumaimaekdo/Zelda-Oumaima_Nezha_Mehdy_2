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

    //private Rectangle[][] tabRectangle;

    private Timeline gameLoop;

    private int temps;

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



        this.map.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
        });

        initGameLoop();
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


        /*
        Image link2;


        switch (a.getDirection()) {
            case "nord":
                link2 = new Image("file:src/main/resources/images/link_nord.png");
                break;
            case "sud":
                link2 = new Image("file:src/main/resources/images/link_sud.png");
                break;
            case "ouest":
                link2 = new Image("file:src/main/resources/images/link_ouest.png");
                break;
            case "est":
                link2 = new Image("file:src/main/resources/images/link_est.png");
                break;
            default:
                //en cas d'erreur : on donnera l'image par défaut
                link2 = new Image("file:chemin/vers/image_par_defaut.png");
                break;
            }

            ImageView vuePersonnage = new ImageView(link2);

         */
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

    /*

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
                ImageView imageView = new ImageView();
                switch (carte[y][x]) {
                    case 0:
                        rectangle.setFill(Color.GREEN);
                        break;
                    case 1:
                        Image image = new Image("file:src/main/resources/images/arbreVert.png");
                        //imageView.setImage(image);
                        //imageView.setFitWidth(65);
                        //imageView.setFitHeight(65);
                        rectangle.setFill(new ImagePattern(image));
                        break;
                    case 2:
                        Image link = new Image("file:src/main/resources/images/link_defaut.png");
                        rectangle.setFill(new ImagePattern(link));
                        //rectangle.setFill(Color.BLUE);
                        break;
                }
            }
        }
    }

     */



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


    /*
    public void raffraichir() {
        int[][] carte = champ.getChamp();
        for (int y = 0; y < carte.length; y++) {
            for (int x = 0; x < carte[y].length; x++) {
                switch (carte[y][x]) {
                    case 0:
                        tabRectangle[y][x].setFill(Color.GREEN);
                        break;
                    case 1:
                        //tabRectangle[y][x].setFill(Color.BLACK);
                        Image image = new Image("file:src/main/resources/images/arbreVert.png");
                        tabRectangle[y][x].setFill(new ImagePattern(image));
                        break;
                    case 2:
                        Image link = new Image("file:src/main/resources/images/link_defaut.png");
                        tabRectangle[y][x].setFill(new ImagePattern(link));
                        //tabRectangle[y][x].setFill(Color.BLUE);
                        break;
                }
            }
        }
    }

     */

    private void initGameLoop() {

        temps = 0;
        int nbFramesParSeconde = 60;

        // Création de la KeyFrame avec l'événement à exécuter à chaque frame
        KeyFrame kf = new KeyFrame(
                Duration.seconds(1.0 / nbFramesParSeconde), // Durée entre chaque frame
                event -> {
                    // Code exécuté à chaque frame
                    jeuMisaJour();
                    renduDuJeu();
                }
        );

        // Création de la Timeline (boucle de jeu) et ajout de la KeyFrame
        gameLoop = new Timeline(kf);
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        gameLoop.play();
    }

    // Méthode appelée à chaque frame pour mettre à jour l'état du jeu

    private void jeuMisaJour() {

        temps++;
        if (temps >= 100) {
            System.out.println("Fin de la boucle de jeu.");
            gameLoop.stop();
        }
    }

    // Méthode appelée à chaque frame pour mettre à jour l'affichage du jeu

    private void renduDuJeu() { System.out.println("Frame : " + temps); }



}

