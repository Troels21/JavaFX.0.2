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
    Beregner b = new Beregner();

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
            getText();
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
                b.error("Max has to be greater than Min");
            }
        } catch (NumberFormatException e) {
            b.error("Invalid Number, Write a real number comma with . ");
        }
    }


    public void setText() {
        pulseMax.setText(String.valueOf(b.pulseMaxDouble));
        pulseMin.setText(String.valueOf(b.pulseMinDouble));
        tempMax.setText(String.valueOf(b.tempMaxDouble));
        tempMin.setText(String.valueOf(b.tempMinDouble));
        SpO2Max.setText(String.valueOf(b.SpO2MaxDouble));
        SpO2Min.setText(String.valueOf(b.SpO2MinDouble));
        ekgMax.setText(String.valueOf(b.ekgMaxDouble));
        ekgMin.setText(String.valueOf(b.ekgMinDouble));
    }

    public void getText(){
        b.pulseMaxDouble = Double.parseDouble(pulseMax.getText());
        b.pulseMinDouble = Double.parseDouble(pulseMin.getText());
        b.tempMaxDouble = Double.parseDouble(tempMax.getText());
        b.tempMinDouble = Double.parseDouble(tempMin.getText());
        b.SpO2MaxDouble = Double.parseDouble(SpO2Max.getText());
        b.SpO2MinDouble = Double.parseDouble(SpO2Min.getText());
        b.ekgMaxDouble = Double.parseDouble(ekgMax.getText());
        b.ekgMinDouble = Double.parseDouble(ekgMin.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setText();
    }
}
