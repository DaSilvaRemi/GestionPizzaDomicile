package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import org.gestionrapizz.gestionpizzadomicile.models.view.MenuPizzeria;

public class MesPizzas extends LigneMenuPizzeria {
    private final SimpleIntegerProperty id;
    public MesPizzas(MenuPizzeria menuPizzeria) {
        super(menuPizzeria);
        this.id = new SimpleIntegerProperty(menuPizzeria.getPizza().getId());
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
