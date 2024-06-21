module oumaima_nezha_mehdy.zelda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    //requires junit;
    //requires org.junit.jupiter.api;
    //requires org.junit.jupiter.api;
    //requires gdx;
    //requires gdx.backend.lwjgl;

    opens oumaima_nezha_mehdy.zelda to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda;
    exports oumaima_nezha_mehdy.zelda.controleur;
    opens oumaima_nezha_mehdy.zelda.controleur to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda.Vue;
    opens oumaima_nezha_mehdy.zelda.Vue to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda.Vue.Acteurs;
    opens oumaima_nezha_mehdy.zelda.Vue.Acteurs to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda.Vue.Outils.Armes;
    opens oumaima_nezha_mehdy.zelda.Vue.Outils.Armes to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda.Vue.Outils.Elements;
    opens oumaima_nezha_mehdy.zelda.Vue.Outils.Elements to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda.Vue.Outils;
    opens oumaima_nezha_mehdy.zelda.Vue.Outils to javafx.fxml;
    exports oumaima_nezha_mehdy.zelda.modele.Univers.Acteurs to junit;
}