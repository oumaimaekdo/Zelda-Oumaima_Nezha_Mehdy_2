package oumaima_nezha_mehdy.zelda.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
    private HBox coeursLink;
    @FXML
    private HBox coeursVolcanorax;

    private Champ champ;
    private int[] sol;
    private Acteur link;
    private Volcanorax volcanorax;
    private Timeline gameLoop;
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

        deplacementBFS = new DeplacementBFS();


        ennemis = new ArrayList<>();
        sbires = new ArrayList<>();

        this.link = new Acteur("newlink", 0, 0, champ);
        creerSprite(link);

        // Ajouter le boss du feu : Volcanorax
        this.volcanorax = new Volcanorax(0, 200, champ, link);
        creerSpriteEnnemi(volcanorax);
        ennemis.add(volcanorax);

        // Ajouter un sbire
        ennemis.add(new Sbire("nouveau sbire", 300, 300, champ));

        updateCoeurs(link, coeursLink);
        updateCoeurs(volcanorax, coeursVolcanorax);

        deplacementBFS = new DeplacementBFS();
        cheminActuel = new ArrayList<>();
        initGameLoop();
    }

    public void creerSpriteEnnemi(Acteur a) {
        Circle r = new Circle(10);
        r.setFill(new ImagePattern(new Image("file:src/main/resources/Bloc/squeletteMarcheEst.gif")));
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
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/Bloc/Herbe.jpg")));
                    break;
                case 1:
                    rectangle.setFill(Color.BLACK);
                    break;
                case 2:
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/Bloc/Eau.jpg")));
                    break;
                case 3:
                    rectangle.setFill(new ImagePattern(new Image("file:src/main/resources/Bloc/Herbe.jpg")));
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
                this.link.seDeplacer("nord");
                break;
            case "Q":
            case "LEFT":
                this.link.seDeplacer("ouest");
                break;
            case "S":
            case "DOWN":
                this.link.seDeplacer("sud");
                break;
            case "D":
            case "RIGHT":
                this.link.seDeplacer("est");
                break;
        }

        champ.afficherMap();
        System.out.println(link.getX() + "," + link.getY());

        // Mettre à jour les coeurs après le déplacement
        updateCoeurs(link, coeursLink);
        updateCoeurs(volcanorax, coeursVolcanorax);
    }

    private void updateCoeurs(Acteur acteur, HBox coeursBox) {
        coeursBox.getChildren().clear();
        int pointsDeVie = acteur.getPointsDeVie();
        int maxPointsDeVie = acteur.getPointsDeVie(); //obtenir les points de vie max
        Image heartFull = new Image("file:src/main/resources/Bloc/coeur-rempli.png");
        Image heartEmpty = new Image("file:src/main/resources/Bloc/coeur-vide.png");
        int nombreCoeurs = Math.min(pointsDeVie, 3); // Limite à un maximum de 3 coeurs

        for(Ennemi ennemi : ennemis){

            if(ennemi.estEnCollisionAvec(link)){
                nombreCoeurs = Math.min(pointsDeVie, 2);
            }
        }

        for (int i = 0; i < 3; i++) {
            ImageView heartView;
            if (i < nombreCoeurs) {
                heartView = new ImageView(heartFull);
            } else {
                heartView = new ImageView(heartEmpty);
            }
            heartView.setFitHeight(20);
            heartView.setFitWidth(20);
            coeursBox.getChildren().add(heartView);
        }
    }

    private void deplacerEnnemi() {
        // Calculer un nouveau chemin si nécessaire
        if (cheminActuel == null || indexChemin >= cheminActuel.size() || cheminActuel.isEmpty()) {
            cheminActuel = deplacementBFS.deplacementBFS(link.getX() / 65, link.getY() / 65, champ, volcanorax);
            indexChemin = 0;
        }

        // Déplacer l'ennemi le long du chemin calculé
        if (cheminActuel != null && indexChemin < cheminActuel.size()) {
            int[] prochainePosition = cheminActuel.get(indexChemin);
            volcanorax.setX(prochainePosition[0] * 65);
            volcanorax.setY(prochainePosition[1] * 65);
            indexChemin++;
        }
    }



    private void initGameLoop() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame kf = new KeyFrame(
                Duration.seconds(0.017), // 60 Frames par seconde
                ae -> {
                    update();
                }
        );
        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }

    private void update() {
        deplacerEnnemi();

        for (Ennemi ennemi : ennemis) {
            if (ennemi.estEnCollisionAvec(link)) {
                link.setPointsDeVie(link.getPointsDeVie() - 1);
                updateCoeurs(link, coeursLink);
                if (link.getPointsDeVie() <= 0) {
                    // Gérer la mort du joueur
                    System.out.println("Link est mort");
                    gameLoop.stop();
                }
            }
        }

        // Mettre à jour les coeurs après le déplacement
        updateCoeurs(link, coeursLink);
        updateCoeurs(volcanorax, coeursVolcanorax);
    }
}
