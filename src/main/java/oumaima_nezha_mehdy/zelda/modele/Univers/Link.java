package oumaima_nezha_mehdy.zelda.modele.Univers;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;

import java.util.ArrayList;

public class Link extends Acteur{


    public static int id= 0;;
    private String nom;

    private Champ champ;

    private int vitesse=10;


    private ObservableList<Outils> inventaire;

    private DoubleProperty vie ;


    private IntegerProperty x = new SimpleIntegerProperty(0);
    private IntegerProperty y = new SimpleIntegerProperty(0);

    private Outils armeEquipé;


    public Link(String nom, int x, int y, Champ m) {

        super(nom, x, y, m);
        this.champ = m;
        this.inventaire= FXCollections.observableArrayList();
        chargerInventaire();
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

    public void ramasser(Outils vA) {
        for(int i=0 ; i<5;i++)
            if(inventaire.get(i)==null) {
                inventaire.set(i,vA);
                break;
            }
        super.champ.getItem().remove(vA);
    }

    public ArrayList<Outils> armeAutour() {
        int rayon = 30;
        ArrayList<Outils> itemAutour = new ArrayList<>();
        for (Outils a : champ.getItem())
            if (!inventaire.contains(a))
                if ((this.getY() - rayon <= a.getY() && a.getY() <= this.getY() + rayon) && (this.getX() - rayon <= a.getX() && a.getX() <= this.getX() + rayon)){
                    itemAutour.add(a);
                }
        return itemAutour;
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

    public void ramasserAutour() {
        if (!armeAutour().isEmpty()){
            ramasser(armeAutour().get(0));
        }
    }
}
