package oumaima_nezha_mehdy.zelda.modele.Outils.Arme;

import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Outils;

public abstract class Armes extends Outils {
    private String nom;
    private int degats;

    private String id;

    private Champ champ;



    public Armes(String nom, int degats,Champ champ) {
        super(nom,champ);
        this.degats = degats;
        champ.ajouterItem(this);
    }

    public String getNom() {
        return super.getNom();
    }

    public int getDegats() {
        return degats;
    }


    public void utiliser() {
        System.out.println("L'arme " + nom + " a été utilisée, infligeant " + degats + " dégâts.");
    }


    public Champ getChamp() {
        return champ;
    }

    public void setDegats(int degats) {
        this.degats = degats;
    }
}