package sample;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAlarm implements Initializable {
    Main m = new Main();

    public TextField pulseMax;
    public TextField pulseMin;
    public TextField tempMax;
    public TextField tempMin;
    public TextField SpO2Max;
    public TextField SpO2Min;
    public TextField ekgMax;
    public TextField ekgMin;
    private int a = 0;

    public void updateAlarm() {
        a = 0;
        check(pulseMax, pulseMin);
        check(tempMax, tempMin);
        check(SpO2Max, SpO2Min);
        check(ekgMax, ekgMin);

        if (a == 4) {
            m.pulseMaxDouble = Double.parseDouble(pulseMax.getText());
            m.pulseMinDouble = Double.parseDouble(pulseMin.getText());
            m.tempMaxDouble = Double.parseDouble(tempMax.getText());
            m.tempMinDouble = Double.parseDouble(tempMin.getText());
            m.SpO2MaxDouble = Double.parseDouble(SpO2Max.getText());
            m.SpO2MinDouble = Double.parseDouble(SpO2Min.getText());
            m.ekgMaxDouble = Double.parseDouble(ekgMax.getText());
            m.ekgMinDouble = Double.parseDouble(ekgMin.getText());

            System.out.println(m.pulseMaxDouble);
            System.out.println(m.pulseMinDouble);
            System.out.println(m.tempMaxDouble);
            System.out.println(m.tempMinDouble);
            System.out.println(m.SpO2MaxDouble);
            System.out.println(m.SpO2MinDouble);
            System.out.println(m.ekgMaxDouble);
            System.out.println(m.ekgMinDouble);

            setText();
        }
    }

    public void check(TextField text, TextField text2) {
        try {
            double max = Double.parseDouble(text.getText());
            double min = Double.parseDouble(text2.getText());
            if (max > min) {
                a++;
            } else {
                Label alertLabel = new Label();
                StackPane allertLayout = new StackPane();
                Stage allertStage = new Stage();
                Button allertButton = new Button();

                allertButton.setText("OK");
                alertLabel.setText("Max has to be greater than Min");
                allertStage.setTitle("Alert");

                allertButton.setOnAction(p -> allertStage.close());
                allertLayout.getChildren().addAll(allertButton, alertLabel);
                Scene allertScene = new Scene(allertLayout, 200, 100);
                alertLabel.setTranslateY(-25);

                allertStage.setScene(allertScene);
                allertStage.initModality(Modality.APPLICATION_MODAL);
                allertStage.show();
            }
        } catch (NumberFormatException e) {
            Label alertLabel = new Label();
            StackPane allertLayout = new StackPane();
            Stage allertStage = new Stage();
            Button allertButton = new Button();

            allertButton.setText("OK");
            alertLabel.setText("Invalid Number, Write a real number comma with . ");
            allertStage.setTitle("Alert");

            allertButton.setOnAction(p -> allertStage.close());
            allertLayout.getChildren().addAll(allertButton, alertLabel);
            Scene allertScene = new Scene(allertLayout, 200, 100);
            alertLabel.setTranslateY(-25);

            allertStage.setScene(allertScene);
            allertStage.initModality(Modality.APPLICATION_MODAL);
            allertStage.show();
        }
    }


    public void setText() {
        pulseMax.setText(String.valueOf(m.pulseMaxDouble));
        pulseMin.setText(String.valueOf(m.pulseMinDouble));
        tempMax.setText(String.valueOf(m.tempMaxDouble));
        tempMin.setText(String.valueOf(m.tempMinDouble));
        SpO2Max.setText(String.valueOf(m.SpO2MaxDouble));
        SpO2Min.setText(String.valueOf(m.SpO2MinDouble));
        ekgMax.setText(String.valueOf(m.ekgMaxDouble));
        ekgMin.setText(String.valueOf(m.ekgMinDouble));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setText();
    }
}
