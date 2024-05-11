package oumaima_nezha_mehdy.zelda.Univers;


import java.util.ArrayList;

public class Champ {

    private int[][] champ;

    private ArrayList<Bloc> listBloc =new ArrayList<>();

    private ArrayList<Acteur> listActeur = new ArrayList<>();

    private Acteur link;

    private int longueur;

    private int largeur;
    public Champ(int L , int l){
        this.largeur=l;
        this.longueur=L;
        this.champ = new int[l][L];
        this.link = new Acteur("Link",0,0,this);
        listActeur.add(link);
        genererMap();
        raffraichirMap();

    }


    private void genererMap(){
        for(int i=0; i<champ.length;i++)
            for (int j =0;j<champ[i].length ; j++){
                if(Math.random()>0.25) {
                    listBloc.add(new Terre(j, i));
                    champ[i][j]=0;
                }
                else {
                    listBloc.add(new Mur(j, i));
                    champ[i][j]=1;
                }
            }

    }

    public void ajouterActeur(Acteur a){
        listActeur.add(a);
        champ[a.getY()][a.getX()]=2;
    }

    public void afficherMap(){
        raffraichirMap();
        for(int i=0; i<champ.length;i++) {
            for (int j = 0; j < champ[i].length; j++) {
                System.out.print(champ[i][j]);
                System.out.print("\t");
            }
            System.out.println("");
        }
    }

    private void raffraichirMap() {
        // Réinitialiser champ à 0
        for (int i = 0; i < champ.length; i++) {
            for (int j = 0; j < champ[i].length; j++) {
                champ[i][j] = 0;
            }
        }

        for (Bloc b : listBloc) {
            int x = b.getX();
            int y = b.getY();
            if (x >= 0 && x < champ[0].length && y >= 0 && y < champ.length) {
                if (b.getpassable()) {
                    champ[y][x] = 0;
                } else {
                    champ[y][x] = 1;
                }
            }
        }

        for (Acteur a : listActeur) {
            int x = a.getX();
            int y = a.getY();
            if (x >= 0 && x < champ[0].length && y >= 0 && y < champ.length) {
                champ[y][x] = 2;
            }
        }
    }

    public int[][] getChamp(){return champ;}

    public ArrayList<Acteur> getListActeur() {
        return listActeur;
    }

    public Acteur getLink() {
        return link;
    }

    public int getLongueur(){return longueur;}
    public int getLargeur(){return largeur;}
}
