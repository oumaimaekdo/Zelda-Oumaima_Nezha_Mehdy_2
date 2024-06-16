package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private IntegerProperty vie;


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
        this.vie = new SimpleIntegerProperty();
    }
    public Acteur(String nom, Champ m){
        this.nom=nom;
        this.champ=m;
        this.x.set(m.getLongueur()/2);
        this.y.set(m.getLargeur()/2);
        this.arme = null;
        this.vie = new SimpleIntegerProperty();
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

    public final int getVie(){ return vie.getValue(); }

    public final void setVie(int vie){ this.vie.setValue(vie);}

    public final IntegerProperty vieProperty(int vie){ return this.vie;}

    public int getVitesse() {
        return vitesse;
    }

    public ArrayList<Armes> armeAutour() {
        int rayon = 30;
        ArrayList<Armes> itemAutour = new ArrayList<>();
        for (Armes a : champ.getItem())
            if (!inventaire.contains(a))
                if ((this.getY() - rayon <= a.getY() && a.getY() <= this.getY() + rayon) && (this.getX() - rayon <= a.getX() && a.getX() <= this.getX() + rayon)){
                    itemAutour.add(a);
                }
        return itemAutour;
    }

    public ArrayList<Acteur> ennemiAutour() {
        int rayon = 30;
        ArrayList<Acteur> ennemi = new ArrayList<>();
        for (Acteur a : champ.getListEnnemi())
                if ((this.getY() - rayon <= a.getY() && a.getY() <= this.getY() + rayon) && (this.getX() - rayon <= a.getX() && a.getX() <= this.getX() + rayon)){
                    ennemi.add(a);
                }
        return ennemi;
    }
    public void ramasserAutour() {
        if (!armeAutour().isEmpty()){
            ramasser(armeAutour().get(0));
        }
    }


    public void attaquer(VueArmes armeEquipe, Acteur acteur) {
        if(armeEquipe.getArme().getNom().equals("bombe")){
            acteur.setVie(acteur.getVie()-armeEquipe.getArme().getDegats());
        }
        else{
            acteur.setVie(acteur.getVie()-armeEquipe.getArme().getDegats());
            System.out.println("l'acteur a : "+acteur.getVie()+"de vie");
        }

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
                inventaire.set(i,vA);
                break;
            }
        champ.getItem().remove(vA);
    }

    public void selectioner(int i){
        if(inventaire.get(i-1)!=null) {
            armeEquipé = inventaire.get(i-1);
            //armeEquipé.getYProperty().bind(this.y);
            //armeEquipé.getXProperty().bind(this.x);
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

    public Champ getChamp(){
        return this.champ;
    }

    public boolean estEnCollisionAvec(Acteur autre) {
        System.out.println("est en collission");
        return (this.getX() == autre.getX() && this.getY() == autre.getY());
    }

    public String getNom(){
        return this.nom;
    }

}
