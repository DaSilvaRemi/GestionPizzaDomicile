package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import javafx.beans.property.SimpleStringProperty;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;

public class ListeIngredients {

    private final SimpleStringProperty ingredient_name;

    public ListeIngredients(Ingredients ingredient) {
        this.ingredient_name = new SimpleStringProperty(ingredient.getNom());
    }

    public String getIngredient_name() {
        return ingredient_name.get();
    }

    public SimpleStringProperty ingredient_nameProperty() {
        return ingredient_name;
    }

    public void setIngredient_name(String ingredient_name) {
        this.ingredient_name.set(ingredient_name);
    }
}
