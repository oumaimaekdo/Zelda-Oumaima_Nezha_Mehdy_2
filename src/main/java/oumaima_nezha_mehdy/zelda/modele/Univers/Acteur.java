package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

import java.util.ArrayList;

public class Acteur {

    public static int id= 0;;
    private String nom;

    private Champ champ;

    private int vitesse=10;


    private ObservableList<Outils> inventaire;

    private static DoubleProperty vie ;


    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    private Outils armeEquipé;


    public Acteur(String nom, int x , int y, Champ m){
        this.nom=nom;
        this.x.set(x);
        this.y.set(y);
        this.champ=m;
        id += 1;
        this.inventaire= FXCollections.observableArrayList();
        chargerInventaire();
        this.vie =new SimpleDoubleProperty(100);
    }

    public Acteur(String nom, Champ m){
        this.nom=nom;
        this.champ=m;
        this.x.set(m.getLongueur()/2);
        this.y.set(m.getLargeur()/2);
        this.vie =new SimpleDoubleProperty(100);
    }

    public boolean enVie(){
        return getVie()>0;
    }

    public void seDeplacer(String direction) {
        if(enVie()){
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

    public final double getVie(){ return vie.getValue(); }

    public final void setVie(double vie){ if (vie >= 0) this.vie.setValue(vie);}

    public final DoubleProperty vieProperty(){ return this.vie;}

    public int getVitesse() {
        return vitesse;
    }

    public ArrayList<Outils> armeAutour() {
        int rayon = 30;
        ArrayList<Outils> itemAutour = new ArrayList<>();
        for (Outils a : champ.getItem())
            if (!inventaire.contains(a))
                if ((this.getY() - rayon <= a.getY() && a.getY() <= this.getY() + rayon) && (this.getX() - rayon <= a.getX() && a.getX() <= this.getX() + rayon)){
                    itemAutour.add(a);
                }
        return itemAutour;
    }

    public ArrayList<Acteur> ennemiAutour() {
        int rayon = 50;
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


    public void attaquer(Armes armeEquipe, Acteur acteur) {
        acteur.setVie(acteur.getVie()-armeEquipe.getDegats());
        System.out.println("l'acteur a : "+acteur.getVie()+"de vie");
    }


    public ArrayList<Coffre> InteragirCoffreAutour(){
        ArrayList<Coffre> coffreAutour = new ArrayList<>();
        int rayon = 30;
        for (Coffre c : champ.getListBloc()){
            if ((this.getY() - rayon <= c.getY() && c.getY() <= this.getY() + rayon) && (this.getX() - rayon <= c.getX() && c.getX() <= this.getX() + rayon))
            coffreAutour.add(c);
        }
        return coffreAutour;
    }
    public void interagirCoffre(){
        if(!InteragirCoffreAutour().isEmpty()) {
            Coffre c = InteragirCoffreAutour().get(0);
            if(armeEquipé!=null) {
                if (armeEquipé.getNom().equals(c.getCléRequise()) || c.getouvertProperty().getValue()) {
                    c.interagir();
                    System.out.println(inventaire.indexOf(armeEquipé));
                }
            }
            else if(c.getouvert())
                c.interagir();

            if(c.getNbInteraction()>1)
                for(Armes a : c.getContenu())
                ramasser(a);

        }
    }


    public void ajouterArme(Armes arme) {
        inventaire.add(arme);
    }

    public ObservableList<Outils> getInventaire() {
        return inventaire;
    }

    public Outils getArmeParIndex(int index) {
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
    public void ramasser(Outils vA) {
        for(int i=0 ; i<5;i++)
            if(inventaire.get(i)==null) {
                inventaire.set(i,vA);
                break;
            }
        champ.getItem().remove(vA);
    }

    public void selectioner(int i){
        armeEquipé=null;
        if(inventaire.get(i-1)!=null) {
            armeEquipé = inventaire.get(i-1);

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

    public void estMort(){

    }

}
