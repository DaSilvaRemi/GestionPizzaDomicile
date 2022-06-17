package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.apache.commons.validator.routines.EmailValidator;
import org.gestionrapizz.gestionpizzadomicile.models.UtilisateurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;

public class UserCRUDController {
    public static boolean verifyUsersFieldsToInsert(
            TextField name_input,
            TextField prenom_input,
            TextField emailadress_input,
            PasswordField password_input,
            PasswordField confirmpassword_input) {
        if(name_input.getText().isBlank()){
            DialogUtils.showDialog("Le nom n'est pas renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return false;
        }

        if(prenom_input.getText().isBlank()){
            DialogUtils.showDialog("Le prénom n'est pas renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return false;
        }

        if(emailadress_input.getText().isBlank()){
            DialogUtils.showDialog("L'adresse email n'est pas renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return false;
        }

        if(password_input.getText().isBlank()){
            DialogUtils.showDialog("Le mot de passe n'est pas renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return false;
        }

        if(confirmpassword_input.getText().isBlank()){
            DialogUtils.showDialog("La confirmation mot de passe n'est pas renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return false;
        }

        if(name_input.getText().length() > 50){
            DialogUtils.showDialog("Le nom est trop long !", "Erreur : champ trop long", Alert.AlertType.ERROR);
            return false;
        }

        if(prenom_input.getText().length() > 50){
            DialogUtils.showDialog("Le mot de passe est trop long !", "Erreur : champ trop long", Alert.AlertType.ERROR);
            return false;
        }

        if(emailadress_input.getText().length() > 50){
            DialogUtils.showDialog("L'email est trop long' !", "Erreur : champ trop long", Alert.AlertType.ERROR);
            return false;
        }

        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(emailadress_input.getText())){
            DialogUtils.showDialog("L'email entré n'est pas correcte !", "Erreur : verification email", Alert.AlertType.ERROR);
            return false;
        }

        if(!password_input.getText().equals(confirmpassword_input.getText())){
            DialogUtils.showDialog("Les mot de passes ne correspondent pas !", "Erreur : verification mot de passe", Alert.AlertType.ERROR);
            return false;
        }

        UtilisateurDAO utilisateurDAO = UtilisateurDAO.getInstance();
        if(utilisateurDAO.getByEmail(emailadress_input.getText()) != null){
            DialogUtils.showDialog("Adresse email déjà existante !", "Erreur : adresse mail dupliqué", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }
}
