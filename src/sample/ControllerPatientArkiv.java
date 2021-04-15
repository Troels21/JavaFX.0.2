package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ControllerPatientArkiv extends Simulering implements Initializable {

    ControllerLogin CL = new ControllerLogin();

    @FXML
    public TextField timeMin;
    @FXML
    public TextField timeMax;
    @FXML
    public Label Cprlabel;
    @FXML
    public NumberAxis EKGxAkse2;
    @FXML
    public NumberAxis SpO2xAkse2;
    @FXML
    public NumberAxis tempxAkse2;
    @FXML
    public NumberAxis pulsexakse2;
    @FXML
    LineChart<NumberAxis, NumberAxis> PulseChart;
    @FXML
    LineChart<NumberAxis, NumberAxis> TempChart;
    @FXML
    LineChart<NumberAxis, NumberAxis> SpO2Chart;
    @FXML
    LineChart<NumberAxis, NumberAxis> EKGChart;


    public void PulsArkiv() throws FileNotFoundException, SQLException {
        populateChart(PulseXYChart, PulseChart, PulseTime, PulseValue, pulsexakse2, timeMax, timeMin, CL.CPR);
    }

    public void TempArkiv() throws FileNotFoundException, SQLException {
        populateChart(TempXYChart, TempChart, TempTime, TempValue, tempxAkse2, timeMax, timeMin, CL.CPR);
    }

    public void SpO2Arkiv() throws FileNotFoundException, SQLException {
        populateChart(SpO2XYChart, SpO2Chart, SpO2Time, SpO2Value, SpO2xAkse2, timeMax, timeMin, CL.CPR);
    }

    public void EKGArkiv() throws FileNotFoundException, SQLException {
        populateChart(EKGXYChart, EKGChart, EKGTime, EKGValue, EKGxAkse2, timeMax, timeMin, CL.CPR);
    }

    @Override  //En patient skal kun kunne tilgå sine egne data, derfor bliver CPR automatisk overført
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cprlabel.setText(CL.CPR);
        try {
            updateArray(CL.CPR);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // populære charts fra start
        try {
            EKGArkiv();
            PulsArkiv();
            TempArkiv();
            SpO2Arkiv();
        } catch (FileNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
