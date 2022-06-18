package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.MainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import org.gestionrapizz.gestionpizzadomicile.models.entity.*;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesLivraisonsAFaire;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesVehicules;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;
import java.util.List;

public class LivreurLivraisonTodoController {
    @FXML private TableView<MesLivraisonsAFaire> livraison_table_view;
    @FXML private TableColumn<MesVehicules, String> livraison_dateCommande_tableColumn;
    @FXML private TableColumn<MesVehicules, Double> livraison_montant_tableColumn;
    @FXML private TableColumn<MesVehicules, String> livraison_immatriculation_tableColumn;
    @FXML private TableColumn<MesVehicules, String> livraison_nom_tableColumn;
    @FXML private TableColumn<MesVehicules, String> livraison_prenom_tableColumn;
    @FXML private TableColumn<MesVehicules, String> livraison_email_tableColumn;
    @FXML private TableColumn<MesVehicules, String> livraison_telephone_tableColumn;
    @FXML private TableColumn<MesVehicules, String> livraison_adresse_rue_tableColumn;
    @FXML private TableColumn<MesVehicules, String> livraison_adresse_ville_tableColumn;
    @FXML private TableColumn<MesVehicules, String> livraison_adresse_codepostal_tableColumn;

    public void initialize(){
        this.updateLivraisonTableView();
        livraison_dateCommande_tableColumn.setCellValueFactory(new PropertyValueFactory<>("dateCommande"));
        livraison_montant_tableColumn.setCellValueFactory(new PropertyValueFactory<>("montant"));
        livraison_immatriculation_tableColumn.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        livraison_nom_tableColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        livraison_prenom_tableColumn.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        livraison_email_tableColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        livraison_telephone_tableColumn.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        livraison_adresse_rue_tableColumn.setCellValueFactory(new PropertyValueFactory<>("adresse_rue"));
        livraison_adresse_ville_tableColumn.setCellValueFactory(new PropertyValueFactory<>("adresse_ville"));
        livraison_adresse_codepostal_tableColumn.setCellValueFactory(new PropertyValueFactory<>("adresse_codepostal"));
    }

    private void updateLivraisonTableView() {
        this.livraison_table_view.getItems().clear();

        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        StatutDAO statutDAO = StatutDAO.getInstance();
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        List<Commande> allCommandes = commandeDAO.getByIdLivreurAndIdStatut(userSessionUtil.getUtilisateur().getId() , statutDAO.getByNom("Livraison en cours").getId());


        for (Commande commande : allCommandes){
            livraison_table_view.getItems().add(new MesLivraisonsAFaire(commande));
        }
    }


    @FXML
    private void onClickValiderLivraisonButton(MouseEvent event){
        MesLivraisonsAFaire livraisonSelectionnee = livraison_table_view.getSelectionModel().getSelectedItem();

        if(livraisonSelectionnee == null) {
            DialogUtils.showDialog("Sélectionnez un client à livrer", "Choisir un client", Alert.AlertType.WARNING);
            return;
        }

        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        StatutDAO statutDAO = StatutDAO.getInstance();
        Statut statut = statutDAO.getByNom("Livré");

        Commande c = commandeDAO.getById(livraisonSelectionnee.getId());
        c.setStatut(statut);

        commandeDAO.updateWithoutDateCommande(c);

        this.updateLivraisonTableView();

        double soldeActuel = c.getClient().getSolde();
        double montant = c.getMontant();

        double soldeFinal = soldeActuel - montant;
        if(soldeFinal>=0){
            c.getClient().setSolde(soldeFinal);
            ClientDAO clientDAO = ClientDAO.getInstance();
            clientDAO.updateWithoutPassword(c.getClient());
            DialogUtils.showDialog("Commande livrée au client", "Commande livrée !", Alert.AlertType.INFORMATION);
        }
        else{
            DialogUtils.showDialog("Montant du solde du client insuffisant pour cette commande. Veuillez contacter votre responsable", "Commande non-livrable", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onExitButtonClick(MouseEvent event) {
        UserSessionUtil.getInstance(null).clearUserSession();
        JavaFXOpenWindowUtil.openAndCloseAWindow(new MainApplication(), ((Node) event.getSource()));
    }
}
