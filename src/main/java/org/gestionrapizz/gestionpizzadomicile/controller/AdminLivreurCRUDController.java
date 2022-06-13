package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminLivreurInsertApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminLivreurUpdateApplication;
import org.gestionrapizz.gestionpizzadomicile.models.LivreurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesLivreurs;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;
import java.util.Optional;

public class AdminLivreurCRUDController {
    @FXML
    private TableView<MesLivreurs> livreur_tableview;
    @FXML
    private TableColumn<MesLivreurs, Integer> id_tableColumn;
    @FXML
    private TableColumn<MesLivreurs, String> nom_tableColumn;
    @FXML
    private TableColumn<MesLivreurs, String> prenom_tableColumn;
    @FXML
    private TableColumn<MesLivreurs, String> email_tableColumn;

    public void initialize(){
        id_tableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_tableColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_tableColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email_tableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.updateTable();
    }

    private void updateTable(){
        LivreurDAO livreurDAO = LivreurDAO.getInstance();
        List<Livreur> livreurs = livreurDAO.get();

        for (Livreur livreur : livreurs) {
            this.livreur_tableview.getItems().add(new MesLivreurs(livreur));
        }
    }

    @FXML
    private void onAddLivreurButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminLivreurInsertApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onUpdateLivreurButtonClick(MouseEvent event){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        MesLivreurs selectedLivreur = this.livreur_tableview.getSelectionModel().getSelectedItem();
        userSessionUtil.getVAR_SESSION().put("id_livreur", selectedLivreur.getId());
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminLivreurUpdateApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onDeleteLivreurButtonClick(MouseEvent event){
        Optional<ButtonType> options = DialogUtils.showDialog("Etes vous sur de vouloir supprimer ce livreur ? ", "Confirmation suppresion", Alert.AlertType.CONFIRMATION);
        if(options.orElse(ButtonType.CANCEL).equals(ButtonType.OK)){
            LivreurDAO livreurDAO = LivreurDAO.getInstance();
            MesLivreurs mesLivreurs = this.livreur_tableview.getSelectionModel().getSelectedItem();
            Livreur livreur = livreurDAO.getById(mesLivreurs.getId());
            boolean isDelete = livreurDAO.delete(livreur);

            if(!isDelete){
                DialogUtils.showDialog("Suppresion échoué !", "Erreur : Suppression", Alert.AlertType.ERROR);
                return;
            }
            DialogUtils.showDialog("Suppresion réussi !");
            this.updateTable();
        }
    }
}
