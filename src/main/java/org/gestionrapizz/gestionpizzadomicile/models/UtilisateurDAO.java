package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Utilisateur;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilisateurDAO extends DAO<Utilisateur> {
    private static UtilisateurDAO instance;

    private UtilisateurDAO() {
    }

    public static UtilisateurDAO getInstance(){
        if(UtilisateurDAO.instance == null){
            UtilisateurDAO.instance = new UtilisateurDAO();
        }

        return UtilisateurDAO.instance;
    }

    @Override
    public List<Utilisateur> get(){
        return super.find("SELECT Utilisateur.* FROM Utilisateur;", new ArrayList<>());
    }

    @Override
    public Utilisateur getById(int id){
        List<Utilisateur> result = super.find("SELECT Utilisateur.* FROM Utilisateur WHERE Utilisateur.id_utilisateur ?;", List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    public Utilisateur getByEmail(String email){
        List<Utilisateur> result = super.find("SELECT Utilisateur.* FROM Utilisateur WHERE Utilisateur.email = ?;", List.of(email));

        return result.size() == 1 ? result.get(0) : null;
    }

    public Utilisateur getByEmailAndPassword(String email, String password){
        String query = "SELECT Utilisateur.* FROM Utilisateur WHERE " +
                "Utilisateur.email = ? AND " +
                "Utilisateur.motdepasse = PASSWORD(?);";
        List<Utilisateur> result =  super.find(query, List.of(email, password));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Utilisateur obj) {
        return super.add("INSERT INTO Utilisateur (nom, email, motdepasse) VALUES(?, ?, PASSWORD(?));", Arrays.asList(obj.getNom(), obj.getEmail(), obj.getMotdepasse()));
    }

    @Override
    public boolean update(Utilisateur obj) {
        String query = "UPDATE Utilisateur SET " +
                "Utilisateur.nom = ?, " +
                "Utilisateur.email = ?, " +
                "Utilisateur.motdepasse = PASSWORD(?) " +
                "WHERE Utilisateur.id_utilisateur = ?;";
        return super.modify(query, Arrays.asList(obj.getNom(), obj.getEmail(), obj.getMotdepasse())) > 0;
    }

    @Override
    public boolean delete(Utilisateur obj) {
        String query = "DELETE FROM Utilisateur WHERE Utilisateur.id_utilisateur = ?;";
        return super.modify(query, List.of(obj.getId())) > 0;
    }

    @Override
    public Utilisateur resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Utilisateur(
                resultSet.getInt("id_utilisateur"),
                resultSet.getString("nom"),
                resultSet.getString("email"),
                resultSet.getString("motdepasse")
        );
    }
}
