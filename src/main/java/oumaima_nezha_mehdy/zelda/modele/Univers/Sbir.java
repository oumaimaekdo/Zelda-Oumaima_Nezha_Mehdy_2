package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import static java.nio.file.Files.move;

public class Sbir extends Ennemi {

    private Timeline mouvementTimeline;
    private String direction = "est";

    private int vie;
    private int vitesse;

    private Champ champ;


    public Sbir(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
        this.champ = m;
        this.vie = 100;
        this.vitesse = 10;
        //initialiserMouvement();
    }

/*
    private void initialiserMouvement() {
        mouvementTimeline = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> seDirigerVersLink()));
        mouvementTimeline.setCycleCount(Timeline.INDEFINITE);
        mouvementTimeline.play();
    }

 */


    public void seDirigerVersLink() {

        /*
        Random rand = new Random();
        int direction = rand.nextInt(2); // Génère un nombre aléatoire entre 0 et 3 inclus

        String[] directions = {"nord","sud","ouest","est"};
        /*
        switch (direction) {

            case 0:
                seDeplacer("nord");
                break;
            case 1:
                seDeplacer("sud");
                break;
            case 2:
                seDeplacer("ouest");
                break;
            case 3:
                seDeplacer("est");
                break;


        }


        if(Math.random()>0.5){
            this.direction = directions[(int) Math.random()*4];
        }
        seDeplacer(this.direction);
        */
    }
}

        /**/
