package sample;

import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ControllerLogin {
    Main m = new Main();
    Beregner b = new Beregner();
    static String CPR;

    @FXML
    TextField Username;
    @FXML
    PasswordField Password;


    public void login() throws IOException {


        if (KontrolL()) {
            // læge skal kunne tilgå det hele
            Parent ProgramChooser = FXMLLoader.load(getClass().getResource("ProgramChooser.fxml"));
            m.stage.setScene(new Scene(ProgramChooser, 650, 400));
            m.stage.show();
        } else if (KontrolSP()) {
            // sundhedspersonale skal kunne tilgå det meste, undtagen alarmgrænser.
            Parent ProgramChooser = FXMLLoader.load(getClass().getResource("ProgramChooser.fxml"));
            m.stage.setScene(new Scene(ProgramChooser, 650, 400));
            m.stage.show();
        } else if(KontrolP()) {
            // patienter skal kun kunne tilgå deres arkiv.

            Parent ProgramChooser = FXMLLoader.load(getClass().getResource("PatientArkiv.fxml"));
            m.stage.setScene(new Scene(ProgramChooser, 650, 400));
            m.stage.show();
        } else{
            b.error("Forkert adgangskode");
        }
    }

    private boolean KontrolP() {
        // skal upgraderes til at kigge efter allerede eksisterende filer
        String[] arkiv = new String[]{"dude"};
        String U = Username.getText();
        String P = Password.getText();
        CPR = U;

        for (int i = 0; i < arkiv.length; i++) {
            if (U.equals(arkiv[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean KontrolL() {
        String[] arkiv = new String[]{"bro"};
        String U = Username.getText();
        String P = Password.getText();

        for (int i = 0; i < arkiv.length; i++) {
            if (U.equals(arkiv[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean KontrolSP() {
        String[] arkiv = new String[]{"guy", "friend", "pal"};
        String U = Username.getText();
        String P = Password.getText();

        for (int i = 0; i < arkiv.length; i++) {
            if (U.equals(arkiv[i])) {
                return true;
            }
        }
        return false;
    }
}




