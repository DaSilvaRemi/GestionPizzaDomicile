package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminVehiculeMainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminVehiculeMainApplication.class.getResource("admin_vehicule-main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Véhicules");
        stage.setScene(scene);
        stage.show();
    }
}
