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

    XYChart.Series PulseXYChart = new XYChart.Series();
    XYChart.Series TempXYChart = new XYChart.Series();
    XYChart.Series SpO2XYChart = new XYChart.Series();
    XYChart.Series EKGXYChart = new XYChart.Series();


    int[] PulseTime, PulseValue, TempTime, TempValue, SpO2Time, SpO2Value, EKGTime, EKGValue;
    String[] pulsArray, tempArray, SpO2Array, EKGArray;
    int timeMaxInt = 60;
    int timeMinInt = 0;

    public void PulsArkiv(ActionEvent actionEvent) throws FileNotFoundException {
        populateChart("Pulse", pulsArray, PulseXYChart, PulseChart, PulseTime, PulseValue);
    }

    public void TempArkiv(ActionEvent actionEvent) throws FileNotFoundException {
        populateChart("Temp", tempArray, TempXYChart, TempChart, TempTime, TempValue);
    }

    public void SpO2Arkiv(ActionEvent actionEvent) throws FileNotFoundException {
        populateChart("SpO2", SpO2Array, SpO2XYChart, SpO2Chart, SpO2Time, SpO2Value);
    }

    public void EKGArkiv(ActionEvent actionEvent) throws FileNotFoundException {
        populateChart("EKG", EKGArray, EKGXYChart, EKGChart, EKGTime, EKGValue);
    }

    public void populateChart(String filetype, String[] array, XYChart.Series xyChart, LineChart lineChart, int[] time, int[] value) throws FileNotFoundException {
        xyChart.getData().clear();
        lineChart.getData().clear();

        String FileName = Cprlabel.getText();
        File Pulse1 = new File("PatientData/" + FileName + "/" + filetype); //mac :FileName, "Pulse"
        Scanner Patient = new Scanner(Pulse1);
        String PulseData = Patient.nextLine();

        String Rå = PulseData.replaceAll("[^0-9,]", "");
        array = Rå.split(",");

        time = new int[array.length / 2];
        if (array.length > 1) {
            for (int i = 0; i < array.length; i = i + 2) {
                time[i / 2] = Integer.parseInt(array[i]);

            }
        }

        value = new int[array.length / 2];
        if (array.length > 1) {
            for (int i = 1; i < array.length; i = i + 2) {
                value[i / 2] = Integer.parseInt(array[i]); // hvad sker der når man deler 3 med 2 som integer.

            }
        }
        if (timeMax.getText() != "null" && timeMin.getText() != "null") {
            try {
                timeMaxInt = Integer.parseInt(timeMax.getText());
                timeMinInt = Integer.parseInt(timeMin.getText());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (timeMaxInt > value.length) {
            timeMaxInt = value.length;
            timeMax.setText(String.valueOf(timeMaxInt));
        }
        for (int a = timeMinInt; a < timeMaxInt; a++) {
            xyChart.getData().add(new XYChart.Data(time[a], value[a]));
        }
        lineChart.getData().add(xyChart);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Cprlabel.setText(CL.CPR);
    }
}
