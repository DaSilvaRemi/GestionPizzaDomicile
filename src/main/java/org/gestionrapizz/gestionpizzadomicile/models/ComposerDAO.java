package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Composer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComposerDAO extends DAO<Composer>{
    private static ComposerDAO instance;

    private ComposerDAO(){

    }

    public static ComposerDAO getInstance(){
        if(instance == null){
            instance = new ComposerDAO();
        }
        return instance;
    }

    @Override
    public List<Composer> get() {
        return super.find("SELECT Composer.* FROM Composer;", new ArrayList<>());
    }

    @Override
    public Composer getById(int id) {
        return this.getByIdPizzaAndIdIngredient(id, id);
    }


    public List<Composer> getByIdPizza(int id) {
        return super.find("SELECT Composer.* FROM Type WHERE Composer.id_pizza = ?;", List.of(id));
    }

    public List<Composer> getByIdIngredient(int id) {
        return super.find("SELECT Composer.* FROM Type WHERE Composer.id_ingredient = ?;", List.of(id));
    }

    public Composer getByIdPizzaAndIdIngredient(int idPizza, int idIngredient) {
        String query = "SELECT Composer.* FROM Composer WHERE Composer.id_pizza = ? AND Composer.id_ingredient = ?;";
        List<Composer> result = super.find(query, Arrays.asList(idPizza, idIngredient));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Composer obj) {
        return super.add("INSERT INTO Composer(id_pizza, id_ingredient) VALUES(?, ?);", Arrays.asList(obj.getPizza().getId(), obj.getIngredients().getId()));
    }

    @Override
    public boolean update(Composer obj) {
        return this.updateByPizza(obj);
    }

    public boolean updateByTaille(Composer obj) {
        String query = "UPDATE Composer SET Composer.id_pizza = ? WHERE Composer.id_ingredient = ?;";
        return super.modify(query, Arrays.asList(obj.getPizza().getId(), obj.getIngredients().getId())) > 0;
    }

    public boolean updateByPizza(Composer obj) {
        String query = "UPDATE Composer SET Composer.id_ingredient = ? WHERE Composer.id_pizza = ?;";
        return super.modify(query, Arrays.asList(obj.getIngredients().getId(), obj.getPizza().getId())) > 0;
    }

    @Override
    public boolean delete(Composer obj) {
        String query = "DELETE FROM Composer WHERE Composer.id_pizza = ? AND Composer.id_ingredient = ?;";
        return super.modify(query, Arrays.asList(obj.getPizza().getId(), obj.getIngredients().getId())) > 0;
    }

    @Override
    public Composer resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Composer(
                PizzaDAO.getInstance().getById(resultSet.getInt("id_pizza")),
                IngredientsDAO.getInstance().getById(resultSet.getInt("id_ingredient"))
        );
    }
}
