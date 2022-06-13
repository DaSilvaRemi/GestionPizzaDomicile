package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminLivreurCRUDApplication;
import org.gestionrapizz.gestionpizzadomicile.StatistiquesApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminVehiculeMainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.AdministrateurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Administrateur;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

public class AdminAccountController {
    @FXML
    private Label adminName_label;

    public void initialize(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        AdministrateurDAO administrateurDAO = AdministrateurDAO.getInstance();
        Administrateur administrateur = administrateurDAO.getById(userSessionUtil.getUtilisateur().getId());
        this.adminName_label.setText(administrateur.getNom());
    }


    @FXML
    private void onCRUDPizzaButtonClick(MouseEvent event){

    }

    @FXML
    private void onCRUDIngredientsButtonClick(MouseEvent event){

    }

    @FXML
    private void onCRUDVehiculesButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminVehiculeMainApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onCRUDLivreursButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminLivreurCRUDApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onStatistiquesButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new StatistiquesApplication(), ((Node) event.getSource()));
    }
}
