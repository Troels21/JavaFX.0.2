package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{
    Main m = new Main();

    public void scene1Clicked() throws IOException {
        Parent Scene1 = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
        m.stage.setScene(new Scene(Scene1, 600, 400));
        m.stage.show();
    }
}
