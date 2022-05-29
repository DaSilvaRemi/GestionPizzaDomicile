package org.gestionrapizz.gestionpizzadomicile.models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public abstract class GestionBDDModele {
    private String urlHote;
    private String user;
    private String password;
    private Connection db;
    private PreparedStatement myStatement;

    public GestionBDDModele(){
        this("localhost", "test", "test");
    }

    public GestionBDDModele(String urlHote, String user, String password) {
        this.urlHote = urlHote;
        this.user = user;
        this.password = password;
    }

    public String getUrlHote() {
        return urlHote;
    }

    public String getUser() {
        return user;
    }

    public Connection getDb() {
        return db;
    }

    public PreparedStatement getMyStatement() {
        return myStatement;
    }

    public GestionBDDModele setMyStatement(String query) throws SQLException {
        this.myStatement = this.getDb().prepareStatement(query);
        return this;
    }

    public Connection connect() throws SQLException {
        this.db = DriverManager.getConnection("jdbc:mariadb://" + this.urlHote, this.user, this.password);
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

    protected void executeRequest() throws SQLException {
        this.getMyStatement().execute();
    }

    protected ResultSet getRequestResult() throws SQLException {
        ResultSet result = this.getMyStatement().executeQuery();
        return result.next() ? result : null;
    }
}
