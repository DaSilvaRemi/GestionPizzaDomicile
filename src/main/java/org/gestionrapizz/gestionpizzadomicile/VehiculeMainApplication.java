package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.gestionrapizz.gestionpizzadomicile.controller.VehiculeMainController;

public class VehiculeMainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(VehiculeMainApplication.class.getResource("vehicule_main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Véhicules");
        stage.setScene(scene);
        stage.show();
    }
}
