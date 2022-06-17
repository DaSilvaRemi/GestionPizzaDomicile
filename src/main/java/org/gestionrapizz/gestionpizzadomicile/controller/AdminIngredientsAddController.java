package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminIngredientsCRUDApplication;
import org.gestionrapizz.gestionpizzadomicile.models.IngredientsDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

public class AdminIngredientsAddController {
    @FXML
    private TextField add_ingredient_name_field;

    @FXML
    private void onConfirmAddIngredientBtnClick(MouseEvent event){
        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();

        if(add_ingredient_name_field.getText().isBlank() || add_ingredient_name_field.getText().length() > 50){
            DialogUtils.showDialog("Champ vide ou trop de caractères pour le nom ! ", "Erreur champ incorrect !", Alert.AlertType.ERROR);
            return;
        }

        int idIngredient = ingredientsDAO.insert(new Ingredients(0,add_ingredient_name_field.getText())) ;
        if(idIngredient == 0){
            DialogUtils.showDialog("Insertion ingrédient échouée ! ", "Erreur Insertion !", Alert.AlertType.ERROR);
            return;
        }

        onReturnAddIngredientBtnClick(event);
    }
    private void onReturnAddIngredientBtnClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminIngredientsCRUDApplication(), ((Node) event.getSource()));
    }
}


