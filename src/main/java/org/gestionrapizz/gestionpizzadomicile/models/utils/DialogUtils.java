package org.gestionrapizz.gestionpizzadomicile.models.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public abstract class DialogUtils {
    public static Optional<ButtonType> showDialog(String message){
        return DialogUtils.showDialog(message, "INFORMATION");
    }

    public static Optional<ButtonType> showDialog(String message, String title){
        return DialogUtils.showDialog(message, title, Alert.AlertType.INFORMATION);
    }

    public static Optional<ButtonType> showDialog(String message, String title, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}
