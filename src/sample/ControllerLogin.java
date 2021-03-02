package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class ControllerLogin {
    Main m = new Main();

    public void login() throws IOException {
        Parent ProgramChooser = FXMLLoader.load(getClass().getResource("ProgramChooser.fxml"));
        m.stage.setScene(new Scene(ProgramChooser, 650, 400));
        m.stage.show();
    }
}
