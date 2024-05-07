package oumaima_nezha_mehdy.zelda.Univers;



import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Champ champ = new Champ(10,10);

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
        }



    }
}
