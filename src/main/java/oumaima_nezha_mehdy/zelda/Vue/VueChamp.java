package oumaima_nezha_mehdy.zelda.Vue;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.TilePane;
import oumaima_nezha_mehdy.zelda.controleur.VueArmes;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Champ;
import oumaima_nezha_mehdy.zelda.modele.Univers.MapInt;
import java.util.ArrayList;


public class VueChamp {

    private TilePane map1;
    private TilePane LayerSup;
    private TilePane armesMap;


    private Champ champ;

    private int[] sol;
    private int[] Layersup;
    private int[] collision;



    private int tailleTuile;

    private int LongueurInt;

    private int LargeurInt;


    private Armes epeeRouge;
    private VueArmes vueEpeeRouge;



    public VueChamp(TilePane map1, TilePane LayerSup,TilePane armesMap, Champ champ, MapInt mapInt, MapInt mapInt2, MapInt collision) {

        this.map1 = map1;
        this.LayerSup = LayerSup;
        this.armesMap = armesMap;

        this.champ = champ;
        this.tailleTuile = champ.gettT();

        this.sol = mapInt.getCarte();
        this.Layersup = mapInt2.getCarte();
        this.collision = collision.getCarte();


        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();



        this.LargeurInt = mapInt.getLargeur();
        this.LongueurInt = mapInt.getLongueur();


        //this.arc = new Arc();
        map1.setPrefTileHeight(tailleTuile);
        map1.setPrefTileWidth(tailleTuile);
        map1.setPrefHeight(LargeurInt*tailleTuile);
        map1.setPrefWidth(LongueurInt*tailleTuile);
        LayerSup.setPrefTileHeight(tailleTuile);
        LayerSup.setPrefTileWidth(tailleTuile);
        LayerSup.setPrefHeight(LargeurInt*tailleTuile);
        LayerSup.setPrefWidth(LongueurInt*tailleTuile);
        armesMap.setPrefTileHeight(tailleTuile);
        armesMap.setPrefTileWidth(tailleTuile);
        armesMap.setPrefHeight(LargeurInt*tailleTuile);
        armesMap.setPrefWidth(LongueurInt*tailleTuile);
        CreationMap();
        CreationArme();
        CreationLayerSup();




    }


