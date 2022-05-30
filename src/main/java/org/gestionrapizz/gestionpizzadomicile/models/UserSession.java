package org.gestionrapizz.gestionpizzadomicile.models;

import javafx.application.Application;
import javafx.stage.Window;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

public final class UserSession {
    private static UserSession instance;
    private int idUser;
    private Boolean isAdmin;

    private UserSession(int idUser, Boolean isAdmin){
        this.setIdUser(idUser);
        this.setAdmin(isAdmin);
    }

    public static UserSession getInstance(int idUser, Boolean isAdmin){
        if(instance == null) {
            instance = new UserSession(idUser, isAdmin);
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

    public void clearUserSession(){
        this.setIdUser(-1);
        this.setAdmin(false);
    }

    public void LoginVerification(Application redirectionApplication, Window windowsToCloseIfFailed){
        this.LoginVerification(redirectionApplication, windowsToCloseIfFailed, this.getIdUser(), this.getAdmin());
    }

    public void LoginVerification(Application redirectionApplication, Window windowsToCloseIfFailed, int idUser, boolean isAdmin){
        if(!this.isLogged() || !this.hasId(idUser) || !this.hasGoodRights(isAdmin)){
            JavaFXOpenWindowUtil.openAndCloseAWindow(redirectionApplication, windowsToCloseIfFailed);
        }
    }

    public boolean isLogged(){
        return !this.hasId(-1);
    }

    public boolean hasId(int idUser){
        return this.getIdUser() == idUser;
    }

    public boolean hasGoodRights(boolean isAdmin){
        return  this.getAdmin() == isAdmin;
    }
}
