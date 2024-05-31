package oumaima_nezha_mehdy.zelda.Univers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

import java.util.*;


public class Acteur {
    private String nom;

    private Champ champ;
    private static int id = 0;
    private int vitesse = 10;
    private ArrayList<String> inventaire;

    private String direction, directionActuelle = "nord";

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);
    private IntegerProperty pointsDeVie;

    public Acteur(String nom, int x, int y, Champ m) {
        this.nom = nom;
        this.x.set(x);
        this.y.set(y);
        this.champ = m;
        this.pointsDeVie = new SimpleIntegerProperty(50);
        this.inventaire = new ArrayList<>();
        id += 1;
    }

    public Acteur(String nom, Champ m) {
        this.nom = nom;
        this.champ = m;
        this.pointsDeVie =  new SimpleIntegerProperty(50);
        this.x.set(m.getLongueur() / 2);
        this.y.set(m.getLargeur() / 2);
    }

    public void seDeplacer(String direction) {

        this.directionActuelle = direction;

        int x2base, y2base;
        x2base = this.x.getValue();
        y2base = this.y.getValue();

        switch (direction) {
            case "nord":
                this.y.set(this.y.getValue() - (1 * vitesse));
                break;
            case "sud":
                this.y.set(this.y.getValue() + (1 * vitesse));
                break;
            case "ouest":
                this.x.set(this.x.getValue() - (1 * vitesse));
                break;
            case "est":
                this.x.set(this.x.getValue() + (1 * vitesse));
                break;
            default:
        }


        if (!coordonnéPossible(this.x.getValue(), this.y.getValue())) {
            this.x.set(x2base);
            this.y.set(y2base);
        }


    }

    private boolean coordonnéPossible(int x, int y) {
        boolean retourneur = x >= 0 && y >= 0 && x <= this.champ.getLongueur() * 64 && y <= this.champ.getLargeur() * 64;
        x -= 0;
        y -= 5;
        int indice = x / 64 + ((y / 64) * (champ.getLongueur()));
        boolean collision = champ.getChamp()[indice] != 2;
        x += 10;
        y += 30;
        indice = x / 64 + ((y / 64) * (champ.getLongueur()));
        return retourneur && (collision && champ.getChamp()[indice] != 2);

    }

    public boolean ennemis() {
        return !nom.equals("link");
    }

    //remplir
    public void ajouterObjet(String objet) {
        inventaire.add(objet);
    }

    //remplir
    public List<String> getInventaire() {
        return inventaire;
    }


    public int getX() {
        return x.getValue();
    }

    public int getY() {
        return y.getValue();
    }

    public void setX(int n) {
        this.x.setValue(n);
    }

    public void setY(int n) {
        this.y.setValue(n);
    }

    public String getDirection() {
        return this.directionActuelle;
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    public IntegerProperty getYProperty() {
        return y;
    }

    public static String getId() {
        return "#" + id;
    }

    public boolean estEnCollisionAvec(Acteur autreActeur) {
        return this.getX() == autreActeur.getX() && this.getY() == autreActeur.getY();
    }

    //changer la valeur, elle change en fonction du personnage
    public void attaquer(Acteur cible){
        cible.reduirePointsDeVie(10);
    }

    public void reduirePointsDeVie(int pointsEnMoins){
        this.pointsDeVie.set(this.pointsDeVie.get()-pointsEnMoins);
    }

    public int getPointsDeVie() {
        return pointsDeVie.getValue();
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie.setValue(pointsDeVie);
    }

    public IntegerProperty pointsDeVieProperty() {
        return pointsDeVie;
    }

    public Champ getChamp(){ return this.champ = champ; }
}
