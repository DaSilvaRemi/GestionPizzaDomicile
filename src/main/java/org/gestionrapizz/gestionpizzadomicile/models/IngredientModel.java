package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientModel extends GestionBDDModele {
    public IngredientModel(){
        super();
    }

    public IngredientModel(String urlHote, String user, String password) {
        super(urlHote, user, password);
    }

    public ResultSet getAllIngredients() throws SQLException {
        return super.setMyStatement("SELECT i.nom FROM ingredient i;").getRequestResult();
    }

    public ResultSet getAllIngredientsById(int id) throws SQLException {
        super.setMyStatement("SELECT i.nom FROM ingredient i WHERE i.idIngredient = ?;");
        super.getMyStatement().setInt(1, id);
        return this.getRequestResult();
    }

    public ResultSet getMostUsedIngredients() throws SQLException {
        super.setMyStatement("SELECT ingredient.id, ingredient.nom, COUNT(contenir.idIngredient) AS nbIngredients FROM ingredient i " +
                "INNER JOIN contenir ON ingredient.idIngredient = contenir.idIngredients" +
                "INNER JOIN pizza ON pizza.idPizza = contenir.idPizza" +
                "INNER JOIN commande ON commande.idPizza = pizza.idPizza " +
                "WHERE commande.status = ?" +
                "GROUP BY ingredient.id, ingredient.nom " +
                "ORDER BY nbIngredients DESC" +
                "LIMIT 5;");
        super.getMyStatement().setString(1, "Livree");
        return super.getRequestResult();
    }

    /**
     *
     * @return
     * @throws SQLException
     */
    public ResultSet getLessUsedIngredients() throws SQLException {
        super.setMyStatement("SELECT ingredient.id, ingredient.nom, COUNT(contenir.idIngredient) AS nbIngredients FROM ingredient i " +
                "INNER JOIN contenir ON ingredient.idIngredient = contenir.idIngredients" +
                "INNER JOIN pizza ON pizza.idPizza = contenir.idPizza" +
                "INNER JOIN commande ON commande.idPizza = pizza.idPizza " +
                "GROUP BY ingredient.id, ingredient.nom " +
                "ORDER BY nbIngredients ASC" +
                "LIMIT 5;");
        return super.getRequestResult();
    }
}
