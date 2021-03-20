package sample;

import java.io.IOException;

public class ControllerProgramChooser {
    Main m = new Main();
    //alle metoder laver ny stage med FXML som scene
    public void pulsTempClicked() throws IOException {
        m.openStage("Puls_SpO2_Temp.fxml");
    }

    public void arkivClicked() throws IOException {
        m.openStage("Arkiv.fxml");
    }

    public void ekgClicked() throws IOException {
        m.openStage("EKG.½FXML");
    }

    public void alarmClicked() throws IOException {
       m.openStage("Alarm.fxml");
    }
}
