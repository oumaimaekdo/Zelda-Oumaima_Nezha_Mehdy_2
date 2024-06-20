package oumaima_nezha_mehdy.zelda.modele.Outils.Arme;


import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;

public  class Arc extends Armes {

    private String nom;
    private int degats;
    private int munitions;
    public static int id= 0;


    public Arc(Champ champ){
        super("Arc",25,champ);
        this.munitions = 3;
    }
    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public int getDegats() {
        return 0;
    }

    public int getMunitions(){
        return this.munitions;
    }

    public void setId(int id){
        this.id = id;
    }

}
