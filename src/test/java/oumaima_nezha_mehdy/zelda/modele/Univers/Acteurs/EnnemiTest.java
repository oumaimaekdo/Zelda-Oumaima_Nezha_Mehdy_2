package oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import org.junit.jupiter.api.*;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs.Boss;
import oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs.Link;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

import static org.junit.jupiter.api.Assertions.*;

class EnnemiTest {

    private Boss boss;
    private Champ champ;

    @BeforeEach
    void setUp() {
        int[] map = new int[100];
        for (int i = 0; i < map.length; i++) {
            map[i] = 1;
        }
        champ = new Champ(10, 10, map);
        boss = new Boss("Boss", 100, 100, champ);
    }

    @Test
    void testSeDirigerVersLink() {
        Link link = champ.getLink();
        link.getXProperty().set(50);
        link.getYProperty().set(50);

        int initialX = boss.getX();
        int initialY = boss.getY();

        boss.seDirigerVersLink();

        assertEquals(initialX, boss.getX());
        assertEquals(initialY, boss.getY());
    }

    @Test
    void testAttaquerLink() {
        Link link = champ.getLink();
        link.getXProperty().set(10);
        link.getYProperty().set(10);
/*
        IntegerProperty vieInitiale = new SimpleIntegerProperty(link.getVie());
        boss.attaquerLink();
        assertEquals(vieInitiale - boss.getDegat(), link.getVie());

 */
    }

    @Test
    void testLinkAutour() {
        Link link = champ.getLink();
        link.getXProperty().set(10);
        link.getYProperty().set(10);

        assertFalse(boss.linkAutour());
    }

    @Test
    void testEstMort() {
        boss.setVie(0);
        assertTrue(boss.estmort());
    }

    @Test
    void testEstUnSbire() {
        assertFalse(boss.estUnSbire());
    }
}