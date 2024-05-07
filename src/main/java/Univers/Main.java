package Univers;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map map = new Map(10,10);

        map.afficherMap();

        Acteur a = new Acteur("Link",map);
        map.ajouterActeur(a);
        System.out.println("\n \n \n" );
        map.afficherMap();
        String c = "aeaz";
        while(!c.equals("quit")) {
            c = sc.nextLine();
            a.seDeplacer(c);
            map.afficherMap();
        }



    }
}
