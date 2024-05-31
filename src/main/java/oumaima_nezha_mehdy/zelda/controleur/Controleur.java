package oumaima_nezha_mehdy.zelda.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
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
import javafx.util.Duration;
import org.controlsfx.control.spreadsheet.Grid;
import oumaima_nezha_mehdy.zelda.Univers.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controleur implements Initializable {

    @FXML
    private TilePane map;
    @FXML
    private Pane vueActeur;
    @FXML
    private VBox vieContainer;
    @FXML
    private Label pointDeVie;
    @FXML
    private ListView<String> inventaireListeVue;

    //@FXML
    //private ProgressBar pointsDeVie;

    private Champ champ;
    private int[] sol;
    private Acteur link2;
    private Volcanorax e;
    private Timeline gameLoop;
    private int temps;

    // ajouter une constance pour le Nombre initial de points de vie
    private boolean mageExistant = false;
    private ArrayList<Ennemi> ennemis;
    private ArrayList<Sbire> sbires;



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
        map.setPrefTileWidth(65);

        CreationMap();
        champ.afficherMap();

        this.map.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
            touchePressé(e.getCode().toString());
        });

        ennemis = new ArrayList<>();
        sbires = new ArrayList<>();

        this.link2 = new Acteur("newlink", 0, 0, champ);
        creerSprite(link2);

        // Ajouter le boss du feu, qui est Volcanorax
        Volcanorax volcanorax = new Volcanorax(10, 10, champ, link2);
        ennemis.add(volcanorax);

        this.e =  volcanorax;
        e.seDeplaceBoss();

        // Ajouter les sbires
        //ennemis.add(new Sbire("Sbire1", 5, 5, champ));
        //ennemis.add(new Sbire("Sbire2", 6, 6, champ));

        //sbires.add(new Sbire("le premier sbire de feu", 100, 100, champ));
        //sbires.add(new Sbire("le premier sbire de feu", 200, 200, champ));

        ennemis.add(new Sbire("Sbire1", 5, 5, champ));
        ennemis.add(new Sbire("Sbire2", 6, 6, champ));

        for(Ennemi e : ennemis){
            if (e instanceof Sbire) {
                ((Sbire) e).deplacementAleatoire();
            }
            creerSpriteEnnemi(e);
        }



        initGameLoop();


        //pointsDeVie.progressProperty().bind(link2.pointsDeVieProperty().divide(3));

        //pointsDeVie.idProperty().bind(Bindings.concat("Points de Vie: ", link2.pointsDeVieProperty()));
    }

    //explique comment t'as fait
    /*
    private void ajouterItemInventaire(String item) {
        inventaireListeVue.getItems().add(item);
    }

     */

    public void creerSpriteEnnemi(Acteur a) {

        Circle r = new Circle(3);

        //r.setFill(a instanceof Mage ? Color.PURPLE : Color.GREEN);

        if(a instanceof Volcanorax || a instanceof Freezragon){
            r.setFill(Color.PURPLE);
        }else{
            r.setFill(Color.PURPLE);
        }
        vueActeur.getChildren().add(r);
        r.setId(a.getId());
        r.setTranslateX(a.getX());
        r.setTranslateY(a.getY());
        r.translateXProperty().bind(a.getXProperty());
        r.translateYProperty().bind(a.getYProperty());
    }

    public void creerSprite(Acteur a) {
        Circle r = new Circle(5);
        r.setFill(Color.RED);
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
            double lig = Math.floor(i / 10);
            double x = col * 65;
            double y = lig * 65;
            rectangle.setX(x);
            rectangle.setY(y);

            switch (carte[i]) {
                case 0:
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/images/grass.png")));
                    break;
                case 1:
                    rectangle.setFill(Color.BLACK);
                    break;
                case 2:
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/images/water.png")));
                    break;
                case 3:
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/images/stone.png")));
                    break;
            }
        }
    }

    public void touchePressé(String key) {
        System.out.println("\n \n \n");
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
        System.out.println(link2.getX() + "," + link2.getY());
    }

    private void initGameLoop() {

        temps = 0;
        int nbFramesParSeconde = 60;  // on le met à 60 pour avoir une animation fluide

        KeyFrame kf = new KeyFrame(
                Duration.seconds(2.0 / nbFramesParSeconde),
                event -> {
                    deplacerEnnemi(); //l'ennemi se déplace "automatique" (en fonction de la boucle de jeu)
                    renduDuJeu();
                }
        );

        gameLoop = new Timeline(kf);
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    /*
    private void deplacerEnnemi() {
        ArrayList<int[]> chemin = e.deplacementBFS(link2.getX() / 64, link2.getY() / 64);
        e.suivreChemin(chemin);
    }
    */




    private void deplacerEnnemi() {
        for (Ennemi ennemi : ennemis) {
            if (ennemi instanceof Sbire) {
                ((Sbire) ennemi).deplacementAleatoire();
            }
            //else if (ennemi instanceof Volcanorax) {
                //ennemi.seDeplaceBoss();
            //}
        }
    }

    private void renduDuJeu() {
        System.out.println("Frame : " + temps);

        if (link2.estEnCollisionAvec(e)) {
            System.out.println("⚠ Collision !");
            link2.attaquer(e);
            //faire appel au link qui utilise son arme
        }
    }

    public void creerMageUnique() {
        if (!mageExistant) {
            Mage mage = new Mage("mage de feu", 200, 200, champ);
            creerSpriteEnnemi(mage);
            mageExistant = true;
        }
    }

    /*

    // Méthode pour mettre à jour les mouvements des ennemis
    public void update() {
        for (Ennemi ennemi : ennemis) {
            ennemi.suivreChemin(link2);
        }

        for (Sbire sbire : sbires) {
            sbire.deplacementAleatoire();
        }
    }

     */
}


