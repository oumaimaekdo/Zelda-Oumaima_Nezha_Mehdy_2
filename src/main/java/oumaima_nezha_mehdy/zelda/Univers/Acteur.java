package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;
import java.util.List;

public class Acteur {
    private String nom;
    private Champ champ;
    private static int id = 0;
    private int vitesse = 10;
    private ArrayList<String> inventaire;
    private String directionActuelle = "nord";

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
        this.pointsDeVie = new SimpleIntegerProperty(50);
        this.x.set(m.getLongueur() / 2 * 64);
        this.y.set(m.getLargeur() / 2 * 64);
    }

    public void seDeplacer(String direction) {
        this.directionActuelle = direction;

        int xBase = this.x.get();
        int yBase = this.y.get();

        switch (direction) {
            case "nord":
                this.y.set(this.y.get() - (1 * vitesse));
                break;
            case "sud":
                this.y.set(this.y.get() + (1 * vitesse));
                break;
            case "ouest":
                this.x.set(this.x.get() - (1 * vitesse));
                break;
            case "est":
                this.x.set(this.x.get() + (1 * vitesse));
                break;
            default:
                break;
        }

        if (!coordonneesPossibles(this.x.get(), this.y.get())) {
            this.x.set(xBase);
            this.y.set(yBase);
        }
    }

    private boolean coordonneesPossibles(int x, int y) {
        int longueurChamp = this.champ.getLongueur();
        int largeurChamp = this.champ.getLargeur();
        int tailleTuile = 64;

        boolean retourneur = x >= 0 && y >= 0 && x < longueurChamp * tailleTuile && y < largeurChamp * tailleTuile;
        x -= 0;
        y -= 5;
        int indice = x / tailleTuile + ((y / tailleTuile) * longueurChamp);
        boolean collision = champ.getChamp()[indice] != 2;
        x += 10;
        y += 30;
        indice = x / tailleTuile + ((y / tailleTuile) * longueurChamp);
        return retourneur && (collision && champ.getChamp()[indice] != 2);
    }

    public boolean ennemis() {
        return !nom.equals("link");
    }

    public void ajouterObjet(String objet) {
        inventaire.add(objet);
    }

    public List<String> getInventaire() {
        return inventaire;
    }

    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }

    public void setX(int n) {
        this.x.set(n);
    }

    public void setY(int n) {
        this.y.set(n);
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
        if (autreActeur == null) {
            return false;
        }
        return this.getX() == autreActeur.getX() && this.getY() == autreActeur.getY();
    }

    public void attaquer(Acteur cible) {
        cible.reduirePointsDeVie(10);
    }

    public void reduirePointsDeVie(int pointsEnMoins) {
        this.pointsDeVie.set(this.pointsDeVie.get() - pointsEnMoins);
    }

    public int getPointsDeVie() {
        return pointsDeVie.get();
    }

    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie.set(pointsDeVie);
    }

    public IntegerProperty pointsDeVieProperty() {
        return pointsDeVie;
    }

    public Champ getChamp() {
        return this.champ;
    }
}
