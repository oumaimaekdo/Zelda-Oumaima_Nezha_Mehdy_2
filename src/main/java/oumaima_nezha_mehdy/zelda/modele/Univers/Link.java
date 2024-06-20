package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

import java.util.ArrayList;

public class Link extends Acteur{


    public static int id= 0;;
    private String nom;

    protected Champ champ;

    private int vitesse=10;


    private ObservableList<Outils> inventaire;

    private DoubleProperty vie ;


    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    private Outils armeEquipé;


    public Link(String nom, int x, int y, Champ m) {
        super(nom, x, y, m);
    }


    public boolean enVie(){
        return getVie()>0;
    }

    public void seDeplacer(String direction) {
        if(enVie()){
            switch (direction) {
                case "nord":
                    if(champ.coordonnéPossible(this.getX(), this.getY() - (1 * this.getVitesse())))
                        this.y.set(this.y.getValue()-(1*vitesse));
                    break;
                case "sud":
                    if(champ.coordonnéPossible(this.getX(), this.getY() + (1 * this.getVitesse())))
                        this.y.set(this.y.getValue()+(1*vitesse));
                    break;
                case "ouest":
                    if(champ.coordonnéPossible(this.getX() - (1 * this.getVitesse()), this.getY()))
                        this.x.set(this.x.getValue()-(1*vitesse));
                    break;
                case "est":
                    if(this.champ.coordonnéPossible(this.getX()+(1*this.vitesse),this.getY()))
                        this.x.set(this.x.getValue()+(1*vitesse));
                    break;
                default:
            }
        }

    }

    public void ajouterArme(Armes arme) {
        inventaire.add(arme);
    }

    public ObservableList<Outils> getInventaire() {
        return inventaire;
    }

    public Outils getArmeParIndex(int index) {
        if (index >= 0 && index < inventaire.size()) {
            return inventaire.get(index);
        }
        return null;
    }
    public ArrayList<Coffre> InteragirCoffreAutour(){
        ArrayList<Coffre> coffreAutour = new ArrayList<>();
        int rayon = 30;
        for (Coffre c : champ.getListBloc()){
            if ((this.getY() - rayon <= c.getY() && c.getY() <= this.getY() + rayon) && (this.getX() - rayon <= c.getX() && c.getX() <= this.getX() + rayon))
                coffreAutour.add(c);
        }
        return coffreAutour;
    }
    public void interagirCoffre(){
        if(!InteragirCoffreAutour().isEmpty()) {
            Coffre c = InteragirCoffreAutour().get(0);
            if(armeEquipé!=null) {
                if (armeEquipé.getNom().equals(c.getCléRequise()) || c.getouvertProperty().getValue()) {
                    c.interagir();
                    System.out.println(inventaire.indexOf(armeEquipé));
                }
            }
            else if(c.getouvert())
                c.interagir();

            if(c.getNbInteraction()>1)
                for(Armes a : c.getContenu())
                    ramasser(a);

        }
    }

    private void chargerInventaire(){
        for(int i=0;i<5;i++){
            inventaire.add(null);
        }
    }
    public void ramasser(Outils vA) {
        for(int i=0 ; i<5;i++)
            if(inventaire.get(i)==null) {
                inventaire.set(i,vA);
                break;
            }
        champ.getItem().remove(vA);
    }

    public void selectioner(int i){
        armeEquipé=null;
        if(inventaire.get(i-1)!=null) {
            armeEquipé = inventaire.get(i-1);

        }

    }

    public void lacher(){
        if(armeEquipé!=null){
            int indice = inventaire.indexOf(armeEquipé);
            inventaire.set(indice,null);
            champ.ajouterItem(armeEquipé);
            armeEquipé.getYProperty().unbind();
            armeEquipé.getXProperty().unbind();
            armeEquipé=null;
        }
    }
}
