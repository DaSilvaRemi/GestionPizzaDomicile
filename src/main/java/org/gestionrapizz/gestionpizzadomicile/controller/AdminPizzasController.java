package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminAddPizzaApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminLivreurInsertApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminLivreurUpdateApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminUpdatePizzaApplication;
import org.gestionrapizz.gestionpizzadomicile.models.LivreurDAO;
import org.gestionrapizz.gestionpizzadomicile.models.MenuPizzeriaDAO;
import org.gestionrapizz.gestionpizzadomicile.models.PizzaDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Livreur;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Pizza;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.LigneMenuPizzeria;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesLivreurs;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesPizzas;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;
import org.gestionrapizz.gestionpizzadomicile.models.view.MenuPizzeria;

import java.util.List;
import java.util.Optional;

public class AdminPizzasController {

    @FXML
    private TableView<MesPizzas> pizza_list;
    @FXML
    private TableColumn<MesPizzas, String> list_pizza_name;
    @FXML
    private TableColumn<MesPizzas, String> list_pizza_ingredients;
    @FXML
    private TableColumn<MesPizzas, Double> list_pizza_price;

    public void initialize(){
        list_pizza_name.setCellValueFactory(new PropertyValueFactory<>("nomPizza"));
        list_pizza_ingredients.setCellValueFactory(new PropertyValueFactory<>("ingredientspizza"));
        list_pizza_price.setCellValueFactory(new PropertyValueFactory<>("prix"));

        this.updateTable();

    }
    private void updateTable(){
        MenuPizzeriaDAO menuPizzeriaDAO = MenuPizzeriaDAO.getInstance();
        List<MenuPizzeria> da_pizza_list = menuPizzeriaDAO.get();

        for (MenuPizzeria liste_pizza: da_pizza_list) {
            this.pizza_list.getItems().add(new MesPizzas(liste_pizza));
        }
    }

    @FXML
    private void onOpenAddPizzaButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminAddPizzaApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onOpenUpdatePizzaButtonClick(MouseEvent event){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        MesPizzas selectedPizza = this.pizza_list.getSelectionModel().getSelectedItem();
        userSessionUtil.getVAR_SESSION().put("id_pizza", selectedPizza.getId());
        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminUpdatePizzaApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onDeletePizzaButtonClick(MouseEvent event){
       /* Optional<ButtonType> options = DialogUtils.showDialog("Etes vous sur de vouloir supprimer cette pizza ? ", "Confirmation suppression", Alert.AlertType.CONFIRMATION);
        if(options.orElse(ButtonType.CANCEL).equals(ButtonType.OK)){
            PizzaDAO pizzaDAO = PizzaDAO.getInstance();
            MesPizzas selectedPizza = this.pizza_list.getSelectionModel().getSelectedItem();
            Pizza pizza = pizzaDAO.getById(selectedPizza.getId());
            boolean isDelete = pizzaDAO.delete(pizza);

            if(!isDelete){
                DialogUtils.showDialog("Suppression échouée !", "Erreur : Suppression", Alert.AlertType.ERROR);
                return;
            }
            DialogUtils.showDialog("Suppression réussie !");
            this.updateTable();
        }*/
    }
}


