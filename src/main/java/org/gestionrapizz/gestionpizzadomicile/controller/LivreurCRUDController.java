package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.Node;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.LivreurInsertApplication;
import org.gestionrapizz.gestionpizzadomicile.LivreurUpdateApplication;
import org.gestionrapizz.gestionpizzadomicile.models.LivreurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;
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

    public void initialize(){
        id_tableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_tableColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom_tableColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        email_tableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        LivreurDAO livreurDAO = LivreurDAO.getInstance();
        List<Livreur> livreurs = livreurDAO.get();

        for (Livreur livreur : livreurs) {
            this.livreur_tableview.getItems().add(livreur);
        }
    }

    @FXML
    private void onAddLivreurButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new LivreurInsertApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onUpdateLivreurButtonClick(MouseEvent event){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        Livreur selectedLivreur = this.livreur_tableview.getSelectionModel().getSelectedItem();
        userSessionUtil.getVAR_SESSION().put("id_livreur", selectedLivreur.getId());
        JavaFXOpenWindowUtil.openAndCloseAWindow(new LivreurUpdateApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onDeleteLivreurButtonClick(MouseEvent event){
        Optional<ButtonType> options = DialogUtils.showDialog("Etes vous sur de vouloir supprimer ce livreur ? ");
        if(options.orElse(ButtonType.CANCEL).equals(ButtonType.OK)){
            LivreurDAO livreurDAO = LivreurDAO.getInstance();
            livreurDAO.delete(this.livreur_tableview.getSelectionModel().getSelectedItem());
        }
    }
}
