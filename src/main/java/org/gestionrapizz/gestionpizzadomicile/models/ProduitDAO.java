package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Produit;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProduitDAO extends DAO<Produit> {
    private static ProduitDAO instance;

    private ProduitDAO(){

    }

    public static ProduitDAO getInstance(){
        if(instance == null){
            instance = new ProduitDAO();
        }
        return instance;
    }

    @Override
    public List<Produit> get() {
        String query = "SELECT Produit.*, " +
                "CASE Taille.nom " +
                "WHEN 'Naine' THEN ROUND(prix * 0.67, 2) " +
                "WHEN 'Ogresse' THEN ROUND(prix * 1.33, 2) " +
                "ELSE prix " +
                "END AS prix_produit "+
                "FROM Produit " +
                "INNER JOIN Taille ON Taille.id_taille = Produit.id_taille " +
                "INNER JOIN Pizza ON Pizza.id_pizza = Produit.id_pizza;";
        return super.find(query, new ArrayList<>());
    }

    @Override
    public Produit getById(int id) {
        return this.getByIdTailleAndPizza(id, id);
    }

    public List<Produit> getByIdTaille(int id) {
        String query = "SELECT Produit.*, " +
                "CASE Taille.nom " +
                "WHEN 'Naine' THEN ROUND(prix * 0.67, 2) " +
                "WHEN 'Ogresse' THEN ROUND(prix * 1.33, 2) " +
                "ELSE Pizza.prix " +
                "END AS prix_produit"+
                "FROM Produit " +
                "INNER JOIN Taille ON Taille.id_taille = Produit.id_taille " +
                "INNER JOIN Pizza ON Pizza.id_pizza = Produit.id_pizza " +
                "WHERE Produit.id_taille = ?;";
        return super.find(query, List.of(id));
    }


    public List<Produit> getByIdPizza(int id) {
        String query = "SELECT Produit.*, " +
                "CASE Taille.nom " +
                "WHEN 'Naine' THEN ROUND(prix * 0.67, 2) " +
                "WHEN 'Ogresse' THEN ROUND(prix * 1.33, 2) " +
                "ELSE Pizza.prix " +
                "END AS prix_produit"+
                "FROM Produit " +
                "INNER JOIN Taille ON Taille.id_taille = Produit.id_taille " +
                "INNER JOIN Pizza ON Pizza.id_pizza = Produit.id_pizza " +
                "WHERE Produit.id_pizza = ?;";
        return super.find(query, List.of(id));
    }

    public Produit getByIdTailleAndPizza(int idTaille, int idPizza) {
        String query = "SELECT Produit.*, " +
                "CASE Taille.nom " +
                "WHEN 'Naine' THEN ROUND(prix * 0.67, 2) " +
                "WHEN 'Ogresse' THEN ROUND(prix * 1.33, 2) " +
                "ELSE Pizza.prix " +
                "END AS prix_produit "+
                "FROM Produit " +
                "INNER JOIN Taille ON Taille.id_taille = Produit.id_taille " +
                "INNER JOIN Pizza ON Pizza.id_pizza = Produit.id_pizza " +
                "WHERE Produit.id_taille = ? AND Produit.id_pizza = ?;";
        List<Produit> result = super.find(query, Arrays.asList(idTaille, idPizza));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Produit obj) {
        return super.modify("INSERT INTO Produit(id_taille, id_pizza) VALUES(?, ?);", Arrays.asList(obj.getTaille().getId(), obj.getPizza().getId()));
    }

    @Override
    public boolean update(Produit obj) {
        return this.updateByPizza(obj);
    }

    public boolean updateByTaille(Produit obj) {
        String query = "UPDATE Produit SET Produit.id_pizza = ? WHERE Produit.id_taille = ?;";
        return super.modify(query, Arrays.asList(obj.getPizza().getId(), obj.getTaille().getId())) > 0;
    }

    public boolean updateByPizza(Produit obj) {
        String query = "UPDATE Produit SET Produit.id_taille = ? WHERE Produit.id_pizza = ?;";
        return super.modify(query, Arrays.asList(obj.getTaille().getId(), obj.getPizza().getId())) > 0;
    }

    @Override
    public boolean delete(Produit obj) {
        String query = "DELETE FROM Produit WHERE Produit.id_taille = ? AND Produit.id_pizza = ?;";
        return super.modify(query, Arrays.asList(obj.getTaille().getId(), obj.getPizza().getId())) > 0;
    }

    @Override
    public Produit resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Produit(
                PizzaDAO.getInstance().getById(resultSet.getInt("id_pizza")),
                TailleDAO.getInstance().getById(resultSet.getInt("id_taille")),
                resultSet.getDouble("prix_produit")
                );
    }
}
