package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IngredientsDAO extends DAO<Ingredients> {
    private static IngredientsDAO instance;

    private IngredientsDAO(){

    }

    public static IngredientsDAO getInstance(){
        if(IngredientsDAO.instance == null){
            IngredientsDAO.instance = new IngredientsDAO();
        }
        return IngredientsDAO.instance;
    }

    @Override
    public List<Ingredients> get() {
        return super.find("SELECT Ingredients.* FROM Ingredients;", new ArrayList<>());
    }

    @Override
    public Ingredients getById(int id) {
        String query = "SELECT Pizza.* FROM Ingredients WHERE Ingredients.id_ingredient = ?;";
        List<Ingredients> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Ingredients obj) {
        String query = "INSERT INTO Ingredients (nom) VALUES(?);";
        return super.add(query, List.of(obj.getNom()));
    }

    @Override
    public boolean update(Ingredients obj) {
        String query = "UPDATE Ingredients SET nom = ? WHERE Ingredients.id_ingredient = ?;";
        return super.modify(query, Arrays.asList(obj.getNom(), obj.getId())) > 0;
    }

    @Override
    public boolean delete(Ingredients obj) {
        String query = "DELETE FROM Ingredients WHERE Ingredients.id_ingredient = ?;";
        return super.modify(query, List.of(obj.getId())) > 0;
    }

    @Override
    public Ingredients resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Ingredients(
                resultSet.getInt("id_ingredient"),
                resultSet.getString("nom"));
    }
}
