package oumaima_nezha_mehdy.zelda.Univers;

import javafx.beans.property.IntegerProperty;

import java.util.*;

public class Ennemi extends Acteur{

    private IntegerProperty x, y;
    public Champ champ;
    private int pointDeVie;
    private int i;

    public Ennemi(String nom, int x, int y, Champ m){
        super(nom,x,y,m);
        this.champ = m;
        this.i=0;
    }
    public Ennemi(String nom, Champ m){
        super(nom,m);
    }


    public void suivreChemin(ArrayList<int[]> chemin) {
        if (chemin == null || chemin.isEmpty() || i >= chemin.size()) {

            int[] pas = chemin.get(i);
            int nouveauX = pas[0];
            int nouveauY = pas[1];
            this.setX(nouveauX);
            this.setY(nouveauY);
            i++;
        }
    }



    /*
    public ArrayList<int[]> deplacementBFS(int cibleX, int cibleY) {

        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visites = new boolean[champ.getLargeur()][champ.getLongueur()];
        Queue<int[]> queue = new LinkedList<>();
        Map<String, int[]> parents = new HashMap<>();

        int departX = this.getX() / 64;
        int departY = this.getY() / 64;

        queue.add(new int[]{departX, departY});
        visites[departY][departX] = true;

        while (!queue.isEmpty()) {
            int[] actuel = queue.poll();
            int actuelX = actuel[0];
            int actuelY = actuel[1];

            if (actuelX == cibleX && actuelY == cibleY) {
                ArrayList<int[]> chemin = new ArrayList<>();
                int[] pas = actuel;
                while (pas != null) {
                    chemin.add(pas);
                    String key = pas[0] + "," + pas[1];
                    pas = parents.get(key);
                }
                Collections.reverse(chemin);
                return chemin;
            }

            for (int[] dir : directions) {
                int nouveauX = actuelX + dir[0];
                int nouveauY = actuelY + dir[1];
                if (nouveauX >= 0 && nouveauX < champ.getLongueur() && nouveauY >= 0 && nouveauY < champ.getLargeur() && !visites[nouveauY][nouveauX] && champ.getChamp()[nouveauY * champ.getLongueur() + nouveauX] != 2) {
                    queue.add(new int[]{nouveauX, nouveauY});
                    visites[nouveauY][nouveauX] = true;
                    parents.put(nouveauX + "," + nouveauY, actuel);
                }
            }
        }
        return null;

    }

 */


}
