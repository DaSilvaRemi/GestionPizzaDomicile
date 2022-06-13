package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LivreurLivraisonTodoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LivreurLivraisonTodoApplication.class.getResource("livreur_livraison_todo-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Livraisons à faire");
        stage.setScene(scene);
        stage.show();
    }
}
