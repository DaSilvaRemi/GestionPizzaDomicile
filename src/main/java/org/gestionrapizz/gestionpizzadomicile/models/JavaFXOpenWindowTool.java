package org.gestionrapizz.gestionpizzadomicile.models;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public abstract class JavaFXOpenWindowTool {
    public static void openAndCloseAWindow(Application applicationWillBeOpen, Node nodewillBeClose){
        JavaFXOpenWindowTool.openAndCloseAWindow(applicationWillBeOpen, nodewillBeClose.getScene().getWindow());
    }

    public static void openAndCloseAWindow(Application applicationWillBeOpen, Window windowWillBeClose){
        try {
            applicationWillBeOpen.start(new Stage());
            windowWillBeClose.hide();
        } catch (Exception e) {
            DialogUtils.showDialog(e.getMessage(), "Error : Cannot start the new application", Alert.AlertType.ERROR);
        }
    }
}
