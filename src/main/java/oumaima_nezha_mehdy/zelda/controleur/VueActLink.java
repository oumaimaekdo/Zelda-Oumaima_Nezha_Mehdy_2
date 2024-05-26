package oumaima_nezha_mehdy.zelda.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import oumaima_nezha_mehdy.zelda.Univers.*;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class VueActLink {


    private Acteur link;

    @FXML
    private Pane vueActeur;

    private Champ champ;



    @FXML
    private ImageView vueLink;
    private Timeline gameLoop;

    private int temps;

    private Image linkNord;
    private Image linkSud;
    private Image linkEst;
    private Image linkOuest;

    private int tT;

    private ArrayList<String> touchePressé;

    private boolean isAnimating;
    public VueActLink(Pane pane, Champ c,int tailleTuile){
        vueActeur=pane;
        this.champ=c;
        this.link=champ.getLink();
        this.tT=tailleTuile;
        this.touchePressé = new ArrayList<>(2);
        creerlink("file:src/main/resources/images/Link/SudDefault.png",link);
        linkNord=new Image("file:src/main/resources/images/Link/NordDefault.png");
        linkSud=new Image("file:src/main/resources/images/Link/SudDefault.png");
        linkEst=new Image("file:src/main/resources/images/Link/EstDefault.png");
        linkOuest=new Image("file:src/main/resources/images/Link/OuestDefault.png");
        initAnimation();  
    }


    public void DeplacementLink(String key) {
        System.out.println("\n \n \n");
        System.out.println(key);
        String touche = key;
        touchePressé.add(touche);
        for (String e : touchePressé)
            System.out.println(e);
        if (touchePressé.contains("Z")) {
            if (coordonnéPossible(this.link.getX(), this.link.getY() - (1 * link.getVitesse()))) {
                link.seDeplacer("nord");

            }
            this.vueLink.setImage(linkNord);
        }
        if (touchePressé.contains("Q")) {
            if (coordonnéPossible(this.link.getX() - (1 * link.getVitesse()), this.link.getY())) {
                link.seDeplacer("ouest");

            }
            this.vueLink.setImage(linkOuest);
        }
        if (touchePressé.contains("S")){
            if (coordonnéPossible(this.link.getX(), this.link.getY() + (1 * link.getVitesse()))){
                link.seDeplacer("sud");

            }
            this.vueLink.setImage(linkSud);
        }
        if (touchePressé.contains("D")){
            if (coordonnéPossible(this.link.getX() + (1 * link.getVitesse()), this.link.getY())){
                link.seDeplacer("est");

             }
            this.vueLink.setImage(linkEst);
        }

        System.out.println(link.getX() + "," + link.getY());
        System.out.println(link.getX() / tT + "," + link.getY() / tT);
    }
    public void toucheRelaché(KeyEvent keyEvent) {
        while (touchePressé.contains(keyEvent.getCode().toString()))
            touchePressé.remove(keyEvent.getCode().toString());
    }


    private boolean coordonnéPossible(int x,int y){
        int haut,bas,gauche,droite;
        haut = 25;
        gauche = 20;
        bas = -20;
        droite = -10;
        if(((x+gauche)/tT) + ((y+haut)/tT)*(champ.getLongueur())>=champ.getChamp().length)
            return false;
        boolean retourneur = x>=0&&y>=0&&x+15<this.champ.getLongueur()*tT&&y+15<this.champ.getLargeur()*tT;
        boolean collisionhautgauche =champ.getChamp()[((x+gauche)/tT) + ((y+haut)/tT)*(champ.getLongueur())]!=2;
        boolean collisionbasdroite =champ.getChamp()[(x-droite)/tT + ((y-bas)/tT)*(champ.getLongueur())]!=2;
        return retourneur&&(collisionhautgauche&&collisionbasdroite);
    }




    public void creerlink(String path , Acteur a){
        ImageView r = new ImageView();
        Image Image = new Image(path);
        r.setImage(Image);
        r.setFitWidth(30);
        r.setFitHeight(30);
        vueActeur.getChildren().add(r);
        r.setId(a.getId());
        r.setTranslateX(a.getX());
        r.setTranslateY(a.getY());
        r.translateXProperty().bind(a.getXProperty());
        r.translateYProperty().bind(a.getYProperty());
        this.vueLink=r;
        
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        temps=1;
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.1),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    vueLink.setImage(new Image("file:src/main/resources/images/Link/Est/Est"+(temps%11)+".png"));
                    if (temps%11==0)
                        vueLink.setImage(linkEst);
                    temps++;



                })
        );
        gameLoop.getKeyFrames().add(kf);
    }


}
