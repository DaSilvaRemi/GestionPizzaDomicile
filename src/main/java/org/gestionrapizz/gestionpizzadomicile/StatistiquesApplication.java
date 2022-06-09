package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class StatistiquesApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(StatistiquesApplication.class.getResource("statistiques-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Statistiques de Rapizz");
        stage.setScene(scene);
        stage.show();
    }
}