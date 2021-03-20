package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ControllerProgramChooser {
    Main m = new Main();
    //alle metoder laver ny stage med FXML som scene
    public void pulsTempClicked() throws IOException {
        openStage("Puls_SpO2_Temp.fxml");
    }

    public void arkivClicked() throws IOException {
        openStage("Arkiv.fxml");
    }

    public void ekgClicked() throws IOException {
        openStage("EKG.½FXML");
    }

    public void alarmClicked() throws IOException {
       openStage("Alarm.fxml");
    }
    public void openStage(String filename) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(filename));
        m.stage.setScene(new Scene(parent, 650, 400));
        m.stage.show();
    }
}
