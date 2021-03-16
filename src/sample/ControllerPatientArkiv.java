package sample;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPatientArkiv extends ControllerArkiv implements Initializable {
    public Label Cprlabel;
    ControllerLogin CL = new ControllerLogin();

    public void PulsArkiv(ActionEvent actionEvent) throws FileNotFoundException {
        PulsArkiv();
    }

    public void TempArkiv(ActionEvent actionEvent) throws FileNotFoundException {
        TempArkiv();
    }

    public void SpO2Arkiv(ActionEvent actionEvent) throws FileNotFoundException {
        SpO2Arkiv();
    }

    public void EKGArkiv(ActionEvent actionEvent) throws FileNotFoundException {
        EKGArkiv();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cprlabel.setText(CL.CPR);
    }
}