/*
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

        // pourquoi il y est 2 fois ?

        map.setPrefTileHeight(65);
        map.setPrefTileHeight(65);

        CreationMap();
        champ.afficherMap();

        // "fonctionnalité" qui nous permet d'utiliser les touches du clavier pour effectuer des actions spécifiques ?

        this.map.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
        });

        this.e = new Sbire("sbire de feu", 100, 100, champ);
        this.link2 = new Acteur("newlink", 0, 0, champ);

        creerSprite(link2);
        creerSpriteEnnemi(e);

        //pourquoi il y est une 2e fois ?

        this.map.setOnKeyPressed(e -> {
            System.out.println(e.getCode());
        });

        initGameLoop();

    }

    public void creerSpriteEnnemi(Acteur a) {

        Circle r = new Circle(3);
        r.setFill(Color.GREEN);
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

    }

    private void initGameLoop() {

        temps = 0;
        int nbFramesParSeconde = 1;

        // Création de la KeyFrame avec l'événement à exécuter à chaque frame
        KeyFrame kf = new KeyFrame(
                Duration.seconds(1.0 / nbFramesParSeconde), // Durée entre chaque frame
                event -> {
                    deplacerEnnemi();
                    renduDuJeu();
                }
        );

        // Création de la Timeline (boucle de jeu) et ajout de la KeyFrame
        gameLoop = new Timeline(kf);
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        gameLoop.play();
    }


    private void deplacerEnnemi() {

        ArrayList<int[]> chemin = e.deplacementBFS(link2.getX() / 64, link2.getY() / 64);
        e.suivreChemin(chemin);


    }

    // Méthode appelée à chaque frame pour mettre à jour l'affichage du jeu

    private void renduDuJeu() {

        System.out.println("Frame : " + temps);

        if (link2.estEnCollisionAvec(e)) {
            System.out.println("⚠\uFE0F Collision !");
            // Appel de la méthode pour attaquer l'ennemi
            link2.attaquer(e);
            //On ajoutera d'autres actions à effectuer lors de la collision : utilsation d'arme, ...
        }


    }
}

 */



