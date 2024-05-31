package oumaima_nezha_mehdy.zelda.Univers;

import java.util.*;

public class DeplacementBFS {



    //je choisis le bfs car il consiste à prendre le chemin le plus court pour atteindre sa cible
    public ArrayList<int[]> deplacementBFS(int cibleX, int cibleY,Champ champ, Ennemi e) {


        // Directions possibles de mouvement : droite, bas, gauche, haut
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visites = new boolean[champ.getLargeur()][champ.getLongueur()];
        //(queue FIFO) pour gerer les cases non explorées, linkedList pour rendre les insertions et les files plus efficaces
        Queue<int[]> queue = new LinkedList<>();
        // on crée une HashMap qui prend en compte les coordonnées, ce qui permettra de suivre le même chemin au retour (de la cible au point de départ)
        Map<String, int[]> cheminRetour = new HashMap<>();

        // on initialise les positions de départ de l'ennemi en case de la grille
        int departX = e.getX() / 64;
        int departY = e.getY() / 64;

        queue.add(new int[]{departX, departY});
        // on dit que la case est visitée
        visites[departY][departX] = true;

        // on vide la file dans un premier temps
        //on souhaite trouver le chemin entre 2 points
        //queue contient les coordonnées des cases à explorer
        while (!queue.isEmpty()) {
            int[] actuel = queue.poll();
            int actuelX = actuel[0];
            int actuelY = actuel[1];


            //on vérifie si les coordonnées correspondent à ceux de la cible recherchée
            if (actuelX == cibleX && actuelY == cibleY) {
                ArrayList<int[]> chemin = new ArrayList<>();
                int[] pas = actuel;
                while (pas != null) {
                    chemin.add(pas);
                    String key = pas[0] + "," + pas[1]; //on cherche le "parent", donc les coordonnées precedentes
                    pas = cheminRetour.get(key);
                }
                Collections.reverse(chemin); // on inverse la liste pour avoir le chemin dans le bon ordre
                //une fois que la cible est atteinte
                return chemin;
            }


            //on explore les directions
            for (int[] dir : directions) {
                //dir[0] et dir[1] calculent les positions adjacentes
                //on définit de nouvelles coordonnées en ajoutant la direction de droite par défaut
                int nouveauX = actuelX + dir[0];
                int nouveauY = actuelY + dir[1];
                //si les coordonnées sont incluses dans le champ, alors on les prend en compte
                if (nouveauX >= 0 && nouveauX < champ.getLongueur() && nouveauY >= 0 && nouveauY < champ.getLargeur() && !visites[nouveauY][nouveauX] && champ.getChamp()[nouveauY * champ.getLongueur() + nouveauX] != 2) {
                    queue.add(new int[]{nouveauX, nouveauY});
                    visites[nouveauY][nouveauX] = true;
                    //String key = ;
                    cheminRetour.put(nouveauX + "," + nouveauY, actuel);//enregistre les coordonnes qui precedent
                }
            }
        }
        // en cas d'échec
        return null;
    }

}
