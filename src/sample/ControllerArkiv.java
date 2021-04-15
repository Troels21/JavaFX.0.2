package sample;

import javafx.fxml.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextField;

import java.sql.SQLException;

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

    public void PatientChooser() throws SQLException {
        if (cprCheck2(CPR())) {
            updateArray(CPR());
            error("CPR-nummer er godkendt");
        } else {
            error("Ugyldigt CPR-nummer");
        }
    }

    public void PulsArkiv() throws SQLException {
        populateChart(PulseXYChart, PulseChart, PulseTime, PulseValue, pulsexAkse, timeMax, timeMin, CPR());
    }

    public void TempArkiv() throws SQLException {
        populateChart(TempXYChart, TempChart, TempTime, TempValue, tempXAkse, timeMax, timeMin, CPR());
    }

    public void SpO2Arkiv() throws SQLException {
        populateChart(SpO2XYChart, SpO2Chart, SpO2Time, SpO2Value, SpO2XAkse, timeMax, timeMin, CPR());
    }

    public void EKGArkiv() throws SQLException {
        populateChart(EKGXYChart, EKGChart, EKGTime, EKGValue, EKGXAkse, timeMax, timeMin, CPR());

    }

    public void saveDataToJournal() {
        saveData(PulseChart, TempChart, EKGChart, SpO2Chart);
    }

    public String CPR() {
        return CPR.getText();
    }  //En patient skal kun kunne tilgå sine egne data

    public void closeScene() {
        m.closeStage(Main.stage2);
    }
}
