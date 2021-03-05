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

                System.out.println(RåPuls);
                System.out.println(RåTemp);

                RåPuls = RåPuls.replaceAll("[^0-9,]", "");
                RåTemp = RåTemp.replaceAll("[^0-9,]", "");

                System.out.println(RåPuls);
                System.out.println(RåTemp);

                String[] Pulse = RåPuls.split(",");
                int PulseLenght = Pulse.length;
                int Lenght = PulseLenght/2;

                System.out.println("Længde "+ PulseLenght);

                String[] PulseTime = new String[Lenght];
                for (int i = 0; i== Pulse.length; i++){
                    while (i%2 != 0) {
                        PulseTime[i] = Pulse[i];


                    }
                }

                String[] PulseValue = new String[PulseLenght / 2];
                for (int i = 1; i== Pulse.length; i++){
                    while (i%2 == 0) {
                        PulseValue[i] = Pulse[i];
                    }
                }

                System.out.println(PulseTime);
                System.out.println(PulseValue);

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




