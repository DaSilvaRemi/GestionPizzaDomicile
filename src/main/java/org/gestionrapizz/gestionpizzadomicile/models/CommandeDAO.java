package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Utilisateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandeDAO extends DAO<Commande> {
    private static CommandeDAO instance;

    private CommandeDAO(){

    }

    public static CommandeDAO getInstance(){
        if(CommandeDAO.instance == null){
            CommandeDAO.instance = new CommandeDAO();
        }
        return CommandeDAO.instance;
    }

    @Override
    public List<Commande> get() {
        String query = "SELECT Commande.*, Statut.nom, Client.* " +
                "FROM Commande " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "INNER JOIN Client ON Commande.id_utilisateur = Client.id_utilisateur";
        return super.find(query, new ArrayList<>());
    }

    @Override
    public Commande getById(int id)  {
        String query = "SELECT Commande.*, Statut.nom, Client.* " +
                "FROM Commande " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "INNER JOIN Client ON Commande.id_utilisateur = Client.id_utilisateur " +
                "WHERE Commande.id_commande = ?";
        List<Commande> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }


    public Commande getByIdStatut(int id) {
        String query = "SELECT Commande.*, Statut.nom, Client.* " +
                "FROM Commande " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "INNER JOIN Client ON Commande.id_utilisateur = Client.id_utilisateur " +
                "WHERE Commande.id_statut = ?";
        List<Commande> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    public List<Commande> getCommandesByIdUtilisateur(int id) {
        String query = "SELECT Commande.*, Statut.nom, Client.telephone, Client.adresse_rue, Client.adresse_ville, Client.adresse_codepostal, Client.solde " +
                "FROM Commande " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "INNER JOIN Client ON Commande.id_utilisateur = Client.id_utilisateur " +
                "WHERE Commande.id_utilisateur = ?";
        return super.find(query, List.of(id));
    }

    @Override
    public int insert(Commande obj) {
        int idStatut = StatutDAO.getInstance().insert(obj.getStatut());

        String query = "INSERT INTO Commande (montant, retard, id_statut, id_utilisateur, immatriculation, id_utilisateur_1) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        List<Object> params = Arrays.asList(
                obj.getMontant(),
                obj.isRetard(),
                idStatut,
                obj.getLivreur().getId(),
                obj.getVehicule().getImmatriculation(),
                obj.getClient().getId()
        );
        return super.add(query, params);
    }

    @Override
    public boolean update(Commande obj) {
        int idStatut = StatutDAO.getInstance().insert(obj.getStatut());

        String query = "UPDATE Commande SET " +
                "dateHeure_commande = ?" +
                "dateHeure_livraison = ?," +
                "montant = ?, " +
                "retard = ?, " +
                "id_statut = ?, " +
                "id_utilisateur = ?, " +
                "immatriculation = ?, " +
                "id_utilisateur_1 = ?" +
                "WHERE Commande.id_commande = ?;";
        List<Object> params = Arrays.asList(
                obj.getDateHeure(),
                obj.getDateHeureLivraison(),
                obj.getMontant(),
                obj.isRetard(),
                idStatut,
                obj.getLivreur().getId(),
                obj.getVehicule().getImmatriculation(),
                obj.getClient().getId(),
                obj.getId()
        );
        return super.modify(query, params) > 0;
    }

    @Override
    public boolean delete(Commande obj) {
        String query = "DELETE FROM Commande WHERE Commande.id_commande = ?;";
        return super.modify(query, Arrays.asList(obj.getId())) > 0;
    }

    @Override
    public Commande resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Commande(
          resultSet.getInt("id_commande"),
          resultSet.getTimestamp("dateHeure_commande"),
                resultSet.getTimestamp("dateHeure_livraison"),
                resultSet.getDouble("montant"),
                resultSet.getBoolean("retard"),
                LivreurDAO.getInstance().getById(resultSet.getInt("id_utilisateur")),
                VehiculeDAO.getInstance().getById(resultSet.getInt("immatriculation")),
                ClientDAO.getInstance().getById(resultSet.getInt("id_utilisateur_1")),
                StatutDAO.getInstance().getById(resultSet.getInt("id_statut"))
        );
    }
}
