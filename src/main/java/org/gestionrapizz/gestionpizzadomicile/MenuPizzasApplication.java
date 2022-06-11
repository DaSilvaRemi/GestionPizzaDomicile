package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPizzasApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MenuPizzasApplication.class.getResource("menupizzas-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Menu de la pizzeria");
        stage.setScene(scene);
        stage.show();
    }
}
