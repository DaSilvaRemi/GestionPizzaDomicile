package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Pizza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PizzaDAO extends DAO<Pizza> {
    private static PizzaDAO instance;

    private PizzaDAO(){

    }

    public static PizzaDAO getInstance(){
        if(PizzaDAO.instance == null){
            PizzaDAO.instance = new PizzaDAO();
        }
        return PizzaDAO.instance;
    }

    @Override
    public List<Pizza> get() {
        return super.find("SELECT Pizza.* FROM Pizza;", new ArrayList<>());
    }

    @Override
    public Pizza getById(int id) {
        String query = "SELECT Pizza.* FROM Pizza WHERE Pizza.id_pizza = ?;";
        List<Pizza> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    public Pizza getByNom(String nom) {
        String query = "SELECT Pizza.* FROM Pizza WHERE Pizza.nom = ?;";
        List<Pizza> result = super.find(query, List.of(nom));
        return result.size() == 1 ? result.get(0) : null;
    }


    @Override
    public int insert(Pizza obj) {
        String query = "INSERT INTO Pizza(nom, prix) VALUES(?, ?);";
        return super.add(query, Arrays.asList(obj.getNom(), obj.getPrix()));
    }

    @Override
    public boolean update(Pizza obj) {
        String query = "UPDATE Pizza SET nom = ?, prix = ? WHERE Pizza.id_pizza = ?;";
        return super.modify(query, Arrays.asList(obj.getNom(), obj.getPrix(), obj.getId())) > 0;
    }

    @Override
    public boolean delete(Pizza obj) {
        String query = "DELETE FROM Pizza WHERE Pizza.id_pizza = ?;";
        return super.modify(query, List.of(obj.getId())) > 0;
    }

    @Override
    public Pizza resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Pizza(
                resultSet.getInt("id_pizza"),
                resultSet.getString("nom"),
                resultSet.getDouble("prix")
        );
    }
}
