package org.gestionrapizz.gestionpizzadomicile.models.utils;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class JavaFXOpenWindowUtil {
    public static void openAndCloseAWindow(Application applicationWillBeOpen, Node nodewillBeClose){
        JavaFXOpenWindowUtil.openAndCloseAWindow(applicationWillBeOpen, nodewillBeClose.getScene().getWindow());
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
