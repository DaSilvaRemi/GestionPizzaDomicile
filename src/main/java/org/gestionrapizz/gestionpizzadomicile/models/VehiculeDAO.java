package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Vehicule;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        return null;
    }

    @Override
    public Vehicule getById(int id) {
        return null;
    }

    @Override
    public int insert(Vehicule obj) {
        return 0;
    }

    @Override
    public boolean update(Vehicule obj) {
        return false;
    }

    @Override
    public boolean delete(Vehicule obj) {
        return false;
    }

    @Override
    public Vehicule resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Vehicule(
          resultSet.getString("immatriculation"),
          TypeDAO.getInstance().getById(resultSet.getInt("id_type"))
        );
    }
}
