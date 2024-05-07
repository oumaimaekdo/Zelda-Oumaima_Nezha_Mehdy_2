package oumaima_nezha_mehdy.zelda.modele;

public class Environnement {

    private int map[][];

    public Environnement(int ligne, int colonne) {

        TileMap(ligne,colonne);
    }

    public int[][] getMap() {
        return map;
    }

    public int[][] TileMap(int ligne, int colonne){
        this.map = new int[ligne][colonne];
        for(int i = 0; i< ligne; i++){
            for(int y = 0; y< colonne; y++){
                this.map[i][y] = 1;
            }
        }

        return map;
    }

    public void afficherMap(){
        for(int i = 0; i<this.map.length; i++){
            for(int y = 0; y<this.map[i].length; y++){
                System.out.print(this.map[i][y]);
            }
            System.out.println();
        }
    }

    public void creationCase(){
        this.map[2][3]=1;
    }

}
