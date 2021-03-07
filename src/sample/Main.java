package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    Stage stage = new Stage();
    double pulseMaxDouble = 220;
    double pulseMinDouble = 0;
    double tempMaxDouble = 40;
    double tempMinDouble = 35;
    double SpO2MaxDouble = 100;
    double SpO2MinDouble = 95;
    double ekgMaxDouble = 1337;
    double ekgMinDouble = 420;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 650, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
