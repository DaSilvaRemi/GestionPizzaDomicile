package org.gestionrapizz.gestionpizzadomicile;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.Node;
import org.gestionrapizz.gestionpizzadomicile.models.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.JavaFXOpenWindowTool;
import org.gestionrapizz.gestionpizzadomicile.models.UserSession;
import org.gestionrapizz.gestionpizzadomicile.models.UtilisateurModel;

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
        UtilisateurModel utilisateurModel = new UtilisateurModel();
        try {
            utilisateurModel.connect();
            int nbUtilisateurs = utilisateurModel
                    .countUtilisateurWithEmailAndPassword(emailadress_input.getText(), password_input.getText())
                    .getInt("nbUsersWithGoodCredential");

            ResultSet resultSet = utilisateurModel.getUtilisateurByEmail(emailadress_input.getText());
            int idUser = resultSet.getInt("id_utilisateur");
            Boolean isAdmin = nbUtilisateurs == 1 && resultSet.getBoolean("is_admin");
            utilisateurModel.disconnect();

            if(nbUtilisateurs != 1){
                DialogUtils.showDialog("Incorrect credentials pls retry !", "Error : incorrect credentials", Alert.AlertType.ERROR);
                return;
            }

            UserSession.getInstance(idUser, isAdmin);
            JavaFXOpenWindowTool.openAndCloseAWindow( new ClientAccountApplication(), ((Node) event.getSource()));
        } catch (SQLException e) {
           DialogUtils.showDialog(e.getMessage(), "Error : Login Failed !", Alert.AlertType.ERROR);
        }
    }

    @FXML
    protected void onExitButtonClick(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    protected void onSigninButtonClick(MouseEvent event){
        JavaFXOpenWindowTool.openAndCloseAWindow( new SigninApplication(), ((Node) event.getSource()));
    }
}