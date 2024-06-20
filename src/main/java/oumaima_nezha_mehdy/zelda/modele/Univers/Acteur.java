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

    protected Champ champ;

    private int vitesse=10;



    private DoubleProperty vie ;

    private IntegerProperty maxPointsDeVie;

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    private Outils armeEquipé;


    public Acteur(String nom, int x , int y, Champ m){
        this.nom=nom;
        this.x.set(x);
        this.y.set(y);
        this.champ=m;
        id += 1;

        this.vie =new SimpleDoubleProperty(100);
        this.maxPointsDeVie =new SimpleIntegerProperty(100);
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


    public final double getMaxVie(){ return maxPointsDeVie.getValue(); }

    public final void setMaxVie(double vie){ if (vie >= 0) this.maxPointsDeVie.setValue(vie);}

    public final IntegerProperty maxVieProperty(){ return this.maxPointsDeVie;}

    public int getVitesse() {
        return vitesse;
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



    public void attaquer(Armes armeEquipe, Acteur acteur) {
        acteur.setVie(acteur.getVie()-armeEquipe.getDegats());
        System.out.println("l'acteur a : "+acteur.getVie()+"de vie");
    }







    public Champ getChamp(){
        return this.champ;
    }

    public boolean estEnCollisionAvec(Acteur autre) {
        System.out.println("est en collision");
        return (this.getX() == autre.getX() && this.getY() == autre.getY());
    }

    public String getNom(){
        return this.nom;
    }

    public void estMort(){

    }

}
