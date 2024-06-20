package oumaima_nezha_mehdy.zelda.Vue;


import javafx.beans.InvalidationListener;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;



import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Armes.Bombe;
import oumaima_nezha_mehdy.zelda.modele.Armes.EpeeDeFer;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

import java.util.HashSet;



public class VueActLink {


    private Link link;
    @FXML
    private Pane vueActeur;

    private Champ champ;

    private Pane VueArmesJeu;



    @FXML
    private ImageView vueLink;
    @FXML
    private Button linkMort ;

    private Image linkNord;
    private Image linkSud;
    private Image linkEst;
    private Image linkOuest;

    private HBox vueCaseInventaire;

    private int tT;

    public VueOutils armeEquipé ;

    private Pane vueArmesInventaire;

    private String directionregardé;

    private Timeline gameLoop;

    private ArrayList<VueOutils> vueInventaire;
    private ArrayList<VueOutils> vueItem;

    private HashSet<String> touchePressé;

    private int numeroImage;

    private Armes epee ;




    public VueActLink(Pane pane, Champ c, int tailleTuile, Pane VueArmesJeu, HBox vueCaseInventaire, Pane vueArmesInventaire){
        vueActeur=pane;
        numeroImage=1;
        this.VueArmesJeu = VueArmesJeu;
        this.champ=c;
        this.link=champ.getLink();
        this.tT=tailleTuile;
        this.vueCaseInventaire=vueCaseInventaire;
        this.vueArmesInventaire=vueArmesInventaire;
        this.touchePressé = new HashSet<>();
        this.directionregardé="est";
        this.vueInventaire = new ArrayList<>();
        this.vueItem = new ArrayList<>();
        chargerInventaire();
        link.getInventaire().addListener(new InventaireObs(link,vueArmesInventaire,vueCaseInventaire,this));
        champ.getItem().addListener(new ChampItemObs(VueArmesJeu,this));
        creerlink("file:src/main/resources/images/link_profil.png",link);
        linkNord=new Image("file:src/main/resources/images/Link/nordDefault.png");
        linkSud=new Image("file:src/main/resources/images/Link/sudDefault.png");
        linkEst=new Image("file:src/main/resources/images/Link/estDefault.png");
        linkOuest=new Image("file:src/main/resources/images/Link/ouestDefault.png");
        this.epee = new EpeeDeFer(this.champ);
        initAnimation();
        gameLoop.play();
        Clé clé= new  Clé("CléNormal",this.champ);
        link.ramasser(clé);


    }

    public void ajouterTouche(String key){
        touchePressé.add(key);
    }

