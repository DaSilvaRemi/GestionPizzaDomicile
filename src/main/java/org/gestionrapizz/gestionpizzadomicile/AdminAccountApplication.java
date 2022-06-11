package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminAccountApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminAccountApplication.class.getResource("admin_account-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("ADMIN Gestion Pizzas");
        stage.setScene(scene);
        stage.show();
    }
}
