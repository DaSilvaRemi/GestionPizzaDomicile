package org.gestionrapizz.gestionpizzadomicile.models.utils;

import javafx.scene.control.Alert;

public abstract class DialogUtils {
    public static void showDialog(String message){
        DialogUtils.showDialog(message, message);
    }

    public static void showDialog(String message, String title){
        DialogUtils.showDialog(message, title, Alert.AlertType.INFORMATION);
    }

    public static void showDialog(String message, String title, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
