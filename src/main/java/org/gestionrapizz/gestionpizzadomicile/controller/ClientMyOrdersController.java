package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.ClientFicheLivraisonApplication;
import org.gestionrapizz.gestionpizzadomicile.models.CommandeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesCommandes;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;

public class ClientMyOrdersController {
    @FXML
    private TableColumn<MesCommandes, Integer> id_commande_tablecolumn;
    @FXML
    private TableColumn<MesCommandes, String> dateheure_commande_tablecolumn;
    @FXML
    private TableColumn<MesCommandes, String> dateheure_livraison_tablecolumn;
    @FXML
    private TableColumn<MesCommandes, String> statut_commande_tablecolumn;
    @FXML
    private TableColumn<MesCommandes, String> livreur_commande_tablecolumn;
    @FXML
    private TableColumn<MesCommandes, Double> montant_commande_tablecolumn;
    @FXML
    private TableView<MesCommandes> orders_tableview;

    public void initialize(){
        id_commande_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dateheure_commande_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("dateHeureCommande"));
        dateheure_livraison_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("dateHeureLivraison"));
        statut_commande_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("statut"));
        livreur_commande_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("livreur"));
        montant_commande_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("montant"));

        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        List<Commande> commandeList = commandeDAO.getByIdClient(userSessionUtil.getUtilisateur().getId());
        for (Commande commande : commandeList) {
            orders_tableview.getItems().add(new MesCommandes(commande));
        }
    }

    @FXML
    private void onBonLivraisonButtonClicked(MouseEvent event){
        MesCommandes mesCommandes = this.orders_tableview.getSelectionModel().getSelectedItem();
        if(mesCommandes == null || !mesCommandes.getStatut().equals("Livré")){
            DialogUtils.showDialog("Veuillez sélectionner une commande ayant le statut 'Livré'");
            return;
        }

        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        userSessionUtil.getVAR_SESSION().put("id_commande", mesCommandes.getId());
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientFicheLivraisonApplication(), ((Node) event.getSource()));
    }
}
