package sample;

import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;

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
            Parent ProgramChooser = FXMLLoader.load(getClass().getResource("ProgramChooserNoAlarm.fxml"));
            m.stage.setScene(new Scene(ProgramChooser, 650, 400));
            m.stage.show();
        } else if (KontrolP()) {
            // patienter skal kun kunne tilgå deres arkiv.
            Parent ProgramChooser = FXMLLoader.load(getClass().getResource("PatientArkiv.fxml"));
            m.stage.setScene(new Scene(ProgramChooser, 650, 400));
            m.stage.show();
        } else {
            b.error("Forkert adgangskode");
        }
    }

    private boolean KontrolP() {
        //Hvis dit CPR findes at PatientData folderen, kan du logge ind
        String U = Username.getText();
        String P = Password.getText();

        File checker = new File("PatientData", U);
        CPR = U;
        if (checker.exists()) {
            return true;
        }
        return false;
    }

    private boolean KontrolL() {
        // her skal SQL implementeres
        String[] Uarkiv = new String[3];
        String[] Parkiv = new String[3];
        Uarkiv[0] = "DR";
        Parkiv[0] = "Password";
        String U = Username.getText();
        String P = Password.getText();

        for (int i = 0; i < Uarkiv.length; i++) {
            if (U.equals(Uarkiv[i]) && P.equals(Parkiv[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean KontrolSP() {
        // her skal SQL implementeres
        String[] Uarkiv = new String[3];
        String[] Parkiv = new String[3];
        Uarkiv[0] = "nurse";
        Parkiv[0] = "Password";
        String U = Username.getText();
        String P = Password.getText();

        for (int i = 0; i < Uarkiv.length; i++) {
            if (U.equals(Uarkiv[i]) && P.equals(Parkiv[i])) {
                return true;
            }
        }
        return false;
    }
}




