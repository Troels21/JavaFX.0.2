package sample;

import javafx.application.Platform;

import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Beregner {

    //Variabler til puls, temperatur,ekg simulation og fremvisning
    double nametest, math, math2, math3, temp, SpO2double;
    int puls, red, u, pulseCheck, tempCheck, i;
    String Spo2, SpO2String;
    double intervalmin = 70;
    double intervalmax = 70;
    double intervalmin2 = 98;
    double intervalmax2 = 100;
    double intervalmin3 = 36;
    double intervalmax3 = 38;

    //værdier brugt til Alarmgrænser
    static double pulseMaxDouble = 220;
    static double pulseMinDouble = 0;
    static double tempMaxDouble = 49;
    static double tempMinDouble = 35;
    static double SpO2MaxDouble = 100;
    static double SpO2MinDouble = 95;
    static double ekgMaxDouble = 150;
    static double ekgMinDouble = -50;

    //String brugt til holde CPR gemt, mellem stages.
    static String name;

    //Objekter brugt til at fremvise linechart serie i realtid
    ScheduledExecutorService Eventhandler = Executors.newSingleThreadScheduledExecutor();
    XYChart.Series pulsSeries = new XYChart.Series();
    XYChart.Series temperatureSeries = new XYChart.Series();

    //metode til at fremvise puls,temp og spo2
    public void monitorStartPuls(TextField textField, LineChart<CategoryAxis, NumberAxis> linechart,
                                 Label label, Label label2) throws IOException {
        this.name = textField.getText();
        try {
            nametest = Double.parseDouble(name);
            if (name.length() == 10) {
                FileHandler fh = new FileHandler(name);
                pulsSeries.setName("puls");
                temperatureSeries.setName("Temperature");
                linechart.getData().addAll(pulsSeries, temperatureSeries);
                Eventhandler.scheduleAtFixedRate(() ->
                        Platform.runLater(() -> {
                            String bogstav = String.valueOf(i);
                            SpO2Simulation();
                            SpO2String += bogstav + " " + Spo2 + " ";
                            pulseSimulation();
                            temperatureSimulation();
                            if (tempCheck == 1) {
                                label2.setText((temp + "°C"));
                                temperatureSeries.getData().add(new XYChart.Data(bogstav, temp));
                                alarmCheck("ALARM TEMPERATUR ER FARLIG", tempMaxDouble, tempMinDouble, temp);
                            }
                            try {
                                fh.saveDataDouble("Temp", bogstav, temp);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (pulseCheck == 1) {
                                pulsSeries.getData().add(new XYChart.Data(bogstav, puls));
                                alarmCheck("ALARM PULS ER FARLIG", pulseMaxDouble, pulseMinDouble, puls);
                            }
                            try {
                                fh.saveData("Pulse", bogstav, puls);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            if (i % 2 == 0) {
                                label.setText(Spo2);
                                alarmCheck("SPO2 ER FARLIG", SpO2MaxDouble, SpO2MinDouble, SpO2double);

                                try {
                                    fh.saveDataDouble("SpO2", bogstav, SpO2double);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            i++;
                        }), 0, 1, TimeUnit.SECONDS);
            } else {
                error("Invalid input, Cpr skal være 10 cifre");
            }

        } catch (Exception e) {
            error("Invalid input, CPR skal være tal");
        }
    }

    //Metode til at stoppe fremvisning i realtid af puls, temp og spo2
    public void monitorStopPuls() throws IOException {
        Eventhandler.shutdown();
    }

    //Kontrol logic in radiobutton pulse
    public int showPulsePuls() {
        if (pulseCheck == 1) {
            pulseCheck = 0;
            return pulseCheck;
        }
        if (pulseCheck == 0) {
            pulseCheck = 1;
            return pulseCheck;
        }
        return 0;
    }

    //Kontrol logic in radiobutton temp
    public int showTemperaturePuls() {
        if (tempCheck == 1) {
            tempCheck = 0;
            return tempCheck;
        }
        if (tempCheck == 0) {
            tempCheck = 1;
            return tempCheck;
        }
        return 0;
    }

    //metode til at kontrollere alarm
    public void alarmCheck(String string, double alarmMax, double alarmMin, double value) {
        if (value < alarmMin || value > alarmMax) {
            error(string);
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
    double Red[] = {0, 10, 15, 20, 15, 10, 0, 0, -10, 100, -30, 0, 0, 5, 10, 20, 25, 30, 20, 10, 5, 0};

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
}
