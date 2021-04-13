package sample;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.io.*;

public class ControllerArkiv extends Simulering {
    @FXML
    public TextField timeMin;
    @FXML
    public TextField timeMax;
    @FXML
    public NumberAxis SpO2XAkse;
    @FXML
    public NumberAxis EKGXAkse;
    @FXML
    public NumberAxis tempXAkse;
    @FXML
    public NumberAxis pulsexAkse;
    @FXML
    TextField CPR;

    @FXML
    LineChart<NumberAxis, NumberAxis> PulseChart;
    @FXML
    LineChart<NumberAxis, NumberAxis> TempChart;
    @FXML
    LineChart<NumberAxis, NumberAxis> SpO2Chart;
    @FXML
    LineChart<NumberAxis, NumberAxis> EKGChart;

    public void PatientChooser() throws FileNotFoundException, SQLException {
        if (cprCheck2(CPR())) {

            error("CPR-nummer er godkendt");
            updateArray(CPR());
        } else {
            error("Ugyldigt CPR-nummer");
        }
    }

    public void PulsArkiv() throws FileNotFoundException, SQLException {
        populateChart("Pulse", pulsArray, PulseXYChart, PulseChart, PulseTime, PulseValue, pulsexAkse, timeMin, timeMax, CPR());
    }

    public void TempArkiv() throws FileNotFoundException, SQLException {
        populateChart("Temp", tempArray, TempXYChart, TempChart, TempTime, TempValue, tempXAkse, timeMin, timeMax, CPR());
    }

    public void SpO2Arkiv() throws FileNotFoundException, SQLException {
        populateChart("SpO2", SpO2Array, SpO2XYChart, SpO2Chart, SpO2Time, SpO2Value, SpO2XAkse, timeMin, timeMax,CPR());
    }

    public void EKGArkiv() throws FileNotFoundException, SQLException {
        populateChart("EKG", EKGArray, EKGXYChart, EKGChart, EKGTime, EKGValue, EKGXAkse, timeMin, timeMax, CPR());

    }

    public void saveDataToJournal(){
        saveData(PulseChart, TempChart, EKGChart, SpO2Chart);
    }

    public String CPR() {
        return CPR.getText();
    }  //En patient skal kun kunne tilgå sine egne data

    public void closeScene(ActionEvent actionEvent) {
        m.closeStage(m.stage2);
    }
}
