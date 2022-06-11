package org.gestionrapizz.gestionpizzadomicile.models.view;

import org.gestionrapizz.gestionpizzadomicile.models.entity.Pizza;

public class MenuPizzeria {
    Pizza pizza;
    String nomsIngredients;

    public MenuPizzeria(Pizza pizza, String nomsIngredients) {
        this.pizza = pizza;
        this.nomsIngredients = nomsIngredients;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getNomsIngredients() {
        return nomsIngredients;
    }

    public void setNomsIngredients(String nomsIngredients) {
        this.nomsIngredients = nomsIngredients;
    }

    @Override
    public String toString() {
        return "MenuPizzeria{" +
                "pizza=" + pizza +
                ", nomsIngredients='" + nomsIngredients + '\'' +
                '}';
    }
}
