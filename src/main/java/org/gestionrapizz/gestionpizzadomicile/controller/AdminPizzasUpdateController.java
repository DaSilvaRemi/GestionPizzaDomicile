package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminIngredientsApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminPizzasApplication;
import org.gestionrapizz.gestionpizzadomicile.models.ComposerDAO;
import org.gestionrapizz.gestionpizzadomicile.models.IngredientsDAO;
import org.gestionrapizz.gestionpizzadomicile.models.PizzaDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Composer;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Ingredients;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Pizza;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.ListeIngredients;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;
import java.util.Optional;

public class AdminPizzasUpdateController {
    @FXML
    private TextField update_pizza_name_field;

    @FXML
    private TextField update_pizza_price_field;

    @FXML
    private ListView<ListeIngredients> update_pizza_ingredients_list;

    private Pizza current_pizza;

    private Pizza updated_pizza;
    private List<ListeIngredients> current_ingredients;

    private List<ListeIngredients> updated_ingredients;

    private List<Composer> current_composed;
    private List<Composer> updated_composed;


    public void initialize(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);

        if(userSessionUtil.getVAR_SESSION().isEmpty()){
            JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminIngredientsApplication(), update_pizza_name_field.getScene().getWindow());
        }
        update_pizza_ingredients_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        PizzaDAO pizzaDAO = PizzaDAO.getInstance();
        ComposerDAO composerDAO = ComposerDAO.getInstance();
        //IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        int idPizza = (int) userSessionUtil.getVAR_SESSION().get("id_pizza");
        this.current_composed = composerDAO.getByIdPizza(idPizza);
        for(Composer composer : current_composed){
            current_ingredients.add(new ListeIngredients(composer.getIngredients()));
        }
        this.current_pizza = pizzaDAO.getById(idPizza);
        this.Reinitialize();
        userSessionUtil.getVAR_SESSION().clear();
    }

    private void Reinitialize(){
        this.updated_pizza = this.current_pizza;
        update_pizza_name_field.setText(this.current_pizza.getNom());
        update_pizza_price_field.setText(this.current_pizza.getPrix()+"");
        for(ListeIngredients ingredient : current_ingredients){
            update_pizza_ingredients_list.getItems().add(ingredient);
            update_pizza_ingredients_list.getSelectionModel().getSelectedItems().add(ingredient);
        }

    }

    @FXML
    private void onConfirmUpdatePizzaBtnClick(MouseEvent event){
        Optional<ButtonType> options = DialogUtils.showDialog("Confirmer les modifications ? ", "Confirmation Insertion", Alert.AlertType.CONFIRMATION);
        if(!options.orElse(ButtonType.CANCEL).equals(ButtonType.OK)){
            return;
        }
        String pizza_name = this.update_pizza_name_field.getText();
        String pizza_price = this.update_pizza_price_field.getText();
        if(!pizza_name.isBlank()){
            this.updated_pizza.setNom(pizza_name);
        }
        if(!pizza_price.isBlank()){
            this.updated_pizza.setPrix(Double.parseDouble(pizza_price));
        }

        updated_ingredients = this.update_pizza_ingredients_list.getSelectionModel().getSelectedItems();


        ComposerDAO composerDAO = ComposerDAO.getInstance();
        PizzaDAO pizzaDAO = PizzaDAO.getInstance();
        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        boolean pizzaUpdated = pizzaDAO.update(this.updated_pizza);
        for(ListeIngredients ingredient : updated_ingredients){
            if(!current_ingredients.contains(ingredient)){
                composerDAO.insert(new Composer(updated_pizza,new Ingredients(ingredient.getIngredient_id(),ingredient.getIngredient_name())));
            }else{
                composerDAO.delete(composerDAO.getByIdPizzaAndIdIngredient(updated_pizza.getId(),ingredient.getIngredient_id()));
            }
        }
        if(!pizzaUpdated){
            DialogUtils.showDialog("Modification de la pizza échouée !", "Erreur modification pizza", Alert.AlertType.ERROR);
            return;
        }

        DialogUtils.showDialog("Pizza modifiée !");
        onReturnUpdatePizzaButtonClick(event);
    }

    @FXML
    private void onReturnUpdatePizzaButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminPizzasApplication(), ((Node) event.getSource()));
    }
}
