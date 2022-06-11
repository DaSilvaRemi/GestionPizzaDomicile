package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Pizza;
import org.gestionrapizz.gestionpizzadomicile.models.view.MenuPizzeria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuPizzeriaDAO extends DAO<MenuPizzeria> {
    private static MenuPizzeriaDAO instance;

    private MenuPizzeriaDAO() {
    }

    public static MenuPizzeriaDAO getInstance() {
        if(MenuPizzeriaDAO.instance == null){
            MenuPizzeriaDAO.instance = new MenuPizzeriaDAO();
        }

        return MenuPizzeriaDAO.instance;
    }

    @Override
    public List<MenuPizzeria> get() {
        String query = "SELECT Pizza.*, GROUP_CONCAT(Ingredients.nom) AS nomIngredients FROM Pizza " +
                "INNER JOIN Composer on Pizza.id_pizza = Composer.id_pizza " +
                "INNER JOIN Ingredients on Composer.id_ingredient = Ingredients.id_ingredient " +
                "GROUP BY Pizza.nom, Pizza.prix";
        return super.find(query, new ArrayList<>());
    }

    @Override
    public MenuPizzeria getById(int id) {
        String query = "SELECT Pizza.*, GROUP_CONCAT(Ingredients.nom) AS nomIngredients FROM Pizza " +
                "INNER JOIN Composer on Pizza.id_pizza = Composer.id_pizza " +
                "INNER JOIN Ingredients on Composer.id_ingredient = Ingredients.id_ingredient " +
                "WHERE Composer.id_pizza = ?" +
                "GROUP BY Pizza.id, Pizza.nom, Pizza.prix";
        List<MenuPizzeria> result = super.find(query, List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(MenuPizzeria obj) {
        return 0;
    }

    @Override
    public boolean update(MenuPizzeria obj) {
        return false;
    }

    @Override
    public boolean delete(MenuPizzeria obj) {
        return false;
    }

    @Override
    public MenuPizzeria resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new MenuPizzeria(new Pizza(
                resultSet.getInt("id_pizza"),
                resultSet.getString("nom"),
                resultSet.getDouble("prix")
                ), resultSet.getString("nomIngredients"));
    }
}
