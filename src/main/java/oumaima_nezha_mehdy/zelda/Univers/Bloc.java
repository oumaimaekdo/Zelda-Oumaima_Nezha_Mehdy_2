package oumaima_nezha_mehdy.zelda.Univers;


public abstract class Bloc {
    private boolean passable;

    private double x;

    private double y;

    public Bloc(boolean t,double x, double y){
        this.passable=t;
        this.x=x;
        this.y=y;
    }

    public boolean getpassable(){return passable;}

    public double getX(){return x;}

    public double getY(){return y;}
}
