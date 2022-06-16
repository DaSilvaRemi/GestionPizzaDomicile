package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.AdminVehiculeCreateApplication;
import org.gestionrapizz.gestionpizzadomicile.AdminVehiculeUpdateApplication;
import org.gestionrapizz.gestionpizzadomicile.models.CommandeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.TypeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.VehiculeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Vehicule;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesVehicules;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;

import java.util.List;

public class AdminVehiculeMainController {

    @FXML
    private TableView<MesVehicules> vehicule_main_tableview;
    @FXML
    private TableColumn<MesVehicules, String> vehicule_main_type_tableColumn;
    @FXML
    private TableColumn<MesVehicules, String> vehicule_main_immatriculation_tableColumn;

    public void initialize(){
        //vehicule_main_tableview.getItems().removeAll();

        vehicule_main_type_tableColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        vehicule_main_immatriculation_tableColumn.setCellValueFactory(new PropertyValueFactory<>("immatriculation"));
        this.updateVehiculeMainTableView();
    }

    private void updateVehiculeMainTableView() {
        vehicule_main_tableview.getItems().clear();
        VehiculeDAO vehiculeDAO = VehiculeDAO.getInstance();
        List<Vehicule> allVehicules = vehiculeDAO.get();
        for (Vehicule vehicule : allVehicules){
            vehicule_main_tableview.getItems().add(
                    new MesVehicules(vehicule)
            );
        }
    }

    @FXML
    private void onCreateVehiculeButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new AdminVehiculeCreateApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onUpdateVehiculeButtonClick(MouseEvent event){
        UserSessionUtil usu = UserSessionUtil.getInstance(null);
        MesVehicules mesVehicules = vehicule_main_tableview.getSelectionModel().getSelectedItem();


        if(mesVehicules == null){
            DialogUtils.showDialog("Sélectionner un véhicule à modifier");
            return;
        }

        usu.getVAR_SESSION().put("id_immatriculation", mesVehicules.getImmatriculation());
        usu.getVAR_SESSION().put("id_type", mesVehicules.getType());

        JavaFXOpenWindowUtil.openAndCloseAWindow(new AdminVehiculeUpdateApplication(), ((Node) event.getSource()));
    }

    @FXML
    private void onDeleteVehiculeButtonClick(MouseEvent event){
        UserSessionUtil usu = UserSessionUtil.getInstance(null);
        MesVehicules mesVehicules = vehicule_main_tableview.getSelectionModel().getSelectedItem();


        if(mesVehicules == null){
            DialogUtils.showDialog("Sélectionner un véhicule à supprimer");
            return;
        }

        VehiculeDAO allVehic = VehiculeDAO.getInstance();
        TypeDAO typeDAO = TypeDAO.getInstance();

        /*List<Commande> commandes = CommandeDAO.getInstance().get();
        boolean vehiculeEnCoursDutilisation = false;
        for (Commande commande: commandes) {
            if (commande.getVehicule().getImmatriculation().equals(mesVehicules.getImmatriculation())){
                vehiculeEnCoursDutilisation = true;
                break;
            }
        }
        if(vehiculeEnCoursDutilisation){
            DialogUtils.showDialog("Le véhicule est en cours d'utilisation !");
            return;
        }*/
        CommandeDAO commandeDAO = CommandeDAO.getInstance();
        if(commandeDAO.getByImmatriculation(mesVehicules.getImmatriculation()).size() != 0){
            DialogUtils.showDialog("Le véhicule est en cours d'utilisation !");
            return;
        }

        allVehic.delete(new Vehicule(mesVehicules.getImmatriculation(), typeDAO.getByName(mesVehicules.getType())));

        DialogUtils.showDialog(
            "Véhicule " +
            mesVehicules.getImmatriculation() +
            " supprimé."
        );
        this.updateVehiculeMainTableView();
    }
}
