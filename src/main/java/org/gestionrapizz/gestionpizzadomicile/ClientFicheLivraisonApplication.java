package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientFicheLivraisonApplication extends Application {
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ClientFicheLivraisonApplication.class.getResource("client_fichelivraison-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CRUD Livreur");
        stage.setScene(scene);
        stage.show();
    }
}
