package oumaima_nezha_mehdy.zelda.Univers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class Champ {

    private int[] champ;

    private ArrayList<Bloc> listBloc =new ArrayList<>();

    private ArrayList<Acteur> listActeur = new ArrayList<>();

    private ObservableList<Armes> item;

    private Acteur link;


    private int longueur;

    private int largeur;

    private int tT;


    public Champ(int L , int l,int[] map){
        this.largeur=l;
        this.longueur=L;
        this.champ = map;
        this.link = new Acteur("Link",0,0,this);
        this.tT=64;
        this.item = FXCollections.observableArrayList();

    }



    public void ajouterActeur(Acteur a){
        listActeur.add(a);
    }

    public void ajouterItem(Armes a){
        item.add(a);
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



    public boolean coordonnéPossible(int x,int y){
        int haut,bas,gauche,droite;
        haut = 20;
        gauche = 20;
        bas = -20;
        droite = -10;
        boolean retourneur = x>=0&&y>=0&&x+15<this.longueur*tT&&y+haut<this.largeur*tT;
        if(!retourneur)
            return false;
        boolean collisionhautgauche =champ[((x+gauche)/tT) + ((y+haut)/tT)*(this.longueur)]!=2;
        boolean collisionbasdroite =champ[(x-droite)/tT + ((y-bas)/tT)*(this.longueur)]!=2;
        return retourneur&&(collisionhautgauche&&collisionbasdroite);
    }


    public int[] getChamp(){return champ;}

    public ArrayList<Acteur> getListActeur() {
        return listActeur;
    }

    public ObservableList<Armes> getItem() {
        return item;
    }

    public Acteur getLink() {
        return link;
    }


    public int getLongueur(){return longueur;}
    public int getLargeur(){return largeur;}

    public int gettT() {
        return tT;
    }
}
