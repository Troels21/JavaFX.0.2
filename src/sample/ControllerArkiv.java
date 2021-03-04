package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.*;

public class ControllerArkiv {
    @FXML
    TextField CPR;

    public void PatientChooser() {
        String Patient = CPR.getText();
        File checker = new File(Patient);
        boolean exists = checker.exists();
        if (exists){
            Patient = "";
        }
        if (Patient.equals("")) {
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
        }

       // File checker = new File(Patient);
        //boolean exists = checker.exists();

       // if (CPR.equals("") || CPR.equals("Set CPR")) {

   // }




