package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.gestionrapizz.gestionpizzadomicile.models.CommandeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MyOrders;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;

public class ClientMyOrdersController {
    @FXML
    private TableView<MyOrders> orders_tableview;

    public void initialize(){
        UserSessionUtil userSessionUtil = UserSessionUtil.getInstance(null);
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        List<Commande> commandeList = commandeDAO.getCommandesByIdUtilisateur(userSessionUtil.getUtilisateur().getId());
        for (Commande commande : commandeList) {
            orders_tableview.getItems().add(new MyOrders(commande));
        }
    }
}
