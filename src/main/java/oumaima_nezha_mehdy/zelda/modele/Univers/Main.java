package oumaima_nezha_mehdy.zelda.modele.Univers;



import java.util.Scanner;

public class Main {
   /* public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Champ champ = new Champ(10,10,new int[5]);

        champ.afficherMap();

        Acteur a = new Acteur("Link", champ);
        champ.ajouterActeur(a);
        System.out.println("\n \n \n" );
        champ.afficherMap();
        String c = "aeaz";
        while(!c.equals("quit")) {
            c = sc.nextLine();
            a.seDeplacer(c);
            champ.afficherMap();
        }*/
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

