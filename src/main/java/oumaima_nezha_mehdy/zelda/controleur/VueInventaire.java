package oumaima_nezha_mehdy.zelda.controleur;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import oumaima_nezha_mehdy.zelda.Univers.*;

import java.util.ArrayList;

public class VueInventaire {

    @FXML
    HBox vueCaseInventaire;

    public VueInventaire(HBox vueCaseInventaire){
        this.vueCaseInventaire=vueCaseInventaire;
    }


    public void selectionerCase(KeyEvent e){

        switch (e.getCode().toString()){
            case "AMPERSAND":
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case1").setOpacity(1);
                break;
            case "UNDEFINED":
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case2").setOpacity(1);
                break;
            case "QUOTEDBL":
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case3").setOpacity(1);
                break;
            case "QUOTE":
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case4").setOpacity(1);
                break;
            case "LEFT_PARENTHESIS":
                reinitialiserOpacitéCase();
                vueCaseInventaire.lookup("#case5").setOpacity(1);
                break;
        }

    }
    private void reinitialiserOpacitéCase(){
        for(int i=0; i<vueCaseInventaire.getChildren().size() ; i++){
            vueCaseInventaire.getChildren().get(i).setOpacity(0.5);
        }
    }
}
