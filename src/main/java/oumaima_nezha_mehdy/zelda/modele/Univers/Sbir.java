package oumaima_nezha_mehdy.zelda.modele.Univers;


public class Sbir extends Ennemi {

    private String direction = "est";
    private static int etape = 0 ;

    private int vie;
    private int vitesse;

    private Champ champ;


    public Sbir(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
        this.champ = m;
        this.vie = 100;
        this.vitesse = 10;
    }

    @Override
    public int getDegat() {
        return 5;
    }

}

        /**/
