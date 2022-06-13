package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import org.apache.commons.validator.routines.EmailValidator;
import org.gestionrapizz.gestionpizzadomicile.*;
import org.gestionrapizz.gestionpizzadomicile.models.AdministrateurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.ClientDAO;
import org.gestionrapizz.gestionpizzadomicile.models.LivreurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.UtilisateurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Administrateur;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Utilisateur;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

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

        ClientDAO clientDAO = ClientDAO.getInstance();
        AdministrateurDAO administrateurDAO = AdministrateurDAO.getInstance();
        LivreurDAO livreurDAO = LivreurDAO.getInstance();

        Client client = clientDAO.getById(utilisateur.getId());
        if(client != null){
            UserSessionUtil.getInstance(client);
            JavaFXOpenWindowUtil.openAndCloseAWindow( new ClientAccountApplication(), ((Node) event.getSource()));
            return;
        }

        Administrateur administrateur = administrateurDAO.getById(utilisateur.getId());
        if(administrateur != null){
            UserSessionUtil.getInstance(administrateur);
            JavaFXOpenWindowUtil.openAndCloseAWindow( new AdminAccountApplication(), ((Node) event.getSource()));
            return;
        }

        Livreur livreur = livreurDAO.getById(utilisateur.getId());
        if(livreur != null){
            UserSessionUtil.getInstance(livreur);
            JavaFXOpenWindowUtil.openAndCloseAWindow( new LivreurLivraisonTodoApplication(), ((Node) event.getSource()));
            return;
        }

        DialogUtils.showDialog("Impossible de détecter votre role utilisateur !", "Identification impossible !", Alert.AlertType.ERROR);
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