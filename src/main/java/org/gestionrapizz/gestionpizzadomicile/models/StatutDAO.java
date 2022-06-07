package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Statut;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatutDAO extends DAO<Statut> {
    private static StatutDAO instance;

    private StatutDAO(){

    }

    public static StatutDAO getInstance(){
        if(StatutDAO.instance == null){
            StatutDAO.instance = new StatutDAO();
        }
        return StatutDAO.instance;
    }


    @Override
    public List<Statut> get() {
        String query = "SELECT Statut.* FROM Statut;";
        return super.find(query, new ArrayList<>());
    }

    @Override
    public Statut getById(int id) {
        String query = "SELECT Statut.* FROM Statut WHERE Statut.id_statut = ?;";
        List<Statut> statuts = super.find(query, List.of(id));
        return statuts.size() == 1 ? statuts.get(0) : null;
    }

    public Statut getByNom(String nom) {
        String query = "SELECT Statut.* FROM Statut WHERE Statut.nom = ?;";
        List<Statut> statuts = super.find(query, List.of(nom));
        return statuts.size() == 1 ? statuts.get(0) : null;
    }

    @Override
    public int insert(Statut obj) {
        String query = "INSERT INTO Statut (nom) VALUES(?);";
        return super.add(query, List.of(obj.getNom()));
    }

    @Override
    public boolean update(Statut obj) {
        String query = "UPDATE Statut SET nom = ? WHERE Statut.id_statut = ?;";
        return super.modify(query, Arrays.asList(obj.getId(), obj.getNom())) > 0;
    }

    @Override
    public boolean delete(Statut obj) {
        String query = "DELETE FROM Statut WHERE Statut.id_statut = ?;";
        return super.modify(query, List.of(obj.getId())) > 0;
    }

    @Override
    public Statut resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Statut(
                resultSet.getInt("id_statut"),
                resultSet.getString("nom")
        );
    }
}
