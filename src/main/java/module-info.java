module org.gestionrapizz.gestionpizzadomicile {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires commons.validator;

    opens org.gestionrapizz.gestionpizzadomicile to javafx.fxml;
    opens org.gestionrapizz.gestionpizzadomicile.models.tabs to javafx.base;
    exports org.gestionrapizz.gestionpizzadomicile;
    exports org.gestionrapizz.gestionpizzadomicile.controller;
    opens org.gestionrapizz.gestionpizzadomicile.controller to javafx.fxml;
    exports org.gestionrapizz.gestionpizzadomicile.models;
    opens org.gestionrapizz.gestionpizzadomicile.models to javafx.fxml;
}