package sample;

import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;

public class ControllerArkiv {
    @FXML
    TextField CPR;

    @FXML
    LineChart<NumberAxis, NumberAxis> Pulse;
    @FXML
    LineChart<NumberAxis, NumberAxis> Temp;
    @FXML
    LineChart<NumberAxis, NumberAxis> SpO2;
    @FXML
    LineChart<NumberAxis, NumberAxis> EKG;

    XYChart.Series PulseLineChart = new XYChart.Series();
    XYChart.Series TempLineChart = new XYChart.Series();
    XYChart.Series SpO2LineChart = new XYChart.Series();
    XYChart.Series EKGLineChart = new XYChart.Series();



    int[] PulseTime, PulseValue, TempTime, TempValue, SpO2Time, SpO2Value, EKGTime, EKGValue ;

    public void PatientChooser() throws FileNotFoundException {
        File checker = new File("PatientData", CPR.getText());

        if (checker.exists() && CPR.getText().length() > 0) {
            Label alertLabel = new Label();
            StackPane allertLayout = new StackPane();
            Stage allertStage = new Stage();
            Button allertButton = new Button();

            allertButton.setText("OK");
            alertLabel.setText("CPR-nummer er godkendt");
            allertStage.setTitle("Godkendt");

            allertButton.setOnAction(e -> allertStage.close());
            allertLayout.getChildren().addAll(allertButton, alertLabel);
            Scene allertScene = new Scene(allertLayout, 200, 100);
            alertLabel.setTranslateY(-25);

            allertStage.setScene(allertScene);
            allertStage.initModality(Modality.APPLICATION_MODAL);
            allertStage.show();

        } else {
            Label alertLabel = new Label();
            StackPane allertLayout = new StackPane();
            Stage allertStage = new Stage();
            Button allertButton = new Button();

            allertButton.setText("OK");
            alertLabel.setText("Ugyldigt CPR-nummer");
            allertStage.setTitle("Fejl");

            allertButton.setOnAction(e -> allertStage.close());
            allertLayout.getChildren().addAll(allertButton, alertLabel);
            Scene allertScene = new Scene(allertLayout, 200, 100);
            alertLabel.setTranslateY(-25);

            allertStage.setScene(allertScene);
            allertStage.initModality(Modality.APPLICATION_MODAL);
            allertStage.show();
        }
    }

    public void PulsArkiv() throws FileNotFoundException {
        String FileName = CPR.getText();
        File Pulse1 = new File("PatientData/"+FileName+"/Pulse"); //mac :FileName, "Pulse"
        Scanner Patient = new Scanner(Pulse1);
        String PulseData = Patient.nextLine();

        String RåPuls = PulseData.replaceAll("[^0-9,]", "");
        String[] Pulse = RåPuls.split(",");

        PulseTime = new int[Pulse.length / 2];
        if (Pulse.length > 1) {
            for (int i = 0; i < Pulse.length; i = i + 2) {
                PulseTime[i / 2] = Integer.parseInt(Pulse[i]);
            }
        }
        PulseValue = new int[Pulse.length / 2];
        if (Pulse.length > 1) {
            for (int i = 1; i < Pulse.length; i = i + 2) {
                PulseValue[i / 2] = Integer.parseInt(Pulse[i]);
            }
        }
        final NumberAxis Xakse = new NumberAxis();
        final NumberAxis Yakse = new NumberAxis();
        LineChart<Number,Number> PulseDiagram = new LineChart<>(Xakse, Yakse);
        for (int a = 0; a<PulseTime.length; a++){
            PulseLineChart.getData().add(new XYChart.Data(PulseTime[a],PulseValue[a]));
        }
        PulseDiagram.getData().add(PulseLineChart);
    }

