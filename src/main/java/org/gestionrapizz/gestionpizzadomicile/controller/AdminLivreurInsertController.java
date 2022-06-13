package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminLivreurCRUDApplication;
import org.gestionrapizz.gestionpizzadomicile.models.LivreurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

public class AdminLivreurInsertController {
    @FXML
    private TextField name_input;
    @FXML
    private TextField prenom_input;
    @FXML
    private TextField emailadress_input;
    @FXML
    private PasswordField password_input;
    @FXML
    private PasswordField confirmpassword_input;
    @FXML
    private Button insert_button;
    @FXML
    private Button return_button;

    @FXML
    private void onInsertButtonClick(MouseEvent event){
        if (!UserCRUDController.verifyUsersFieldsToInsert(name_input, prenom_input, emailadress_input, password_input, confirmpassword_input))  {
            return;
        }

        LivreurDAO livreurDAO = LivreurDAO.getInstance();
        int idLivreur = livreurDAO.insert(new Livreur(0, name_input.getText(), prenom_input.getText(), emailadress_input.getText(), password_input.getText()));
        if(idLivreur == 0){
            DialogUtils.showDialog("Insertion livreur échoué ! ", "Erreur Insertion !", Alert.AlertType.ERROR);
            return;
        }

        DialogUtils.showDialog("Livreur inséré ! ", "Insertion réussi !");
        onReturnButtonClick(event);
    }

    @FXML
    private void onReturnButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminLivreurCRUDApplication(), ((Node) event.getSource()));
    }
}
