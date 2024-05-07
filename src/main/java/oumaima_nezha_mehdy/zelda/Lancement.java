package oumaima_nezha_mehdy.zelda;
import oumaima_nezha_mehdy.zelda.modele.Environnement;

public class Lancement {

    public static void main(String[] args) {

        Environnement e = new Environnement(5, 10);

        e.afficherMap();
        e.creationCase();
    }
}