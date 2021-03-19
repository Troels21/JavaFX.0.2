package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ControllerPatientArkiv extends ControllerArkiv implements Initializable {
    ControllerLogin CL = new ControllerLogin();
    ControllerProgramChooser cpc=new ControllerProgramChooser();

    @FXML
    public TextField timeMin;
    @FXML
    public TextField timeMax;
    @FXML
    public Label Cprlabel;

    @FXML
    LineChart<NumberAxis, NumberAxis> PulseChart;
    @FXML
    LineChart<NumberAxis, NumberAxis> TempChart;
    @FXML
    LineChart<NumberAxis, NumberAxis> SpO2Chart;
    @FXML
    LineChart<NumberAxis, NumberAxis> EKGChart;


    public void PulsArkiv() throws FileNotFoundException {
        populateChart("Pulse", pulsArray, PulseXYChart, PulseChart, PulseTime, PulseValue);
    }

    public void TempArkiv() throws FileNotFoundException {
        populateChart("Temp", tempArray, TempXYChart, TempChart, TempTime, TempValue);
    }

    public void SpO2Arkiv() throws FileNotFoundException {
        populateChart("SpO2", SpO2Array, SpO2XYChart, SpO2Chart, SpO2Time, SpO2Value);
    }

    public void EKGArkiv() throws FileNotFoundException {
        populateChart("EKG", EKGArray, EKGXYChart, EKGChart, EKGTime, EKGValue);
    }

    @Override
    public String CPR() {
        return Cprlabel.getText();
    }  //En patient skal kun kunne tilgå sine egne data

    @Override  //En patient skal kun kunne tilgå sine egne data, derfor bliver CPR automatisk overført
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        Cprlabel.setText(CL.CPR);
        // populære charts fra start
        try {
            PulsArkiv();
            TempArkiv();
            SpO2Arkiv();
            EKGArkiv();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
