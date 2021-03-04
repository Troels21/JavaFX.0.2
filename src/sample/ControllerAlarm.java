package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerAlarm {
    double pulseMaxDouble;
    double pulseMinDouble;
    double tempMaxDouble;
    double tempMinDouble;
    double SpO2MaxDouble;
    double SpO2MinDouble;
    double ekgMaxDouble;
    double ekgMinDouble;

    public TextField pulseMax;
    public TextField pulseMin;
    public TextField tempMax;
    public TextField tempMin;
    public TextField SpO2Max;
    public TextField SpO2Min;
    public TextField ekgMax;
    public TextField ekgMin;
    private int a=0;

    public void updateAlarm() {
        a=0;
        check(pulseMax,pulseMin);
        check(tempMax,tempMin);
        check(SpO2Max,SpO2Min);
        check(ekgMax,ekgMin);

        if (a==4) {
            pulseMaxDouble = Double.parseDouble(pulseMax.getText());
            pulseMinDouble = Double.parseDouble(pulseMin.getText());
            tempMaxDouble = Double.parseDouble(tempMax.getText());
            tempMinDouble = Double.parseDouble(tempMin.getText());
            SpO2MaxDouble = Double.parseDouble(SpO2Max.getText());
            SpO2MinDouble = Double.parseDouble(SpO2Min.getText());
            ekgMaxDouble = Double.parseDouble(ekgMax.getText());
            ekgMinDouble = Double.parseDouble(ekgMin.getText());

            System.out.println(pulseMaxDouble);
            System.out.println(pulseMinDouble);
            System.out.println(tempMaxDouble);
            System.out.println(tempMinDouble);
            System.out.println(SpO2MaxDouble);
            System.out.println(SpO2MinDouble);
            System.out.println(ekgMaxDouble);
            System.out.println(ekgMinDouble);
        }
    }

    public void check(TextField text,TextField text2) {
            try {
                double max = Double.parseDouble(text.getText());
                double min= Double.parseDouble(text2.getText());
                if (max>min){
                    a++;
                }
                else{
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
    }
