package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminLivreurCRUDApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminPizzasApplication;
import org.gestionrapizz.gestionpizzadomicile.models.ComposerDAO;
import org.gestionrapizz.gestionpizzadomicile.models.IngredientsDAO;
import org.gestionrapizz.gestionpizzadomicile.models.LivreurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.PizzaDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Composer;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Pizza;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.ListeIngredients;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesLivreurs;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

import java.util.List;

public class AdminPizzasAddController {
    @FXML
    private TextField add_pizza_name_field;

    @FXML
    private TextField add_pizza_price_field;

    @FXML
    private ListView<ListeIngredients> add_pizza_ingredients_list;

    public void initialize(){
        add_pizza_ingredients_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        updateTable();
    }

    private void updateTable(){
        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        List<Ingredients> ingredients = ingredientsDAO.get();

        for (Ingredients ingredient : ingredients) {
            this.add_pizza_ingredients_list.getItems().add(new ListeIngredients(ingredient));
        }
    }
    @FXML
    private void onConfirmAddPizzaButtonClick(MouseEvent event){
       /* if (!UserCRUDController.verifyUsersFieldsToInsert(pizza_name_field,pizza_price_field,pizza_ingredients_list))  {
            return;
        }*/

        PizzaDAO pizzaDAO = PizzaDAO.getInstance();
        ComposerDAO composerDAO = ComposerDAO.getInstance();
        int idPizza = pizzaDAO.insert(new Pizza(0, add_pizza_name_field.getText(), Double.parseDouble(add_pizza_price_field.getText()) ));
        int idComposer = -1;
        for(ListeIngredients ingredient : add_pizza_ingredients_list.getSelectionModel().getSelectedItems()){
            idComposer = composerDAO.insert(new Composer(pizzaDAO.getById(idPizza),new Ingredients(ingredient.getIngredient_id(),ingredient.getIngredient_name())));
        }

        if(idPizza == 0 && idComposer == 0){
            DialogUtils.showDialog("Insertion pizza échouée ! ", "Erreur Insertion !", Alert.AlertType.ERROR);
            return;
        }

        DialogUtils.showDialog("Pizza ajoutée ! ", "Insertion réussie !");
        onAddPizzaReturnButtonClick(event);

    }

    @FXML
    private void onAddPizzaReturnButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminPizzasApplication(), ((Node) event.getSource()));
    }

}
