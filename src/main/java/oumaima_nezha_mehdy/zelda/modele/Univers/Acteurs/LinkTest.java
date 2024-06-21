package oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.Test;
import oumaima_nezha_mehdy.zelda.modele.Outils.Arme.Armes;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Coffre;
import oumaima_nezha_mehdy.zelda.modele.Outils.Element.Outils;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

//import static org.junit.jupiter.api.Assertions.*;

public class LinkTest {

    private Link link;
    private Champ champ;

    @BeforeEach
    public void setUp() {
        int[] map = new int[100];
        for (int i = 0; i < map.length; i++) {
            map[i] = 1;
        }
        champ = new Champ(10, 10, map);
        link = new Link("Link", 0, 0, champ);
    }

    @Test
    public void testEnnemiAutour() {

        Ennemi ennemi = new Sbir("sbire", 20, 20, champ);
        champ.getListEnnemi().add(ennemi);

        ArrayList<Acteur> ennemisAutour = link.ennemiAutour();
        assertEquals(1, ennemisAutour.size());
        assertEquals(ennemi, ennemisAutour.get(0));
    }

    @Test
    public void testAjouterArme() {
        Armes arme = new Armes("épée", 10, champ) {};
        link.ajouterArme(arme);
        assertTrue(link.getInventaire().contains(arme));
    }

    @Test
    public void testRamasser() {
        Outils outil = new Outils("Potion", champ) {};
        champ.ajouterItem(outil);
        link.ramasser(outil);
        assertTrue(link.getInventaire().contains(outil));
        assertFalse(champ.getItem().contains(outil));
    }

    @Test
    public void testArmeAutour() {
        Outils arme = new Armes("epée", 10, champ) {};
        champ.ajouterItem(arme);
        arme.setX(5);
        arme.setY(5);

        ArrayList<Outils> armesAutour = link.armeAutour();
        assertTrue(armesAutour.contains(arme));
    }

    @Test
    public void testInteragirCoffre() {
        ArrayList<Armes> contenu = new ArrayList<>();
        Armes arme = new Armes("épée", 10, champ) {};
        contenu.add(arme);

        Coffre coffre = new Coffre(contenu, "clé1", champ);

        link.interagirCoffre();
        assertTrue(coffre.getouvert());


        assertTrue(link.getInventaire().contains(arme));
    }

    @Test
    public void testSelectioner() {
        Armes arme = new Armes("épée", 10, champ) {};
        link.ajouterArme(arme);
        link.selectioner(1);
        assertEquals(arme, link.getArmeParIndex(0));
    }

    @Test
    public void testLacher() {
        Armes arme = new Armes("épée", 10, champ) {};
        link.ajouterArme(arme);
        link.selectioner(1);
        link.lacher();
        assertNull(link.getArmeParIndex(0));
        assertTrue(champ.getItem().contains(arme));
    }

    /*
    @Test
    public void testRamasserAutour() {
        Outils arme = new Armes("épée", 10, champ) {};
        champ.ajouterItem(arme);
        arme.setX(5);
        arme.setY(5);
        link.ramasserAutour();
        assertTrue(link.getInventaire().contains(arme));
    }

     */
}
