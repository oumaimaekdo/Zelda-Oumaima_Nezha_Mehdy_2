package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.*;

public class LinkController {


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

    public LinkController (Pane pane,Champ c){
        vueActeur=pane;
        this.champ=c;
        this.link=new Acteur("newlink",0,0,champ);
        creerlink("file:src/main/resources/images/link_defaut.png",link);
        linkNord=new Image("file:src/main/resources/images/link_nord.png");
        linkSud=new Image("file:src/main/resources/images/link_sud.png");
        linkEst=new Image("file:src/main/resources/images/link_est.png");
        linkOuest=new Image("file:src/main/resources/images/link_ouest.png");
    }


    public void touchePressé(String key){
        System.out.println("\n \n \n" );
        System.out.println(key);
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

        System.out.println(link.getX()+","+link.getY());
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
