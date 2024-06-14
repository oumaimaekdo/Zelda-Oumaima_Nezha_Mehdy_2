package oumaima_nezha_mehdy.zelda.modele.Univers;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[] tableau = new int[1793]; // Création d'un tableau de 1792 éléments

        for (int i = 0; i < tableau.length; i++) {
            tableau[i] = i; // Initialisation de chaque élément avec sa valeur correspondante
        }

        // Affichage du tableau pour vérifier
        for (int i = 0; i < tableau.length; i++) {
            System.out.print(tableau[i] + " ");
        }
    }
}

