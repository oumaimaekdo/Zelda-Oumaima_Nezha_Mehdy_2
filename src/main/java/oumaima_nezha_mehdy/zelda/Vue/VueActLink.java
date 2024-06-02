package oumaima_nezha_mehdy.zelda.Vue;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Armes.EpeeDeFer;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;

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
    private ObservableList<VueArmes> inventaire;
    private Armes epee = new EpeeDeFer();
    private static boolean attaquer = true;


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
        armeEquipé=new VueArmes(new Image("file:src/main/resources/images/epeeFer.png"),epee);
        ramasser(armeEquipé);
        VueArmes arcInventaire=new VueArmes(new Image("file:src/main/resources/images/arc.png"),new Armes("arc",25));
        ramasser(arcInventaire);

    }


    public void DeplacementLink(String key){
        System.out.println("\n \n \n" );
        System.out.println(key);
        switch (key) {
            case"Z" :
            case "UP":
                if(coordonnéPossible(this.link.getX(),this.link.getY()-(1*link.getVitesse())))
                link.seDeplacer("nord");
                this.vueLink.setImage(linkNord);
                break;
            case "Q":
            case "LEFT":
                if(coordonnéPossible(this.link.getX()-(1*link.getVitesse()),this.link.getY()))
                link.seDeplacer("ouest");
                this.vueLink.setImage(linkOuest);
                break;
            case "S":
            case "DOWN":
                if(coordonnéPossible(this.link.getX(),this.link.getY()+(1*link.getVitesse())))
                link.seDeplacer("sud");
                this.vueLink.setImage(linkSud);
                break;
            case "D":
            case "RIGHT":
                if(coordonnéPossible(this.link.getX()+(1*link.getVitesse()),this.link.getY()))
                link.seDeplacer("est");
                this.vueLink.setImage(linkEst);
                break;
            case "A":
                new Thread(() -> {
                    try{
                        link.attaquer(link, epee);
                        armeEquipé.vueAttaque(link,epee);
                        System.out.println("le perso attaque");
                        Thread.sleep(3000);
                        armeEquipé.vueRepos(new Image("file:src/main/resources/images/epeeFer.png"),epee,link);
                        System.out.println("le perso arrete l'attaque");

                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
                break;
        }

        System.out.println(link.getX()+","+link.getY());
        System.out.println(link.getX()/tT+","+link.getY()/tT);
    }
    private boolean coordonnéPossible(int x,int y){
        int haut,bas,gauche,droite;
        haut = 25;
        gauche = 15;
        bas = -20;
        droite = -10;
        boolean retourneur = x>=0&&y>=0&&x+15<this.champ.getLongueur()*tT&&y+15<this.champ.getLargeur()*tT;
        if(!retourneur)
            return false;
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
            armeEquipé.getArmeVue().xProperty().bind(this.link.getXProperty().add(17));
            armeEquipé.getArmeVue().yProperty().bind(this.link.getYProperty().add(10));
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
        armecase.setFitWidth(100);
        armecase.setFitHeight(100);
        vueArmesInventaire.getChildren().add(armecase);
        armecase.setX(vueCaseInventaire.getLayoutX()+(100*indice)+50);
        armecase.setY(25);
    }

    public ObservableList getInventaire(){return inventaire;}

    public Acteur getLink(){
        return this.link;
    }
}