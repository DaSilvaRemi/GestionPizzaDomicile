package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gestionrapizz.gestionpizzadomicile.models.MenuPizzeriaDAO;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.LigneMenuPizzeria;
import org.gestionrapizz.gestionpizzadomicile.models.view.MenuPizzeria;

import java.util.List;

public class AdminPizzasController {

    @FXML
    private TableView<LigneMenuPizzeria> pizza_list;
    @FXML
    private TableColumn<LigneMenuPizzeria, String> list_pizza_name;
    @FXML
    private TableColumn<LigneMenuPizzeria, String> list_pizza_ingredients;
    @FXML
    private TableColumn<LigneMenuPizzeria, Double> list_pizza_price;

    private void Initialize(){
        list_pizza_name.setCellValueFactory(new PropertyValueFactory<>("nomPizza"));
        list_pizza_ingredients.setCellValueFactory(new PropertyValueFactory<>("ingredientspizza"));
        list_pizza_price.setCellValueFactory(new PropertyValueFactory<>("prix"));

        MenuPizzeriaDAO menuPizzeriaDAO = MenuPizzeriaDAO.getInstance();
        List<MenuPizzeria> da_pizza_list = menuPizzeriaDAO.get();

        for (MenuPizzeria liste_pizza: da_pizza_list) {
            this.pizza_list.getItems().add(new LigneMenuPizzeria(liste_pizza));
        }
    }
}
