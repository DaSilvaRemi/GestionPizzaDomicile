package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.gestionrapizz.gestionpizzadomicile.MainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientAccountController {
    @FXML
    public Button myoldorders_button;
    @FXML
    public Label clientname_label;
    @FXML
    public Label soldeclient_label;
    @FXML
    public Button makeadeposit_button;
    @FXML
    public Button orderpizza_button;
    @FXML
    public TableView lastorders_tab;
    @FXML
    public TableView trendings_tabs;

    public ClientAccountController(){
        this.loadDatas();
    }

    private void loadDatas(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        userSessionUtil.LoginVerification(new MainApplication(), this.makeadeposit_button.getScene().getWindow());

        ClientDAO clientDAO = ClientDAO.getInstance();
        Client client = clientDAO.getById(userSessionUtil.getUtilisateur().getId());
        clientname_label.setText(client.getNom());
        soldeclient_label.setText(String.valueOf(client.getSolde()));

        //TODO Add commandes dates on tabs
    }

    @FXML
    protected void onMyOldOrdersButtonClick(MouseEvent mouseEvent) {
    }

    @FXML
    protected void onMakeADepositButtonClick(MouseEvent mouseEvent) {

    }

    @FXML
    protected void onOrderPizzaButtonClick(MouseEvent mouseEvent) {

    }
}
