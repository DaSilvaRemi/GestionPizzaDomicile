package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;

public class ListeIngredients {

    private final SimpleStringProperty ingredient_name;
    private final SimpleIntegerProperty ingredient_id;


    public ListeIngredients(Ingredients ingredient) {
        this.ingredient_name = new SimpleStringProperty(ingredient.getNom());
        this.ingredient_id = new SimpleIntegerProperty(ingredient.getId());
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

    public int getIngredient_id() {
        return ingredient_id.get();
    }

    public SimpleIntegerProperty ingredient_idProperty() {
        return ingredient_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id.set(ingredient_id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListeIngredients that = (ListeIngredients) o;

        if (!this.getIngredient_name().equals(that.getIngredient_name())) return false;
        return this.getIngredient_id() == that.getIngredient_id();
    }

    @Override
    public String toString() {
        return this.getIngredient_name();
    }
}
