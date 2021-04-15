package sample;

import javafx.application.Platform;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulering extends GenMetoder {
    Main m = new Main();
    SQL sql_objekt = new SQL();

    //Variabler til puls, temperatur,ekg simulation og fremvisning
    double math, math2, math3, temp, SpO2double;
    int puls, red, u, pulseCheck, tempCheck;
    String Spo2, SpO2String;
    double intervalmin = 70;
    double intervalmax = 70;
    double intervalmin2 = 98;
    double intervalmax2 = 100;
    double intervalmin3 = 36;
    double intervalmax3 = 38;
    int i = 0;
    int y = 0;

    Boolean threadCheck = true;

    //Objekter brugt til at fremvise linechart serie i realtid
    //ScheduledExecutorService Eventhandler = Executors.newSingleThreadScheduledExecutor();
    ScheduledExecutorService Eventhandler;
    XYChart.Series pulsSeries = new XYChart.Series();
    XYChart.Series temperatureSeries = new XYChart.Series();
    XYChart.Series<String, Number> ekgseries = new XYChart.Series<>();


    //metode til at fremvise puls,temp og spo2

    public void monitorStartPuls(TextField textField, LineChart<CategoryAxis, NumberAxis> linechart,
                                 Label label, Label label2){
        name = textField.getText();
        if (!threadCheck){
            return;
        }
        if (cprCheck2(name)) {
            sql_objekt.createNewPatient(name);
            threadCheck = false;
            pulsSeries.setName("puls");
            temperatureSeries.setName("Temperature");
            linechart.getData().clear();
            linechart.getData().addAll(pulsSeries, temperatureSeries);
            Eventhandler = Executors.newSingleThreadScheduledExecutor();
            Eventhandler.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                        String bogstav = String.valueOf(i);
                        SpO2Simulation();
                        SpO2String += bogstav + " " + Spo2 + " ";
                        pulseSimulation();
                        temperatureSimulation();
                        if (pulseCheck == 1) {
                            pulsSeries.getData().add(new XYChart.Data(bogstav, puls));
                            alarmCheck("ALARM PULS ER FARLIG", pulseMaxDouble, pulseMinDouble, puls, i);
                        }
                        if (tempCheck == 1) {
                            label2.setText((temp + "°C"));
                            temperatureSeries.getData().add(new XYChart.Data(bogstav, temp));
                            alarmCheck("ALARM TEMPERATUR ER FARLIG", tempMaxDouble, tempMinDouble, temp, i);
                        }

                        label.setText(Spo2);
                        alarmCheck("SPO2 ER FARLIG", SpO2MaxDouble, SpO2MinDouble, SpO2double, i);

                        sql_objekt.writeToPatientMaalingPuls(name, puls, temp, SpO2double);
                        i++;
                    }), 0, 1, TimeUnit.SECONDS);
        } else {
            error("Invalid input, ugyldigt CPR");
        }
    }

    //Metode til at stoppe fremvisning i realtid af puls, temp og spo2
    public void eventhandlerShutdown(){
        if (!threadCheck){
        Eventhandler.shutdown();
        threadCheck = true;
        }
    }

    //Kontrol logic in radiobutton pulse
    public void showPulsePuls() {
        if (pulseCheck == 1) {
            pulseCheck = 0;
        }
        else {
            pulseCheck = 1;
        }
    }

    //Kontrol logic in radiobutton temp
    public void showTemperaturePuls() {
        if (tempCheck == 1) {
            tempCheck = 0;
        }
        else {
            tempCheck = 1;
        }
    }


    public void pulseSimulation() {
        math = Math.random() * (intervalmax - intervalmin) + intervalmin;
        puls = (int) math;
        intervalmax = math + 5;
        intervalmin = math - 5;
    }

    public void SpO2Simulation() {
        math2 = Math.random() * (intervalmax2 - intervalmin2) + intervalmin2;
        SpO2double = new BigDecimal(math2).setScale(2, RoundingMode.HALF_UP).doubleValue();//Runder til 2 decimal
        Spo2 = (SpO2double + "%");
        intervalmax2 = math2 + 0.05;
        intervalmin2 = math2 - 0.05;
    }

    public void temperatureSimulation() {
        math3 = Math.random() * (intervalmax3 - intervalmin3) + intervalmin3;
        temp = new BigDecimal(math3).setScale(2, RoundingMode.HALF_UP).doubleValue(); //Runder til 2 decimal
        intervalmax3 = math3 + 0.25;
        intervalmin3 = math3 - 0.25;
    }

    //EKG værdier array
    double[] Red = {0, 10, 15, 20, 15, 10, 0, 0, -10, 100, -30, 0, 0, 5, 10, 20, 25, 30, 20, 10, 5, 0};

    public void ekgSimulation() {
        if (u < 21) {
            u++;
        } else {
            u = 0;
        }
        red = (int) Red[u];
    }

    public int redv() {
        return red;
    }

    public void EKGSim(TextField CPRLabel, LineChart ekgplot, XYChart.Series<String, Number> data){
        name = CPRLabel.getText();
        if (threadCheck = false){{
        return;}
        }
        if (cprCheck2(name)) {
            sql_objekt.createNewPatient(name);
            threadCheck=false;
            SQL sql_objekt = new SQL();
            Eventhandler = Executors.newSingleThreadScheduledExecutor();

            Eventhandler.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                                ekgplot.getData().clear();
                                ekgSimulation();
                                String n = String.valueOf(y);
                                int redval = redv();
                                data.getData().add((new XYChart.Data<>(n, redval)));
                                ekgplot.getData().add(data);
                                alarmCheck("EKG ER FARLIG", ekgMaxDouble, ekgMinDouble, redval, y);
                                sql_objekt.writeToPatientMaalingEKG(name, redval);

                                y++;
                            }
                    ), 0, 100, TimeUnit.MILLISECONDS);
        } else {
            error("Invalid input, ugyldigt Cpr");
        }
    }


}
