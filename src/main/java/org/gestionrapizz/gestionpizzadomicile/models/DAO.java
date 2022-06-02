package org.gestionrapizz.gestionpizzadomicile.models;

import javafx.scene.control.Alert;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<Entity, ChildrenType> {
    public abstract List<Entity> get();
    public abstract Entity getById(int id);
    public abstract int insert(Entity obj);
    public abstract boolean update(Entity obj);
    public abstract boolean delete(Entity obj);
    public abstract Entity resultSetToAbstract(ResultSet resultSet) throws SQLException;

    public final List<Entity> find(String query, List<Object> params){
        List<Entity> objects = new ArrayList<>();
        try(PreparedStatement preparedStatement = ConnectionManager.getPreparedStatement(query)){

            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                objects.add(resultSetToAbstract(resultSet));
            }
        } catch (SQLException e) {
            DialogUtils.showDialog(e.getSQLState(), "SQL EXCEPTION !", Alert.AlertType.ERROR);
        }
        return objects;
    }

    public final int modify(String query, List<Object> params){
        try(PreparedStatement preparedStatement = ConnectionManager.getPreparedStatement(query)){

            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            DialogUtils.showDialog(e.getSQLState(), "SQL EXCEPTION !", Alert.AlertType.ERROR);
            return 0;
        }
    }
}
