package org.gestionrapizz.gestionpizzadomicile.controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.MainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.ClientDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

import java.sql.SQLException;

public class SigninController {
    @FXML
    private TextField name_input;
    @FXML
    private TextField emailadress_input;
    @FXML
    private TextField zipcode_input;
    @FXML
    private TextField phonenumber_input;
    @FXML
    private TextField adress_input;
    @FXML
    private TextField city_input;
    @FXML
    private PasswordField password_input;
    @FXML
    private PasswordField confirmpassword_input;
    @FXML
    private Button signin_button;
    @FXML
    private Button return_button;

    protected void onSignInButtonClick(MouseEvent event){
        if(!password_input.getText().equals(confirmpassword_input.getText())){
            DialogUtils.showDialog("Entered passwords don't matche !", "Error : password verification", Alert.AlertType.ERROR);
            return;
        }

        ClientDAO clientDAO = ClientDAO.getInstance();
        clientDAO.insert(
                new Client(
                        0,
                        name_input.getText(),
                        emailadress_input.getText(),
                        password_input.getText(),
                        phonenumber_input.getText(),
                        adress_input.getText(),
                        city_input.getText(),
                        zipcode_input.getText()
                )
        );

        DialogUtils.showDialog("Signin successful !", "Welcom to our community !");
        this.onReturnInButtonClick(event);
    }

    protected void onReturnInButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new MainApplication(), ((Node) event.getSource()));
    }
}
