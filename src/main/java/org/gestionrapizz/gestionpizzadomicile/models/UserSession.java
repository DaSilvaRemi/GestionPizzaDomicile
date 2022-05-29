package org.gestionrapizz.gestionpizzadomicile.models;

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
}