    public void TempArkiv() throws FileNotFoundException {
        String FileName = CPR.getText();
        File Temp1 = new File("PatientData/"+FileName+"/Temp"); // Mac:FileName, "Temp"
        Scanner Patient = new Scanner(Temp1);
        String TempData = Patient.nextLine();

        String RåTemp = TempData.replaceAll("[^0-9,]", "");
        String[] Temp = RåTemp.split(",");

        TempTime = new int[Temp.length / 2];
        if (Temp.length > 1) {
            for (int i = 0; i < Temp.length; i = i + 2) {
                TempTime[i / 2] = Integer.parseInt(Temp[i]);
            }
        }
        TempValue = new int[Temp.length / 2];
        if (Temp.length > 1) {
            for (int i = 1; i < Temp.length; i = i + 2) {
                TempValue[i / 2] = Integer.parseInt(Temp[i]);
            }
        }
        final NumberAxis Xakse = new NumberAxis();
        final NumberAxis Yakse = new NumberAxis();
        LineChart<Number,Number> TempDiagram = new LineChart<>(Xakse, Yakse);
        for (int a = 0; a<TempTime.length; a++){
            TempLineChart.getData().add(new XYChart.Data(TempTime[a],TempValue[a]));
        }
        TempDiagram.getData().add(TempLineChart);
    }

    public void SpO2Arkiv() throws FileNotFoundException {
        String FileName = CPR.getText();
        File SpO21 = new File("PatientData/"+FileName+"/SpO2"); //FileName, "SpO2"
        Scanner Patient = new Scanner(SpO21);
        String SpO2Data = Patient.nextLine();

        String RåSpO2 = SpO2Data.replaceAll("[^0-9,]", "");
        String[] SpO2 = RåSpO2.split(",");

        SpO2Time = new int[SpO2.length / 2];
        if (SpO2.length > 1) {
            for (int i = 0; i < SpO2.length; i = i + 2) {
                SpO2Time[i / 2] = Integer.parseInt(SpO2[i]);
            }
        }
        SpO2Value = new int[SpO2.length / 2];
        if (SpO2.length > 1) {
            for (int i = 1; i < SpO2.length; i = i + 2) {
                SpO2Value[i / 2] = Integer.parseInt(SpO2[i]);
            }
        }
        final NumberAxis Xakse = new NumberAxis();
        final NumberAxis Yakse = new NumberAxis();
        LineChart<Number,Number> SpO2Diagram = new LineChart<>(Xakse, Yakse);
        for (int a = 0; a<SpO2Time.length; a++){
            SpO2LineChart.getData().add(new XYChart.Data(SpO2Time[a],SpO2Value[a]));
        }
        SpO2Diagram.getData().add(SpO2LineChart);
    }

    public void EKGArkiv() throws FileNotFoundException {
        String FileName = CPR.getText();
        File EKG1 = new File("PatientData/"+FileName+"/EKG"); //FileName, "EKG"
        Scanner Patient = new Scanner(EKG1);
        String EKGData = Patient.nextLine();

        String RåEKG = EKGData.replaceAll("[^0-9,]", "");
        String[] EKG = RåEKG.split(",");

        EKGTime = new int[EKG.length / 2];
        if (EKG.length > 1) {
            for (int i = 0; i < EKG.length; i = i + 2) {
                EKGTime[i / 2] = Integer.parseInt(EKG[i]);
            }
        }
        EKGValue = new int[EKG.length / 2];
        if (EKG.length > 1) {
            for (int i = 1; i < EKG.length; i = i + 2) {
                EKGValue[i / 2] = Integer.parseInt(EKG[i]);
            }
        }
        final NumberAxis Xakse = new NumberAxis();
        final NumberAxis Yakse = new NumberAxis();
        LineChart<Number,Number> EKGDiagram = new LineChart<>(Xakse, Yakse);
        for (int a = 0; a<EKGTime.length; a++){
            EKGLineChart.getData().add(new XYChart.Data(EKGTime[a],EKGValue[a]));
        }
        EKGDiagram.getData().add(EKGLineChart);
    }

}
