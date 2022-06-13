package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class AdminStatistiquesApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminStatistiquesApplication.class.getResource("admin_statistiques-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Statistiques de Rapizz");
        stage.setScene(scene);
        stage.show();
    }
}