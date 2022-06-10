package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import org.gestionrapizz.gestionpizzadomicile.models.entity.*;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.LignePanier;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;

public class ClientOrderPizzaController {
    @FXML
    private TableView<LignePanier> cart_tableview;
    @FXML
    private TableColumn<LignePanier, String> pizza_tablecolumn;
    @FXML
    private TableColumn<LignePanier, String> taille_tablecolumn;
    @FXML
    private TableColumn<LignePanier, Double> price_tablecolumn;
    @FXML
    private ChoiceBox<String> pizzaschoice_selector;
    @FXML
    private ChoiceBox<String> sizepizzachoice_selector;
    @FXML
    private Label totalAmount_label;

    private Commande currentCommande;

    public void initialize(){
        pizza_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("nomPizza"));
        taille_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("taillePizza"));
        price_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

       List<Taille> tailles = TailleDAO.getInstance().get();
       List<Pizza> pizzas = PizzaDAO.getInstance().get();

        for (Pizza pizza: pizzas) {
            pizzaschoice_selector.getItems().add(pizza.getId() + " - " + pizza.getNom() + " " + pizza.getPrix() + " €");
        }

        for (Taille taille: tailles) {
            sizepizzachoice_selector.getItems().add(taille.getId() + " - " + taille.getNom());
        }

        this.updateCommmande();
        this.updateCart();
        this.updateMontant();
    }

    private void updateCommmande(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        //INIT DAO
        LivreurDAO livreurDAO = LivreurDAO.getInstance();
        VehiculeDAO vehiculeDAO = VehiculeDAO.getInstance();
        ClientDAO clientDAO = ClientDAO.getInstance();
        StatutDAO statutDAO = StatutDAO.getInstance();
        CommandeDAO commandeDAO = CommandeDAO.getInstance();

        //Get entities
        Statut statut = statutDAO.getByNom("En attente");
        Client client = clientDAO.getById(userSessionUtil.getUtilisateur().getId());
        List<Vehicule> vehiculesDisponible = vehiculeDAO.getVehiculesDisponible();
        List<Livreur> livreurDisponible = livreurDAO.getLivreurDisponible();

        Vehicule vehicule = vehiculesDisponible.size() > 0 ? vehiculesDisponible.get(0) : vehiculeDAO.get().get(0);
        Livreur livreur = livreurDisponible.size() > 0 ? livreurDisponible.get(0) : livreurDAO.get().get(0);

        List<Commande> commandes = commandeDAO.getByIdClientAndIdStatus(client.getId(), statut.getId());
        this.currentCommande = commandes.size() > 0 ? commandes.get(0) : commandeDAO.getById(commandeDAO.insert(new Commande(livreur, vehicule, client, statut)));
    }

    private void updateMontant(){
        this.currentCommande = CommandeDAO.getInstance().getById(this.currentCommande.getId());
        String newSolde = String.format("%.2f €", this.currentCommande.getMontant());
        this.totalAmount_label.setText(newSolde);
    }

    private void updateCart(){
        this.cart_tableview.getItems().clear();
        ContenirDAO contenirDAO = ContenirDAO.getInstance();
        List<Contenir> contenirs = contenirDAO.getByIdCommande(this.currentCommande.getId());

        for (Contenir contenir: contenirs) {
            Produit produit = contenir.getProduit();
            this.cart_tableview.getItems().add(new LignePanier(produit.getPizza().getNom(), produit.getTaille().getNom(), produit.getPrixProduit()));
        }
    }


    @FXML
    protected void onClickAddPizzaButton(MouseEvent event){
        String selectedPizza = pizzaschoice_selector.getSelectionModel().getSelectedItem();
        String selectedTaille = sizepizzachoice_selector.getSelectionModel().getSelectedItem();
        int idPizza = Integer.parseInt(selectedPizza.substring(0, selectedPizza.indexOf("-") - 1));
        int idTaille = Integer.parseInt(selectedTaille.substring(0, selectedTaille.indexOf("-") - 1));

        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        ProduitDAO produitDAO = ProduitDAO.getInstance();
        ContenirDAO contenirDAO = ContenirDAO.getInstance();

        Contenir contenir = contenirDAO.getByIdCommandeAndIdTailleAndPizza(this.currentCommande.getId(), idTaille, idPizza);
        if(contenir != null){
            DialogUtils.showDialog("Vous ne pouvez pas ajoutez deux fois la même pizza avec la même taille dans la même commande !", "Action impossible !", Alert.AlertType.WARNING);
            return;
        }

        if(!this.verifySoldeClient()){
            return;
        }

        Produit produit = produitDAO.getByIdTailleAndPizza(idTaille, idPizza);
        this.currentCommande.setMontant(this.currentCommande.getMontant() + produit.getPrixProduit());
        boolean isUpdate = commandeDAO.update(this.currentCommande);
        if(!isUpdate){
            DialogUtils.showDialog("Impossible de mettre à jour la table !", "");
        }

        contenirDAO.insert(new Contenir(this.currentCommande, produit));
        this.updateCart();
        this.updateMontant();
    }

    @FXML
    protected void onClickRemovePizzaButton(MouseEvent event){
        LignePanier lignePanier = cart_tableview.getSelectionModel().getSelectedItem();

        if(lignePanier == null || lignePanier.getNomPizza().isBlank() || lignePanier.getTaillePizza().isBlank()) {
            return;
        }

        ContenirDAO contenirDAO = ContenirDAO.getInstance();
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        PizzaDAO pizzaDAO = PizzaDAO.getInstance();
        TailleDAO tailleDAO = TailleDAO.getInstance();

        Pizza pizza = pizzaDAO.getByNom(lignePanier.getNomPizza());
        Taille taille = tailleDAO.getByNom(lignePanier.getTaillePizza());
        Contenir contenir = contenirDAO.getByIdCommandeAndIdTailleAndPizza(this.currentCommande.getId(), taille.getId(), pizza.getId());
        contenirDAO.delete(contenir);
        this.currentCommande.setMontant(this.currentCommande.getMontant() - contenir.getProduit().getPrixProduit());
        commandeDAO.update(this.currentCommande);
        this.updateCart();
        this.updateMontant();
    }

    @FXML
    protected void onConfirmOrderButton(MouseEvent event){
        StatutDAO statutDAO = StatutDAO.getInstance();
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        ContenirDAO contenirDAO = ContenirDAO.getInstance();

        if(this.cart_tableview.getItems().size() == 0 || contenirDAO.getByIdCommande(this.currentCommande.getId()).size() == 0){
            DialogUtils.showDialog("Aucun produits sélectionnés !", "Erreur : commande vide !", Alert.AlertType.ERROR);
            return;
        }

        if(!this.verifySoldeClient()){
            return;
        }

        this.currentCommande.setStatut(statutDAO.getByNom("Livraison en cours"));
        boolean isConfirm = commandeDAO.confirmACommand(this.currentCommande);

        if(isConfirm){
            DialogUtils.showDialog("Commande confirmé ! \n Votre commande sera débité lors de la livraison !");
            this.clearCart();
        }
    }

    @FXML
    protected void onClearCartButton(MouseEvent event){
        this.clearCommande();
        this.clearCart();
    }

    private void clearCommande(){
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        ContenirDAO contenirDAO = ContenirDAO.getInstance();
        contenirDAO.deleteByCommande(this.currentCommande);
        commandeDAO.delete(this.currentCommande);
    }

    private void clearCart(){
        this.updateCommmande();
        this.updateMontant();
        this.updateCart();
        DialogUtils.showDialog("Panier effacé !");
    }

    private boolean verifySoldeClient(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        ClientDAO clientDAO = ClientDAO.getInstance();
        Client client = clientDAO.getById(userSessionUtil.getUtilisateur().getId());

        if(client.getSolde() < this.currentCommande.getMontant()){
            DialogUtils.showDialog("Votre solde est insuffisant pour cette commande !", "Erreur : solde insuffisant !", Alert.AlertType.ERROR);
            return false;
        }
        return true;
    }
}
