package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import org.gestionrapizz.gestionpizzadomicile.ClientAccountApplication;
import org.gestionrapizz.gestionpizzadomicile.SigninApplication;
import org.gestionrapizz.gestionpizzadomicile.models.UtilisateurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Utilisateur;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        UtilisateurDAO utilisateurDAO = UtilisateurDAO.getInstance();
        Utilisateur utilisateur = utilisateurDAO.getByEmailAndPassword(emailadress_input.getText(), password_input.getText());

        if(utilisateur == null){
            DialogUtils.showDialog("Incorrect credentials pls retry !", "Error : incorrect credentials", Alert.AlertType.ERROR);
            return;
        }

        UserSessionUtil.getInstance(utilisateur);
        JavaFXOpenWindowUtil.openAndCloseAWindow( new ClientAccountApplication(), ((Node) event.getSource()));
    }

    @FXML
    protected void onExitButtonClick(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void onSigninButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new SigninApplication(), ((Node) event.getSource()));
    }
}