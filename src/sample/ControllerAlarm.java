package sample;


import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerAlarm extends Simulering implements Initializable {
    Main m = new Main();

    //Finder textfields i fxml
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
            error("Values updated");
        }
    }

    public void check(TextField text, TextField text2) {
        try {
            double max = Double.parseDouble(text.getText());
            double min = Double.parseDouble(text2.getText());
            if (max > min) {
                a++;
            } else {
                error("Max has to be greater than Min");
            }
        } catch (NumberFormatException e) {
            error("Invalid Number, Write a real number comma with . ");
        }
    }


    public void setText() {
        pulseMax.setText(String.valueOf(pulseMaxDouble));
        pulseMin.setText(String.valueOf(pulseMinDouble));
        tempMax.setText(String.valueOf(tempMaxDouble));
        tempMin.setText(String.valueOf(tempMinDouble));
        SpO2Max.setText(String.valueOf(SpO2MaxDouble));
        SpO2Min.setText(String.valueOf(SpO2MinDouble));
        ekgMax.setText(String.valueOf(ekgMaxDouble));
        ekgMin.setText(String.valueOf(ekgMinDouble));
    }

    public void getText() {
        pulseMaxDouble = Double.parseDouble(pulseMax.getText());
        pulseMinDouble = Double.parseDouble(pulseMin.getText());
        tempMaxDouble = Double.parseDouble(tempMax.getText());
        tempMinDouble = Double.parseDouble(tempMin.getText());
        SpO2MaxDouble = Double.parseDouble(SpO2Max.getText());
        SpO2MinDouble = Double.parseDouble(SpO2Min.getText());
        ekgMaxDouble = Double.parseDouble(ekgMax.getText());
        ekgMinDouble = Double.parseDouble(ekgMin.getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setText();
    }

    public void closeScene() {
        m.closeStage(Main.stage2);
    }
}
