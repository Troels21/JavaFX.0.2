package sample;

import javafx.fxml.*;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EKGcontroller extends Simulering implements Initializable {


    @FXML
    LineChart<String, Number> ekgplot;
    @FXML
    TextField CPRLabel;

    public void startEKG() {
        EKGSim(CPRLabel, ekgplot, ekgseries);
    }

    public void EKGstop() {
        eventhandlerShutdown();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CPRLabel.setText(name);
    } //Sætter CPR navn

    public void closeScene() throws IOException {
        eventhandlerShutdown();
        m.closeStage(Main.stage2);
    }
}