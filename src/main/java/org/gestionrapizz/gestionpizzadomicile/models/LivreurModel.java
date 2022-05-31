package org.gestionrapizz.gestionpizzadomicile.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LivreurModel extends UtilisateurModel {
    public ResultSet getLivreurs() throws SQLException{
        super.setMyStatement("SELECT u.id_utilisateur, u.nom, u.email " +
                "FROM utilisateur u " +
                "INNER JOIN Livreur l ON u.id_utilisateur = l.id_utilisateur;");
        return super.getQueryResult();
    }

    public ResultSet getLivreurById(int id) throws SQLException {
        super.setMyStatement("SELECT u.id_utilisateur, u.nom, u.email " +
                "FROM utilisateur u " +
                "INNER JOIN Livreur l ON u.id_utilisateur = l.id_utilisateur " +
                "WHERE l.id_utilisateur = ?");
        super.getMyStatement().setInt(1, id);
        return super.getQueryResult();
    }
}
