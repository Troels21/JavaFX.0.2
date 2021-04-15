package sample;

import javafx.event.ActionEvent;

import java.io.IOException;

public class ControllerProgramChooser {
    Main m = new Main();
    //alle metoder laver ny stage med FXML som scene
    public void pulsTempClicked() throws IOException {
        m.openStage("Puls_SpO2_Temp.fxml", Main.stage2);
    }

    public void arkivClicked() throws IOException {
        m.openStage("Arkiv.fxml", Main.stage2);
    }

    public void ekgClicked() throws IOException {
        m.openStage("EKG.FXML", Main.stage2);
    }

    public void alarmClicked() throws IOException {
       m.openStage("Alarm.fxml", Main.stage2);
    }

    public void closeScene(ActionEvent actionEvent) {
        m.closeStage(Main.stage);
    }
}
