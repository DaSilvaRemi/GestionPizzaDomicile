package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommandeModel extends GestionBDDModel {
    public ResultSet getCommandes() throws SQLException {
        super.setMyStatement("SELECT commande.* FROM commande;");
        return super.getQueryResult();
    }

    public ResultSet getCommandeById(int id) throws SQLException {
        super.setMyStatement("SELECT commande.* FROM commande WHERE commande.id_commande = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getQueryResult();
    }

    public ResultSet getCommandesWithUsersNames() throws SQLException {
        super.setMyStatement("SELECT Commande.id_commande, Commande.dateHeure_commande, Commande.dateHeure_livraison, Commande.statut, Commande.montant, Commande.retard, " +
                "ULivreur.nom, UClient.nom, Commande.immatriculation " +
                "FROM Commande " +
                "INNER JOIN Client C on Commande.id_utilisateur_1 = C.id_utilisateur " +
                "INNER JOIN Livreur L on Commande.id_utilisateur = L.id_utilisateur " +
                "INNER JOIN Vehicule V on Commande.immatriculation = V.immatriculation " +
                "INNER JOIN Utilisateur UClient on C.id_utilisateur = UClient.id_utilisateur " +
                "INNER JOIN Utilisateur ULivreur ON L.id_utilisateur = ULivreur.id_utilisateur;");
        return super.getQueryResult();
    }

    public ResultSet getCommandeByIdWithUsersNames(int id) throws SQLException {
        super.setMyStatement("SELECT Commande.id_commande, Commande.dateHeure_commande, Commande.dateHeure_livraison, Commande.statut, Commande.montant, Commande.retard, " +
                "ULivreur.nom, UClient.nom, Commande.immatriculation " +
                "FROM Commande " +
                "INNER JOIN Client C on Commande.id_utilisateur_1 = C.id_utilisateur " +
                "INNER JOIN Livreur L on Commande.id_utilisateur = L.id_utilisateur " +
                "INNER JOIN Vehicule V on Commande.immatriculation = V.immatriculation " +
                "INNER JOIN Utilisateur UClient on C.id_utilisateur = UClient.id_utilisateur " +
                "INNER JOIN Utilisateur ULivreur ON L.id_utilisateur = ULivreur.id_utilisateur" +
                "WHERE Commande.id_commande = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getQueryResult();
    }

    public ResultSet getCommandesWithUsersNamesByLivreursId(int id) throws SQLException {
        super.setMyStatement("SELECT Commande.id_commande, Commande.dateHeure_commande, Commande.dateHeure_livraison, Commande.statut, Commande.montant, Commande.retard, " +
                "ULivreur.nom AS nomLivreur, UClient.nom AS nomClient, Commande.immatriculation " +
                "FROM Commande " +
                "INNER JOIN Client C on Commande.id_utilisateur_1 = C.id_utilisateur " +
                "INNER JOIN Livreur L on Commande.id_utilisateur = L.id_utilisateur " +
                "INNER JOIN Vehicule V on Commande.immatriculation = V.immatriculation " +
                "INNER JOIN Utilisateur UClient on C.id_utilisateur = UClient.id_utilisateur " +
                "INNER JOIN Utilisateur ULivreur ON L.id_utilisateur = ULivreur.id_utilisateur" +
                "WHERE ULivreur.id_utilisateur = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getQueryResult();
    }

    public ResultSet getCommandesWithUsersNamesByClientId(int id) throws SQLException {
        super.setMyStatement("SELECT Commande.id_commande, Commande.dateHeure_commande, Commande.dateHeure_livraison, Commande.statut, Commande.montant, Commande.retard, " +
                "ULivreur.nom AS nomLivreur, UClient.nom AS nomClient, Commande.immatriculation " +
                "FROM Commande " +
                "INNER JOIN Client C on Commande.id_utilisateur_1 = C.id_utilisateur " +
                "INNER JOIN Livreur L on Commande.id_utilisateur = L.id_utilisateur " +
                "INNER JOIN Vehicule V on Commande.immatriculation = V.immatriculation " +
                "INNER JOIN Utilisateur UClient on C.id_utilisateur = UClient.id_utilisateur " +
                "INNER JOIN Utilisateur ULivreur ON L.id_utilisateur = ULivreur.id_utilisateur" +
                "WHERE UClient.id_utilisateur = ?;");
        super.getMyStatement().setInt(1, id);
        return super.getQueryResult();
    }
}
