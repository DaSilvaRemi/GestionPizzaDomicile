package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SigninApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SigninApplication.class.getResource("sign_in-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gestion Pizzas");
        stage.setScene(scene);
        stage.show();
    }
}
