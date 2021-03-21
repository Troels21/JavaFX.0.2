package sample;

import javafx.fxml.*;
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
            m.openStage("ProgramChooser.fxml");
        } else if (KontrolSP()) {
            // sundhedspersonale skal kunne tilgå det meste, undtagen alarmgrænser.
            m.openStage("ProgramChooserNoAlarm.fxml");
        } else if (KontrolP()) {
            // patienter skal kun kunne tilgå deres arkiv.
            m.openStage("PatientArkiv.fxml");
        }
        else {
            b.error("Forkert adgangskode");
        }
    }

    private boolean KontrolP() {
        //Hvis dit CPR findes at PatientData folderen, kan du logge ind
        String U = Username.getText();
        String P = Password.getText();
        if (U!="") {
            File checker = new File("PatientData", U);
            CPR = U;
            if (checker.exists()) {
                return true;
            }
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




