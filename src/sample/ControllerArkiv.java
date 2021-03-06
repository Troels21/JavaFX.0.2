package sample;

import javafx.fxml.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.*;
import java.io.*;

public class ControllerArkiv {
    @FXML
    TextField CPR;
    public void PatientChooser() throws FileNotFoundException {
            File checker = new File("PatientData",CPR.getText());

            if (checker.exists() && CPR.getText().length() >0) {
                Scanner Patient = new Scanner(checker);
                Patient.nextLine();
                String RåPuls = Patient.nextLine();
                String RåTemp = Patient.nextLine();

                RåPuls = RåPuls.replaceAll("[^0-9,]", "");
                RåTemp = RåTemp.replaceAll("[^0-9,]", "");


                String[] Pulse = RåPuls.split(",");

                int[] PulseTime = new int[Pulse.length/2];
                if(Pulse.length > 1)
                {
                    for (int i = 0; i < Pulse.length; i = i + 2)
                    {
                        PulseTime[i / 2] = Integer.parseInt(Pulse[i]);
                    }
                }

                int[] PulseValue = new int[Pulse.length/2];
                if(Pulse.length > 1)
                {
                    for (int i = 1; i < Pulse.length; i = i + 2)
                    {
                        PulseValue[i / 2] = Integer.parseInt(Pulse[i]);
                    }
                }

                String[] Temp = RåTemp.split(",");

                int[] TempTime = new int[Temp.length/2];
                if(Temp.length > 1)
                {
                    for (int i = 0; i < Temp.length; i = i + 2)
                    {
                        TempTime[i / 2] = Integer.parseInt(Temp[i]);
                    }
                }

                int[] TempValue = new int[Temp.length/2];
                if(Temp.length > 1)
                {
                    for (int i = 1; i < Temp.length; i = i + 2)
                    {
                        TempValue[i / 2] = Integer.parseInt(Temp[i]);
                    }
                }


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




