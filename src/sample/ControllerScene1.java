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

public class ControllerScene1 {
    Main m = new Main();
    Beregner b = new Beregner();
    ScheduledExecutorService pulsEventhandler = Executors.newSingleThreadScheduledExecutor();
    XYChart.Series pulsSerie = new XYChart.Series();
    String name;

    @FXML
        LineChart<CategoryAxis, NumberAxis> Diagram;
    @FXML
        TextField Name;


    public void monitorStart() throws IOException {
        pulsSerie.setName("puls");
        Diagram.getData().add(pulsSerie);
        pulsEventhandler.scheduleAtFixedRate(() ->
                Platform.runLater(() -> {
                    String bogstav = String.valueOf(b.i);
                    b.pulsSimulering();
                    pulsSerie.getData().add(new XYChart.Data(bogstav, b.puls));
                }), 0, 1, TimeUnit.SECONDS);
    }

    public void monitorStop() {
        pulsEventhandler.shutdown();
    }

    public void saveData() {
        String name2 =Name.getText();
        System.out.println(name2);

        if (name2.equals("") || name2.equals("Set Name")){
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

    }

    public void setName() {
        name="";
        //et eller andet med arkiv navn, skal man kunne sætte her.
    }

    public void refresh() {
        Diagram.getData().clear();
        pulsSerie.getData().clear();

    }
}
