package sample;
import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class EKGcontroller extends Beregner implements Initializable {
    public TextField CPRLabel;
    int y=0;

    @FXML LineChart<String,Number> ekgplot;
    Beregner bb = new Beregner();
    ScheduledExecutorService tid = Executors.newSingleThreadScheduledExecutor();
    XYChart.Series<String,Number> data = new XYChart.Series<String, Number>();

    public void startEKG(){
        y=0;
        tid.scheduleAtFixedRate(()->
        Platform.runLater(() ->{
            ekgplot.getData().clear();
            bb.ekgSimulation();
            String n= String.valueOf(y);
            int redval=bb.redv();
            data.getData().add((new XYChart.Data<String, Number>(n,redval)));
            ekgplot.getData().add(data);

            y++;
        }
            ),0,100,TimeUnit.MILLISECONDS);

        }
    public void stop() {
        tid.shutdown();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CPRLabel.setText(name);
    }
}