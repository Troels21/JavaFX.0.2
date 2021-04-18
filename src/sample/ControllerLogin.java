package sample;

import javafx.fxml.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerLogin extends SQL {
    Main m = new Main();
    Simulering b = new Simulering();
    static String CPR;
    String password_kontrol, username_kontrol;
    int DR;

    @FXML
    TextField Username;
    @FXML
    PasswordField Password;

    public void login() throws IOException {
        if (KontrolP()) {
            // patienter skal kun kunne tilgå deres arkiv.
            m.openStage("PatientArkiv.fxml", Main.stage);
        } else if (KontrolL()) {
            // læge skal kunne tilgå det hele
            m.openStage("ProgramChooser.fxml", Main.stage);
        } else if (KontrolSP()) {
            // sundhedspersonale skal kunne tilgå det meste, undtagen alarmgrænser.
            m.openStage("ProgramChooserNoAlarm.fxml", Main.stage);
        } else {
            b.error("Forkert adgangskode");
        }
    }

    private boolean KontrolP() {
        //Hvis dit CPR findes at PatientData folderen, kan du logge ind
        String U = Username.getText();
        if (doesPatientExsist(U)) {
            CPR = U;
            return true;
        } else {
            return false;
        }

    }

    private boolean KontrolL() {
        String U = Username.getText();
        String P = Password.getText();
        String s = ReadDataLogininfo(U);

        if (s.equals("null")) {
            return false;
        } else {
            String[] data = s.split(",");
            username_kontrol = data[0];
            password_kontrol = data[1];
            DR = Integer.parseInt(data[2]);
            return U.equals(username_kontrol) && P.equals(password_kontrol) && DR == 1;
        }
    }

    private boolean KontrolSP() {
        String U = Username.getText();
        String P = Password.getText();
        String s = ReadDataLogininfo(U);

        if (s.equals("null")) {
            return false;
        } else {
            String[] data = s.split(",");
            username_kontrol = data[0];
            password_kontrol = data[1];
            DR = Integer.parseInt(data[2]);
            return U.equals(username_kontrol) && P.equals(password_kontrol) && DR == 0;
        }
    }
}




