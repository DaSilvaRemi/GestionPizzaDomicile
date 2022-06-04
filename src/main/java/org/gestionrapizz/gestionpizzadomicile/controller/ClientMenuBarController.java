package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.gestionrapizz.gestionpizzadomicile.*;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

public class ClientMenuBarController extends VBox {
    @FXML
    private Menu menu_menuitem;
    @FXML
    private Menu orderpizza_menuitem;
    @FXML
    private Menu myorder_menuitem;
    @FXML
    private Menu makeadeposit_menuitem;
    @FXML
    private Menu logout_menuitem;

    @FXML
    protected void onMenuClick(ActionEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientAccountApplication(), ((Node) event.getSource()));
    }

    @FXML
    protected void onOrderPizzaClick(ActionEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientOrderPizzaApplication(), ((Node) event.getSource()));
    }

    @FXML
    protected void onMyOrderClick(ActionEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientMyOrdersApplication(), ((Node) event.getSource()));
    }

    @FXML
    protected void onMakeADepositClick(ActionEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientMakeDepositApplication(), ((Node) event.getSource()));
    }

    @FXML
    protected void onLogoutClick(ActionEvent event){
        UserSessionUtil.getInstance(null).clearUserSession();
        JavaFXOpenWindowUtil.openAndCloseAWindow(new MainApplication(), ((Node) event.getSource()));
    }
}
