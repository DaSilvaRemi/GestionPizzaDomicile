package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.gestionrapizz.gestionpizzadomicile.*;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.net.URL;
import java.util.List;

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
    public TableView<Object> lastorders_tab;
    @FXML
    public TableView<Object> trendings_tabs;

    public void initialize(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);

        ClientDAO clientDAO = ClientDAO.getInstance();
        Client client = clientDAO.getById(userSessionUtil.getUtilisateur().getId());
        clientname_label.setText(client.getNom());
        soldeclient_label.setText(String.valueOf(client.getSolde()));

        //TODO Add commandes dates on tabs
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        List<Commande> commandes = commandeDAO.getCommandesByIdUtilisateur(userSessionUtil.getUtilisateur().getId());
    }

    @FXML
    protected void onMyOldOrdersButtonClick(MouseEvent event) {
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientMyOrdersApplication(), ((Node) event.getSource()));
    }

    @FXML
    protected void onMakeADepositButtonClick(MouseEvent event) {
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientMakeDepositApplication(), ((Node) event.getSource()));

    }

    @FXML
    protected void onOrderPizzaButtonClick(MouseEvent event) {
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientOrderPizzaApplication(), ((Node) event.getSource()));
    }
}
