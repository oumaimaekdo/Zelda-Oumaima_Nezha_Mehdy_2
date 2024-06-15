package oumaima_nezha_mehdy.zelda.modele.Tests;

import org.junit.jupiter.api.Test;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class InventaireTest {

    @Test
    public void testAjouterItem() {
        Champ champ = new Champ(100, 100, new int[10000]); // vérifier la taille de la map
        Armes arme = new Armes("Sword", 10, champ) {

        };
        champ.ajouterItem(arme);

        assertTrue(champ.getItem().contains(arme), "Item doit être dans la  liste");
    }

    @Test
    public void testCoordonneePossible() {
        int[] map = new int[100]; // map simplifié pour le test
        Arrays.fill(map, 1); // Assume all tiles are initially walkable
        Champ champ = new Champ(10, 10, map);

        assertTrue(champ.coordonnéPossible(5, 5), "Should return true for coordinates within boundaries");
        assertFalse(champ.coordonnéPossible(-1, -1), "Should return false for out-of-bound coordinates");
    }

    @Test
    public void testPresencePortail() {
        int[] map = new int[100];
        Arrays.fill(map, 0);
        map[10] = -1; // index spécifique (à vérifier)
        Champ champ = new Champ(10, 10, map);

        assertTrue(champ.presencePortail(1, 2), "doit detecter le portail à l'endroit spécifique");
    }

    @Test
    public void testMortActeur() {
        Champ champ = new Champ(10, 10, new int[100]);
        Acteur acteur = new Acteur("LINK", 50, 50, champ);
        champ.ajouterActeur(acteur);
        champ.mortActeur(acteur);

        assertFalse(champ.getListActeur().contains(acteur), "Acteur doit être enlevé de la liste");
    }
}
