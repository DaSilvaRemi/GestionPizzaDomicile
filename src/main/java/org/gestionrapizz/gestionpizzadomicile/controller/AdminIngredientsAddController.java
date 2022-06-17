package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminIngredientsApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminPizzasApplication;
import org.gestionrapizz.gestionpizzadomicile.models.IngredientsDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.w3c.dom.events.Event;

public class AdminIngredientsAddController {

    @FXML
    private TextField add_ingredient_name_field;

    @FXML
    private void onConfirmAddIngredientBtnClick(MouseEvent event){
        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        int idIngredient = ingredientsDAO.insert(new Ingredients(0,add_ingredient_name_field.getText())) ;
        if(idIngredient == 0){
            DialogUtils.showDialog("Insertion ingrédient échouée ! ", "Erreur Insertion !", Alert.AlertType.ERROR);
            return;
        }

        onReturnAddIngredientBtnClick(event);
    }
    private void onReturnAddIngredientBtnClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminIngredientsApplication(), ((Node) event.getSource()));
    }
}


