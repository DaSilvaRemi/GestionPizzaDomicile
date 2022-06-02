package org.gestionrapizz.gestionpizzadomicile.models.entity;

import java.util.HashMap;
import java.util.List;

public class Composer {
    Pizza pizza;
    Ingredients ingredients;

    public Composer(Pizza pizza, Ingredients ingredients) {
        this.pizza = pizza;
        this.ingredients = ingredients;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Ingredients getIngredients() {
        return ingredients;
    }

    public void setIngredients(Ingredients ingredients) {
        this.ingredients = ingredients;
    }
}
