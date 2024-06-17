package oumaima_nezha_mehdy.zelda.modele.Univers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

import oumaima_nezha_mehdy.zelda.Vue.VueArmes;


import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class ActeurTest {

    private Acteur acteur;
    private Champ champ;
    private Armes epee;

    @BeforeEach
    void preparer() {
        champ = new Champ(100, 100, new int[10000]);
        acteur = new Acteur("Link", 50, 50, champ);
        //epee = new Armes("Épée", 10, champ);
    }

    @Test
    void verifierConditionsInitiales() {
        assertNotNull(acteur.getNom());
        assertEquals(100, acteur.getVie());
        assertTrue(acteur.getInventaire().isEmpty());
        assertEquals("Link", acteur.getNom());
    }

    @Test
    void testerDeplacement() {
        // Assurer que le déplacement est possible selon les coordonnées du champ.
        assertTrue(champ.coordonnéPossible(50, 40));
        acteur.seDeplacer("nord");
        assertEquals(40, acteur.getY());
    }

    @Test
    void testerRamassageEtUtilisationArme() {
        champ.ajouterItem(epee);
        acteur.ramasserAutour();
        assertFalse(acteur.getInventaire().isEmpty());
        assertEquals(epee, acteur.getArmeParIndex(0));

        acteur.attaquer(null, new Acteur("Ennemi", 55, 55, champ));
        assertEquals(90, acteur.getVie());
    }

    @Test
    void testerDetectionCollision() {
        Acteur autre = new Acteur("Ennemi", 50, 50, champ);
        assertTrue(acteur.estEnCollisionAvec(autre));
    }

    @Test
    void testerPresencePortail() {
        assertTrue(champ.presencePortail(10, 10));
    }

}