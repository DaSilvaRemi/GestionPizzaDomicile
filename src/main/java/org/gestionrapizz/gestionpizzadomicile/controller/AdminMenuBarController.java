package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import org.gestionrapizz.gestionpizzadomicile.*;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

public class AdminMenuBarController {

    @FXML
    private void onHomeClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminAccountApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));

    }
    @FXML
    private void onIngredientsClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminIngredientsApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));
    }
    @FXML
    private void onPizzasClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminPizzasApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));

    }
    @FXML
    private void onDeliveryClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminLivreurCRUDApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));

    }

    @FXML
    private void onVehiclesClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminVehiculeMainApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));

    }

    @FXML
    private void onStatsClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new StatistiquesApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));

    }

}
