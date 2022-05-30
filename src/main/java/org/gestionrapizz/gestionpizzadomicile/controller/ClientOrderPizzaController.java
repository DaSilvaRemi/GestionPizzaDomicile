package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class ClientOrderPizzaController {
    @FXML
    private TableView<String> cart_tableview;
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

    protected void onClickAddPizzaButton(MouseEvent event){
        String selectedPizza = pizzaschoice_selector.getSelectionModel().getSelectedItem();
    }

    protected void onClickRemovePizzaButton(MouseEvent event){

    }

    protected void onConfirmOrderButton(MouseEvent event){

    }

    protected void onClearCartButton(MouseEvent event){

    }
}
