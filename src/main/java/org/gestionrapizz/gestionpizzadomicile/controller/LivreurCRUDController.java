package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.models.LivreurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

import java.util.Optional;

public class LivreurCRUDController {
    @FXML
    private TableView<Livreur> livreur_tableview;
    @FXML
    private TableColumn<Livreur, Integer> id_tableColumn;
    @FXML
    private TableColumn<Livreur, String> nom_tableColumn;
    @FXML
    private TableColumn<Livreur, String> prenom_tableColumn;
    @FXML
    private TableColumn<Livreur, String> email_tableColumn;
    @FXML
    private Button ajouterLivreur_button;
    @FXML
    private Button modifierLivreur_button;
    @FXML
    private Button supprimerLivreur_button;

    @FXML
    private void onAjouterLivreurButtonClick(MouseEvent event){

    }

    @FXML
    private void onModifierLivreurButtonClick(MouseEvent event){

    }

    @FXML
    private void onSupprimerLivreurButtonClick(MouseEvent event){
        Optional<ButtonType> options = DialogUtils.showDialog("Etes vous sur de vouloir supprimer ce livreur ? ");
        if(options.orElse(ButtonType.CANCEL).equals(ButtonType.OK)){
            LivreurDAO livreurDAO = LivreurDAO.getInstance();
            livreurDAO.delete(this.livreur_tableview.getSelectionModel().getSelectedItem());
        }
    }
}
