package oumaima_nezha_mehdy.zelda.Univers;


import javafx.scene.image.ImageView;

public abstract class Bloc {
    private boolean passable;

    private double x;

    private double y;
    //private ImageView image;

    public Bloc(boolean t,double x, double y){
        this.passable=t;
        this.x=x;
        this.y=y;
    }

    public boolean getpassable(){return passable;}

    public double getX(){return x;}

    public double getY(){return y;}
}
