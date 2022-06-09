package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LivreurInsertApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LivreurInsertApplication.class.getResource("livreur_insert-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("CRUD Livreur");
        stage.setScene(scene);
        stage.show();
    }
}
