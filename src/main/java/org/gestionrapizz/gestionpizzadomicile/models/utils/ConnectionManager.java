package org.gestionrapizz.gestionpizzadomicile.models.utils;

import java.sql.*;

public abstract class ConnectionManager {
    private static final String HOST_NAME = "mysql-remidasilva.alwaysdata.net";

    private static final String BDD_NAME = "remidasilva_rapizz";
    private static final String URL = "jdbc:mariadb://" + HOST_NAME + "/" + BDD_NAME;
    private static final String USER = "220877_rapizz";
    private static final String PASSWORD = "rapizzAdmin";
    private static Connection connection = null;
    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = connect();
        }
        return connection;
    }

    public static PreparedStatement getPreparedStatement(String query) throws SQLException {
        return ConnectionManager.getConnection().prepareStatement(query, Statement.NO_GENERATED_KEYS);
    }

    public static PreparedStatement getPreparedStatement(String query, int resultSetType) throws SQLException {
        return ConnectionManager.getConnection().prepareStatement(query, resultSetType);
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
