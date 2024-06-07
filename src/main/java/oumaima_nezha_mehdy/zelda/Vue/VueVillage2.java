package oumaima_nezha_mehdy.zelda.Vue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.MapInt;

import java.util.HashMap;
import java.util.Map;


public class VueVillage2 {
    private TilePane map1;
    private Champ champ;

    private int[] sol;

    private int tailleTuile;
    private int LongueurInt;
    private int LargeurInt;
    private String nom;

    private static final String IMAGE_PATH_Village2 = "file:src/main/resources/images/village2/";
    private static final String IMAGE_PATH_VolcanIsland = "file:src/main/resources/images/volcanIsland/";


    public VueVillage2(TilePane map1, Champ champ, MapInt mapInt,String nom){

        this.map1 = map1;
        this.nom = nom;


        this.champ = champ;
        this.tailleTuile = champ.gettT();

        this.sol = mapInt.getCarte();


        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();

        map1.setPrefTileHeight(tailleTuile);
        map1.setPrefTileWidth(tailleTuile);
        map1.setPrefHeight(LargeurInt * tailleTuile);
        map1.setPrefWidth(LongueurInt * tailleTuile);


        CreationMap();

    }

    public void CreationMap() {
        int[] carte = this.sol;


        Map<Integer, Image> imageMap = new HashMap<>();
        if(nom == "village2") {
            for (int i = 0; i <= 299; i++) {
                imageMap.put(i, new Image(IMAGE_PATH_Village2 + "1-" + i + ".png"));
            }
        }
        else if(nom == "volcanIsland") {
            for (int i = 0; i <= 299; i++) {
                imageMap.put(i, new Image(IMAGE_PATH_VolcanIsland + "1-" + i + ".png"));
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

    public String getNom() {
        return nom;
    }
}