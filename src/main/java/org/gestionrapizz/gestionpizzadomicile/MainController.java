package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.IOException;

public class MainController {
    @FXML
    private Button login_button;
    @FXML
    private TextField emailadress_input;
    @FXML
    private Button exit_button;
    @FXML
    private Button signin_button;
    @FXML
    private PasswordField password_input;

    @FXML
    protected void onLoginButtonClick(MouseEvent event) {
        ClientAccountApplication clientAccountApplication  = new ClientAccountApplication();
        try {
            clientAccountApplication.start(new Stage());
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onExitButtonClick(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void onSigninButtonClick(MouseEvent event){
        SigninApplication signinApplication = new SigninApplication();
        try {
            signinApplication.start(new Stage());
            ((Node) event.getSource()).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}