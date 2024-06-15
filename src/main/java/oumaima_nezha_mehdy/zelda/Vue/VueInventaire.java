package oumaima_nezha_mehdy.zelda.Vue;


import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import oumaima_nezha_mehdy.zelda.modele.Armes.Armes;
import oumaima_nezha_mehdy.zelda.modele.Univers.Bloc;
import oumaima_nezha_mehdy.zelda.modele.Univers.Outils;

public class VueInventaire {

    @FXML
    private HBox vueCaseInventaire;

    private VueActLink val;

    public VueInventaire(HBox vueCaseInventaire, VueActLink val){
        this.vueCaseInventaire=vueCaseInventaire;
        this.val = val;
    }


    public void selectionerCase(KeyEvent e){

        switch (e.getCode().toString()){
            case "AMPERSAND":
            case "DIGIT1" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case1").setOpacity(1);
                val.getLink().selectioner(1);
                val.selectioner(1);
                break;
            case "UNDEFINED":
            case "DIGIT2" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case2").setOpacity(1);
                val.getLink().selectioner(2);
                val.selectioner(2);
                break;
            case "QUOTEDBL":
            case "DIGIT3" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case3").setOpacity(1);
                val.getLink().selectioner(3);
                val.selectioner(3);
                break;
            case "QUOTE":
            case "DIGIT4" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case4").setOpacity(1);
                val.getLink().selectioner(4);
                val.selectioner(4);
                break;
            case "LEFT_PARENTHESIS":
            case "DIGIT5" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case5").setOpacity(1);
                val.getLink().selectioner(5);
                val.selectioner(5);
                break;
            case "G": val.getLink().lacher();
                break;
            case "E" : val.getLink().ramasserAutour();
                break;
            case "H" :
                for(Outils o : val.getLink().getInventaire()) {
                    if(o!=null)
                    System.out.println("Outil");
                    else
                        System.out.println("null");

                }

            break;
        }

    }
    private void reinitialiserOpacitéCase(){
        for(int i=0; i<vueCaseInventaire.getChildren().size() ; i++){
            vueCaseInventaire.getChildren().get(i).setOpacity(0.5);
        }

    }

    public void ajoutInventaire(){}
}
