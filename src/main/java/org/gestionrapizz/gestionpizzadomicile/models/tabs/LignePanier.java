package org.gestionrapizz.gestionpizzadomicile.models.tabs;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class LignePanier
{
    private final SimpleStringProperty nomPizza;
    private final SimpleStringProperty taillePizza;
    private final SimpleDoubleProperty prix;

    public LignePanier(String nomPizza, String taillePizza, Double prix) {
        this.nomPizza = new SimpleStringProperty(nomPizza);
        this.taillePizza = new SimpleStringProperty(taillePizza);
        this.prix = new SimpleDoubleProperty(prix);
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

    public String getTaillePizza() {
        return taillePizza.get();
    }

    public SimpleStringProperty taillePizzaProperty() {
        return taillePizza;
    }

    public void setTaillePizza(String taillePizza) {
        this.taillePizza.set(taillePizza);
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
