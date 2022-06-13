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
        stats_chiffre_affaires_label.setText(String.format("%.2f €", CA));
    }

    private void updatePizzaPlusAchetee() {
        // Pizza la plus achetée
        StatistiquesPizzaPlusDemandeeDAO statPizzaTOP1 = StatistiquesPizzaPlusDemandeeDAO.getInstance();
        StatistiquesPizzaPlusDemandee top1pizza = statPizzaTOP1.getPizzaPlusDemandeeTOP1();
        stats_pizza_top1_label.setText(top1pizza.getNom() + "\n(" + top1pizza.getNbCommandesPizza() + " achetée(s))");
    }

    private void updateMeilleurClient() {
        // Meilleur client
        StatistiquesMeilleurClientDAO statMeilleurClient = StatistiquesMeilleurClientDAO.getInstance();
        StatistiquesMeilleurClient topClient = statMeilleurClient.getMeilleurClient();
        stats_meilleur_client_label.setText(topClient.getPrenom() + " " + topClient.getNom() + "\n(" + topClient.getMontantTotal() + " € dépensés)");
    }

    private void updatePireLivreur() {
        // Pire livreur
        StatistiquesPireLivreurDAO statPireLivreur = StatistiquesPireLivreurDAO.getInstance();
        StatistiquesPireLivreur pireLivreur = statPireLivreur.getPireLivreur();
        stats_pire_livreur_label.setText(pireLivreur.getPrenom() + " " + pireLivreur.getNom() + "\n(" + pireLivreur.getNbRetards() + " retard(s))");
    }

    private void updateStatMeilleurIngredient() {
        // Meilleur ingrédient
        StatistiquesMeilleurIngredientDAO statMeilleurIngred = StatistiquesMeilleurIngredientDAO.getInstance();
        StatistiquesMeilleurIngredient meilleurIngred = statMeilleurIngred.getMeilleurIngredient();
        stats_meilleur_ingredient_label.setText(meilleurIngred.getNom() + "\n(Inclus dans " + meilleurIngred.getNbApparition() + " commandes)");

    }

    private void updateStatPizzaMoinsAchetee() {
        // Pizza la moins achetée
        StatistiquesPizzaMoinsAcheteeDAO statPirePizza = StatistiquesPizzaMoinsAcheteeDAO.getInstance();
        StatistiquesPizzaMoinsAchetee pirePizza = statPirePizza.getPizzaMoinsAchetee();
        stats_pizza_moins_achetee_label.setText(pirePizza.getNom() + "\n(" + pirePizza.getNbCommandesPizza() + " achetée(s))");
    }
}