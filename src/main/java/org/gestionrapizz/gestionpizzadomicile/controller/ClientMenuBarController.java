package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Window;
import org.gestionrapizz.gestionpizzadomicile.*;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

public class ClientMenuBarController {
    @FXML
    private Menu menu_bar;
    @FXML
    private MenuItem menu_menuitem;
    @FXML
    private MenuItem orderpizza_menuitem;
    @FXML
    private MenuItem myorder_menuitem;
    @FXML
    private MenuItem makeadeposit_menuitem;
    @FXML
    private MenuItem logout_menuitem;

    @FXML
    protected void onMenuClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientAccountApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));
    }

    @FXML
    protected void onOrderPizzaClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientOrderPizzaApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));
    }

    @FXML
    protected void onMyOrderClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientMyOrdersApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));
    }

    @FXML
    protected void onMakeADepositClick(Event event){
        JavaFXOpenWindowUtil.openAndCloseAWindow(new ClientMakeDepositApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));
    }

    @FXML
    protected void onLogoutClick(Event event){
        UserSessionUtil.getInstance(null).clearUserSession();
        JavaFXOpenWindowUtil.openAndCloseAWindow(new MainApplication(), (((MenuItem)event.getSource()).getParentPopup().getOwnerWindow()));
    }
}
