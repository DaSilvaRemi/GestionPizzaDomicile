package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gestionrapizz.gestionpizzadomicile.models.IngredientsDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.LigneMenuPizzeria;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.ListeIngredients;

import java.util.List;

public class AdminIngredientsController {
    @FXML
    private TableView<ListeIngredients> ingredient_list;
    @FXML
    private TableColumn<ListeIngredients, String> list_ingredients_name;

    private void Initialize(){
        list_ingredients_name.setCellValueFactory(new PropertyValueFactory<>("nomIngredient"));

        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        List<Ingredients> ingredientsListe = ingredientsDAO.get();

        for( Ingredients ingredients : ingredientsListe){
            this.ingredient_list.getItems().add(new ListeIngredients(ingredients));
        }
    }
}