    public void CreationMap() {
        int[] carte = this.sol;

        Image herbeDroit = new Image("file:src/main/resources/images/MapDebut/13.png");
        Image herbe = new Image("file:src/main/resources/images/MapDebut/herbe2.png");
        Image herbeBas = new Image("file:src/main/resources/images/MapDebut/23.png");
        Image herbeGauche = new Image("file:src/main/resources/images/MapDebut/11.png");
        Image herbeHaut = new Image("file:src/main/resources/images/MapDebut/1.png");
        Image terre = new Image("file:src/main/resources/images/MapDebut/54.png");
        Image coin24 = new Image("file:src/main/resources/images/MapDebut/24.png");
        Image coin22 = new Image("file:src/main/resources/images/MapDebut/22.png");
        Image coin17 = new Image("file:src/main/resources/images/MapDebut/17.png");
        Image coin28 = new Image("file:src/main/resources/images/MapDebut/28.png");
        Image coin0 = new Image("file:src/main/resources/images/MapDebut/0.png");
        Image coin16 = new Image("file:src/main/resources/images/MapDebut/16.png");
        Image coin2 = new Image("file:src/main/resources/images/MapDebut/2.png");
        Image coin27 = new Image("file:src/main/resources/images/MapDebut/27.png");
        Image buisson33 = new Image("file:src/main/resources/images/MapDebut/33.png");
        Image buisson34 = new Image("file:src/main/resources/images/MapDebut/34.png");
        Image buisson35 = new Image("file:src/main/resources/images/MapDebut/35.png");
        Image buisson36 = new Image("file:src/main/resources/images/MapDebut/36.png");
        Image sol37 = new Image("file:src/main/resources/images/MapDebut/37.png");
        Image eau38 = new Image("file:src/main/resources/images/MapDebut/38.png");
        Image maison5 = new Image("file:src/main/resources/images/MapDebut/5.png");
        Image maison6 = new Image("file:src/main/resources/images/MapDebut/6.png");
        Image maison7 = new Image("file:src/main/resources/images/MapDebut/7.png");
        Image maison8 = new Image("file:src/main/resources/images/MapDebut/8.png");
        Image maison9 = new Image("file:src/main/resources/images/MapDebut/9.png");
        Image maison10 = new Image("file:src/main/resources/images/MapDebut/10.png");
        Image maison14 = new Image("file:src/main/resources/images/MapDebut/14.png");
        Image maison15 = new Image("file:src/main/resources/images/MapDebut/15.png");
        Image maison55 = new Image("file:src/main/resources/images/MapDebut/55.png");
        Image maison56 = new Image("file:src/main/resources/images/MapDebut/56.png");
        Image maison57 = new Image("file:src/main/resources/images/MapDebut/57.png");
        Image maison58 = new Image("file:src/main/resources/images/MapDebut/58.png");
        Image bloc110 = new Image("file:src/main/resources/images/MapDebut/110.png");
        Image bloc111 = new Image("file:src/main/resources/images/MapDebut/111.png");
        Image bloc100 = new Image("file:src/main/resources/images/MapDebut/100.png");
        Image bloc101 = new Image("file:src/main/resources/images/MapDebut/101.png");
        Image bloc102 = new Image("file:src/main/resources/images/MapDebut/102.png");
        Image bloc103 = new Image("file:src/main/resources/images/MapDebut/103.png");
        Image bloc104 = new Image("file:src/main/resources/images/MapDebut/104.png");
        Image bloc109 = new Image("file:src/main/resources/images/MapDebut/109.png");
        Image bloc112 = new Image("file:src/main/resources/images/MapDebut/112.png");
        Image bloc108 = new Image("file:src/main/resources/images/MapDebut/108.png");
        Image bloc113 = new Image("file:src/main/resources/images/MapDebut/113.png");
        Image bloc114 = new Image("file:src/main/resources/images/MapDebut/114.png");
        Image bloc115 = new Image("file:src/main/resources/images/MapDebut/115.png");
        Image bloc116 = new Image("file:src/main/resources/images/MapDebut/116.png");
        Image bloc118 = new Image("file:src/main/resources/images/MapDebut/118.png");
        Image bloc119 = new Image("file:src/main/resources/images/MapDebut/119.png");
        Image maison65 = new Image("file:src/main/resources/images/MapDebut/65.png");
        Image maison66 = new Image("file:src/main/resources/images/MapDebut/66.png");
        Image tronc67 = new Image("file:src/main/resources/images/MapDebut/67.png");
        Image arbrecote68 = new Image("file:src/main/resources/images/MapDebut/68.png");
        Image maison71 = new Image("file:src/main/resources/images/MapDebut/71.png");
        Image maison72 = new Image("file:src/main/resources/images/MapDebut/72.png");
        Image arbrebas74 = new Image("file:src/main/resources/images/MapDebut/74.png");
        Image arbrebas75 = new Image("file:src/main/resources/images/MapDebut/75.png");
        Image maison90 = new Image("file:src/main/resources/images/MapDebut/9bis.png");




        for (int i = 0; i < carte.length; i++) {
            ImageView imageView = new ImageView();

            map1.getChildren().add(imageView);
            imageView.setId(String.valueOf(i));
            imageView.setFitHeight(tailleTuile);
            imageView.setFitWidth(tailleTuile);
            double col = i % LargeurInt;
            double lig = Math.floor(i/LongueurInt);
            double x = col * LargeurInt;
            double y = lig * LongueurInt;
            imageView.setX(x);
            imageView.setY(y);
            switch (carte[i]) {
                case 12:
                    imageView.setImage(herbe);
                    break;
                case 54:
                    imageView.setImage(terre);
                    break;
                case 13:
                    imageView.setImage(herbeDroit);
                    break;
                case 11:
                    imageView.setImage(herbeGauche);
                    break;
                case 23:
                    imageView.setImage(herbeBas);
                    break;
                case 1:
                    imageView.setImage(herbeHaut);
                    break;
                case 24:
                    imageView.setImage(coin24);
                    break;
                case 22:
                    imageView.setImage(coin22);
                    break;
                case 17:
                    imageView.setImage(coin17);
                    break;
                case 28:
                    imageView.setImage(coin28);
                    break;
                case 0:
                    imageView.setImage(coin0);
                    break;
                case 16:
                    imageView.setImage(coin16);
                    break;
                case 2:
                    imageView.setImage(coin2);
                    break;
                case 27:
                    imageView.setImage(coin27);
                    break;
                case 33:
                    imageView.setImage(buisson33);
                    break;
                case 34:
                    imageView.setImage(buisson34);
                    break;
                case 35:
                    imageView.setImage(buisson35);
                    break;
                case 36:
                    imageView.setImage(buisson36);
                    break;
                case 37:
                    imageView.setImage(sol37);
                    break;
                case 38:
                    imageView.setImage(eau38);
                    break;
                case 5:
                    imageView.setImage(maison5);
                    break;
                case 6:
                    imageView.setImage(maison6);
                    break;
                case 7:
                    imageView.setImage(maison7);
                    break;
                case 8:
                    imageView.setImage(maison8);
                    break;
                case 9:
                    imageView.setImage(maison9);
                    break;
                case 10:
                    imageView.setImage(maison10);
                    break;
                case 14:
                    imageView.setImage(maison14);
                    break;
                case 15:
                    imageView.setImage(maison15);
                    break;
                case 55:
                    imageView.setImage(maison55);
                    break;
                case 56:
                    imageView.setImage(maison56);
                    break;
                case 57:
                    imageView.setImage(maison57);
                    break;
                case 58:
                    imageView.setImage(maison58);
                    break;
                case 100:
                    imageView.setImage(bloc100);
                    break;
                case 110:
                    imageView.setImage(bloc110);
                    break;
                case 111:
                    imageView.setImage(bloc111);
                    break;
                case 101:
                    imageView.setImage(bloc101);
                    break;
                case 102:
                    imageView.setImage(bloc102);
                    break;
                case 103:
                    imageView.setImage(bloc103);
                    break;
                case 104:
                    imageView.setImage(bloc104);
                    break;
                case 109:
                    imageView.setImage(bloc109);
                    break;
                case 112:
                    imageView.setImage(bloc112);
                    break;
                case 108:
                    imageView.setImage(bloc108);
                    break;
                case 67:
                    imageView.setImage(tronc67);
                    break;
                case 71:
                    imageView.setImage(maison71);
                    break;
                case 72:
                    imageView.setImage(maison72);
                    break;
                case 74:
                    imageView.setImage(arbrebas74);
                    break;
                case 75:
                    imageView.setImage(arbrebas75);
                    break;
                case 90:
                    imageView.setImage(maison90);
                    break;
                case 113:
                    imageView.setImage(bloc113);
                    break;
                case 114:
                    imageView.setImage(bloc114);
                    break;
                case 115:
                    imageView.setImage(bloc115);
                    break;
                case 116:
                    imageView.setImage(bloc116);
                    break;
                case 118:
                    imageView.setImage(bloc118);
                    break;
                case 119:
                    imageView.setImage(bloc119);
                    break;

            }
        }
    }

