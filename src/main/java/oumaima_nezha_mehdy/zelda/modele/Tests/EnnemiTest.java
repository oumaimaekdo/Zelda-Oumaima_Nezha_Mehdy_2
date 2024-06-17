package oumaima_nezha_mehdy.zelda.modele.Tests;

import org.junit.jupiter.api.Test;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.Ennemi;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.*;

public class EnnemiTest {

    @Test
    public void testEnnemiMovement() {
        // Créer un champ de test avec une carte simple
        int[] map = new int[100];
        Arrays.fill(map, 1);
        Champ testChamp = new Champ(10, 10, map);

        // Créer un Link
        Acteur link = testChamp.getLink();
        link.setX(5);
        link.setY(5);

        // Créer un Ennemi
        Ennemi ennemi = new Ennemi("Goblin", 0, 0, testChamp);
        testChamp.ajouterActeur(ennemi);

        // Déplacer Link pour déclencher le mouvement de l'Ennemi
        link.setX(7);
        link.setY(7);

        // Simuler le déplacement de l'Ennemi vers Link
        ennemi.seDirigerVersLink();

        // Vérifier que l'Ennemi s'est rapproché de Link
        int ennemiX = ennemi.getX();
        int ennemiY = ennemi.getY();

        assertTrue(ennemiX > 0 || ennemiY > 0, "L'Ennemi devrait s'être déplacé vers Link.");
    }

    @Test
    public void testEnnemiMort() {
        // Créer un champ de test avec une carte simple
        int[] map = new int[100];
        Arrays.fill(map, 1); // Remplir la carte avec des cellules traversables
        Champ testChamp = new Champ(10, 10, map);

        // Créer un Link
        Acteur link = testChamp.getLink(); // Récupérer Link initialisé dans le constructeur de Champ
        link.setX(5);
        link.setY(5);

        // Créer un Ennemi
        Ennemi ennemi = new Ennemi("Goblin", 0, 0, testChamp);
        testChamp.ajouterActeur(ennemi);
        testChamp.getListEnnemi().add(ennemi);

        // Réduire la vie de l'Ennemi à 0
        ennemi.setVie(0);

        // Vérifier que l'Ennemi est marqué comme mort
        assertTrue(ennemi.estmort(), "L'Ennemi devrait être mort.");

        // Vérifier que l'Ennemi est retiré du champ
        ennemi.seDirigerVersLink(); // Déclencher la vérification dans le mouvement
        assertFalse(testChamp.getListActeur().contains(ennemi), "L'Ennemi devrait être retiré du champ lorsqu'il est mort.");
        assertFalse(testChamp.getListEnnemi().contains(ennemi), "L'Ennemi devrait être retiré de la liste des ennemis lorsqu'il est mort.");
    }

    @Test
    public void testEnnemiCollision() {
        // Créer un champ de test avec une carte simple
        int[] map = new int[100];
        Arrays.fill(map, 1); // Remplir la carte avec des cellules traversables
        Champ testChamp = new Champ(10, 10, map);

        // Créer un Link
        Acteur link = testChamp.getLink(); // Récupérer Link initialisé dans le constructeur de Champ
        link.setX(1);
        link.setY(1);

        // Créer un Ennemi à la même position que Link
        Ennemi ennemi = new Ennemi("Goblin", 1, 1, testChamp);
        testChamp.ajouterActeur(ennemi);
        testChamp.getListEnnemi().add(ennemi);

        // Simuler le déplacement de l'Ennemi vers Link
        ennemi.seDirigerVersLink();

        // Vérifier que l'Ennemi ne s'est pas déplacé à cause de la collision
        int ennemiX = ennemi.getX();
        int ennemiY = ennemi.getY();

        assertEquals(1, ennemiX, "L'Ennemi ne devrait pas se déplacer lorsqu'il entre en collision avec Link.");
        assertEquals(1, ennemiY, "L'Ennemi ne devrait pas se déplacer lorsqu'il entre en collision avec Link.");
    }
}
