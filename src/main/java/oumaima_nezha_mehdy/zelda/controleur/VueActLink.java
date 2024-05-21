package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.*;

public class VueActLink {


    private Acteur link;

    @FXML
    private Pane vueActeur;

    private Champ champ;


    @FXML
    private ImageView vueLink;

    private Image linkNord;
    private Image linkSud;
    private Image linkEst;
    private Image linkOuest;

    private double tT;

    public VueActLink(Pane pane, Champ c,double tailleTuile){
        vueActeur=pane;
        this.champ=c;
        this.link=new Acteur("newlink",0,0,champ);
        this.tT=tailleTuile;
        creerlink("file:src/main/resources/images/link_defaut.png",link);
        linkNord=new Image("file:src/main/resources/images/link_nord.png");
        linkSud=new Image("file:src/main/resources/images/link_sud.png");
        linkEst=new Image("file:src/main/resources/images/link_est.png");
        linkOuest=new Image("file:src/main/resources/images/link_ouest.png");
    }


    public void DeplacementLink(String key){
        System.out.println("\n \n \n" );
        System.out.println(key);
        int x2base,y2base;
        x2base = this.link.getX();
        y2base = this.link.getX();
        switch (key) {
            case"Z" :
            case "UP":
                this.link.seDeplacer("nord");
                this.vueLink.setImage(linkNord);
                break;
            case "Q":
            case "LEFT":this.link.seDeplacer("ouest");
                this.vueLink.setImage(linkOuest);
                break;
            case "S":
            case "DOWN":this.link.seDeplacer("sud");
                this.vueLink.setImage(linkSud);
                break;
            case "D":
            case "RIGHT":this.link.seDeplacer("est");
                this.vueLink.setImage(linkEst);
                break;
        }
        /*if(!coordonnéPossible(this.link.getX(),this.link.getY())) {
            this.link.setX(x2base);
            this.link.setY(y2base);
        }*/


        System.out.println(link.getX()+","+link.getY());
    }
    private boolean coordonnéPossible(int x,int y){
        boolean retourneur = x>=0&&y>=0&&x<=this.champ.getLongueur()*64&&y<=this.champ.getLargeur()*64;
        int haut,bas,gauche,droite;
        haut = 25;
        gauche = 10;
        bas = 1;
        droite = -5;
        boolean collisionhautgauche =champ.getChamp()[(int) (((x+gauche)/tT) + ((y+haut)/tT)*(champ.getLongueur()))]!=2;
        boolean collisionbasdroite =champ.getChamp()[(int) ((x-droite)/tT + ((y-bas)/tT)*(champ.getLongueur()))]!=2;
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
}
