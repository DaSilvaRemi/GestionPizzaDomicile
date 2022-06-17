package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminIngredientsCRUDApplication;
import org.gestionrapizz.gestionpizzadomicile.models.IngredientsDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.Optional;

public class AdminIngredientsUpdateController {

    @FXML
    private TextField update_ingredient_name_field;
    private Ingredients current_ingredient;
    private Ingredients updated_ingredient;

    public void initialize(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);

        if(userSessionUtil.getVAR_SESSION().isEmpty()){
            JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminIngredientsCRUDApplication(), update_ingredient_name_field.getScene().getWindow());
        }

        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        int idIngredient = (int) userSessionUtil.getVAR_SESSION().get("id_ingredient");
        this.current_ingredient = ingredientsDAO.getById(idIngredient);
        this.Reinitialize();
        userSessionUtil.getVAR_SESSION().clear();
    }

    private void Reinitialize(){
        this.updated_ingredient = this.current_ingredient;
        update_ingredient_name_field.setText(this.current_ingredient.getNom());

    }

    @FXML
    private void onConfirmUpdateIngredientBtnClick(MouseEvent event){
        Optional<ButtonType> options = DialogUtils.showDialog("Confirmer les modifications ?, ", "Confirmation Insertion", Alert.AlertType.CONFIRMATION);
        if(!options.orElse(ButtonType.CANCEL).equals(ButtonType.OK)){
            return;
        }
        String ingredient_name = this.update_ingredient_name_field.getText();

        if(!ingredient_name.isBlank()){
            this.updated_ingredient.setNom(ingredient_name);
        }

        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        boolean isUpdated = ingredientsDAO.update(this.updated_ingredient);

        if(!isUpdated){
            DialogUtils.showDialog("La modification de l'ingrédient a échoué !", "Erreur modification ingrédient", Alert.AlertType.ERROR);
            return;
        }

        DialogUtils.showDialog("Ingrédient modifié !");
        onReturnUpdateIngredientButtonClick(event);
    }
    @FXML
    private void onReturnUpdateIngredientButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminIngredientsCRUDApplication(), ((Node) event.getSource()));
    }
}
