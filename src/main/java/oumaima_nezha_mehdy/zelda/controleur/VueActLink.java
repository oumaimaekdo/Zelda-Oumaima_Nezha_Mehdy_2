package oumaima_nezha_mehdy.zelda.controleur;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.Univers.*;

import java.util.ArrayList;

public class VueActLink {


    private Acteur link;

    @FXML
    private Pane vueActeur;

    private Champ champ;

    private Pane VueArmesJeu;


    @FXML
    private ImageView vueLink;

    private Image linkNord;
    private Image linkSud;
    private Image linkEst;
    private Image linkOuest;

    private HBox vueCaseInventaire;

    private int tT;

    private VueArmes armeEquipé;

    private Pane vueArmesInventaire;

    private String directionregardé;

    private ObservableList<VueArmes> inventaire;

    public VueActLink(Pane pane, Champ c, int tailleTuile, Pane VueArmesJeu, HBox vueCaseInventaire, Pane vueArmesInventaire){
        vueActeur=pane;
        this.VueArmesJeu = VueArmesJeu;
        this.champ=c;
        this.link=champ.getLink();
        this.tT=tailleTuile;
        this.vueCaseInventaire=vueCaseInventaire;
        this.vueArmesInventaire=vueArmesInventaire;
        this.inventaire = FXCollections.observableArrayList();
        creerlink("file:src/main/resources/images/link_profil.png",link);
        linkNord=new Image("file:src/main/resources/images/Link/NordDefault.png");
        linkSud=new Image("file:src/main/resources/images/Link/SudDefault.png");
        linkEst=new Image("file:src/main/resources/images/Link/EstDefault.png");
        linkOuest=new Image("file:src/main/resources/images/Link/OuestDefault.png");
        chargerInventaire();
        VueArmes vA1=new VueArmes(new Image("file:src/main/resources/images/epeeFer.png"),new Armes("epee",20),new Image("file:src/main/resources/images/epeeFerInversé.png"));
        ramasser(vA1);
        VueArmes arcInventaire=new VueArmes(new Image("file:src/main/resources/images/arc.png"),new Armes("arc",25),new Image("file:src/main/resources/images/arcInversé.png"));
        ramasser(arcInventaire);


    }


    public void DeplacementLink(String key){
        System.out.println("\n \n \n" );
        System.out.println(key);
        switch (key) {
            case"Z" :
            case "UP":
                directionregardé="nord";
                link.seDeplacer(directionregardé);
                this.vueLink.setImage(linkNord);
                break;
            case "Q":
            case "LEFT":
                directionregardé="ouest";
                link.seDeplacer(directionregardé);
                this.vueLink.setImage(linkOuest);
                break;
            case "S":
            case "DOWN":
                directionregardé="sud";
                link.seDeplacer(directionregardé);
                this.vueLink.setImage(linkSud);
                break;
            case "D":
            case "RIGHT":
                directionregardé="est";
                this.vueLink.setImage(linkEst);
                link.seDeplacer(directionregardé);
                break;
            case "R":
                link.attaquer(armeEquipé,link);
                break;
        }
        if (armeEquipé!=null)
        bindeur(directionregardé);
        System.out.println(link.getX()+","+link.getY());
        System.out.println(link.getX()/tT+","+link.getY()/tT);
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
    public void chargerInventaire(){
        for(int i=0;i<5;i++){
            inventaire.add(null);
        }
    }
    public void selectioner(int i){
        if(armeEquipé!=null) {
            VueArmesJeu.getChildren().remove(armeEquipé.getArmeVue());
            System.out.println(VueArmesJeu.getChildren().isEmpty());
        }
        if(inventaire.get(i-1)!=null) {
            armeEquipé = inventaire.get(i-1);
            VueArmesJeu.getChildren().add(armeEquipé.getArmeVue());
            bindeur(directionregardé);

        }

    }
    public void bindeur(String direction){
        switch (direction){
            case "sud" :
                armeEquipé.getArmeVue().setImage(armeEquipé.getArmeImage());
                armeEquipé.getArmeVue().xProperty().bind(this.link.getXProperty().add(-3)); // Adjust offset as needed
                armeEquipé.getArmeVue().yProperty().bind(this.link.getYProperty().add(10)); // Adjust offset as needed
                break;
            case "nord" :
                armeEquipé.getArmeVue().setImage(armeEquipé.getArmeInversé());
                armeEquipé.getArmeVue().xProperty().bind(this.link.getXProperty().add(20));
                armeEquipé.getArmeVue().yProperty().bind(this.link.getYProperty().add(7));
                break;
            case "ouest" :
                armeEquipé.getArmeVue().setImage(armeEquipé.getArmeImage());
                armeEquipé.getArmeVue().xProperty().bind(this.link.getXProperty().add(-3)); // Adjust offset as needed
                armeEquipé.getArmeVue().yProperty().bind(this.link.getYProperty().add(5));
                break;
            case "est" :
                armeEquipé.getArmeVue().setImage(armeEquipé.getArmeInversé());
                armeEquipé.getArmeVue().xProperty().bind(this.link.getXProperty().add(15));
                armeEquipé.getArmeVue().yProperty().bind(this.link.getYProperty().add(10));
                break;
        }
    }
    public void ramasser(VueArmes vA) {
        for(int i=0 ; i<5;i++)
            if(inventaire.get(i)==null) {
                inventaire.add(i, vA);
                break;
            }
        ImageView armecase = new ImageView();
        int indice = inventaire.indexOf(vA);
        armecase.setImage(vA.getArmeVue().getImage());
        System.out.println(vA.getArmeVue().getImage().getHeight()+".........."+vA.getArmeVue().getImage().getWidth());
        armecase.setFitWidth(80);
        armecase.setFitHeight(80);
        vueArmesInventaire.getChildren().add(armecase);
        armecase.setX(vueCaseInventaire.getLayoutX()+(100*indice)+65);
        armecase.setY(40);
    }

    public ObservableList getInventaire(){return inventaire;}

    public Acteur getLink(){
        return this.link;
    }
}