package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.VehiculeMainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

public class VehiculeUpdateController {

    @FXML
    protected void onReturnToMainVehiculeButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new VehiculeMainApplication(), ((Node) event.getSource()));
    }
}
