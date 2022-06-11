package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import org.apache.commons.validator.routines.EmailValidator;
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
    private TextField emailadress_input;
    @FXML
    private PasswordField password_input;

    @FXML
    private void onLoginButtonClick(MouseEvent event) {
        if(emailadress_input.getText().isBlank()){
            DialogUtils.showDialog("Le nom est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(password_input.getText().isBlank()){
            DialogUtils.showDialog("Le mot de passe est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(emailadress_input.getText())){
            DialogUtils.showDialog("Le format de l'email entré n'est pas correcte !", "Erreur : verification email", Alert.AlertType.ERROR);
            return;
        }

        UtilisateurDAO utilisateurDAO = UtilisateurDAO.getInstance();
        Utilisateur utilisateur = utilisateurDAO.getByEmailAndPassword(emailadress_input.getText(), password_input.getText());

        if(utilisateur == null){
            DialogUtils.showDialog("indentifiants incorrects !", "Error : identifiants incorrects", Alert.AlertType.ERROR);

            utilisateur = utilisateurDAO.getByEmail(emailadress_input.getText());
            if(utilisateur == null){
                DialogUtils.showDialog("Aucun compte enregistrer à cette adresse mail !", "Error : adresse mail inconnu", Alert.AlertType.ERROR);
            }
            return;
        }

        UserSessionUtil.getInstance(utilisateur);
        JavaFXOpenWindowUtil.openAndCloseAWindow( new ClientAccountApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onExitButtonClick(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void onSigninButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new SigninApplication(), ((Node) event.getSource()));
    }
}