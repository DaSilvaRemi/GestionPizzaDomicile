package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.MainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.ClientDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.sql.SQLException;

public class ClientMakeDepositController {
    @FXML
    private Button deposit_button;
    @FXML
    private TextField amounttodeposit_input;
    @FXML
    private Label walletamount_label;

    private void verifySession(UserSessionUtil userSessionUtil){
        userSessionUtil.LoginVerification(new MainApplication(), this.deposit_button.getScene().getWindow());
    }

    @FXML
    protected void onDepositButtonClick(MouseEvent mouseEvent){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        this.verifySession(userSessionUtil);

        String amounttodepositInputText = amounttodeposit_input.getText();
        if(!amounttodepositInputText.matches("-?\\d+(\\.\\d+)?")){
            DialogUtils.showDialog("The amount entered is not a number ! ", "Error : Number Format invalid", Alert.AlertType.ERROR);
            return;
        }

        ClientDAO clientDAO = ClientDAO.getInstance();
        Client client = clientDAO.getById(userSessionUtil.getUtilisateur().getId());
        client.setSolde(client.getSolde() + Double.parseDouble(amounttodepositInputText));
        clientDAO.update(client);
        String newSolde = String.format("%.2f $", clientDAO.getById(userSessionUtil.getUtilisateur().getId()).getSolde());
        walletamount_label.setText(newSolde);
    }
}
