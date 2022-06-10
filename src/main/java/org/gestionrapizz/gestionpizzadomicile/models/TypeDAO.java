package org.gestionrapizz.gestionpizzadomicile.models;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TypeDAO extends DAO<Type> {
    private static TypeDAO instance;

    private TypeDAO(){

    }

    public static TypeDAO getInstance(){
        if(instance == null){
            instance = new TypeDAO();
        }
        return instance;
    }

    @Override
    public List<Type> get() {
        return super.find("SELECT Type.* FROM Type;", new ArrayList<>());
    }

    @Override
    public Type getById(int id) {
        List<Type> result = super.find("SELECT Type.* FROM Type WHERE Type.id_type = ?;", List.of(id));
        return result.size() == 1 ? result.get(0) : null;
    }

    public Type getByName(String nom) {
        List<Type> result = super.find("SELECT Type.* FROM Type WHERE Type.nom = ?;", List.of(nom));
        return result.size() == 1 ? result.get(0) : null;
    }

    @Override
    public int insert(Type obj) {
        return super.add("INSERT INTO Type(nom) VALUES(?);", List.of(obj.getNom()));
    }

    @Override
    public boolean update(Type obj) {
        String query = "UPDATE Type SET " +
                "nom = ? " +
                "WHERE Type.id_type = ?;";
        return super.modify(query, List.of(obj.getId())) > 0;
    }

    @Override
    public boolean delete(Type obj) {
        String query = "DELETE FROM Type " +
                "WHERE Type.id_type = ?;";
        return super.modify(query, List.of(obj.getId())) > 0;
    }

    @Override
    public Type resultSetToAbstract(ResultSet resultSet) throws SQLException {
        return new Type(
                resultSet.getInt("id_type"),
                resultSet.getString("nom")
        );
    }
}
