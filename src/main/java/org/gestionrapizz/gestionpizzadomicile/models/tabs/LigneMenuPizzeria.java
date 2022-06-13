package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import org.gestionrapizz.gestionpizzadomicile.models.view.MenuPizzeria;

public class LigneMenuPizzeria {
    private final SimpleStringProperty nomPizza;
    private final SimpleStringProperty ingredientspizza;
    private final SimpleDoubleProperty prix;

    public LigneMenuPizzeria(MenuPizzeria menuPizzeria) {
        this.nomPizza = new SimpleStringProperty(menuPizzeria.getPizza().getNom());
        this.ingredientspizza = new SimpleStringProperty(menuPizzeria.getNomsIngredients());
        this.prix = new SimpleDoubleProperty(menuPizzeria.getPizza().getPrix());
    }

    public String getNomPizza() {
        return nomPizza.get();
    }

    public SimpleStringProperty nomPizzaProperty() {
        return nomPizza;
    }

    public void setNomPizza(String nomPizza) {
        this.nomPizza.set(nomPizza);
    }

    public String getIngredientspizza() {
        return ingredientspizza.get();
    }

    public SimpleStringProperty ingredientspizzaProperty() {
        return ingredientspizza;
    }

    public void setIngredientspizza(String ingredientspizza) {
        this.ingredientspizza.set(ingredientspizza);
    }

    public double getPrix() {
        return prix.get();
    }

    public SimpleDoubleProperty prixProperty() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix.set(prix);
    }
}
