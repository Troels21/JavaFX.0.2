package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class EKGcontroller extends Beregner implements Initializable {
    ControllerProgramChooser cpc=new ControllerProgramChooser();
    static String navn;
    double tjek;
    int y = 0;

    @FXML
    LineChart<String, Number> ekgplot;
    @FXML
    TextField CPRLabel;
    //Dataserie
    XYChart.Series<String, Number> data = new XYChart.Series<String, Number>();

    public void startEKG() {
        y = 0;
        navn = CPRLabel.getText();
        try {
            tjek = Double.parseDouble(navn);
            if (navn.length() == 10) {
                name = navn;
                FileHandler FL = new FileHandler(navn);
                Eventhandler.scheduleAtFixedRate(() ->
                        Platform.runLater(() -> {
                                    ekgplot.getData().clear();
                                    ekgSimulation();
                                    String n = String.valueOf(y);
                                    int redval = redv();
                                    data.getData().add((new XYChart.Data<String, Number>(n, redval)));
                                    ekgplot.getData().add(data);
                                    alarmCheck("EKG ER FARLIG", ekgMaxDouble, ekgMinDouble, redval);
                                    try {
                                        FL.saveData("EKG", n, redval);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    y++;
                                }
                        ), 0, 100, TimeUnit.MILLISECONDS);
            } else {
                error("Invalid input, Cpr skal være 10 cifre");
            }

        } catch (Exception e) {
            error("Invalid input, CPR skal være tal");
        }
    }


    public void EKGstop() {
        Eventhandler.shutdown();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CPRLabel.setText(name);
    } //Sætter CPR navn


}