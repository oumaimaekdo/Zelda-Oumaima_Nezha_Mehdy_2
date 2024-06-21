package oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.EpeeDeFer;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

class ActeurTest {
    private Acteur acteur;
    private Champ champ;
    private Armes epee;

    @BeforeEach
    void setUp() {

        champ = new Champ(100, 100, new int[10000]);
        acteur = new Acteur("Link", 50, 50, champ);

        epee = new EpeeDeFer(champ);
    }

    @Test
    void testConstructor() {
        assertEquals("Link", acteur.getNom());
        assertEquals(50, acteur.getX());
        assertEquals(50, acteur.getY());
        assertTrue(acteur.enVie());
        assertEquals(100.0, acteur.getVie());
    }

    @Test
    void testSeDeplacer() {
        acteur.seDeplacer("nord");
        assertEquals(40, acteur.getY());
        acteur.seDeplacer("sud");
        assertEquals(50, acteur.getY());
        acteur.seDeplacer("ouest");
        assertEquals(40, acteur.getX());
        acteur.seDeplacer("est");
        assertEquals(50, acteur.getX());
    }

    @Test
    void testEnVie() {
        assertTrue(acteur.enVie());
        acteur.setVie(0);
        assertFalse(acteur.enVie());
    }

    @Test
    void testAttaquer() {
        Acteur ennemi = new Acteur("Ganon", champ);
        acteur.attaquer(epee, ennemi);
        assertEquals(80, ennemi.getVie());
    }

    @Test
    void testEstEnCollisionAvec() {
        Acteur autre = new Acteur("Zelda", 50, 50, champ);
        assertTrue(acteur.estEnCollisionAvec(autre));
        autre.setY(60);
        assertFalse(acteur.estEnCollisionAvec(autre));
    }
}
