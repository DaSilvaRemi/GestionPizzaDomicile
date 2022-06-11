package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.VehiculeMainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.*;
import org.gestionrapizz.gestionpizzadomicile.models.entity.*;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;

import java.util.List;

public class VehiculeCreateController {

    @FXML
    private ChoiceBox<String> vehicule_create_type;

    @FXML
    private TextField vehicule_create_immatriculation;

    public void initialize() {
        // Ajout dynamique des types dans la choiceBox
        List<Type> types = TypeDAO.getInstance().get();
        for (Type type: types) {
            vehicule_create_type.getItems().add(type.getNom());
        }
    }

    @FXML
    protected void onClickAddVehiculeButton(MouseEvent event){
        TypeDAO type = TypeDAO.getInstance();

        String new_immat = vehicule_create_immatriculation.getText();
        Type new_type;

        // Vérif type non inséré
        try {
            new_type = type.getByName(vehicule_create_type.getSelectionModel().getSelectedItem());
        }
        catch(Exception e) {
            DialogUtils.showDialog("Choisir un type de véhicule");
            return;
        }

        // Vérif plaque non vide
        if(new_immat == ""){
            DialogUtils.showDialog("Choisir une immatriculation");
            return;
        }

        /*List<Vehicule> vehicules = VehiculeDAO.getInstance().get();
        boolean vehiculeExiste = false;
        for (Vehicule vehicule: vehicules) {
            if (vehicule.getImmatriculation().equals(new_immat)){
                vehiculeExiste = true;
                break;
            }
        }
        if(vehiculeExiste){
            DialogUtils.showDialog("Le véhicule existe déjà");
            return;
        }*/
        VehiculeDAO vehiculeDao = VehiculeDAO.getInstance();
        if(vehiculeDao.getById(new_immat) != null){
            DialogUtils.showDialog("Le véhicule existe déjà");
            return;
        }

        // Ajout dans la table
        vehiculeDao.insert(
            new Vehicule(
                new_immat,
                new_type
            )
        );

        DialogUtils.showDialog(
            vehicule_create_type.getSelectionModel().getSelectedItem() +
            " " +
            new_immat +
            " ajouté."
        );
    }

    @FXML
    protected void onReturnToMainVehiculeButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new VehiculeMainApplication(), ((Node) event.getSource()));
    }
}
