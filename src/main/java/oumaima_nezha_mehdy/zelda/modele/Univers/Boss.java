package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.*;

public class Boss extends Ennemi{

    //private BFS bfs;

    public Boss(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
        seDirigerVersLink();
    }


    @Override
    public void seDirigerVersLink() {

        //ArrayList<int[]> chemin = bfs(champ.getLink().getX(), champ.getLink().getY(), super.champ, this);
        //suivreChemin(chemin);
        //System.out.println("chemin du boss :" + chemin);

        Acteur link = this.getChamp().getLink();
        if (link == null) {
            return;
        }

        int linkX = link.getX();
        int linkY = link.getY();
        int deltaX = linkX - this.getX();
        int deltaY = linkY - this.getY();

        String direction = "";
        if(!estmort()){
            if (!estEnCollisionAvec(getChamp().getLink())) {
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX > 0) {
                        direction = "est";
                    } else {
                        direction = "ouest";
                    }
                } else {
                    if (deltaY > 0) {
                        direction = "sud";
                    } else {
                        direction = "nord";
                    }
                }
                this.seDeplacer(direction);
            } else {
                direction = "ouest";
            }
        }else{
            getChamp().mortActeur(this);
        }
    }

    /*
    public ArrayList<int[]> bfs(int cibleX, int cibleY, Champ champ, Ennemi e) {


        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visites = new boolean[champ.getLargeur()][champ.getLongueur()];
        Queue<int[]> queue = new LinkedList<>();
        Map<String, int[]> cheminRetour = new HashMap<>();

        int departX = e.getX() / 65;
        int departY = e.getY() / 65;

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
                    pas = cheminRetour.get(key);
                }
                Collections.reverse(chemin);
                //return chemin;
            }

            for (int[] dir : directions) {
                int nouveauX = actuelX + dir[0];
                int nouveauY = actuelY + dir[1];

                if (nouveauX >= 0 && nouveauX < champ.getLongueur() && nouveauY >= 0 && nouveauY < champ.getLargeur() &&
                        !visites[nouveauY][nouveauX] && champ.getChamp()[nouveauY * champ.getLongueur() + nouveauX] != 1) {
                    queue.add(new int[]{nouveauX, nouveauY});
                    visites[nouveauY][nouveauX] = true;
                    cheminRetour.put(nouveauX + "," + nouveauY, actuel);
                }
            }
        }

        // Retourne une liste vide si aucun chemin n'est trouvé
        return new ArrayList<>();
    }

     */

    /*
    public void suivreChemin() {
        List<String> chemin = calculerCheminVersLink();
        if (!chemin.isEmpty()) {
            seDeplacer(chemin.get(0));
        }
    }

     */



/*
    public void seDirigerVersLink() {
        Acteur link = this.getChamp().getLink();
        if (link == null) {
            return;
        }

        int linkX = link.getX();
        int linkY = link.getY();
        int deltaX = linkX - this.getX();
        int deltaY = linkY - this.getY();

        String direction = "";
        if(!estmort()){
            if (!estEnCollisionAvec(getChamp().getLink())) {
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX > 0) {
                        direction = "est";
                    } else {
                        direction = "ouest";
                    }
                } else {
                    if (deltaY > 0) {
                        direction = "sud";
                    } else {
                        direction = "nord";
                    }
                }
                this.seDeplacer(direction);
            } else {
                direction = "ouest";
            }
        }else{
            getChamp().mortActeur(this);
        }

    }

 */
}
