package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();
    }
    public void openStage(String filename) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(filename));
        stage.setScene(new Scene(parent, 650, 400));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
