package Univers;

public abstract class Bloc {
    private boolean passable;

    private int x;

    private int y;

    public Bloc(boolean t,int x, int y){
        this.passable=t;
        this.x=x;
        this.y=y;
    }

    public boolean getpassable(){return passable;}

    public int getX(){return x;}

    public int getY(){return y;}
}
