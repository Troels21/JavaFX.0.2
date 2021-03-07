package sample;

import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class EKGcontroller {

    @FXML
    LineChart<String, Number> ekgplot;
    Beregner bb = new Beregner();
    ScheduledExecutorService tid = Executors.newSingleThreadScheduledExecutor();
    XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();

    public void startEKG() {
        tid.scheduleAtFixedRate(() ->
                Platform.runLater(() -> {
                            bb.ekgSimulation();
                            String n = String.valueOf(bb.u);
                            int redval = bb.redv();
                            data.getData().add((new XYChart.Data<String, Number>(n, redval)));
                            ekgplot.getData().add(data);
                        }
                ), 0, 100, TimeUnit.MILLISECONDS);

    }

    public void stop() {
        tid.shutdown();
    }

}