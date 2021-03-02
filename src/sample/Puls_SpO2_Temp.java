package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Puls_SpO2_Temp {
    Main m = new Main();
    Beregner b = new Beregner();
    ScheduledExecutorService pulsEventhandler = Executors.newSingleThreadScheduledExecutor();
    XYChart.Series pulsSeries = new XYChart.Series();
    XYChart.Series temperatureSeries = new XYChart.Series();
    String name;
    int pulseCheck,tempCheck,i;

    @FXML
        LineChart<CategoryAxis, NumberAxis> Diagram;
    @FXML
        TextField Name;
    @FXML
        Label Spo2Label;



    public void monitorStart() throws IOException {
        pulsSeries.setName("puls");
        temperatureSeries.setName("Temperature");
        Diagram.getData().addAll(pulsSeries,temperatureSeries);
        pulsEventhandler.scheduleAtFixedRate(() ->
                Platform.runLater(() -> {
                    String bogstav = String.valueOf(i);
                    b.SpO2Simulation();
                    b.pulseSimulation();
                    b.temperatureSimulation();
                    if (tempCheck==1)
                        temperatureSeries.getData().add(new XYChart.Data(bogstav, b.temp));
                    if (pulseCheck==1)
                        pulsSeries.getData().add(new XYChart.Data(bogstav, b.puls));
                    if (i%5==0)
                    Spo2Label.setText(b.Spo2);
                    i++;
                }), 0, 1, TimeUnit.SECONDS);
    }

    public void monitorStop() {
        pulsEventhandler.shutdown();
    }

    public void saveData() {
        //String pulseString=pulsSeries.getData().toString();
        //String tempString=temperatureSeries.getData().toString();
        String name =Name.getText();
        if (name.equals("") || name.equals("Set CPR")){
            Label alertLabel = new Label();
            StackPane allertLayout= new StackPane();
            Stage allertStage = new Stage();
            Button allertButton = new Button();

            allertButton.setText("OK");
            alertLabel.setText("Invalid Name");
            allertStage.setTitle("Alert");

            allertButton.setOnAction(e ->allertStage.close());
            allertLayout.getChildren().addAll(allertButton,alertLabel);
            Scene allertScene = new Scene(allertLayout, 200,100);
            alertLabel.setTranslateY(-25);

            allertStage.setScene(allertScene);
            allertStage.initModality(Modality.APPLICATION_MODAL);
            allertStage.show();
        }

        //filewriter.print(name2+"  Pulse: "+pulsString+"  Temp: "+tempString);
    }

    public String getName() {
        if (name !=null){
        name=Name.getText();
        return name;}
        else{
            name="fejl";
            return name;
        }
    }

    public void refresh() {
        Diagram.getData().clear();
        pulsSeries.getData().clear();
        temperatureSeries.getData().clear();
        Spo2Label.setText("00%");
    }

    public int showPulse(){
    if (pulseCheck==1){
        pulseCheck=0;
        return pulseCheck;}
    if (pulseCheck==0){
        pulseCheck=1;
        return pulseCheck;}
        return 0;
    }
    public int showTemperature(){
        if (tempCheck==1){
            tempCheck=0;
            return tempCheck;}
        if (tempCheck==0){
            tempCheck=1;
            return tempCheck;}
        return 0;
    }
}
