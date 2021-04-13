package sample;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.*;
import java.io.*;

public class ControllerArkiv extends Beregner {
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

    SQL sql_objekt = new SQL();

    XYChart.Series PulseXYChart = new XYChart.Series();
    XYChart.Series TempXYChart = new XYChart.Series();
    XYChart.Series SpO2XYChart = new XYChart.Series();
    XYChart.Series EKGXYChart = new XYChart.Series();

    int[] PulseTime, TempTime, SpO2Time, EKGTime;
    double[] PulseValue, TempValue, SpO2Value, EKGValue;
    String[] pulsArray, tempArray, SpO2Array, EKGArray;
    int timeMaxInt = 60;
    int timeMinInt = 0;

    public void PatientChooser() throws FileNotFoundException {
        if (cprCheck2()) {
            error("CPR-nummer er godkendt");
        } else {
            error("Ugyldigt CPR-nummer");
        }
    }

    public void PulsArkiv() throws FileNotFoundException {
        populateChart("Pulse", pulsArray, PulseXYChart, PulseChart, PulseTime, PulseValue,pulsexAkse);
    }

    public void TempArkiv() throws FileNotFoundException {
        populateChart("Temp", tempArray, TempXYChart, TempChart, TempTime, TempValue,tempXAkse);
    }

    public void SpO2Arkiv() throws FileNotFoundException {
        populateChart("SpO2", SpO2Array, SpO2XYChart, SpO2Chart, SpO2Time, SpO2Value,SpO2XAkse);
    }

    public void EKGArkiv() throws FileNotFoundException, SQLException {
        String cpr = CPR();
        sql_objekt.readDataEKG(cpr, EKGTime, EKGValue);
        populateChart("EKG", EKGArray, EKGXYChart, EKGChart, EKGTime, EKGValue,EKGXAkse);

    }

    public String CPR() {
        return CPR.getText();
    }


    //metode til at finde data og indlæse det i grafer.
    public void populateChart(String filetype, String[] array, XYChart.Series xyChart, LineChart lineChart,
                              int[] time, double[] value, NumberAxis xakse) throws FileNotFoundException {

        if (cprCheck2()) {


            xyChart.getData().clear();
            lineChart.getData().clear();
            /*
            String FileName = CPR();
            File Pulse1 = new File("PatientData/" + FileName + "/" + filetype); //mac :FileName, "Pulse"
            Scanner Patient = new Scanner(Pulse1);
            String PulseData = Patient.nextLine();

            String Rå = PulseData.replaceAll("[^0-9,.]", "");
            array = Rå.split(",");

            time = new int[array.length / 2];
            if (array.length > 1) {
                for (int i = 0; i < array.length; i = i + 2) {
                    time[i / 2] = Integer.parseInt(array[i]);

                }
            }

            value = new double[array.length / 2];
            if (array.length > 1) {
                for (int i = 1; i < array.length; i = i + 2) {
                    value[i / 2] = Double.parseDouble(array[i]); // hvad sker der når man deler 3 med 2 som integer.
                }
            }*/
            //



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
        }else{
            error("Ugyldigt cpr");
        }
    }

    public boolean cprCheck2(){
        // metode som kontrollere om der er indtastet et eksiterende cpr
        File checker = new File("PatientData", CPR());
        if (checker.exists() && CPR().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void saveDataToJournal(ActionEvent actionEvent) {
        FileHandler fh= new FileHandler(CPR());
        fh.saveAsPng(PulseChart,"Puls.png");
        fh.saveAsPng(TempChart,"Temp.png");
        fh.saveAsPng(EKGChart,"EKG.png");
        fh.saveAsPng(SpO2Chart,"SpO2.png");

    }

    public void closeScene(ActionEvent actionEvent) {
        m.closeStage(m.stage2);
    }
}
