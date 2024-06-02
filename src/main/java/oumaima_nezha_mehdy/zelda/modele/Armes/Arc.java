package oumaima_nezha_mehdy.zelda.modele.Armes;

import javafx.scene.image.ImageView;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

public  class Arc extends Armes {

    private String nom;
    private int degats;
    private int munitions;
    public static int id= 0;

    private Champ champ;
    private ImageView imageView;

    public Arc(){
        super("Arc",25);
        this.munitions = 3;
    }
    @Override
    public String getNom() {
        return null;
    }

    @Override
    public int getDegats() {
        return 0;
    }

    public int getMunitions(){
        return this.munitions;
    }

    public static String getId() {
        return "#"+id;
    }
    @Override
    public void utiliser() {

    }

}
