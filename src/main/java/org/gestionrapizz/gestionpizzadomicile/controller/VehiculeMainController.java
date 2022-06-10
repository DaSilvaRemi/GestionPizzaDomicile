package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.VehiculeCreateApplication;
import org.gestionrapizz.gestionpizzadomicile.VehiculeMainApplication;
import org.gestionrapizz.gestionpizzadomicile.VehiculeUpdateApplication;
import org.gestionrapizz.gestionpizzadomicile.models.CommandeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.TypeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.VehiculeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Commande;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Vehicule;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesCommandes;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesVehicules;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.lang.invoke.VarHandle;
import java.util.List;

public class VehiculeMainController {

    @FXML
    private TableView<MesVehicules> vehicule_main_tableview;
    @FXML
    private TableColumn<MesVehicules, String> vehicule_main_type_tableColumn;
    @FXML
    private TableColumn<MesVehicules, String> vehicule_main_immatriculation_tableColumn;

    public void initialize(){
        vehicule_main_type_tableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        vehicule_main_immatriculation_tableColumn.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));

        VehiculeDAO vehiculeDAO = VehiculeDAO.getInstance();
        List<Vehicule> allVehicules = vehiculeDAO.get();
        for (Vehicule vehicule : allVehicules){
            vehicule_main_tableview.getItems().add(
                new MesVehicules(vehicule)
            );
        }
    }

    @FXML
    protected void onCreateVehiculeButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new VehiculeCreateApplication(), ((Node) event.getSource()));
    }

    @FXML
    protected void onUpdateVehiculeButtonClick(MouseEvent event){
        UserSessionUtil usu = UserSessionUtil.getInstance(null);
        MesVehicules mesVehicules = vehicule_main_tableview.getSelectionModel().getSelectedItem();


        if(mesVehicules == null){
            DialogUtils.showDialog("Sélectionner un véhicule à modifier");
            return;
        }

        usu.getVAR_SESSION().put("id_immatriculation", mesVehicules.getImmatriculation());
        usu.getVAR_SESSION().put("id_type", mesVehicules.getType());

        JavaFXOpenWindowUtil.openAndCloseAWindow(new VehiculeUpdateApplication(), ((Node) event.getSource()));
    }
}
