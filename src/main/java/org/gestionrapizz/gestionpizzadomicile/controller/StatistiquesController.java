package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import org.gestionrapizz.gestionpizzadomicile.models.view.*;

public class StatistiquesController {
    @FXML
    private Label stats_chiffre_affaires_label;

    @FXML
    private Label stats_pizza_top1_label;

    @FXML
    private Label stats_meilleur_client_label;

    @FXML
    private Label stats_pire_livreur_label;

    @FXML
    private Label stats_meilleur_ingredient_label;

    @FXML
    private Label stats_pizza_moins_achetee_label;


    public void initialize() {
        this.updateStats();
    }

    private void updateStats() {
        this.updateChiffreAffaire();
        this.updatePizzaPlusAchetee();
        this.updateMeilleurClient();
        this.updatePireLivreur();
        this.updateStatMeilleurIngredient();
        this.updateStatPizzaMoinsAchetee();
    }

    private void updateChiffreAffaire() {
        // Chiffre d'affaires
        StatistiquesChiffreAffairesDAO statCA = StatistiquesChiffreAffairesDAO.getInstance();
        Double CA = statCA.getCA();
        StatistiquesNombrePizzaAcheteesDAO nbPizza = StatistiquesNombrePizzaAcheteesDAO.getInstance();
        int nbPizzaCA = nbPizza.getNbPizzaAchetees();
        String s_ou_pas = nbPizzaCA > 1 ? "s" : "";
        stats_chiffre_affaires_label.setText(String.format("%.2f €", CA) + "\n(Pour " + nbPizzaCA + " pizza" + s_ou_pas + " achetée" + s_ou_pas + ")");
    }

    private void updatePizzaPlusAchetee() {
        // Pizza la plus achetée
        StatistiquesPizzaPlusDemandeeDAO statPizzaTOP1 = StatistiquesPizzaPlusDemandeeDAO.getInstance();
        StatistiquesPizzaPlusDemandee top1pizza = statPizzaTOP1.getPizzaPlusDemandeeTOP1();
        int nbPizza = top1pizza.getNbCommandesPizza();
        stats_pizza_top1_label.setText(top1pizza.getNom() + "\n(" + nbPizza + " achetée" + (nbPizza > 1 ? "s" : "") + ")");
    }

    private void updateMeilleurClient() {
        // Meilleur client
        StatistiquesMeilleurClientDAO statMeilleurClient = StatistiquesMeilleurClientDAO.getInstance();
        StatistiquesMeilleurClient topClient = statMeilleurClient.getMeilleurClient();
        int montant = topClient.getMontantTotal();
        stats_meilleur_client_label.setText(topClient.getPrenom() + " " + topClient.getNom() + "\n(" + montant + " € dépensé" + (montant > 1 ? "s" : "") + ")");
    }

    private void updatePireLivreur() {
        // Pire livreur
        StatistiquesPireLivreurDAO statPireLivreur = StatistiquesPireLivreurDAO.getInstance();
        StatistiquesPireLivreur pireLivreur = statPireLivreur.getPireLivreur();
        int nbRetards = pireLivreur.getNbRetards();
        stats_pire_livreur_label.setText(pireLivreur.getPrenom() + " " + pireLivreur.getNom() + "\n(" + nbRetards + " retard" + (nbRetards > 1  ? "s" : "") + ")");
    }

    private void updateStatMeilleurIngredient() {
        // Meilleur ingrédient
        StatistiquesMeilleurIngredientDAO statMeilleurIngred = StatistiquesMeilleurIngredientDAO.getInstance();
        StatistiquesMeilleurIngredient meilleurIngred = statMeilleurIngred.getMeilleurIngredient();
        int nbApparition = meilleurIngred.getNbApparition();
        stats_meilleur_ingredient_label.setText(meilleurIngred.getNom() + "\n(Inclus dans " + nbApparition + " commande" + (nbApparition > 1 ? "s" : "") + ")");

    }

    private void updateStatPizzaMoinsAchetee() {
        // Pizza la moins achetée
        StatistiquesPizzaMoinsAcheteeDAO statPirePizza = StatistiquesPizzaMoinsAcheteeDAO.getInstance();
        StatistiquesPizzaMoinsAchetee pirePizza = statPirePizza.getPizzaMoinsAchetee();
        int nbCommande = pirePizza.getNbCommandesPizza();
        stats_pizza_moins_achetee_label.setText(pirePizza.getNom() + "\n(" + nbCommande + " achetée" + (nbCommande > 1 ? "s" : "") + ")");
    }
}