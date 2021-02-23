package sample;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ControllerScene1 {
    Main m = new Main();
    Beregner b = new Beregner();
    int counter;
    ScheduledExecutorService andreas = Executors.newSingleThreadScheduledExecutor();

    public void monitorStart() throws IOException {
        b.switchpulse =true;
        XYChart.Series pulsSerie = new XYChart.Series();
        pulsSerie.setName("puls");


        andreas.scheduleAtFixedRate(()->
                Platform.runLater(()->{
                    String bogstav = String.valueOf(b.i);
                    b.pulsSimulering();
                    pulsSerie.getData().add(new XYChart.Data(bogstav, b.puls));
                }),0,1,TimeUnit.SECONDS);
        /*
        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time/s");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Value");
        yAxis.setAnimated(false);

        final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Realtime JavaFX Charts");
        lineChart.setAnimated(false);
        lineChart.setTitle("Stock Monitoring, 2010");

        XYChart.Series pulsSerie = new XYChart.Series();
        pulsSerie.setName("puls");

        Scene testScene = new Scene(lineChart,600,600);*/
        lineChart.getData().add(pulsSerie);
        Stage stage2 = new Stage();
        stage2.setTitle("hello");
        stage2.setScene(testScene);
        stage2.show();

        ScheduledExecutorService andreas = Executors.newSingleThreadScheduledExecutor();

        andreas.scheduleAtFixedRate(()->
            Platform.runLater(()->{
                String bogstav = String.valueOf(b.i);
                b.pulsSimulering();
                pulsSerie.getData().add(new XYChart.Data(bogstav, b.puls));
            }),0,1,TimeUnit.SECONDS);
    }

    public void saveData(){

    }

    public void monitorStop(){
        b.stopPulse();
    }

    public void setName(){
        System.out.println("det virker");
    }
}
