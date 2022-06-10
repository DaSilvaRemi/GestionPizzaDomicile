package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import javafx.beans.property.SimpleStringProperty;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Vehicule;

public class MesVehicules {
    private final SimpleStringProperty immatriculation;
    private final SimpleStringProperty type;

    public MesVehicules(Vehicule vehicule) {
        this.immatriculation = new SimpleStringProperty(vehicule.getImmatriculation());
        this.type = new SimpleStringProperty(vehicule.getType().getNom());
    }

    public String getImmatriculation() {
        return immatriculation.get();
    }

    public SimpleStringProperty immatriculationProperty() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation.set(immatriculation);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
