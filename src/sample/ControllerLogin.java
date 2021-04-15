package sample;

import javafx.fxml.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public class ControllerLogin extends SQL{
    Main m = new Main();
    Simulering b = new Simulering();
    static String CPR;


    @FXML
    TextField Username;
    @FXML
    PasswordField Password;

    public void login() throws IOException, SQLException {
        if (KontrolL()) {
            // læge skal kunne tilgå det hele
            m.openStage("ProgramChooser.fxml",m.stage);
        } else if (KontrolSP()) {
            // sundhedspersonale skal kunne tilgå det meste, undtagen alarmgrænser.
            m.openStage("ProgramChooserNoAlarm.fxml", m.stage);
        } else if (KontrolP()) {
            // patienter skal kun kunne tilgå deres arkiv.
            m.openStage("PatientArkiv.fxml",m.stage);
        }
        else {
            b.error("Forkert adgangskode");
        }
    }

    private boolean KontrolP() throws SQLException {
        //Hvis dit CPR findes at PatientData folderen, kan du logge ind
        String U = Username.getText();
        String P = Password.getText();
        if (doesPatientExsist(U)){
            return true;
        }
        else{
            return false;
        }

    }

    private boolean KontrolL() {
        String U = Username.getText();
        String P = Password.getText();

        return false;
    }

    private boolean KontrolSP() {
        String U = Username.getText();
        String P = Password.getText();

        return false;
    }
}




