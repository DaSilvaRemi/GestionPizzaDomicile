package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.gestionrapizz.gestionpizzadomicile.*;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;

public class ClientAccountController {
    @FXML
    private Label clientname_label;
    @FXML
    private Label soldeclient_label;
    @FXML
    private TableView<Object> lastorders_tab;
    @FXML
    private TableView<Object> trendings_tabs;

    public void initialize(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);

        ClientDAO clientDAO = ClientDAO.getInstance();
        Client client = clientDAO.getById(userSessionUtil.getUtilisateur().getId());
        clientname_label.setText(client.getNom());
        soldeclient_label.setText(String.valueOf(String.format("%.2f €", client.getSolde())));
    }

    @FXML
    private void onMyOldOrdersButtonClick(MouseEvent event) {
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientMyOrdersApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onMakeADepositButtonClick(MouseEvent event) {
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientMakeDepositApplication(), ((Node) event.getSource()));

    }

    @FXML
    private void onOrderPizzaButtonClick(MouseEvent event) {
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientOrderPizzaApplication(), ((Node) event.getSource()));
    }
}
