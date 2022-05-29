package org.gestionrapizz.gestionpizzadomicile;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.models.ClientModel;
import org.gestionrapizz.gestionpizzadomicile.models.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.UserSession;

import java.sql.SQLException;

public class ClientMakeDepositController {
    @FXML
    private Button deposit_button;
    @FXML
    private TextField amounttodeposit_input;
    @FXML
    private Label walletamount_label;

    public ClientMakeDepositController(){
        this.verifySession(UserSession.getInstance(-1, false));
    }

    private void verifySession(UserSession userSession){
        userSession.LoginVerification(new MainApplication(), this.deposit_button.getScene().getWindow());
    }

    @FXML
    protected void onDepositButtonClick(MouseEvent mouseEvent){
        UserSession userSession = UserSession.getInstance(-1, false);
        this.verifySession(userSession);
        String amounttodepositInputText = amounttodeposit_input.getText();
        if(!amounttodepositInputText.matches("-?\\d+(\\.\\d+)?")){
            DialogUtils.showDialog("The amount entered is not a number ! ", "Error : Number Format invalid", Alert.AlertType.ERROR);
            return;
        }

        ClientModel clientModel = new ClientModel();
        try {
            clientModel.connect();
            clientModel.addSoldeClient(userSession.getIdUser(), Double.parseDouble(amounttodepositInputText));
            String newSolde = String.format("%.2f $", clientModel.getInfosClientsById(userSession.getIdUser()).getDouble("c.solde"));
            walletamount_label.setText(newSolde);
            clientModel.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
