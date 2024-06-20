package oumaima_nezha_mehdy.zelda.modele.Univers;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Coffre;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Outils;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Champ {

    private int[] champ;

    private ObservableList<Coffre> listCoffre =FXCollections.observableArrayList();

    private ArrayList<Acteur> listActeur = new ArrayList<>();
    private ArrayList<Ennemi> listEnnemi = new ArrayList<>();
    private ArrayList<DonneurQuetes> listQuetes = new ArrayList<>();

    private ObservableList<Outils> item;
    private Link link;

    private int longueur;

    private int largeur;

    private int tT;

    private Ennemi boss;
    private Ennemi sbir2;
    private Ennemi sbir1;
    private DonneurQuetes Arthur;
    private DonneurQuetes Vanessa;

    public Champ(int L , int l,int[] map){
        this.largeur=l;
        this.longueur=L;
        this.champ = map;
        this.link = new Link("Link",0,700,this);
        this.sbir1 = new Sbir("sbir1",450,650,this);
        listEnnemi.add(sbir1);
        this.sbir2 = new Sbir("sbir2",650,650,this);
        listEnnemi.add(sbir2);
        this.boss = new Boss("Volcanorax",650,650,this);
        listEnnemi.add(boss);
        this.tT=64;
        this.item = FXCollections.observableArrayList();

        this.Arthur = new DonneurQuetes("Arthur",920,410,this);
        listQuetes.add(Arthur);
        this.Vanessa = new DonneurQuetes("Vanessa",730,850,this);
        listQuetes.add(Vanessa);
    }



    public void ajouterActeur(Acteur a){
        listActeur.add(a);
    }

    public void afficherMap(){
        for(int i=0; i<champ.length;i++) {
            if(i%longueur==0) {
                System.out.println("");
            }
            System.out.print(champ[i]);
            System.out.print("\t");
        }
    }



    public boolean coordonnéPossible(int x, int y) {
        // Define the boundary offsets
        final int haut = 20;
        final int gauche = 20;
        final int bas = -20;
        final int droite = -10;

        // Check initial boundary conditions
        if (x < 0 || y < 0 || x + haut >= this.longueur * tT || y + gauche >= this.largeur * tT) {
            return false;
        }

        // Calculate grid indices once
        int indexHautGauche = ((x + gauche) / tT) + ((y + haut) / tT) * this.longueur;
        int indexBasDroite = ((x - droite) / tT) + ((y - bas) / tT) * this.longueur;

        // Check for collisions
        boolean collisionHautGauche = champ[indexHautGauche] != 1;
        boolean collisionBasDroite = champ[indexBasDroite] != 1;
        int rayon=40;
        for(Coffre b : listCoffre)
            if(x<b.getX()+rayon&&x>b.getX()-(rayon-15)&&y<b.getY()+rayon&&y>b.getY()-(rayon-15))
                return b.getpassable();

        // Return the final condition
        return collisionHautGauche && collisionBasDroite;
    }

    public boolean presenceArme(int x, int y){

        final int haut = 20;
        final int gauche = 20;
        final int bas = -20;
        final int droite = -10;

        int indexHautGauche = ((x + gauche) / tT) + ((y + haut) / tT) * this.longueur;
        int indexBasDroite = ((x - droite) / tT) + ((y - bas) / tT) * this.longueur;

        boolean presenceHautGauche = champ[indexHautGauche] == 2;
        boolean presenceBasDroite = champ[indexBasDroite] == 2;

        if(presenceHautGauche && presenceBasDroite){
            champ[indexHautGauche]=0;
            champ[indexBasDroite] = 0;

        }

        return (presenceHautGauche && presenceBasDroite);

    }
    public void ajouterCoffre(Coffre b){
        listCoffre.add(b);
    }

    public ObservableList<Coffre> getListBloc() {
        return listCoffre;
    }

    public boolean presencePortail(int x, int y){

        final int haut = 20;
        final int gauche = 20;
        final int bas = -20;
        final int droite = -10;

        int indexHautGauche = ((x + gauche) / tT) + ((y + haut) / tT) * this.longueur;
        int indexBasDroite = ((x - droite) / tT) + ((y - bas) / tT) * this.longueur;

        boolean presenceHautGauche = champ[indexHautGauche] == -1;
        boolean presenceBasDroite = champ[indexBasDroite] == -1;

        return (presenceHautGauche && presenceBasDroite);

    }

    public void ajouterItem(Outils a){
        item.add(a);
    }

    public int[] getChamp(){return champ;}

    public ArrayList<Acteur> getListActeur() {
        return listActeur;
    }
    public ArrayList<Ennemi> getListEnnemi() {
        return listEnnemi;
    }


    public ObservableList<Outils> getItem() {
        return item;
    }

    public Link getLink() {
        return link;
    }

    public Ennemi getSbir() {
        return sbir1;
    }
    public Ennemi getSbir2() {
        return sbir2;
    }

    public Ennemi getBoss() {return boss;}

    public DonneurQuetes getArthur() {
        return Arthur;
    }
    public DonneurQuetes getVanessa() {
        return Vanessa;
    }


    public int getLongueur(){return longueur;}
    public int getLargeur(){return largeur;}

    public int gettT() {
        return tT;
    }

    public void setChamp(int L , int l,int[] map){
        this.largeur=l;
        this.longueur=L;
        this.champ = map;
        this.tT=64;
    }

    public void mortActeur(Acteur acteur){
        listActeur.remove(acteur);
        listEnnemi.remove(acteur);
    }


}
