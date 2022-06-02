module org.gestionrapizz.gestionpizzadomicile {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires java.sql;

    opens org.gestionrapizz.gestionpizzadomicile to javafx.fxml;
    exports org.gestionrapizz.gestionpizzadomicile;
    exports org.gestionrapizz.gestionpizzadomicile.controller;
    opens org.gestionrapizz.gestionpizzadomicile.controller to javafx.fxml;
}