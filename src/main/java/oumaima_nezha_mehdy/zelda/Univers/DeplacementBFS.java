package oumaima_nezha_mehdy.zelda.Univers;

import java.util.*;

public class DeplacementBFS {

    public ArrayList<int[]> deplacementBFS(int cibleX, int cibleY, Champ champ, Ennemi e) {
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
                return chemin;
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
}
