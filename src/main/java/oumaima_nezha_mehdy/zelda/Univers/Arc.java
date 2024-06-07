package oumaima_nezha_mehdy.zelda.Univers;

import javafx.scene.image.ImageView;

public  class Arc extends Armes{

    private String nom;
    private int degats;
    private int munitions;
    public static int id= 0;

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
    public void setId(int id){
        this.id = id;
    }

    @Override
    public void utiliser() {

    }

}
