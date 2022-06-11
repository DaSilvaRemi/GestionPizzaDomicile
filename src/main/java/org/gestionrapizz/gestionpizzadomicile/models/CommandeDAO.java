package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;

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
        String query = "SELECT Commande.* " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "ORDER BY Commande.dateHeure_commande DESC;";
        return super.find(query, new ArrayList<>());
    }

    @Override
    public Commande getById(int id)  {
        String query = "SELECT Commande.* " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Commande.id_commande = ? " +
                "ORDER BY Commande.dateHeure_commande DESC;";
        List<Commande> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    public List<Commande> getByImmatriculation(String immatriculation) {
        String query = "SELECT Commande.* " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Commande.immatriculation= ? " +
                "ORDER BY Commande.dateHeure_commande DESC;";
        return super.find(query, List.of(immatriculation));
    }


    public Commande getByIdStatut(int id) {
        String query = "SELECT Commande.* " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Commande.id_statut = ? " +
                "ORDER BY Commande.dateHeure_commande DESC;";
        List<Commande> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    public List<Commande> getByIdLivreur(int id) {
        String query = "SELECT Commande.* " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Commande.id_utilisateur = ? " +
                "ORDER BY Commande.dateHeure_commande DESC;";;
        return super.find(query, List.of(id));
    }

    public List<Commande> getByIdLivreurAndIdStatut(int idLivreur, int idStatut) {
        String query = "SELECT Commande.* " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Commande.id_utilisateur = ? AND Statut.id_statut = ? " +
                "ORDER BY Commande.dateHeure_commande;";;
        return super.find(query, Arrays.asList(idLivreur, idStatut));
    }

    public List<Commande> getByIdClient(int id) {
        String query = "SELECT Commande.* " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Commande.id_utilisateur_1 = ? " +
                "ORDER BY Commande.dateHeure_commande DESC;";
        return super.find(query, List.of(id));
    }

    public List<Commande> getByIdClientAndIdStatus(int idClient, int idStatut) {
        String query = "SELECT Commande.* " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Commande.id_utilisateur_1 = ? AND Commande.id_statut = ? " +
                "ORDER BY Commande.dateHeure_commande DESC;";
        return super.find(query, Arrays.asList(idClient, idStatut));
    }

    public Commande getTotMontantCommandeEnCoursByClient(int idClient)  {
        String query = "SELECT Commande.id_commande, Commande.dateHeure_commande, Commande.dateHeure_livraison, " +
                "Commande.retard, SUM(Commande.montant) AS montant, Commande.id_utilisateur, Commande.immatriculation, " +
                "Commande.id_utilisateur_1, Commande.id_statut " +
                "FROM Commande " +
                "INNER JOIN Livreur ON Commande.id_utilisateur = Livreur.id_utilisateur " +
                "INNER JOIN Vehicule ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Client ON Commande.id_utilisateur_1 = Client.id_utilisateur " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Commande.id_utilisateur_1 = ? AND (Statut.nom != ? AND Statut.nom != ?) " +
                "GROUP BY Commande.id_utilisateur_1;";
        List<Object> params = Arrays.asList(idClient, "Livré", "Refusé");
        List<Commande> result = super.find(query, params);
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Commande obj) {
        String query = "INSERT INTO Commande (montant, retard, id_statut, id_utilisateur, immatriculation, id_utilisateur_1) " +
                "VALUES(?, ?, ?, ?, ?, ?);";
        List<Object> params = Arrays.asList(
                obj.getMontant(),
                obj.isRetard(),
                obj.getStatut().getId(),
                obj.getLivreur().getId(),
                obj.getVehicule().getImmatriculation(),
                obj.getClient().getId()
        );
        return super.add(query, params);
    }

    @Override
    public boolean update(Commande obj) {
        String query = "UPDATE Commande SET " +
                "dateHeure_commande = ?, " +
                "dateHeure_livraison = ?," +
                "montant = ?, " +
                "retard = ?, " +
                "id_statut = ?, " +
                "id_utilisateur = ?, " +
                "immatriculation = ?, " +
                "id_utilisateur_1 = ? " +
                "WHERE Commande.id_commande = ?;";
        List<Object> params = Arrays.asList(
                obj.getDateHeure(),
                obj.getDateHeureLivraison(),
                obj.getMontant(),
                obj.isRetard(),
                obj.getStatut().getId(),
                obj.getLivreur().getId(),
                obj.getVehicule().getImmatriculation(),
                obj.getClient().getId(),
                obj.getId()
        );
        return super.modify(query, params) > 0;
    }

    public boolean updateWithoutDateLivraison(Commande obj) {
        String query = "UPDATE Commande SET " +
                "dateHeure_commande = CURRENT_TIMESTAMP(), " +
                "montant = ?, " +
                "retard = ?, " +
                "id_statut = ?, " +
                "id_utilisateur = ?, " +
                "immatriculation = ?, " +
                "id_utilisateur_1 = ? " +
                "WHERE Commande.id_commande = ?;";
        List<Object> params = Arrays.asList(
                obj.getMontant(),
                obj.isRetard(),
                obj.getStatut().getId(),
                obj.getLivreur().getId(),
                obj.getVehicule().getImmatriculation(),
                obj.getClient().getId(),
                obj.getId()
        );
        return super.modify(query, params) > 0;
    }

    public boolean updateWithoutDateCommande(Commande obj) {
        String query = "UPDATE Commande SET " +
                "dateHeure_livraison = CURRENT_TIMESTAMP(), " +
                "montant = ?, " +
                "retard = ?, " +
                "id_statut = ?, " +
                "id_utilisateur = ?, " +
                "immatriculation = ?, " +
                "id_utilisateur_1 = ? " +
                "WHERE Commande.id_commande = ?;";
        List<Object> params = Arrays.asList(
                obj.getMontant(),
                obj.isRetard(),
                obj.getStatut().getId(),
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
                VehiculeDAO.getInstance().getById(resultSet.getString("immatriculation")),
                ClientDAO.getInstance().getById(resultSet.getInt("id_utilisateur_1")),
                StatutDAO.getInstance().getById(resultSet.getInt("id_statut"))
        );
    }
}
