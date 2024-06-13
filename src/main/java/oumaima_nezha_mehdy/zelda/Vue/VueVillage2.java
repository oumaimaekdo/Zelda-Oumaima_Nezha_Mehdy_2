package oumaima_nezha_mehdy.zelda.Vue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.MapInt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class VueVillage2 {
    private TilePane map1;
    private TilePane LayerSup;
    private Champ champ;

    private int[] sol;
    private int[] carteLayersup;

    private int tailleTuile;
    private int LongueurInt;
    private int LargeurInt;
    private String nom;
    private ArrayList<VueArmes> listeArmesCreer;

    private static final String IMAGE_PATH_Debut = "file:src/main/resources/images/mapDebut64/";
    private static final String IMAGE_PATH_Village2 = "file:src/main/resources/images/village2/";
    private static final String IMAGE_PATH_VolcanIsland = "file:src/main/resources/images/villageFeu/";
    private static final String IMAGE_PATH_iceLand = "file:src/main/resources/images/villageGlace/";
    private static final String IMAGE_PATH_foret = "file:src/main/resources/images/foret/";


    public VueVillage2(TilePane map1,TilePane LayerSup, Champ champ, MapInt mapInt,MapInt carteLayersup,String nom){

        this.map1 = map1;
        this.LayerSup = LayerSup;
        this.nom = nom;


        this.champ = champ;
        this.tailleTuile = champ.gettT();

        this.sol = mapInt.getCarte();
        this.carteLayersup = carteLayersup.getCarte();



        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();

        map1.setPrefTileHeight(tailleTuile);
        map1.setPrefTileWidth(tailleTuile);
        map1.setPrefHeight(LargeurInt * tailleTuile);
        map1.setPrefWidth(LongueurInt * tailleTuile);

        LayerSup.setPrefTileHeight(tailleTuile);
        LayerSup.setPrefTileWidth(tailleTuile);
        LayerSup.setPrefHeight(LargeurInt * tailleTuile);
        LayerSup.setPrefWidth(LongueurInt * tailleTuile);
        CreationMap();
        CreationLayerSup();

    }

    public void CreationMap() {
        int[] carte = this.sol;


        Map<Integer, Image> imageMap = new HashMap<>();
        if(nom == "village2") {
            for (int i = 0; i <= 299; i++) {
                imageMap.put(i, new Image(IMAGE_PATH_Village2 + "1-" + i + ".png"));
            }
        }
        else if(nom == "volcanLand") {
            for (int i = 0; i <= 439; i++) {
                imageMap.put(i, new Image(IMAGE_PATH_VolcanIsland + "1-" + i + ".png"));
            }
        }
        else if(nom == "iceLand") {
            for (int i = 0; i <= 439; i++) {
                imageMap.put(i, new Image(IMAGE_PATH_iceLand + "1-" + i + ".png"));
            }
        }
        else if(nom == "debut") {
            for (int i = 0; i <= 299; i++) {
                imageMap.put(i, new Image(IMAGE_PATH_Debut + "510-" + i + ".png"));
            }
        }
        else if(nom == "foret") {
            for (int i = 0; i <= 299; i++) {
                imageMap.put(i, new Image(IMAGE_PATH_foret + "1-" + i + ".png"));
            }
        }

        for (int i = 0; i < carte.length; i++) {
            ImageView imageView = new ImageView();
            map1.getChildren().add(imageView);
            imageView.setId(String.valueOf(i));
            imageView.setFitHeight(tailleTuile);
            imageView.setFitWidth(tailleTuile);
            double col = i % LargeurInt;
            double lig = Math.floor(i / LongueurInt);
            double x = col * tailleTuile;
            double y = lig * tailleTuile;
            imageView.setX(x);
            imageView.setY(y);

            int tileValue = carte[i];
            Image tileImage = imageMap.get(tileValue);
            if (tileImage != null) {
                imageView.setImage(tileImage);
            }
        }

    }

    public void CreationLayerSup() {
        int [] carteLayersup = this.carteLayersup;

        Map<Integer, Image> imageMap = new HashMap<>();
        ArrayList<Integer> listeLayerSup ;
        if(nom == "debut") {
            listeLayerSup = new ArrayList<Integer>(Arrays.asList(51, 52, 71, 72, 74, 75, 76, 77, 83, 84, 103, 104, 105, 106, 107, 161, 233, 234, 247, 248, 253, 254, 256, 257, 267, 268, 276, 277));
            for (int i : listeLayerSup) {
                imageMap.put(i, new Image(IMAGE_PATH_Debut + "510-" + i + ".png"));
            }


            for (int i = 0; i < carteLayersup.length; i++) {
                ImageView imageView2 = new ImageView();
                LayerSup.getChildren().add(imageView2);
                imageView2.setId(String.valueOf(i));
                imageView2.setFitHeight(tailleTuile);
                imageView2.setFitWidth(tailleTuile);
                double col = i % LargeurInt;
                double lig = Math.floor(i / LongueurInt);
                double x = col * LargeurInt;
                double y = lig * LongueurInt;
                imageView2.setX(x);
                imageView2.setY(y);

                int tileValue = carteLayersup[i];
                Image tileImage = imageMap.get(tileValue);
                if (tileImage != null) {
                    imageView2.setImage(tileImage);
                }
            }
        }
        else if(nom == "foret") {
            listeLayerSup = new ArrayList<Integer>(Arrays.asList(7, 8,9,27,28,29,47,48,49,51,52,53,56,57,58,71,72,73,76,77,78,80,81,82,91,92,93, 100, 101, 102,119, 123,124,125,139,143,144,145,160,161,276, 277, 278, 253, 252,251, 266, 267, 267, 268, 260,261,262));
            for (int i : listeLayerSup) {
                imageMap.put(i, new Image(IMAGE_PATH_foret + "1-" + i + ".png"));
            }

            for (int i = 0; i < carteLayersup.length; i++) {
                ImageView imageView2 = new ImageView();
                LayerSup.getChildren().add(imageView2);
                imageView2.setId(String.valueOf(i));
                imageView2.setFitHeight(tailleTuile);
                imageView2.setFitWidth(tailleTuile);
                double col = i % LargeurInt;
                double lig = Math.floor(i / LongueurInt);
                double x = col * LargeurInt;
                double y = lig * LongueurInt;
                imageView2.setX(x);
                imageView2.setY(y);

                int tileValue = carteLayersup[i];
                Image tileImage = imageMap.get(tileValue);
                if (tileImage != null) {
                    imageView2.setImage(tileImage);
                }
            }
        }

    }

    public String getNom() {
        return nom;
    }

    public ArrayList<VueArmes> getListeArmesCreer(){
        return this.listeArmesCreer;

    }
}