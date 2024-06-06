package oumaima_nezha_mehdy.zelda.Univers;

import javafx.scene.paint.Color;

import java.util.*;

public class Freezragon extends Ennemi {

    private Acteur link;
    private DeplacementBFS deplacementBFS;

    public Freezragon(int x, int y, Champ m, Acteur link) {
        super("Freezragon", x, y, m);
        this.link = link;
    }

    public Freezragon(Champ m) {
        super("Freezragon", m);
    }

    public void seDeplacer(){
        ArrayList<int[]> chemin = deplacementBFS.deplacementBFS(link.getX(), link.getY(), super.champ, this);
        suivreChemin(chemin);
    }

    @Override
    public void deplacer() {

    }

    @Override
    public void attaquer(Acteur cible) {

    }

    @Override
    public Color getColor() {
        return Color.LIGHTBLUE;
    }



    /*
    public void suivreChemin() {
        List<String> chemin = calculerCheminVersLink();
        if (!chemin.isEmpty()) {
            seDeplacer(chemin.get(0));
        }
    }

    private List<String> calculerCheminVersLink() {
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        String[] directionNames = {"sud", "est", "nord", "ouest"};

        int departX = this.getX() / 64;
        int departY = this.getY() / 64;
        int targetX = link.getX() / 64;
        int targetY = link.getY() / 64;

        Queue<int[]> queue = new LinkedList<>();
        List<int[]> provenance = new ArrayList<>();
        queue.add(new int[]{departX, departY});
        provenance.add(new int[]{departX, departY, -1});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currX = current[0];
            int currY = current[1];

            if (currX == targetX && currY == targetY) {
                return reconstruireChemin(provenance, departX, departY, targetX, targetY);
            }

            for (int i = 0; i < directions.length; i++) {
                int newX = currX + directions[i][0];
                int newY = currY + directions[i][1];

                if (champ.peutTraverserBloc(newX, newY) && !estDejaExploree(provenance, newX, newY)) {
                    queue.add(new int[]{newX, newY});
                    provenance.add(new int[]{newX, newY, provenance.indexOf(current)});
                }
            }
        }

        return new ArrayList<>(); // Retourne une liste vide si aucun chemin n'est trouvé
    }

    private List<String> reconstruireChemin(List<int[]> provenance, int departX, int departY, int cibleX, int cibleY) {
        List<String> chemin = new LinkedList<>();
        int[] current = trouverPosition(provenance, cibleX, cibleY);

        while (current != null && current[2] != -1) {
            int[] previous = provenance.get(current[2]);
            int dx = current[0] - previous[0];
            int dy = current[1] - previous[1];

            if (dx == 0 && dy == 1) {
                chemin.add(0, "sud");
            } else if (dx == 1 && dy == 0) {
                chemin.add(0, "est");
            } else if (dx == 0 && dy == -1) {
                chemin.add(0, "nord");
            } else if (dx == -1 && dy == 0) {
                chemin.add(0, "ouest");
            }

            current = previous;
        }

        return chemin;
    }

    private boolean estDejaExploree(List<int[]> provenance, int x, int y) {
        for (int[] pos : provenance) {
            if (pos[0] == x && pos[1] == y) {
                return true;
            }
        }
        return false;
    }

    private int[] trouverPosition(List<int[]> provenance, int x, int y) {
        for (int[] pos : provenance) {
            if (pos[0] == x && pos[1] == y) {
                return pos;
            }
        }
        return null;
    }
    */
}
