package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

import java.util.List;

public class Acteur {

    public static int id= 0;;
    private String nom;

    private Champ champ;

    private int vitesse=10;

    private Armes armeEquipe;

    private List<Armes> inventaire;

    private static int vie = 100;


    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);


    public Acteur(String nom, int x , int y, Champ m){
        this.nom=nom;
        this.x.set(x);
        this.y.set(y);
        this.champ=m;
        this.armeEquipe = null;
        id += 1;
    }
    public Acteur(String nom, Champ m){
        this.nom=nom;
        this.champ=m;
        this.x.set(m.getLongueur()/2);
        this.y.set(m.getLargeur()/2);
        this.armeEquipe = null;
    }

    public void seDeplacer(String direction) {
        switch (direction) {
            case "nord":
                    this.y.set(this.y.getValue()-(1*vitesse));
                break;
            case "sud":
                    this.y.set(this.y.getValue()+(1*vitesse));
                break;
            case "ouest":
                    this.x.set(this.x.getValue()-(1*vitesse));
                break;
            case "est":
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


    public void attaquer(Acteur acteur,Armes armeEquipe) {
        acteur.setVie(acteur.getVie()-armeEquipe.getDegats());
        System.out.println("l'acteur a : "+acteur.getVie()+"de vie");
    }

    public Armes getArme() {
        return armeEquipe;
    }

    public void ajouterArme(Armes arme) {
        inventaire.add(arme);
    }

    public List<Armes> getInventaire() {
        return inventaire;
    }

    public Armes getArmeParIndex(int index) {
        if (index >= 0 && index < inventaire.size()) {
            return inventaire.get(index);
        }
        return null;
    }

    public void EquiperArme(Armes arme){
        this.armeEquipe = arme;
    }

}
