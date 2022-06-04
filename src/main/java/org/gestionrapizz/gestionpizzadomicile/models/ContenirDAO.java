package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Contenir;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContenirDAO extends DAO<Contenir> {
    private static ContenirDAO instance;

    private ContenirDAO() {

    }

    public static ContenirDAO getInstance() {
        if (instance == null) {
            instance = new ContenirDAO();
        }
        return instance;
    }

    @Override
    public List<Contenir> get() {
        return super.find("SELECT Produit.* FROM Produit;", new ArrayList<>());
    }

    @Override
    public Contenir getById(int id) {
        return this.getByIdTailleAndPizza(id, id);
    }

    public List<Contenir> getIdCommande(int id) {
        return super.find("SELECT Contenir.* FROM Composer WHERE Contenir.id_commande = ?;", List.of(id));
    }

    public List<Contenir> getByIdTaille(int id) {
        return super.find("SELECT Contenir.* FROM Type WHERE Contenir.id_taille = ?;", List.of(id));
    }


    public List<Contenir> getByIdPizza(int id) {
        return super.find("SELECT Contenir.* FROM Composer WHERE Contenir.id_pizza = ?;", List.of(id));
    }

    public Contenir getByIdTailleAndPizza(int idTaille, int idPizza) {
        String query = "SELECT Contenir.* FROM Type WHERE Contenir.id_taille = ? AND Contenir.id_pizza = ?;";
        List<Contenir> result = super.find(query, Arrays.asList(idTaille, idPizza));
        return result.size() == 1 ? result.get(0) : null;
    }

    public Contenir getByIdCommandeAndIdTailleAndPizza(int idCommande, int idTaille, int idPizza) {
        String query = "SELECT Contenir.* FROM Type WHERE Contenir.id_commande = ? AND Contenir.id_taille = ? AND Contenir.id_pizza = ?;";
        List<Contenir> result = super.find(query, Arrays.asList(idCommande, idTaille, idPizza));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Contenir obj) {
        String query = "INSERT INTO Contenir(id_commande, id_taille, id_pizza) VALUES(?, ?, ?);";
        return super.add(query, Arrays.asList(obj.getCommande().getId(), obj.getProduit().getTaille().getId(), obj.getProduit().getPizza().getId()));
    }

    @Override
    public boolean update(Contenir obj) {
        return this.updateByPizza(obj);
    }

    public boolean updateByTaille(Contenir obj) {
        String query = "UPDATE Contenir SET Contenir.id_pizza = ?, Contenir.id_commande = ? WHERE Contenir.id_taille = ?;";
        List<Object> params = Arrays.asList(obj.getProduit().getPizza().getId(), obj.getCommande().getId(), obj.getProduit().getTaille());
        return super.modify(query, params) > 0;
    }

    public boolean updateByPizza(Contenir obj) {
        String query = "UPDATE Contenir SET Contenir.id_taille = ?, Contenir.id_commande = ? WHERE Contenir.id_pizza = ?;";
        List<Object> params = Arrays.asList(obj.getProduit().getTaille().getId(), obj.getCommande().getId(), obj.getProduit().getPizza().getId());
        return super.modify(query, params) > 0;
    }

    public boolean updateByCommande(Contenir obj) {
        String query = "UPDATE Contenir SET Contenir.id_taille = ?, Contenir.id_pizza = ? WHERE Contenir.id_commande = ?;";
        List<Object> params = Arrays.asList(obj.getProduit().getTaille().getId(), obj.getProduit().getPizza().getId(), obj.getCommande().getId());
        return super.modify(query, params) > 0;
    }

    @Override
    public boolean delete(Contenir obj) {
        String query = "DELETE FROM Contenir WHERE Contenir.id_taille = ? AND Contenir.id_pizza = ? AND Contenir.id_commande = ?;";
        List<Object> params = Arrays.asList(obj.getProduit().getTaille().getId(), obj.getProduit().getPizza().getId(), obj.getCommande().getId());
        return super.modify(query, params) > 0;
    }

    @Override
    public Contenir resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Contenir(
                CommandeDAO.getInstance().getById(resultSet.getInt("id_commande")),
                ProduitDAO.getInstance().getByIdTailleAndPizza(
                        resultSet.getInt("id_taille"),
                        resultSet.getInt("id_pizza")
                )
        );
    }
}
