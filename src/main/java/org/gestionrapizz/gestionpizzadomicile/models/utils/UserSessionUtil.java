package org.gestionrapizz.gestionpizzadomicile.models.utils;

import javafx.application.Application;
import javafx.stage.Window;

public final class UserSessionUtil {
    private static UserSessionUtil instance;
    private int idUser;
    private Boolean isAdmin;

    private UserSessionUtil(int idUser, Boolean isAdmin) {
        this.setIdUser(idUser);
        this.setAdmin(isAdmin);
    }

    public static UserSessionUtil getInstance(int idUser, Boolean isAdmin) {
        if (instance == null) {
            instance = new UserSessionUtil(idUser, isAdmin);
        }
        return instance;
    }

    public int getIdUser() {
        return idUser;
    }

    private void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    private void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public void clearUserSession() {
        this.setIdUser(-1);
        this.setAdmin(false);
    }

    public void LoginVerification(Application redirectionApplication, Window windowsToCloseIfFailed) {
        this.LoginVerification(redirectionApplication, windowsToCloseIfFailed, this.getIdUser(), this.getAdmin());
    }

    public void LoginVerification(Application redirectionApplication, Window windowsToCloseIfFailed, int idUser, boolean isAdmin) {
        if (!this.isLogged() || !this.hasId(idUser) || !this.hasGoodRights(isAdmin)) {
            JavaFXOpenWindowUtil.openAndCloseAWindow(redirectionApplication, windowsToCloseIfFailed);
        }
    }

    public boolean isLogged() {
        return !this.hasId(-1);
    }

    public boolean hasId(int idUser) {
        return this.getIdUser() == idUser;
    }

    public boolean hasGoodRights(boolean isAdmin) {
        return this.getAdmin() == isAdmin;
    }
}
