package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Spinner<Double> amounttodeposit_input;
    @FXML
    private Label walletamount_label;

    @FXML
    protected void onDepositButtonClick(MouseEvent mouseEvent){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);

        ClientDAO clientDAO = ClientDAO.getInstance();
        Client client = clientDAO.getById(userSessionUtil.getUtilisateur().getId());
        client.setSolde(client.getSolde() + amounttodeposit_input.getValue());
        clientDAO.update(client);
        String newSolde = String.format("%.2f €", clientDAO.getById(client.getId()).getSolde());
        walletamount_label.setText(newSolde);
    }
}
