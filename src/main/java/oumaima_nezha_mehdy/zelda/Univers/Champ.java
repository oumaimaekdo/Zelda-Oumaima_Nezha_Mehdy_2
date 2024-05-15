package oumaima_nezha_mehdy.zelda.Univers;


import java.util.ArrayList;

public class Champ {

    private int[] champ;

    private ArrayList<Bloc> listBloc =new ArrayList<>();

    private ArrayList<Acteur> listActeur = new ArrayList<>();

    private Acteur link;

    private int longueur;

    private int largeur;

    public Champ(int L , int l){
        this.largeur=l;
        this.longueur=L;
        this.champ = new int[l];
        this.link = new Acteur("Link",0,0,this);
        listActeur.add(link);
        genererMap();
        raffraichirMap();

    }

    public Champ(int L , int l,int[] map){
        this.largeur=l;
        this.longueur=L;
        this.champ = map;
        //this.link = new Acteur("Link",0,0,this);
        //listActeur.add(link);
        raffraichirMap();

    }


    private void genererMap(){
        for(int i=0; i<champ.length;i++) {
            double x, y;

            double col = i % 10;
            double lig = Math.floor(i/10);

            x = col * 10;
            y = lig * 10;

            if (Math.random() > 0.25) {
                listBloc.add(new Terre(x, y));
                champ[i] = 0;
            } else {
                listBloc.add(new Mur(x, y));
                champ[i] = 1;
            }
        }

    }

    public void ajouterActeur(Acteur a){
        listActeur.add(a);
        champ[a.getY()]=2;
    }

    public void afficherMap(){
        raffraichirMap();
        for(int i=0; i<champ.length;i++) {

            if(i%longueur == 0){
                System.out.println("");
            }

            System.out.print(champ[i]);
            System.out.print("\t");


        }
    }

    private void raffraichirMap() {

        for (int i = 0; i < champ.length; i++) {
            switch(champ[i]) {
                case 0:champ[i] = 0;
                    break;
                case 1 : champ[i] = 1;
            }
        }
        // Réinitialiser champ à 0

        /*
        for (int i = 0; i < champ.length; i++) {
            for (int j = 0; j < champ[i].length; j++) {
                champ[i][j] = 0;
            }
        }


        for (Bloc b : listBloc) {
            double x = b.getX();
            double y = b.getY();
            if (x >= 0 && x < champ[0].length && y >= 0 && y < champ.length) {
                if (b.getpassable()) {
                    champ[y][x] = 0;
                } else {
                    champ[y][x] = 1;
                }
            }

            for(int i=0; i < champ.length; i++){

                switch(champ[i]){
                    case 0 : champ[i] = 0;
                    break;
                    case 1 : champ[i] = 1;
                }
            }
        }

        /*

        // on sépare l'acteur de l'environnement

        for (Acteur a : listActeur) {
            int x = a.getX();
            int y = a.getY();
            if (x >= 0 && x < champ[0].length && y >= 0 && y < champ.length) {
                champ[y][x] = 2;
            }
        }
         */
    }

    public int[] getChamp(){return champ;}

    public boolean gererLesCollisions(int x, int y) {
        //
        if (x < 0 || y < 0 || x >= longueur || y >= largeur) {
            return false;
        }

        return champ[y * longueur + x] != 1;
    }

    public boolean peutTraverserBloc(int x ,int y){
        for (Bloc b : listBloc){
            if(Math.floor(b.getX())==x&&Math.floor(b.getY())==y)
                return b.getpassable();
        }
        return true;
    }

    public ArrayList<Acteur> getListActeur() {
        return listActeur;
    }

    public Acteur getLink() {
        return link;
    }

    public int getLongueur(){return longueur;}
    public int getLargeur(){return largeur;}
}
