package org.gestionrapizz.gestionpizzadomicile.models;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class JavaFXOpenWindowTool {
    public static void openAndCloseAWindow(Application applicationWillBeOpen, Node windowWillBeClose){
        try {
            applicationWillBeOpen.start(new Stage());
            windowWillBeClose.getScene().getWindow().hide();
        } catch (Exception e) {
            DialogUtils.showDialog(e.getMessage(), "Error : Cannot start the new application", Alert.AlertType.ERROR);
        }
    }
}
