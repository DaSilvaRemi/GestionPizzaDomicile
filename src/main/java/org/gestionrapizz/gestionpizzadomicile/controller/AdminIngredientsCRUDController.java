package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminAddIngredientApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminUpdateIngredientApplication;
import org.gestionrapizz.gestionpizzadomicile.models.IngredientsDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.ListeIngredients;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;

public class AdminIngredientsCRUDController {
    @FXML
    private TableView<ListeIngredients> ingredient_list;
    @FXML
    private TableColumn<ListeIngredients, String> list_ingredients_name;

    public void initialize(){
        list_ingredients_name.setCellValueFactory(new PropertyValueFactory<>("ingredient_name"));
        this.updateTable();

    }

    private void updateTable(){
        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        List<Ingredients> ingredientsListe = ingredientsDAO.get();

        for( Ingredients ingredients : ingredientsListe){
            this.ingredient_list.getItems().add(new ListeIngredients(ingredients));
        }
    }

    @FXML
    private void onOpenAddIngredientButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminAddIngredientApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onOpenUpdateIngredientButtonClick(MouseEvent event){

        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        ListeIngredients selectedIngredient = this.ingredient_list.getSelectionModel().getSelectedItem();
        if(selectedIngredient == null){
            DialogUtils.showDialog("Aucun ingrédient sélectionné!");
            return;
        }
        userSessionUtil.getVAR_SESSION().put("id_ingredient", selectedIngredient.getIngredient_id());
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminUpdateIngredientApplication(), ((Node) event.getSource()));
    }
}
