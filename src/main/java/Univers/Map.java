package Univers;

import java.util.ArrayList;

public class Map {

    private int[][] champ;

    private ArrayList<Bloc> listBloc =new ArrayList<>();

    private ArrayList<Acteur> listActeur = new ArrayList<>();

    private int longueur;

    private int largeur;
    public Map(int L , int l){
        this.largeur=l;
        this.longueur=L;

        this.champ = new int[l][L];

        genererMap();
    }


    private void genererMap(){
        for(int i=0; i<champ.length;i++)
            for (int j =0;j<champ[i].length ; j++){
                if(Math.random()>0.25) {
                    listBloc.add(new Terre(i, j));
                    champ[i][j]=0;
                }
                else {
                    listBloc.add(new Mur(i, j));
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

    private void raffraichirMap(){
        for(Bloc b : listBloc) {
            if(b.getpassable())
                champ[b.getY()][b.getX()] = 0;
            else
                champ[b.getY()][b.getX()] = 1;
        }
        for(Acteur a : listActeur){
            champ[a.getY()][a.getX()] = 2;
        }

    }


    public int getLongueur(){return longueur;}
    public int getLargeur(){return largeur;}
}