    private ArrayList<VueArmes> listeArmesCreer= new ArrayList<>();
    public void CreationArme(){
        int [] carteArme = this.collision;

        Image epeeRougeMap = new Image("file:src/main/resources/images/Armes/epeeRouge.gif");


        for (int i = 0; i < carteArme.length; i++) {
            ImageView imageView2 = new ImageView();

            armesMap.getChildren().add(imageView2);
            imageView2.setId(String.valueOf(i));
            imageView2.setFitHeight(tailleTuile);
            imageView2.setFitWidth(tailleTuile);
            double col = i % LargeurInt;
            double lig = Math.floor(i / LongueurInt);
            double x = col * LargeurInt;
            double y = lig * LongueurInt;
            imageView2.setX(x);
            imageView2.setY(y);
            switch (carteArme[i]) {
                case 2:
                    imageView2.setImage(epeeRougeMap);
                    imageView2.setId("epeerouge");
                    epeeRouge = new Armes("epeeRouge",35);
                    vueEpeeRouge = new VueArmes(imageView2.getImage(),epeeRouge,imageView2.getImage());
                    this.listeArmesCreer.add(vueEpeeRouge);
                    break;
            }
        }
    }

    public void CreationLayerSup() {
        int [] carteLayersup = this.Layersup;

        Image maison8 = new Image("file:src/main/resources/images/MapDebut/8.png");
        Image maison59 = new Image("file:src/main/resources/images/MapDebut/59.png");
        Image maison60 = new Image("file:src/main/resources/images/MapDebut/60.png");
        Image maison61 = new Image("file:src/main/resources/images/MapDebut/61.png");
        Image maison62 = new Image("file:src/main/resources/images/MapDebut/62.png");
        Image maison63 = new Image("file:src/main/resources/images/MapDebut/63.png");
        Image maison64 = new Image("file:src/main/resources/images/MapDebut/64.png");
        Image maison65 = new Image("file:src/main/resources/images/MapDebut/65.png");
        Image maison66 = new Image("file:src/main/resources/images/MapDebut/66.png");
        Image arbrecote68 = new Image("file:src/main/resources/images/MapDebut/68.png");
        Image arbrehaut69 = new Image("file:src/main/resources/images/MapDebut/69.png");
        Image arbrehaut70 = new Image("file:src/main/resources/images/MapDebut/70.png");
        Image tronc67 = new Image("file:src/main/resources/images/MapDebut/67.png");
        Image arbrecote73 = new Image("file:src/main/resources/images/MapDebut/73.png");
        Image bloc117 = new Image("file:src/main/resources/images/MapDebut/117.png");



        for (int i = 0; i < carteLayersup.length; i++) {
            ImageView imageView2 = new ImageView();

            LayerSup.getChildren().add(imageView2);
            imageView2.setId(String.valueOf(i));
            imageView2.setFitHeight(tailleTuile);
            imageView2.setFitWidth(tailleTuile);
            double col = i % LargeurInt;
            double lig = Math.floor(i/LongueurInt);
            double x = col * LargeurInt;
            double y = lig * LongueurInt;
            imageView2.setX(x);
            imageView2.setY(y);
            switch (carteLayersup[i]) {
                /*case 200:
                    imageView2.setImage(vueEpeeRouge.getArmeImage());
                    break;*/
                case 8:
                    imageView2.setImage(maison8);
                    break;
                case 59:
                    imageView2.setImage(maison59);
                    break;
                case 60:
                    imageView2.setImage(maison60);
                    break;
                case 61:
                    imageView2.setImage(maison61);
                    break;
                case 62:
                    imageView2.setImage(maison62);
                    break;
                case 63:
                    imageView2.setImage(maison63);
                    break;
                case 64:
                    imageView2.setImage(maison64);
                    break;
                case 65:
                    imageView2.setImage(maison65);
                    break;
                case 66:
                    imageView2.setImage(maison66);
                    break;
                case 67:
                    imageView2.setImage(tronc67);
                    break;
                case 68:
                    imageView2.setImage(arbrecote68);
                    break;
                case 69:
                    imageView2.setImage(arbrehaut69);
                    break;
                case 70:
                    imageView2.setImage(arbrehaut70);
                    break;
                case 73:
                    imageView2.setImage(arbrecote73);
                    break;
                case 117:
                    imageView2.setImage(bloc117);
                    break;

            }
        }
    }

    public ArrayList<VueArmes> getListeArmesCreer(){
        return this.listeArmesCreer;

    }
}
