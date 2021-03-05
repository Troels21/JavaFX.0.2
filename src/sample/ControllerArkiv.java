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

    public void PatientChooser() throws FileNotFoundException {
            File checker = new File("PatientData",CPR.getText());

            if (checker.exists() && CPR.getText().length() >0) {
                //Fremvisning af data
            }
            else {
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




