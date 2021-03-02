package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ControllerProgramChooser {
    Main m = new Main();

    public void scene1Clicked() throws IOException {
        Parent Puls_SpO2_Temp = FXMLLoader.load(getClass().getResource("Puls_SpO2_Temp.fxml"));
        m.stage.setScene(new Scene(Puls_SpO2_Temp, 650, 400));
        m.stage.show();
    }

    public void Arkiv() throws IOException {
        Parent ControllerArkiv = FXMLLoader.load(getClass().getResource("Arkiv.fxml"));
        m.stage.setScene(new Scene(ControllerArkiv, 650, 400));
        m.stage.show();
    }
}
