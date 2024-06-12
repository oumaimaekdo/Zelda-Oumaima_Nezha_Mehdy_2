package oumaima_nezha_mehdy.zelda.Vue;


import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;

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
                val.selectioner(1);
                break;
            case "UNDEFINED":
            case "DIGIT2" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case2").setOpacity(1);
                val.selectioner(2);
                break;
            case "QUOTEDBL":
            case "DIGIT3" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case3").setOpacity(1);
                val.selectioner(3);
                break;
            case "QUOTE":
            case "DIGIT4" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case4").setOpacity(1);
                val.selectioner(4);
                break;
            case "LEFT_PARENTHESIS":
            case "DIGIT5" :
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case5").setOpacity(1);
                val.selectioner(5);
                break;
            case "L":
                val.lacher();
                break;
            case "E" :
                val.getLink().ramasserAutour();
            case "R" : val.re();
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
