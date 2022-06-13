package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Utilisateur;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Vehicule;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesLivraisonsAFaire;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesVehicules;
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
    }

    private void updateLivraisonTableView() {
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        StatutDAO statutDAO = StatutDAO.getInstance();
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        List<Commande> allCommandes = commandeDAO.getByIdLivreurAndIdStatut(userSessionUtil.getUtilisateur().getId() , statutDAO.getByNom("Livraison en cours").getId());

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

        for (Commande commande : allCommandes){
            livraison_table_view.getItems().add(new MesLivraisonsAFaire(commande));
        }
    }
}
