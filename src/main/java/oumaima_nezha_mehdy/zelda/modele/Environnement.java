package oumaima_nezha_mehdy.zelda.modele;

import oumaima_nezha_mehdy.zelda.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.Univers.Bloc;

import java.util.ArrayList;

public class Environnement {

    private int map[][];
    private ArrayList<Bloc> listBloc =new ArrayList<>();

    private ArrayList<Acteur> listActeur = new ArrayList<>();

    public Environnement(int ligne, int colonne){
        creerMap(ligne, colonne);
    }

    public int[][] getMap() {
        return map;
    }

    public void creerMap(int ligne, int colonne){
        this.map = new int[ligne][colonne];
        for(int i = 0; i < ligne; i++){
            for(int y = 0; y < colonne; y++){
                this.map[i][y] = 0;
            }
        }

    }



    public void afficherMap(){
        for(int i = 0; i < this.map.length; i++){
            for(int y = 0; y < this.map[i].length; y++){
            }
        }
    }
    public void creationCase(){
        this.map[2][3]=1;

    }

}
