package sample;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class GenMetoder{

    //værider til cotroller ting
    XYChart.Series PulseXYChart = new XYChart.Series();
    XYChart.Series TempXYChart = new XYChart.Series();
    XYChart.Series SpO2XYChart = new XYChart.Series();
    XYChart.Series EKGXYChart = new XYChart.Series();

    int[] PulseTime, TempTime, SpO2Time, EKGTime;
    double[] PulseValue, TempValue, SpO2Value, EKGValue;
    String[] pulsArray, tempArray, SpO2Array, EKGArray;
    int timeMaxInt = 60;
    int timeMinInt = 0;


    //værdier brugt til Alarmgrænser
    static double pulseMaxDouble = 220;
    static double pulseMinDouble = 0;
    static double tempMaxDouble = 49;
    static double tempMinDouble = 35;
    static double SpO2MaxDouble = 100;
    static double SpO2MinDouble = 95;
    static double ekgMaxDouble = 150;
    static double ekgMinDouble = -50;
    int alarmCounter = -50;


    //String brugt til holde CPR gemt, mellem stages.
    static String name;

    SQL sql_objekt = new SQL();

    //metode til at kontrollere alarm
    public void alarmCheck(String string, double alarmMax, double alarmMin, double value, int tæller) {
        if (value < alarmMin || value > alarmMax && (tæller - alarmCounter) > 50) {
            error(string);
            alarmCounter = tæller;
        }
    }

    //Alarm besked
    public void error(String message) {
        Label alertLabel = new Label();
        StackPane allertLayout = new StackPane();
        Stage allertStage = new Stage();
        Button allertButton = new Button();

        allertButton.setText("OK");
        alertLabel.setText(message);
        allertStage.setTitle("Alert");

        allertButton.setOnAction(p -> allertStage.close());
        allertLayout.getChildren().addAll(allertButton, alertLabel);
        Scene allertScene = new Scene(allertLayout, 200, 100);
        alertLabel.setTranslateY(-25);

        allertStage.setScene(allertScene);
        allertStage.initModality(Modality.APPLICATION_MODAL);
        allertStage.show();
    }

    // metode som kontrollere om der er indtastet et eksiterende cpr
    public boolean cprCheck2(String name) throws SQLException {
        if (sql_objekt.doesPatientExsist(name)) {
            return true;
        } else {
            return false;
        }
    }


    //metode til at finde data og indlæse det i grafer.
    public void populateChart(String filetype, String[] array, XYChart.Series xyChart, LineChart lineChart,
                              int[] time, double[] value, NumberAxis xakse,
                              TextField timeMax, TextField timeMin, String cpr) throws FileNotFoundException, SQLException {

        if (cprCheck2(cpr)) {
            xyChart.getData().clear();
            lineChart.getData().clear();
            if (timeMax.getText() != "null" || timeMin.getText() != "null") {
                try {
                    timeMaxInt = Integer.parseInt(timeMax.getText());
                    timeMinInt = Integer.parseInt(timeMin.getText());
                } catch (NumberFormatException e) {
                    timeMaxInt = value.length;
                    timeMax.setText(String.valueOf(timeMaxInt));
                    timeMinInt = 0;
                    timeMin.setText(String.valueOf(timeMinInt));
                }
            }
            if (timeMaxInt > value.length) {
                timeMaxInt = value.length;
                timeMax.setText(String.valueOf(timeMaxInt));
            }
            for (int a = 0; a < timeMaxInt; a++) {
                xyChart.getData().add(new XYChart.Data(time[a], value[a]));

            }
            xakse.setUpperBound(timeMaxInt);
            xakse.setLowerBound(timeMinInt);
            lineChart.getData().add(xyChart);
        } else {
            error("Ugyldigt cpr");
        }
    }

    public void updateArray(String cpr) throws SQLException {
        EKGTime = new int[sql_objekt.rowCounter("patientMaalingEKG", cpr)];
        EKGValue = new double[sql_objekt.rowCounter("patientMaalingEKG", cpr)];

        PulseTime = new int[sql_objekt.rowCounter("patientMaalingPuls", cpr)];
        TempTime = new int[sql_objekt.rowCounter("patientMaalingPuls", cpr)];
        SpO2Time = new int[sql_objekt.rowCounter("patientMaalingPuls", cpr)];

        PulseValue = new double[sql_objekt.rowCounter("patientMaalingPuls", cpr)];
        TempValue = new double[sql_objekt.rowCounter("patientMaalingPuls", cpr)];
        SpO2Value = new double[sql_objekt.rowCounter("patientMaalingPuls", cpr)];

        sql_objekt.readDataEKG(cpr, EKGTime, EKGValue);
        sql_objekt.readDataPuls(cpr, PulseTime, PulseValue, TempValue, SpO2Value);
        TempTime = PulseTime;
        SpO2Time = PulseTime;
    }


    public void saveData(LineChart PulseChart, LineChart TempChart, LineChart EKGChart, LineChart SpO2Chart) {
        FileHandler fh = new FileHandler();
        String path = fh.savepath();
        fh.saveAsPng(path, PulseChart, "Puls.png");
        fh.saveAsPng(path, TempChart, "Temp.png");
        fh.saveAsPng(path, EKGChart, "EKG.png");
        fh.saveAsPng(path, SpO2Chart, "SpO2.png");

    }




}
