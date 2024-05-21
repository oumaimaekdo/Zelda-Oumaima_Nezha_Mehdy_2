package oumaima_nezha_mehdy.zelda.Univers;


import java.util.ArrayList;

public class Champ {

    private int[] champ;

    private ArrayList<Bloc> listBloc =new ArrayList<>();

    private ArrayList<Acteur> listActeur = new ArrayList<>();

    private Acteur link;

    private int longueur;

    private int largeur;

    public Champ(int L , int l,int[] map){
        this.largeur=l;
        this.longueur=L;
        this.champ = map;
        this.link = new Acteur("Link",0,0,this);
        //listActeur.add(link);
        raffraichirMap();

    }


    private void genererMap(){
        for(int i=0; i<champ.length;i++) {
            double x,y;
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
    }

    public void afficherMap(){
        raffraichirMap();
        for(int i=0; i<champ.length;i++) {
            if(i%longueur==0) {
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

    }


    public int[] getChamp(){return champ;}

    public ArrayList<Acteur> getListActeur() {
        return listActeur;
    }


    public Acteur getLink() {
        return link;
    }

    public int getLongueur(){return longueur;}
    public int getLargeur(){return largeur;}
}
