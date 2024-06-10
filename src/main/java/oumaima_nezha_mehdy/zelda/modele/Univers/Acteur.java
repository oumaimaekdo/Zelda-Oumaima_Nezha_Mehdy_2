package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.Vue.VueArmes;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

import java.util.ArrayList;

public class Acteur {

    public static int id= 0;;
    private String nom;

    private Champ champ;

    private int vitesse=10;

    private Armes arme;


    private ObservableList<Armes> inventaire;

    private static int vie = 100;


    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    private Armes armeEquipé;


    public Acteur(String nom, int x , int y, Champ m){
        this.nom=nom;
        this.x.set(x);
        this.y.set(y);
        this.champ=m;
        id += 1;
        this.arme = null;
        this.inventaire= FXCollections.observableArrayList();
        chargerInventaire();
    }
    public Acteur(String nom, Champ m){
        this.nom=nom;
        this.champ=m;
        this.x.set(m.getLongueur()/2);
        this.y.set(m.getLargeur()/2);
        this.arme = null;
    }

    public void seDeplacer(String direction) {
        switch (direction) {
            case "nord":
                if(champ.coordonnéPossible(this.getX(), this.getY() - (1 * this.getVitesse())))
                    this.y.set(this.y.getValue()-(1*vitesse));
                break;
            case "sud":
                if(champ.coordonnéPossible(this.getX(), this.getY() + (1 * this.getVitesse())))
                    this.y.set(this.y.getValue()+(1*vitesse));
                break;
            case "ouest":
                if(champ.coordonnéPossible(this.getX() - (1 * this.getVitesse()), this.getY()))
                    this.x.set(this.x.getValue()-(1*vitesse));
                break;
            case "est":
                if(this.champ.coordonnéPossible(this.getX()+(1*this.vitesse),this.getY()))
                    this.x.set(this.x.getValue()+(1*vitesse));
                break;
            default:
        }
    }
    public int getX(){return x.getValue();}

    public int getY(){return y.getValue();}

    public static String getId() {
        return "#"+id;
    }
    public IntegerProperty getXProperty(){return x;}
    public IntegerProperty getYProperty(){return y;}

    public void setX(int x){this.x.setValue(x);}
    public void setY(int y){this.y.setValue(y);}

    public int getVie(){ return this.vie; }

    public void setVie(int vie){ this.vie = vie;}

    public int getVitesse() {
        return vitesse;
    }

    public ArrayList<Armes> armeAutour() {
        ArrayList<Armes> itemAutour = new ArrayList<>();
        for (Armes a : champ.getItem())
            if (!inventaire.contains(a))
                if ((this.getY() - 5 <= a.getY() && a.getY() <= this.getY() + 5) && (this.getX() - 5 <= a.getX() && a.getX() <= this.getX() + 5))
                    itemAutour.add(a);
        return itemAutour;
    }
    public void ramasserAutour() {
        if (!armeAutour().isEmpty()){
            ramasser(armeAutour().get(0));
        }
    }


    public void attaquer(VueArmes armeEquipe, Acteur acteur) {
        acteur.setVie(acteur.getVie()-armeEquipe.getArme().getDegats());
        System.out.println("l'acteur a : "+acteur.getVie()+"de vie");
    }

    public Armes getArme() {
        return arme;
    }

    public void ajouterArme(Armes arme) {
        inventaire.add(arme);
    }

    public ObservableList<Armes> getInventaire() {
        return inventaire;
    }

    public Armes getArmeParIndex(int index) {
        if (index >= 0 && index < inventaire.size()) {
            return inventaire.get(index);
        }
        return null;
    }
    private void chargerInventaire(){
        for(int i=0;i<5;i++){
            inventaire.add(null);
        }
    }
    public void ramasser(Armes vA) {
        for(int i=0 ; i<5;i++)
            if(inventaire.get(i)==null) {
                System.out.println("Arme ramassée: " + vA.getNom());
                inventaire.add(i,vA);
                break;
            }

        champ.getItem().remove(vA);
    }

    public void selectioner(int i){
        if(inventaire.get(i-1)!=null) {
            armeEquipé = inventaire.get(i-1);
            armeEquipé.getYProperty().bind(this.y);
            armeEquipé.getXProperty().bind(this.x);
        }

    }
    public void lacher(){
        if(armeEquipé!=null){
            int indice = inventaire.indexOf(armeEquipé);
            inventaire.set(indice,null);
            champ.ajouterItem(armeEquipé);
            armeEquipé.getYProperty().unbind();
            armeEquipé.getXProperty().unbind();
            armeEquipé=null;
        }
    }

}
