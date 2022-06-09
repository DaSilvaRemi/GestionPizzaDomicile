package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class StatistiquesChiffreAffairesDAO extends DAO<Double>{
    private static StatistiquesChiffreAffairesDAO instance;

    private StatistiquesChiffreAffairesDAO() {
    }

    public static StatistiquesChiffreAffairesDAO getInstance() {
        if(StatistiquesChiffreAffairesDAO.instance == null){
            StatistiquesChiffreAffairesDAO.instance = new StatistiquesChiffreAffairesDAO();
        }

        return StatistiquesChiffreAffairesDAO.instance;
    }

    @Override
    public List<Double> get() {
        return null;
    }

    public Double getCA() {
        String query = "SELECT SUM(Commande.montant) AS chiffreAffaires FROM Commande " +
                "JOIN Statut ON Statut.id_statut=Commande.id_statut " +
                "WHERE Commande.retard=? AND Statut.nom!=?;";
        List<Double> result = super.find(query, Arrays.asList(0, "Refusé"));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public Double getById(int id) {
        return null;
    }

    @Override
    public int insert(Double obj) {
        return 0;
    }

    @Override
    public boolean update(Double obj) {
        return false;
    }

    @Override
    public boolean delete(Double obj) {
        return false;
    }

    @Override
    public Double resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return resultSet.getDouble("chiffreAffaires");
    }
}
