package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.gestionrapizz.gestionpizzadomicile.models.MenuPizzeriaDAO;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.LigneMenuPizzeria;
import org.gestionrapizz.gestionpizzadomicile.models.view.MenuPizzeria;

import java.util.List;

public class MenuPizzasController {
    @FXML
    private TableView<LigneMenuPizzeria> menupizzas_tableview;
    @FXML
    private TableColumn<LigneMenuPizzeria, String> nompizza_tablecolumn;
    @FXML
    private TableColumn<LigneMenuPizzeria, String> ingredientspizza_tablecolumn;
    @FXML
    private TableColumn<LigneMenuPizzeria, Double> prixpizza_tablecolumn;

    public void initialize(){
        nompizza_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("nomPizza"));
        ingredientspizza_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("ingredientspizza"));
        prixpizza_tablecolumn.setCellValueFactory(new PropertyValueFactory<>("prix"));

        MenuPizzeriaDAO menuPizzeriaDAO = MenuPizzeriaDAO.getInstance();
        List<MenuPizzeria> menuPizzerias = menuPizzeriaDAO.get();

        for (MenuPizzeria menuPizzeria: menuPizzerias) {
            this.menupizzas_tableview.getItems().add(new LigneMenuPizzeria(menuPizzeria));
        }
    }
}
