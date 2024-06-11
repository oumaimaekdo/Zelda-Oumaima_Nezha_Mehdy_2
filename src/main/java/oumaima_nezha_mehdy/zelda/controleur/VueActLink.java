package oumaima_nezha_mehdy.zelda.controleur;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import oumaima_nezha_mehdy.zelda.Univers.*;

import java.util.ArrayList;
import java.util.HashSet;

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

    private int numeroImage;

    private Timeline gameLoop;

    private ArrayList<VueArmes> vueInventaire;
    private ArrayList<VueArmes> vueItem;
    private HashSet<String> touchePressé;

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
        VueArmes a = new VueEpee(new EpeeFer(champ));
        VueArmesJeu.getChildren().add(a.getArmeVue());
        a.getArme().setX(50);
        a.getArme().setY(50);
        VueArmes b = new VueEpee(new EpeeFer(champ));
        VueArmesJeu.getChildren().add(b.getArmeVue());
        b.getArme().setX(100);
        b.getArme().setY(50);
        initAnimation();
        gameLoop.play();


    }

    public void ajouterTouche(String key){
        touchePressé.add(key);
    }



    public void DeplacementLink() {
        if (touchePressé.contains("D")){
            link.seDeplacer("est");
            directionregardé="est";

            this.vueLink.setImage(linkEst);
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
        if (touchePressé.contains("Z")) {
            link.seDeplacer("nord");
            directionregardé="nord";

            this.vueLink.setImage(linkNord);
        }
        if (armeEquipé!=null)
        bindeur(directionregardé);
    }
    public void toucheRelaché(KeyEvent keyEvent) {
            touchePressé.remove(keyEvent.getCode().toString());
    }

    public void re(){
        vueArmesInventaire.getChildren().remove(vueArmesInventaire.lookup("#case" + 1));
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
            VueArmesJeu.getChildren().remove(armeEquipé.getArmeVue());
        }
        if(vueInventaire.get(i-1)!=null) {
            armeEquipé = vueInventaire.get(i-1);
            VueArmesJeu.getChildren().add(armeEquipé.getArmeVue());
            bindeur(directionregardé);
        }

    }
    public void bindeur(String direction){
        switch (direction){
            case "sud" :
                armeEquipé.getArmeVue().setImage(armeEquipé.getArmeImage());
                armeEquipé.getArmeVue().xProperty().bind(this.armeEquipé.getArme().getXProperty().add(-3)); // Adjust offset as needed
                armeEquipé.getArmeVue().yProperty().bind(this.armeEquipé.getArme().getYProperty().add(10)); // Adjust offset as needed
                break;
            case "nord" :
                armeEquipé.getArmeVue().setImage(armeEquipé.getArmeInversé());
                armeEquipé.getArmeVue().xProperty().bind(this.armeEquipé.getArme().getXProperty().add(20));
                armeEquipé.getArmeVue().yProperty().bind(this.armeEquipé.getArme().getYProperty().add(7));
                break;
            case "ouest" :
                armeEquipé.getArmeVue().setImage(armeEquipé.getArmeImage());
                armeEquipé.getArmeVue().xProperty().bind(this.armeEquipé.getArme().getXProperty().add(-3)); // Adjust offset as needed
                armeEquipé.getArmeVue().yProperty().bind(this.armeEquipé.getArme().getYProperty().add(5));
                break;
            case "est" :
                armeEquipé.getArmeVue().setImage(armeEquipé.getArmeInversé());
                armeEquipé.getArmeVue().xProperty().bind(this.armeEquipé.getArme().getXProperty().add(15));
                armeEquipé.getArmeVue().yProperty().bind(this.armeEquipé.getArme().getYProperty().add(10));
                break;
        }
    }
    public void lacher(){
        if(armeEquipé!=null){
            armeEquipé.getArmeVue().xProperty().unbind();
            armeEquipé.getArmeVue().yProperty().unbind();
            armeEquipé=null;
        }
    }
    private void chargerInventaire(){
        for(int i=0;i<5;i++){
            vueInventaire.add(null);
        }
    }

    public Champ getChamp() {
        return champ;
    }

    public Acteur getLink(){
        return this.link;
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

    public VueArmes getArmeEquipé() {
        return armeEquipé;
    }

    public ArrayList<VueArmes> getVueInventaire() {
        return vueInventaire;
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
        );
        gameLoop.getKeyFrames().add(kf);
    }

    public ArrayList<VueArmes> getVueItem() {
        return vueItem;
    }

    public Pane getVueArmesJeu() {
        return VueArmesJeu;
    }
}