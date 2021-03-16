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
    LineChart<CategoryAxis, NumberAxis> Diagram;
    @FXML
    TextField Name;
    @FXML
    Label Spo2Label;


    public void monitorStart() throws IOException {
        monitorStartPuls(Name,Diagram,Spo2Label);
    }

    public void monitorStop() throws IOException {
        monitorStopPuls();
    }

    public void refresh() {
        refreshPuls(Diagram,Spo2Label);
    }

    public void showPulse() {
        showPulsePuls();
    }

    public void showTemperature() {
        showTemperaturePuls();
    }

    public void setName(String string){
        Name.setText(string);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Name.setText(name);
    }
}
