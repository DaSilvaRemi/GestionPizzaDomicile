package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Vehicule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class VehiculeDAO extends DAO<Vehicule> {
    private static VehiculeDAO instance;

    private VehiculeDAO(){

    }

    public static VehiculeDAO getInstance(){
        if(instance == null){
            instance = new VehiculeDAO();
        }
        return instance;
    }

    @Override
    public List<Vehicule> get() {
        return super.find("SELECT Vehicule.* FROM Vehicule;", new ArrayList<>());
    }

    @Override
    @Deprecated
    public Vehicule getById(int id) {
        return this.getById(String.valueOf(id));
    }


    public Vehicule getById(String id) {
        List<Vehicule> result = super.find("SELECT Vehicule.* FROM Vehicule WHERE immatriculation = ?;", Collections.singletonList(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    public List<Vehicule> getVehiculesDisponible() {
        String query = "SELECT Vehicule.*" +
                "FROM Vehicule " +
                "INNER JOIN Commande ON Commande.immatriculation = Vehicule.immatriculation " +
                "INNER JOIN Statut ON Commande.id_statut = Statut.id_statut " +
                "WHERE Statut.nom = ? OR Statut.nom = ? " +
                "GROUP BY Vehicule.immatriculation;";
        return super.find(query, Arrays.asList("Livré", "Refusé"));
    }

    @Override
    public int insert(Vehicule obj) {
        List<Object> params = Arrays.asList(obj.getImmatriculation(), obj.getType().getId());
        return super.modify("INSERT INTO Vehicule(immatriculation, id_type) VALUES(?, ?);", params);
    }

    @Override
    public boolean update(Vehicule obj) {
        List<Object> params = Arrays.asList(obj.getType().getId(), obj.getImmatriculation());
        return super.modify("UPDATE Vehicule SET id_type = ? WHERE immatriculation = ?;", params) > 0;
    }

    @Override
    public boolean delete(Vehicule obj) {
        return super.modify("DELETE FROM Vehicule WHERE immatriculation = ?;", Collections.singletonList(obj.getImmatriculation())) > 0;
    }

    @Override
    public Vehicule resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Vehicule(
          resultSet.getString("immatriculation"),
          TypeDAO.getInstance().getById(resultSet.getInt("id_type"))
        );
    }
}
