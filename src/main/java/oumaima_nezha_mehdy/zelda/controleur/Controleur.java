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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.util.Duration;
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
    private ListView<String> inventaireListeVue;

    @FXML
    private ProgressBar pointsDeVie, pointsDeVieEnnemi;

    private Champ champ;
    private int[] sol;
    private Acteur link2;
    private Volcanorax volcanorax;
    private Timeline gameLoop, timeline;
    private int temps;

    private ArrayList<Ennemi> ennemis;
    private ArrayList<Sbire> sbires;
    private ArrayList<int[]> cheminActuel;
    private int indexChemin;
    private DeplacementBFS deplacementBFS;

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
        this.volcanorax = new Volcanorax(200, 200, champ, link2);
        creerSpriteEnnemi(volcanorax);
        ennemis.add(volcanorax);

        //Ajouter un sbire
        ennemis.add(new Sbire("nouveau sbire",300,300,champ));

        pointsDeVie.progressProperty().bind(link2.pointsDeVieProperty().divide(50.0));
        pointsDeVieEnnemi.progressProperty().bind(volcanorax.pointsDeVieProperty().divide(50.0));

        pointsDeVieEnnemi.setStyle("-fx-accent: red;");

        initGameLoop();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> calculerCheminVolcanorax()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public void creerSpriteEnnemi(Acteur a) {
        Circle r = new Circle(3);
        for(Ennemi ennemi : ennemis) {
            r.setFill(ennemi.getColor());
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
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/images/version1/grass.png")));
                    break;
                case 1:
                    rectangle.setFill(Color.BLACK);
                    break;
                case 2:
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/images/version1/water.png")));
                    break;
                case 3:
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/images/version1/stone.png")));
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
        int nbFramesParSeconde = 60;

        KeyFrame kf = new KeyFrame(
                Duration.seconds(1.0 / nbFramesParSeconde),
                event -> {
                    deplacerEnnemi();
                    renduDuJeu();
                }
        );

        gameLoop = new Timeline(kf);
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();
    }

    private void deplacerEnnemi() {
        for (Ennemi ennemi : ennemis) {
            ennemi.deplacer();
        }
    }

    private void renduDuJeu() {
        System.out.println("Frame : " + temps);

        if (link2.estEnCollisionAvec(volcanorax)) {
            System.out.println("⚠ Collision !");
            link2.attaquer(volcanorax);
        }
    }

    private void calculerCheminVolcanorax() {
        cheminActuel = deplacementBFS.deplacementBFS(link2.getX() / 64, link2.getY() / 64, champ, volcanorax);
        indexChemin = 0;
    }
}
