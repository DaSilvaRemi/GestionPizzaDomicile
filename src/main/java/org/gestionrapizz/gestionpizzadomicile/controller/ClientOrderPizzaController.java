package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.LignePanier;

public class ClientOrderPizzaController {
    @FXML
    private TableView<LignePanier> cart_tableview;
    @FXML
    private Button addpizza_button;
    @FXML
    private Button removepizza_button;
    @FXML
    private Button confirmorder_button;
    @FXML
    private Button clearcart_button;
    @FXML
    private ChoiceBox<String> pizzaschoice_selector;
    @FXML
    private Label totalAmount_label;

    public void initialize(){

    }

    @FXML
    protected void onClickAddPizzaButton(MouseEvent event){
        String selectedPizza = pizzaschoice_selector.getSelectionModel().getSelectedItem();

    }

    @FXML
    protected void onClickRemovePizzaButton(MouseEvent event){

    }

    @FXML
    protected void onConfirmOrderButton(MouseEvent event){

    }

    @FXML
    protected void onClearCartButton(MouseEvent event){
        cart_tableview.getItems().clear();
    }
}
