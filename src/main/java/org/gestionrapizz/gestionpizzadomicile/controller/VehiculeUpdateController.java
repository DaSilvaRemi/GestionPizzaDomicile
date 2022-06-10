package org.gestionrapizz.gestionpizzadomicile.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.gestionrapizz.gestionpizzadomicile.VehiculeMainApplication;
import org.gestionrapizz.gestionpizzadomicile.models.TypeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.VehiculeDAO;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Type;
import org.gestionrapizz.gestionpizzadomicile.models.entity.Vehicule;
import org.gestionrapizz.gestionpizzadomicile.models.tabs.MesVehicules;
import org.gestionrapizz.gestionpizzadomicile.models.utils.DialogUtils;
import org.gestionrapizz.gestionpizzadomicile.models.utils.JavaFXOpenWindowUtil;
import org.gestionrapizz.gestionpizzadomicile.models.utils.UserSessionUtil;
import org.w3c.dom.Text;

import java.util.List;

public class VehiculeUpdateController {

    @FXML
    private Label vehicule_update_immatriculation;

    @FXML
    private ChoiceBox<String> vehicule_update_type;

    private String immatriculation_init;
    private String type_init;

    public void initialize(){
        UserSessionUtil usu = UserSessionUtil.getInstance(null);

        List<Type> types = TypeDAO.getInstance().get();
        for (Type type: types) {
            vehicule_update_type.getItems().add(type.getNom());
        }

        String immatriculation = String.valueOf(usu.getVAR_SESSION().get("id_immatriculation"));
        String type = String.valueOf(usu.getVAR_SESSION().get("id_type"));

        vehicule_update_immatriculation.setText(immatriculation);
        vehicule_update_type.setValue(type);

        immatriculation_init = immatriculation;
        type_init = type;
    }

    @FXML
    protected void onReturnToMainVehiculeButtonClick(MouseEvent event){
        JavaFXOpenWindowUtil.openAndCloseAWindow( new VehiculeMainApplication(), ((Node) event.getSource()));
    }

    @FXML
    protected void onRemiseInitButtonClick(MouseEvent event){
        vehicule_update_immatriculation.setText(immatriculation_init);
        vehicule_update_type.setValue(type_init);
    }

    @FXML
    protected void onValiderUpdateButtonClick(MouseEvent event){
        VehiculeDAO vehiculeDAO = VehiculeDAO.getInstance();
        TypeDAO typeDAO = TypeDAO.getInstance();

        boolean b = vehiculeDAO.update(
            new Vehicule(
                immatriculation_init,
                typeDAO.getByName(vehicule_update_type.getSelectionModel().getSelectedItem())
            )
        );

        DialogUtils.showDialog("Véhicule mis à jour.");
    }
}
