package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Administrateur;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministrateurDAO extends DAO<Administrateur> {
    private static AdministrateurDAO instance;

    private AdministrateurDAO() {
    }

    public static AdministrateurDAO getInstance() {
        if(AdministrateurDAO.instance == null){
            AdministrateurDAO.instance = new AdministrateurDAO();
        }

        return AdministrateurDAO.instance;
    }

    @Override
    public List<Administrateur> get() {
        String query = "SELECT Utilisateur.*" +
                "FROM Administrateur " +
                "INNER JOIN Utilisateur ON Administrateur.id_utilisateur = Utilisateur.id_utilisateur;";
        return super.find(query, new ArrayList<>());
    }

    @Override
    public Administrateur getById(int id) {
        String query = "SELECT Utilisateur.*" +
                "FROM Administrateur " +
                "INNER JOIN Utilisateur ON Administrateur.id_utilisateur = Utilisateur.id_utilisateur " +
                "WHERE Utilisateur.id_utilisateur = ?;";
        List<Administrateur> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Administrateur obj) {
        int idUser = UtilisateurDAO.getInstance().insert(obj.getUtilisateur());
        if(idUser == 0) return 0;

        String query = "INSERT INTO Administrateur (id_utilisateur) VALUES(?);";
        return super.add(query, List.of(idUser));
    }

    @Override
    public boolean update(Administrateur obj) {
        return  UtilisateurDAO.getInstance().update(obj.getUtilisateur());
    }

    public boolean updateWithoutPassword(Administrateur obj) {
        return UtilisateurDAO.getInstance().updateWithoutPassword(obj.getUtilisateur());
    }

    @Override
    public boolean delete(Administrateur obj) {
        String query = "DELETE FROM Administrateur WHERE Administrateur.id_utilisateur = ?;";
        List<Object> params = List.of(obj.getId());
        return super.modify(query, params) > 0 && UtilisateurDAO.getInstance().delete(obj.getUtilisateur());
    }

    @Override
    public Administrateur resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Administrateur(
                resultSet.getInt("id_utilisateur"),
                resultSet.getString("nom"),
                resultSet.getString("prenom"),
                resultSet.getString("email"),
                resultSet.getString("motdepasse")
        );

    }
}
