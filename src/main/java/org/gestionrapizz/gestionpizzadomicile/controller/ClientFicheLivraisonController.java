package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gestionrapizz.gestionpizzadomicile.models.ContenirDAO;
import org.gestionrapizz.gestionpizzadomicile.models.UtilisateurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Contenir;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Produit;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.LignePanier;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;

public class ClientFicheLivraisonController {
    @FXML
    private TableView<LignePanier> contenucommande_tableview;
    @FXML
    private TableColumn<LignePanier, String> nompizza_tablecolumn;
    @FXML
    private TableColumn<LignePanier, String> taillepizza_tablecolumn;
    @FXML
    private TableColumn<LignePanier, Double> montantpizza_tablecolumn;
    @FXML
    private Label clientname_label;
    @FXML
    private Label clientfirstname_label;
    @FXML
    private Label adresseclient_label;
    @FXML
    private Label nomlivreur_label;
    @FXML
    private Label prenomlivreur_label;
    @FXML
    private Label immatriculation_label;
    @FXML
    private Label typevehicule_label;
    @FXML
    private Label idcommande_label;
    @FXML
    private Label cityclient_label;
    @FXML
    private Label codepostal_label;

    public void initialize(){
        nompizza_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("nomPizza"));
        taillepizza_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("taillePizza"));
        montantpizza_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        this.updateDatas();
    }

    private void updateDatas(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        int idCommande = (int) userSessionUtil.getVAR_SESSION().get("id_commande");
        ContenirDAO contenirDAO = ContenirDAO.getInstance();
        List<Contenir> contenirList = contenirDAO.getByIdCommande(idCommande);

        if(contenirList.size() > 0){
            Commande commande = contenirList.get(0).getCommande();
            clientname_label.setText(commande.getClient().getNom());
            clientfirstname_label.setText(commande.getClient().getPrenom());
            adresseclient_label.setText(commande.getClient().getAdresseRue());
            nomlivreur_label.setText(commande.getLivreur().getNom());
            prenomlivreur_label.setText(commande.getLivreur().getPrenom());
            immatriculation_label.setText(commande.getVehicule().getImmatriculation());
            typevehicule_label.setText(commande.getVehicule().getType().getNom());
            idcommande_label.setText(String.valueOf(commande.getId()));
            cityclient_label.setText(commande.getClient().getVille());
            codepostal_label.setText(commande.getClient().getCodePostal());
        }

        for (Contenir contenir: contenirList) {
            Produit produit = contenir.getProduit();
            contenucommande_tableview.getItems().add(new LignePanier(produit.getPizza().getNom(), produit.getTaille().getNom(), contenir.getCommande().getMontant()));
        }
    }
}
