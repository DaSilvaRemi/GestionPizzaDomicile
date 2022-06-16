package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientMyOrdersApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientMyOrdersApplication.class.getResource("client_myorders-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Mes commandes");
        stage.setScene(scene);
        stage.show();
    }
}
