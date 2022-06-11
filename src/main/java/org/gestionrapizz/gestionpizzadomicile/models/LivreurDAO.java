package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivreurDAO extends DAO<Livreur> {
    private static LivreurDAO instance;

    private LivreurDAO() {
    }

    public static LivreurDAO getInstance() {
        if(LivreurDAO.instance == null){
            LivreurDAO.instance = new LivreurDAO();
        }

        return LivreurDAO.instance;
    }

    @Override
    public List<Livreur> get() {
        String query = "SELECT Utilisateur.*" +
                "FROM Livreur " +
                "INNER JOIN Utilisateur ON Livreur.id_utilisateur = Utilisateur.id_utilisateur;";
        return super.find(query, new ArrayList<>());
    }

    public List<Livreur> getLivreurDisponible() {
        String query = "SELECT Utilisateur.* FROM Livreur " +
                "INNER JOIN Utilisateur ON Livreur.id_utilisateur = Utilisateur.id_utilisateur " +
                "WHERE Utilisateur.id_utilisateur NOT IN " +
                "(SELECT Commande.id_utilisateur " +
                "FROM Commande " +
                "WHERE Commande.id_statut IN " +
                "(SELECT Statut.id_statut FROM Statut WHERE Statut.nom = ?))";
        return super.find(query, List.of("Livraison en cours"));
    }

    @Override
    public Livreur getById(int id) {
        String query = "SELECT Utilisateur.*" +
                "FROM Livreur " +
                "INNER JOIN Utilisateur ON Livreur.id_utilisateur = Utilisateur.id_utilisateur " +
                "WHERE Utilisateur.id_utilisateur = ?;";
        List<Livreur> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Livreur obj) {
        int idUser = UtilisateurDAO.getInstance().insert(obj.getUtilisateur());
        if(idUser == 0) return 0;

        String query = "INSERT INTO Livreur (id_utilisateur) VALUES(?);";
        return super.modify(query, List.of(idUser));
    }

    @Override
    public boolean update(Livreur obj) {
        return  UtilisateurDAO.getInstance().update(obj.getUtilisateur());
    }

    public boolean updateWithoutPassword(Livreur obj) {
        return UtilisateurDAO.getInstance().updateWithoutPassword(obj.getUtilisateur());
    }

    @Override
    public boolean delete(Livreur obj) {
        String query = "DELETE FROM Livreur WHERE Livreur.id_utilisateur = ?;";
        List<Object> params = List.of(obj.getId());
        return super.modify(query, params) > 0 && UtilisateurDAO.getInstance().delete(obj.getUtilisateur());
    }

    @Override
    public Livreur resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Livreur(
                resultSet.getInt("id_utilisateur"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getString("email"),
                resultSet.getString("motdepasse")
        );

    }
}
