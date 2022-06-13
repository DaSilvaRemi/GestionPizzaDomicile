package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminVehiculeCreateApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminVehiculeUpdateApplication.class.getResource("admin_vehicule-create-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Créer un véhicule");
        stage.setScene(scene);
        stage.show();
    }
}
