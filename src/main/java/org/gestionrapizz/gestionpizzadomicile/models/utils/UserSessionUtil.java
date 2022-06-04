package org.gestionrapizz.gestionpizzadomicile.models.utils;

import javafx.application.Application;
import javafx.stage.Window;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Administrateur;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Client;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Utilisateur;

public final class UserSessionUtil {
    private static UserSessionUtil instance;
    private Utilisateur utilisateur;

    private UserSessionUtil(Utilisateur utilisateur) {
        this.setUtilisateur(utilisateur);
    }

    public static UserSessionUtil getInstance(Utilisateur utilisateur) {
        if (instance == null) {
            instance = new UserSessionUtil(utilisateur);
        }
        return instance;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public RolesEnum getRoleUtilisateur(){
        return getRoleUtilisateur(this.getUtilisateur());
    }

    public static RolesEnum getRoleUtilisateur(Utilisateur utilisateur){
        if(utilisateur instanceof Livreur){
            return RolesEnum.LIVREUR;
        }

        if(utilisateur instanceof Administrateur){
            return RolesEnum.ADMIN;
        }

        return RolesEnum.CLIENT;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void clearUserSession() {
        this.setUtilisateur(null);
        UserSessionUtil.instance = null;
    }

    public void LoginVerification(Application redirectionApplication, Window windowsToCloseIfFailed) {
        this.LoginVerification(redirectionApplication, windowsToCloseIfFailed, this.getUtilisateur());
    }

    public void LoginVerification(Application redirectionApplication, Window windowsToCloseIfFailed, Utilisateur utilisateur) {
        if (!this.isLogged() || !hasId(utilisateur.getId()) && !this.hasGoodRights(UserSessionUtil.getRoleUtilisateur(utilisateur))) {
            JavaFXOpenWindowUtil.openAndCloseAWindow(redirectionApplication, windowsToCloseIfFailed);
        }
    }

    public boolean isLogged() {
        return this.utilisateur != null;
    }

    public boolean hasId(int id){
        return this.utilisateur.getId() == id;
    }

    public boolean hasGoodRights(RolesEnum rolesEnum) {
        return this.getRoleUtilisateur().equals(rolesEnum);
    }
}
