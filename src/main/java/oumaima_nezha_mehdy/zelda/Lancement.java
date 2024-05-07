package oumaima_nezha_mehdy.zelda;
import com.example.zelda_bo_hazard.modele.Environnement;

public class Lancement {

    public static void main(String[] args) {

        Environnement e = new Environnement(5, 10);

        e.afficherMap();
        e.creationCase();
    }
}