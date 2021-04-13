package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class EKGcontroller extends Beregner implements Initializable {
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
                SQL sql_objekt = new SQL();
                sql_objekt.createNewPatient(navn);

                Eventhandler.scheduleAtFixedRate(() ->
                        Platform.runLater(() -> {
                                    ekgplot.getData().clear();
                                    ekgSimulation();
                                    String n = String.valueOf(y);
                                    int redval = redv();
                                    data.getData().add((new XYChart.Data<String, Number>(n, redval)));
                                    ekgplot.getData().add(data);
                                    alarmCheck("EKG ER FARLIG", ekgMaxDouble, ekgMinDouble, redval,y);
                            // FL.saveData("EKG", n, redval);
                            sql_objekt.writeToPatientMaalingEKG(navn, redval);

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

    public void closeScene(ActionEvent actionEvent) {
        if (Eventhandler.isShutdown()==false){
            EKGstop();
        }
        m.closeStage(m.stage2);
    }
}