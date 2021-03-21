package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Puls_SpO2_TempController extends Beregner implements Initializable {

    @FXML
    Label tempLabel;
    @FXML
    LineChart<CategoryAxis, NumberAxis> Diagram;
    @FXML
    TextField Name;
    @FXML
    Label Spo2Label;

    public void monitorStart() throws IOException {
        monitorStartPuls(Name, Diagram, Spo2Label, tempLabel);
    }

    public void monitorStop() throws IOException {
        monitorStopPuls();
    }

    public void showPulse() {
        showPulsePuls();
    }

    public void showTemperature() {
        showTemperaturePuls();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setText(name);
    } //Bruger navnet fra en tidligere stage til at sætte CPR navn.
}
