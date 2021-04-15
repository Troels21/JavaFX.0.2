package sample;

import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EKGcontroller extends Simulering implements Initializable {


    @FXML
    LineChart<String, Number> ekgplot;
    @FXML
    TextField CPRLabel;

    public void startEKG() throws SQLException {
        EKGSim(CPRLabel, ekgplot, ekgseries);
    }

    public void EKGstop() throws IOException {
        eventhandlerShutdown();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CPRLabel.setText(name);
    } //Sætter CPR navn

    public void closeScene(ActionEvent actionEvent) throws IOException {
        eventhandlerShutdown();
        m.closeStage(Main.stage2);
    }
}