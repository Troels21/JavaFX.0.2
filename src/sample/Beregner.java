package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Beregner{

    double nametest;
    double math, math2, math3;
    int puls, temp, red, u, SpO2int;
    String Spo2;
    double intervalmin = 70;
    double intervalmax = 70;
    double intervalmin2 = 98;
    double intervalmax2 = 100;
    double intervalmin3 = 36;
    double intervalmax3 = 40;


    static String name;
    int pulseCheck, tempCheck, i;
    String SpO2String;

    ScheduledExecutorService pulsEventhandler = Executors.newSingleThreadScheduledExecutor();
    XYChart.Series pulsSeries = new XYChart.Series();
    XYChart.Series temperatureSeries = new XYChart.Series();


    public void monitorStartPuls(TextField textField, LineChart<CategoryAxis, NumberAxis> linechart, Label label) throws IOException {
        this.name = textField.getText();
        try {
            nametest = Double.parseDouble(name);
            FileHandler fh = new FileHandler(name);
            pulsSeries.setName("puls");
            temperatureSeries.setName("Temperature");
            linechart.getData().addAll(pulsSeries, temperatureSeries);
            pulsEventhandler.scheduleAtFixedRate(() ->
                    Platform.runLater(() -> {
                        String bogstav = String.valueOf(i);
                        SpO2Simulation();
                        SpO2String += bogstav + " " + Spo2 + " ";
                        pulseSimulation();
                        temperatureSimulation();
                        if (tempCheck == 1)
                            temperatureSeries.getData().add(new XYChart.Data(bogstav, temp));

                        try {
                            fh.saveData("Temp", bogstav, temp);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (pulseCheck == 1)
                            pulsSeries.getData().add(new XYChart.Data(bogstav, puls));

                        try {
                            fh.saveData("Pulse", bogstav, puls);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if (i % 5 == 0) {
                            label.setText(Spo2);

                            try {
                                fh.saveData("SpO2", bogstav, SpO2int);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                        i++;
                    }), 0, 1, TimeUnit.SECONDS);
        } catch (Exception e) {
            Label alertLabel = new Label();
            StackPane allertLayout = new StackPane();
            Stage allertStage = new Stage();
            Button allertButton = new Button();

            allertButton.setText("OK");
            alertLabel.setText("Invalid Name");
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

    public void monitorStopPuls() throws IOException {
        pulsEventhandler.shutdown();
    }

    public void refreshPuls(LineChart<CategoryAxis, NumberAxis> lineChart, Label label) {
        lineChart.getData().clear();
        pulsSeries.getData().clear();
        temperatureSeries.getData().clear();
        label.setText("00%");
    }

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

    public void pulseSimulation() {
        math = Math.random() * (intervalmax - intervalmin) + intervalmin;
        puls = (int) math;
        intervalmax = math + 5;
        intervalmin = math - 5;
    }

    public void SpO2Simulation() {
        math2 = Math.random() * (intervalmax2 - intervalmin2) + intervalmin2;
        SpO2int = (int) math2;
        Spo2 = String.valueOf(SpO2int + "%");
        intervalmax2 = math2 + 0.05;
        intervalmin2 = math2 - 0.05;
    }

    public void temperatureSimulation() {
        math3 = Math.random() * (intervalmax3 - intervalmin3) + intervalmin3;
        temp = (int) math3;
        intervalmax3 = math3 + 0.05;
        intervalmin3 = math3 - 0.05;
    }

    double Red[] = {0, 10, 15, 20, 15, 10, 0, 0, -10, 100, -30, 0, 0, 5, 10, 20, 25, 30, 20, 10, 5, 0/*1694,1566,1550,1526,1519,1569,1597,1600,1624,1667,1711,1758,
            1789,1831,1883,1898,1930,1964,2003,2045,2069,2038,1791,1607,1527,
            1492,1471,1482,1545,1564,1569,1604,1634,1699,1743,1804,1832,1873,
            1919,1948,1999,2023,2057,2096,2127,2063,1822,1638,1558,1520,1489,
            1492,1558,1574,1592,1615,1670,1730,1773,1834,1857,1892,1925,1955,
            2014,2031,2082,2113,2148,2176,1999,1787,1667,1624,1586,1589,1629,
            1656,1678,1691,1719,1755,1802,1852,1882,1925,1958,1991,2025,2051,
            2106,2143,2124,1921,1711,1605,1571,1563,1581,1637,1657,1670,1705,
            1745,1797,1829,1858,1892,1916,1951,1968,1990,2019,2041,2073,1993,
            1763,1552,1472,1433,1402,1429,1485,1514,1525,1572,1621,1663,1734,
            1770,1818,1852,1886,1949,1979,2024,2056,2107,2067,1837,1647,1551,
            1520,1488,1513,1578,1577,1615,1640,1679,1732,1781,1832,1870,1912,
            1956,1995,2025,2050,2102,2136,2066,1832,1690,1663,1632,1654,1686,
            1715,1724,1731,1789,1822,1868,1905,1943,1998,2030,2073,2103,2137,
            2178,2203,2182,1990,1832,1766,1750,1736,1725,1788,1799,1797,1829,
            1865,1922,1955,1999,2030,2056*/};

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
}
