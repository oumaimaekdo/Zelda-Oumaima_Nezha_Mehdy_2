package oumaima_nezha_mehdy.zelda.modele.Tests;

import org.junit.jupiter.api.Test;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteur;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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
        Arrays.fill(map, 1);
        Champ champ = new Champ(10, 10, map);

        assertTrue(champ.coordonnéPossible(5, 5), "les limites des coordonnées sont respectés");
        assertFalse(champ.coordonnéPossible(-1, -1), "les limites des coordonnées ne sont pas respectés");
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
