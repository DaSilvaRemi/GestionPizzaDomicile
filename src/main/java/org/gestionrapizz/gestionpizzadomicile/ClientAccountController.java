package org.gestionrapizz.gestionpizzadomicile;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import org.w3c.dom.events.MouseEvent;

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
        UserSession userSession = UserSession.getInstance(-1, false);

        if(userSession.getIdUser() == -1){
            return;
        }

        ClientModel clientModel = new ClientModel();
        try {
            clientModel.connect();
            ResultSet resultSet = clientModel.getInfosClientsById(userSession.getIdUser());
            clientname_label.setText(resultSet.getString("u.name"));
            soldeclient_label.setText(String.valueOf(resultSet.getInt("c.solde")));
            clientModel.disconnect();

            //TODO Add commandes dates on tabs
        } catch (SQLException e) {
            DialogUtils.showDialog(e.getMessage(), "Error : Client BDD problem", Alert.AlertType.ERROR);
        }
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
