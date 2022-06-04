package org.gestionrapizz.gestionpizzadomicile.controller;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.apache.commons.validator.routines.EmailValidator;
import org.gestionrapizz.gestionpizzadomicile.MainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.ClientDAO;
import org.gestionrapizz.gestionpizzadomicile.models.UtilisateurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Utilisateur;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

public class SigninController {
    @FXML
    private TextField name_input;
    @FXML
    private TextField prenom_input;
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

    @FXML
    protected void onSignInButtonClick(MouseEvent event){
        if(name_input.getText().isBlank()){
            DialogUtils.showDialog("Le nom est vide !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(prenom_input.getText().isBlank()){
            DialogUtils.showDialog("Le prénom est vide !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(emailadress_input.getText().isBlank()){
            DialogUtils.showDialog("Le nom est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(zipcode_input.getText().isBlank()){
            DialogUtils.showDialog("Le nom est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(phonenumber_input.getText().isBlank()){
            DialogUtils.showDialog("Le nom est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(adress_input.getText().isBlank()){
            DialogUtils.showDialog("L'adresse est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(city_input.getText().isBlank()){
            DialogUtils.showDialog("Le code postal est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(password_input.getText().isBlank()){
            DialogUtils.showDialog("Le mot de passe est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }

        if(confirmpassword_input.getText().isBlank()){
            DialogUtils.showDialog("La confirmation mot de passe est non renseigné !", "Erreur : champ vide", Alert.AlertType.ERROR);
            return;
        }


        EmailValidator emailValidator = EmailValidator.getInstance();
        if(!emailValidator.isValid(emailadress_input.getText())){
            DialogUtils.showDialog("L'email entré n'est pas correcte !", "Erreur : verification email", Alert.AlertType.ERROR);
            return;
        }

        if(!password_input.getText().equals(confirmpassword_input.getText())){
            DialogUtils.showDialog("Les mot de passes ne correspondent pas !", "Erreur : verification mot de passe", Alert.AlertType.ERROR);
            return;
        }

        ClientDAO clientDAO = ClientDAO.getInstance();
        Client clientToInsert = new Client(
                0,
                name_input.getText(),
                prenom_input.getText(),
                emailadress_input.getText(),
                password_input.getText(),
                phonenumber_input.getText(),
                adress_input.getText(),
                city_input.getText(),
                zipcode_input.getText()
        );

        UtilisateurDAO utilisateurDAO = UtilisateurDAO.getInstance();
        Utilisateur utilisateur = clientToInsert.getUtilisateur();
        if(utilisateurDAO.getByEmail(utilisateur.getEmail()) != null){
            DialogUtils.showDialog("Adresse email déjà existante !", "Erreur : adresse mail dupliqué", Alert.AlertType.ERROR);
            return;
        }

        clientDAO.insert(clientToInsert);

        DialogUtils.showDialog("Signin successful !", "Welcom to our community !");
        this.onReturnInButtonClick(event);
    }

    @FXML
    protected void onReturnInButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new MainApplication(), ((Node) event.getSource()));
    }
}
