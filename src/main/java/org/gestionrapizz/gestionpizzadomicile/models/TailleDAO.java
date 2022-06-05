package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Taille;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TailleDAO extends DAO<Taille>{
    private static TailleDAO instance;

    private TailleDAO(){

    }

    public static TailleDAO getInstance(){
        if(TailleDAO.instance == null){
            TailleDAO.instance = new TailleDAO();
        }
        return TailleDAO.instance;
    }

    @Override
    public List<Taille> get() {
        return super.find("SELECT Taille.* FROM Taille;", new ArrayList<>());
    }

    @Override
    public Taille getById(int id) {
        String query = "SELECT Taille.* FROM Taille WHERE Taille.id_taille = ?;";
        List<Taille> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    public Taille getByNom(String nom) {
        String query = "SELECT Taille.* FROM Taille WHERE Taille.nom = ?;";
        List<Taille> result = super.find(query, List.of(nom));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Taille obj) {
        String query = "INSERT INTO Taille (nom) VALUES(?);";
        return super.add(query, List.of(obj.getNom()));
    }

    @Override
    public boolean update(Taille obj) {
        String query = "UPDATE Taille SET nom = ? WHERE Taille.id_taille = ?;";
        return super.modify(query, Arrays.asList(obj.getNom(), obj.getId())) > 0;
    }

    @Override
    public boolean delete(Taille obj) {
        String query = "DELETE FROM Taille WHERE Taille.id_taille = ?;";
        return super.modify(query, List.of(obj.getId())) > 0;
    }

    @Override
    public Taille resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Taille(
                resultSet.getInt("id_taille"),
                resultSet.getString("nom"));
    }
}
