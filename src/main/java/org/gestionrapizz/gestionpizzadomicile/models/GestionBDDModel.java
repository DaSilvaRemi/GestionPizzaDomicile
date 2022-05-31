package org.gestionrapizz.gestionpizzadomicile.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public abstract class GestionBDDModel {
    private final String URL_HOTE;
    private final String DATABASE_NAME;
    private final String USER_NAME;
    private final String PASSWORD;
    private Connection db;
    private PreparedStatement myStatement;

    public GestionBDDModel(){
        this("mysql-remidasilva.alwaysdata.net", "remidasilva_rapizz", "220877_rapizz", "rapizzAdmin");
    }

    public GestionBDDModel(String URL_HOTE, String DATABASE_NAME, String USER_NAME, String PASSWORD) {
        this.URL_HOTE = URL_HOTE;
        this.DATABASE_NAME = DATABASE_NAME;
        this.USER_NAME = USER_NAME;
        this.PASSWORD = PASSWORD;
    }

    public String getURL_HOTE() {
        return URL_HOTE;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public Connection getDb() {
        return db;
    }

    public PreparedStatement getMyStatement() {
        return myStatement;
    }

    public String getDATABASE_NAME() {
        return DATABASE_NAME;
    }

    public GestionBDDModel setMyStatement(String query) throws SQLException {
        this.myStatement = this.getDb().prepareStatement(query);
        return this;
    }

    public Connection connect() throws SQLException {
        this.db = DriverManager.getConnection("jdbc:mariadb://" + this.URL_HOTE + "/" + DATABASE_NAME, this.USER_NAME, this.PASSWORD);
        return this.getDb();
    }

    public void disconnect() throws SQLException {
        this.closeMyStatement();
        this.db.close();
    }

    public void closeMyStatement() throws SQLException {
        this.getMyStatement().clearParameters();
        this.getMyStatement().close();
    }

    protected void executeQuery() throws SQLException {
        this.getMyStatement().execute();
    }

    protected ResultSet getQueryResult() throws SQLException {
        ResultSet result = this.getMyStatement().executeQuery();
        return result.next() ? result : null;
    }
}
