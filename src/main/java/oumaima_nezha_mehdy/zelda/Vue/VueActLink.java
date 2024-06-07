package oumaima_nezha_mehdy.zelda.Vue;


import javafx.scene.layout.TilePane;
import oumaima_nezha_mehdy.zelda.Vue.VueArmes;


import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.controleur.Controleur;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Armes.EpeeDeFer;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.HashSet;



public class VueActLink {


    private Acteur link;
    private VueChamp vueChamp;

    @FXML
    private Pane vueActeur;

    private Champ champ;

    private Pane VueArmesJeu;

    @FXML
    private TilePane armesMap;

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

    private Timeline gameLoop;

    private ObservableList<VueArmes> inventaire;
    private HashSet<String> touchePressé;

    private Armes epee = new EpeeDeFer();
    private int numeroImage;



    public VueActLink(Pane pane, Champ c, int tailleTuile, Pane VueArmesJeu, HBox vueCaseInventaire, Pane vueArmesInventaire, TilePane armesMap,VueChamp vueChamp){
        vueActeur=pane;
        numeroImage=1;
        this.VueArmesJeu = VueArmesJeu;
        this.champ=c;
        this.armesMap = armesMap;
        this.link=champ.getLink();
        this.tT=tailleTuile;
        this.vueChamp = vueChamp;
        this.vueCaseInventaire=vueCaseInventaire;
        this.vueArmesInventaire=vueArmesInventaire;
        this.inventaire = FXCollections.observableArrayList();
        this.touchePressé = new HashSet<>();
        this.directionregardé="est";
        creerlink("file:src/main/resources/images/link_profil.png",link);
        linkNord=new Image("file:src/main/resources/images/Link/NordDefault.png");
        linkSud=new Image("file:src/main/resources/images/Link/SudDefault.png");
        linkEst=new Image("file:src/main/resources/images/Link/EstDefault.png");
        linkOuest=new Image("file:src/main/resources/images/Link/OuestDefault.png");
        chargerInventaire();
        VueArmes vA1=new VueArmes(new Image("file:src/main/resources/images/Armes/epeeFer.png"),new Armes("epee",20),new Image("file:src/main/resources/images/Armes/epeeFerInversé.png"));
        ramasser(vA1);
        VueArmes arcInventaire=new VueArmes(new Image("file:src/main/resources/images/Armes/arc.png"),new Armes("arc",25),new Image("file:src/main/resources/images/Armes/arcInversé.png"));
        ramasser(arcInventaire);
        initAnimation();
        gameLoop.play();


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
            new Thread(() -> {
                try {
                    link.attaquer(link, epee);
                    armeEquipé.vueAttaque(link, epee);
                    System.out.println("le perso attaque");
                    Thread.sleep(3000);
                    armeEquipé.vueRepos(new Image("file:src/main/resources/images/Armes/epeeFerInversé.png"), epee, link);
                    System.out.println("le perso arrete l'attaque");

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
        if (touchePressé.contains("E")){
            if (champ.presenceArme(link.getX(),link.getY())) {
                ramasser(new VueArmes(new Image("file:src/main/resources/images/Armes/epeerouge.png"), new Armes("epeeRouge", 35)));
                this.armesMap.getChildren().remove(armesMap.lookup("#epeerouge"));


            }
        }
        if (touchePressé.contains("L")){
            if(armeEquipé != null){
                lacher();
            }
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
        armecase.setFitWidth(70);
        armecase.setFitHeight(70);
        armecase.setId("case"+indice);
        vueArmesInventaire.getChildren().add(armecase);
        armecase.setX(vueCaseInventaire.getLayoutX()+(100*indice)+65);
        armecase.setY(40);

    }

    public ObservableList getInventaire(){return inventaire;}

    public Acteur getLink(){
        return this.link;
    }


    public void lacher(){
        if(armeEquipé!=null){
            int indice = inventaire.indexOf(armeEquipé);
            armeEquipé.getArmeVue().xProperty().unbind();
            armeEquipé.getArmeVue().yProperty().unbind();
            vueArmesInventaire.getChildren().remove(vueArmesInventaire.lookup("#case"+indice));
            armeEquipé=null;
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
}