package oumaima_nezha_mehdy.zelda.modele.Univers;


import java.util.ArrayList;
import java.util.Arrays;

public class Champ {

    private int[] champ;

    private ArrayList<Bloc> listBloc =new ArrayList<>();

    private ArrayList<Acteur> listActeur = new ArrayList<>();

    private ArrayList<Integer> pointDeCollision;

    private Acteur link;
    private Sbir sbir;

    private int longueur;

    private int largeur;

    private int tT;

    public Champ(int L , int l,int[] map){
        this.largeur=l;
        this.longueur=L;
        this.champ = map;
        this.link = new Acteur("Link",0,700,this);
        this.sbir = new Sbir("Squelette",50,50,this);
        this.tT=64;
        this.pointDeCollision = new ArrayList<Integer>(Arrays.asList(5,6,7,8));

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



    /*public boolean coordonnéPossible(int x,int y){
        int haut,bas,gauche,droite;
        haut = 20;
        gauche = 20;
        bas = -20;
        droite = -10;
        boolean retourneur = x>=0&&y>=0&&x+15<this.longueur*tT&&y+15<this.largeur*tT;
        if(!retourneur)
            return false;
        boolean collisionhautgauche =champ[((x+gauche)/tT) + ((y+haut)/tT)*(this.longueur)]!=2;

        /*for(int i : pointDeCollision){
            boolean b = champ[((x+gauche)/tT) + ((y+haut)/tT)*(this.longueur)]!=i;

        }
        boolean collisionmaison1 =champ[((x+gauche)/tT) + ((y+haut)/tT)*(this.longueur)]!=5;
        boolean collisionmaison2 =champ[((x+gauche)/tT) + ((y+haut)/tT)*(this.longueur)]!=6;
        boolean collisionmaison3 =champ[((x+gauche)/tT) + ((y+haut)/tT)*(this.longueur)]!=7;
        boolean collisionmaison4 =champ[((x+gauche)/tT) + ((y+haut)/tT)*(this.longueur)]!=8;
        boolean collisionbasdroite =champ[(x-droite)/tT + ((y-bas)/tT)*(this.longueur)]!=2;
        return retourneur&&(collisionhautgauche&&collisionbasdroite&&collisionmaison1&&collisionmaison2&&collisionmaison3&&collisionmaison4);
    }*/

    public boolean coordonnéPossible(int x, int y) {
        // Define the boundary offsets
        final int haut = 20;
        final int gauche = 20;
        final int bas = -20;
        final int droite = -10;

        // Check initial boundary conditions
        if (x < 0 || y < 0 || x + 15 >= this.longueur * tT || y + 15 >= this.largeur * tT) {
            return false;
        }

        // Calculate grid indices once
        int indexHautGauche = ((x + gauche) / tT) + ((y + haut) / tT) * this.longueur;
        int indexBasDroite = ((x - droite) / tT) + ((y - bas) / tT) * this.longueur;

        // Check for collisions
        boolean collisionHautGauche = champ[indexHautGauche] != 2;
        boolean collisionBasDroite = champ[indexBasDroite] != 2;

        // Define collision values for houses
        int[] maisonCollisions = {1};
        for (int maison : maisonCollisions) {
            if (champ[indexHautGauche] == maison) {
                return false;
            }
        }

        // Return the final condition
        return collisionHautGauche && collisionBasDroite;
    }

    public int[] getChamp(){return champ;}

    public ArrayList<Acteur> getListActeur() {
        return listActeur;
    }



    public Acteur getLink() {
        return link;
    }
    public Sbir getSbir() {
        return sbir;
    }



    public int getLongueur(){return longueur;}
    public int getLargeur(){return largeur;}

    public int gettT() {
        return tT;
    }
}
