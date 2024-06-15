package oumaima_nezha_mehdy.zelda.modele.Univers;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import javafx.scene.image.Image;
import oumaima_nezha_mehdy.zelda.modele.Armes.*;
import oumaima_nezha_mehdy.zelda.modele.Univers.*;
import oumaima_nezha_mehdy.zelda.Vue.VueArmes;

public class ActeurTest {

    @Test
    public void testConstructeurActeurAvecParametres() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1); // Supposons que '1' représente un espace praticable
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 50, 50, champ);

        assertEquals("Link", acteur.getNom());
        assertEquals(50, acteur.getX());
        assertEquals(50, acteur.getY());
    }

    @Test
    public void testSeDeplacerActeur() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1);
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 50, 50, champ);

        acteur.seDeplacer("est");
        assertEquals(60, acteur.getX(), "L'acteur devrait se déplacer vers l'est de 10 unités");

        acteur.seDeplacer("nord");
        assertEquals(40, acteur.getY(), "L'acteur devrait se déplacer vers le nord de 10 unités");
    }

    @Test
    public void testRamasserArme() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1);
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 0, 0, champ);
        EpeeDeFer epee = new EpeeDeFer(champ);

        champ.ajouterItem(epee); // Ajouter une arme dans le monde du jeu
        acteur.ramasser(epee); // Simuler le ramassage de l'arme

        assertTrue(acteur.getInventaire().contains(epee), "L'arme devrait être dans l'inventaire après avoir été ramassée");
    }

    @Test
    public void testAttaquerAutreActeur() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1);
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 50, 50, champ);
        EpeeDeFeu epeeDeFeu = new EpeeDeFeu(champ);
        Acteur ennemi = new Acteur("Ennemi", 50, 60, champ); // Supposons qu'il soit assez proche pour attaquer

        acteur.ajouterArme(epeeDeFeu);
        acteur.selectioner(1); // Supposons que ce soit le premier et unique article dans l'inventaire
        VueArmes vueArmes = new VueArmesImpl(new Image("file:epee.png"), epeeDeFeu, new Image("file:epee_inverse.png"));
        acteur.attaquer(vueArmes, ennemi); // VueArmes est utilisée ici

        assertEquals(70, ennemi.getVie(), "La vie de l'ennemi devrait diminuer du montant des dégâts de l'arme");
    }

    @Test
    public void testVieApresAttaque() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1);
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 50, 50, champ);
        EpeeDeFeu epeeDeFeu = new EpeeDeFeu(champ);

        acteur.ajouterArme(epeeDeFeu);
        acteur.selectioner(1); // Simuler la sélection de l'arme
        VueArmes vueArmes = new VueArmesImpl(new Image("file:epee.png"), epeeDeFeu, new Image("file:epee_inverse.png"));
        acteur.attaquer(vueArmes, acteur); // Simuler une attaque sur soi pour simplifier

        assertEquals(70, acteur.getVie(), "La vie de l'acteur devrait diminuer de 30 points");
    }

    @Test
    public void testEstEnCollisionAvec() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1);
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 50, 50, champ);
        Acteur autre = new Acteur("Zelda", 50, 50, champ);

        assertTrue(acteur.estEnCollisionAvec(autre), "Les deux acteurs devraient être en collision");
    }

    @Test
    public void testSelectionnerArme() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1);
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 50, 50, champ);
        Bombe bombe = new Bombe(champ);

        acteur.ajouterArme(bombe);
        acteur.selectioner(1); // Supposons que ce soit le premier et unique article dans l'inventaire

        assertEquals(bombe, acteur.getArmeParIndex(0), "L'arme sélectionnée devrait être la Bombe");
    }

    @Test
    public void testAjouterArmeInventaire() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1);
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 50, 50, champ);
        EpeeDeFer epee = new EpeeDeFer(champ);

        acteur.ajouterArme(epee);

        assertFalse(acteur.getInventaire().isEmpty(), "L'inventaire ne devrait pas être vide après l'ajout d'une arme");
        assertEquals(epee, acteur.getInventaire().get(0), "L'arme ajoutée devrait être l'Épée de Fer");
    }

    @Test
    public void testVie() {
        int[] carte = new int[100];
        Arrays.fill(carte, 1);
        Champ champ = new Champ(10, 10, carte);
        Acteur acteur = new Acteur("Link", 50, 50, champ);

        acteur.setVie(50);

        assertEquals(50, acteur.getVie(), "La vie de l'acteur devrait être de 50 après la modification");
    }

    // Classe VueArmes concrète pour les tests
    private class VueArmesImpl extends VueArmes {
        public VueArmesImpl(Image image, Armes arme, Image inverse) {
            super(image, arme, inverse);
        }
    }
}
