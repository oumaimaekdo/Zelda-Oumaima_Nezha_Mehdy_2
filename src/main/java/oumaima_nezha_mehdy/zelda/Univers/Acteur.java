package oumaima_nezha_mehdy.zelda.Univers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

import java.util.*;

public class Acteur {
    private String nom;

    private Champ champ;
    private static int id = 0;
    private int vitesse = 10;

    private String direction, directionActuelle = "nord";

    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    public Acteur(String nom, int x, int y, Champ m) {
        this.nom = nom;
        this.x.set(x);
        this.y.set(y);
        this.champ = m;
        id += 1;
    }

    public Acteur(String nom, Champ m) {
        this.nom = nom;
        this.champ = m;
        this.x.set(m.getLongueur() / 2);
        this.y.set(m.getLargeur() / 2);
    }

    public void seDeplacer(String direction) {

        this.directionActuelle = direction;

        int x2base, y2base;
        x2base = this.x.getValue();
        y2base = this.y.getValue();

        switch (direction) {
            case "nord":
                this.y.set(this.y.getValue() - (1 * vitesse));
                break;
            case "sud":
                this.y.set(this.y.getValue() + (1 * vitesse));
                break;
            case "ouest":
                this.x.set(this.x.getValue() - (1 * vitesse));
                break;
            case "est":
                this.x.set(this.x.getValue() + (1 * vitesse));
                break;
            default:
        }


        if (!coordonnéPossible(this.x.getValue(), this.y.getValue())) {
            this.x.set(x2base);
            this.y.set(y2base);
        }


    }

    private boolean coordonnéPossible(int x, int y) {
        boolean retourneur = x >= 0 && y >= 0 && x <= this.champ.getLongueur() * 64 && y <= this.champ.getLargeur() * 64;
        x -= 0;
        y -= 5;
        int indice = x / 64 + ((y / 64) * (champ.getLongueur()));
        boolean collision = champ.getChamp()[indice] != 2;
        x += 10;
        y += 30;
        indice = x / 64 + ((y / 64) * (champ.getLongueur()));
        return retourneur && (collision && champ.getChamp()[indice] != 2);

    }

    public boolean ennemis() {
        //boolean estEnnemi;
        return nom != "link";
    }

    public void gestionCollisionEnnemi() {


    }


    public int getX() {
        return x.getValue();
    }

    public int getY() {
        return y.getValue();
    }

    public String getDirection() {
        return this.directionActuelle;
    }

    public IntegerProperty getXProperty() {
        return x;
    }

    public IntegerProperty getYProperty() {
        return y;
    }

    public static String getId() {
        return "#" + id;
    }




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


        /*
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visités = new boolean[champ.getLargeur()][champ.getLongueur()];
        Queue<int[]> queue = new LinkedList<>();
        Map<int[], int[]> parents = new HashMap<>();

        int departX = this.getX() / 64;
        int departY = this.getY() / 64;

        queue.add(new int[]{departX, departY});
        visités[departX][departY] = true;

        while (!queue.isEmpty()) {
            int[] actuel = queue.poll();
            int actuelX = actuel[0];
            int actuelY = actuel[1];

            if (actuelX == cibleX && actuelY == cibleY) {
                ArrayList<int[]> chemin = new ArrayList<>();
                int[] pas = actuel;
                while (pas != null) {
                    chemin.add(pas);
                    pas = parents.get(pas);
                }
                Collections.reverse(chemin);
                return chemin;
            }

            for (int[] dir : directions) {
                int nouveauX = actuelX + dir[0];
                int nouveauY = actuelY + dir[1];
                if (nouveauX >= 0 && nouveauX < champ.getLongueur() && nouveauY >= 0 && nouveauY < champ.getLargeur() && !visités[nouveauY][nouveauX] && champ.getChamp()[nouveauY * champ.getLongueur() + nouveauX] != 2) {
                    queue.add(new int[]{nouveauX, nouveauY});
                    visités[nouveauY][nouveauX] = true;
                    parents.put(new int[]{nouveauX, nouveauY}, actuel);
                }
            }
        }
        return null;*/
    }

    public void suivreChemin(ArrayList<int[]> chemin) {
        if (chemin == null || chemin.isEmpty()) return;

        for (int[] step : chemin) {
            int nouveauX = step[0] * 64;
            int nouveauY = step[1] * 64;
            this.x.set(nouveauX);
            this.y.set(nouveauY);
        }
    }

/*
    public void suivreChemin(ArrayList<int[]> chemin) {

        if (chemin == null || chemin.isEmpty()) return;

        int[] step = chemin.removeFirst();

        int nouveauX = step[0] * 64;
        int nouveauY = step[1] * 64;
        this.x.set(nouveauX);
        this.y.set(nouveauY);

    }

*/
}
