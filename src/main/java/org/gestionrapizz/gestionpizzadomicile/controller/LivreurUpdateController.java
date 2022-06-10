package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.LivreurCRUDApplication;
import org.gestionrapizz.gestionpizzadomicile.MainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.LivreurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.Optional;

public class LivreurUpdateController {
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
    private Button update_button;
    @FXML
    private Button return_button;

    private Livreur selectedLivreur;

    public void initialize(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);

        if(userSessionUtil.getVAR_SESSION().isEmpty()){
            JavaFXOpenWindowUtil.openAndCloseAWindow(new LivreurCRUDApplication(), name_input.getScene().getWindow());
        }
        //userSessionUtil.LoginVerification(new MainApplication(), name_input.getScene().getWindow());

        LivreurDAO livreurDAO = LivreurDAO.getInstance();
        int idLivreur = (int) userSessionUtil.getVAR_SESSION().get("id_livreur");
        this.selectedLivreur = livreurDAO.getById(idLivreur);
        name_input.setText(this.selectedLivreur.getNom());
        prenom_input.setText(this.selectedLivreur.getPrenom());
        emailadress_input.setText(this.selectedLivreur.getEmail());
        userSessionUtil.getVAR_SESSION().clear();
    }

    @FXML
    private void onUpdateButtonClick(MouseEvent event){
        Optional<ButtonType> options = DialogUtils.showDialog("Voulez vous vraiment ce livreur ?, ", "Confirmation Insertion", Alert.AlertType.CONFIRMATION);
        if(!options.orElse(ButtonType.CANCEL).equals(ButtonType.OK)){
            return;
        }

        String nomLivreur = this.name_input.getText();
        String prenomLivreur = this.prenom_input.getText();
        String emailadress_input = this.emailadress_input.getText();
        String password_input = this.password_input.getText();
        String confirmpassword_input = this.confirmpassword_input.getText();

        if(!nomLivreur.isBlank()){
            this.selectedLivreur.setNom(nomLivreur);
        }

        if(!prenomLivreur.isBlank()){
            this.selectedLivreur.setPrenom(prenomLivreur);
        }

        if(!emailadress_input.isBlank()){
            this.selectedLivreur.setNom(nomLivreur);
        }

        LivreurDAO livreurDAO = LivreurDAO.getInstance();
        boolean isUpdate = livreurDAO.updateWithoutPassword(this.selectedLivreur);
        if(!password_input.isBlank() && !confirmpassword_input.isBlank() && password_input.equals(confirmpassword_input)){
            this.selectedLivreur.setNom(nomLivreur);
            isUpdate = livreurDAO.update(this.selectedLivreur);
        }

        if(!isUpdate){
            return;
        }

        DialogUtils.showDialog("Livreur modifié !");
        onReturnButtonClick(event);
    }

    @FXML
    private void onReturnButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new LivreurCRUDApplication(), ((Node) event.getSource()));
    }
}