    public void DeplacementLink() {
        if (touchePressé.contains("Z")) {
            link.seDeplacer("nord");
            directionregardé="nord";

            this.vueLink.setImage(linkNord);
        }
        if (touchePressé.contains("Q")) {
            link.seDeplacer("ouest");
            directionregardé="ouest";

            this.vueLink.setImage(linkOuest);
        }
        if (touchePressé.contains("S")){
            link.seDeplacer("sud");
            directionregardé="sud";

            this.vueLink.setImage(linkSud);
        }
        if (touchePressé.contains("D")){
            link.seDeplacer("est");
            directionregardé="est";

            this.vueLink.setImage(linkEst);
        }
        if (touchePressé.contains("A")){
            if(armeEquipé!=null && armeEquipé instanceof VueArmes)
                new Thread(() -> {
                    try {
                        for(Acteur e :link.ennemiAutour()){
                            link.attaquer( ((VueArmes) armeEquipé).getArme(),e);
                            ((VueArmes) armeEquipé).vueAttaque(this,link,((VueArmes) armeEquipé).getArme());
                            System.out.println("le perso attaque");
                            Thread.sleep(300);
                            ((VueArmes) armeEquipé).getArmeVue().setFitWidth(15);
                            ((VueArmes) armeEquipé).getArmeVue().setFitHeight(15);
                            ((VueArmes) armeEquipé).vueRepos(new Image("file:src/main/resources/images/Armes/epeeFerInversé.png"), epee, link);
                            System.out.println("le perso arrete l'attaque");

                        }

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
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

    public void selectioner(int i){
        if(armeEquipé!=null) {
            VueArmesJeu.getChildren().remove(armeEquipé.getVue());
            System.out.println(VueArmesJeu.getChildren().isEmpty());
        }
        if(link.getInventaire().get(i-1)!=null) {
            armeEquipé = vueInventaire.get(i-1);
            VueArmesJeu.getChildren().add(armeEquipé.getVue());
            bindeur(directionregardé);

        }

    }
    public void bindeur(String direction){
        switch (direction){
            case "sud" :
                armeEquipé.getVue().setImage(armeEquipé.getImage());
                armeEquipé.getVue().xProperty().bind(this.link.getXProperty().add(-3)); // Adjust offset as needed
                armeEquipé.getVue().yProperty().bind(this.link.getYProperty().add(10)); // Adjust offset as needed
                break;
            case "nord" :
                armeEquipé.getVue().setImage(armeEquipé.getInversé());
                armeEquipé.getVue().xProperty().bind(this.link.getXProperty().add(20));
                armeEquipé.getVue().yProperty().bind(this.link.getYProperty().add(7));
                break;
            case "ouest" :
                armeEquipé.getVue().setImage(armeEquipé.getImage());
                armeEquipé.getVue().xProperty().bind(this.link.getXProperty().add(-3)); // Adjust offset as needed
                armeEquipé.getVue().yProperty().bind(this.link.getYProperty().add(5));
                break;
            case "est" :
                armeEquipé.getVue().setImage(armeEquipé.getInversé());
                armeEquipé.getVue().xProperty().bind(this.link.getXProperty().add(15));
                armeEquipé.getVue().yProperty().bind(this.link.getYProperty().add(10));
                break;
        }

    }

    public Link getLink(){
        return this.link;
    }


    public void lacher(){
        if(armeEquipé!=null){
            armeEquipé.getVue().xProperty().unbind();
            armeEquipé.getVue().yProperty().unbind();
            armeEquipé=null;
        }
    }

    private void chargerInventaire(){
        for(int i=0;i<5;i++){
            vueInventaire.add(null);
        }
    }

    public void toucheRelaché(KeyEvent keyEvent) {
        touchePressé.remove(keyEvent.getCode().toString());
    }

    public void animation(String direction) {
        switch (direction) {
            case "nord":
                    this.vueLink.setImage(new Image("file:src/main/resources/images/Link/Nord/Nord" + numeroImage + ".png"));
                break;
            case "sud":
                    this.vueLink.setImage(new Image("file:src/main/resources/images/Link/Sud/Sud" + numeroImage + ".png"));
                break;
            case "est":
                this.vueLink.setImage(new Image("file:src/main/resources/images/Link/Est/Est" + numeroImage + ".png"));
                break;
            case "ouest":
                this.vueLink.setImage(new Image("file:src/main/resources/images/Link/Ouest/Ouest" + numeroImage + ".png"));
                break;
            case "inactif":
                this.vueLink.setImage(new Image("file:src/main/resources/images/Link/"+directionregardé+"Default.png"));
                break;
        }
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(
                // on définit le FPS (nbre de frame par seconde)
                Duration.seconds(0.03),
                // on définit ce qui se passe à chaque frame
                // c'est un eventHandler d'ou le lambda
                (ev ->{
                    DeplacementLink();
                    if (touchePressé.isEmpty())
                        animation("inactif");
                    else
                    animation(directionregardé);
                    numeroImage++;
                    if(numeroImage>10)
                        numeroImage=1;

                })
        );gameLoop.getKeyFrames().add(kf);
    }
    public ArrayList<VueOutils> getVueInventaire() {
        return vueInventaire;
    }
    public Champ getChamp() {
        return champ;
    }

    public ArrayList<VueOutils> getVueItem() {
        return vueItem;
    }

    public void re(){
        vueArmesInventaire.getChildren().remove(vueArmesInventaire.lookup("#case" + 1));
    }

    public Pane getVueArmesJeu() {
        return VueArmesJeu;
    }


}