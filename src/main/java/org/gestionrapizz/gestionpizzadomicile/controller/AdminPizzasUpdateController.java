package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminIngredientsCRUDApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminPizzasCRUDApplication;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminPizzasUpdateController {
    @FXML
    private TextField update_pizza_name_field;

    @FXML
    private Spinner<Double> update_pizza_price_field;

    @FXML
    private ListView<ListeIngredients> update_pizza_ingredients_list;

    private Pizza current_pizza;

    private Pizza updated_pizza;
    private List<ListeIngredients> ingredientsList;
    private List<ListeIngredients> listeIngredientsPizza;

    private List<ListeIngredients> updated_ingredients;

    private List<Composer> current_composed;

    public void initialize(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        this.ingredientsList = new ArrayList<>();
        this.listeIngredientsPizza = new ArrayList<>();
        this.updated_ingredients = new ArrayList<>();
        this.current_composed = new ArrayList<>();
        this.update_pizza_ingredients_list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        if(userSessionUtil.getVAR_SESSION().isEmpty()){
            JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminIngredientsCRUDApplication(), update_pizza_name_field.getScene().getWindow());
        }

        PizzaDAO pizzaDAO = PizzaDAO.getInstance();
        int idPizza = (int) userSessionUtil.getVAR_SESSION().get("id_pizza");
        this.current_pizza = pizzaDAO.getById(idPizza);

        this.reset();
        userSessionUtil.getVAR_SESSION().clear();
    }

    private void reset(){
        this.updated_pizza = this.current_pizza;
        this.update_pizza_name_field.setText(this.current_pizza.getNom());
        this.update_pizza_price_field.getValueFactory().setValue(this.current_pizza.getPrix());

        IngredientsDAO ingredientsDAO = IngredientsDAO.getInstance();
        ComposerDAO composerDAO = ComposerDAO.getInstance();
        List<Ingredients> ingredientsList = ingredientsDAO.get();

        this.current_composed = composerDAO.getByIdPizza(this.current_pizza.getId());

        for (Composer composer: this.current_composed) {
            ListeIngredients listeIngredients = new ListeIngredients(composer.getIngredients());
            this.ingredientsList.add(listeIngredients);
            this.listeIngredientsPizza.add(listeIngredients);
        }

        for(Ingredients ingredients : ingredientsList){
            ListeIngredients listeIngredients = new ListeIngredients(ingredients);
            if(!this.ingredientsList.contains(listeIngredients)){
                this.ingredientsList.add(listeIngredients);
            }
        }

        for(ListeIngredients listeIngredients : this.ingredientsList){
            this.update_pizza_ingredients_list.getItems().add(listeIngredients);
        }

        for(ListeIngredients listeIngredients : this.listeIngredientsPizza){
            this.update_pizza_ingredients_list.getSelectionModel().select(listeIngredients);
        }
    }

    @FXML
    private void onConfirmUpdatePizzaBtnClick(MouseEvent event){
        Optional<ButtonType> options = DialogUtils.showDialog("Confirmer les modifications ? ", "Confirmation Insertion", Alert.AlertType.CONFIRMATION);
        if(!options.orElse(ButtonType.CANCEL).equals(ButtonType.OK)){
            return;
        }
        String pizza_name = this.update_pizza_name_field.getText();
        Double pizza_price = this.update_pizza_price_field.getValue();
        if(!pizza_name.isBlank()){
            this.updated_pizza.setNom(pizza_name);
        }
        if(pizza_price != this.updated_pizza.getPrix()){
            this.updated_pizza.setPrix(pizza_price);
        }

        this.updated_ingredients = this.update_pizza_ingredients_list.getSelectionModel().getSelectedItems();

        if(this.updated_ingredients.size() == 0){
            this.updated_ingredients = new ArrayList<>(this.listeIngredientsPizza);
        }

        ComposerDAO composerDAO = ComposerDAO.getInstance();
        PizzaDAO pizzaDAO = PizzaDAO.getInstance();
        boolean pizzaUpdated = pizzaDAO.update(this.updated_pizza);

        for(ListeIngredients ingredient : this.listeIngredientsPizza){
            if(!this.updated_ingredients.contains(ingredient)){
               pizzaUpdated = pizzaUpdated && composerDAO.delete(composerDAO.getByIdPizzaAndIdIngredient(this.updated_pizza.getId(), ingredient.getIngredient_id()));
            }
        }

        for (ListeIngredients ingredient : this.updated_ingredients) {
            if(!this.listeIngredientsPizza.contains(ingredient)){
                Composer composer = new Composer(this.updated_pizza,new Ingredients(ingredient.getIngredient_id(),ingredient.getIngredient_name()));
                pizzaUpdated = pizzaUpdated && composerDAO.insert(composer) > 0;
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
    private void onResetButtonClicked(MouseEvent event){
        this.reset();
    }

    @FXML
    private void onReturnUpdatePizzaButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminPizzasCRUDApplication(), ((Node) event.getSource()));
    }
}
