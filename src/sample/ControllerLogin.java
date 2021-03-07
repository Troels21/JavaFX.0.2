package sample;

import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class ControllerLogin {
    Main m = new Main();

    @FXML
    TextField Username;
    @FXML
    PasswordField Password;


    public void login() throws IOException {

        if (KontrolSP()) {
            Parent ProgramChooser = FXMLLoader.load(getClass().getResource("ProgramChooser.fxml"));
            m.stage.setScene(new Scene(ProgramChooser, 650, 400));
            m.stage.show();
        } else if (KontrolL()) {
            Parent ProgramChooser = FXMLLoader.load(getClass().getResource("ProgramChooser.fxml"));
            m.stage.setScene(new Scene(ProgramChooser, 650, 400));
            m.stage.show();
        } else {
            Parent ProgramChooser = FXMLLoader.load(getClass().getResource("ProgramChooser.fxml"));
            m.stage.setScene(new Scene(ProgramChooser, 650, 400));
            m.stage.show();
        }
    }

    private boolean KontrolL() {
        String[] arkiv = new String[]{"dude", "bro", "guy", "friend", "pal"};
        String U = Username.getText();
        String P = Password.getText();

        for (int i = 0; i < arkiv.length - 1; i++) {
            if (U.equals(arkiv[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean KontrolSP() {
        String[] arkiv = new String[]{"dude", "bro", "guy", "friend", "pal"};
        String U = Username.getText();
        String P = Password.getText();

        for (int i = 0; i < arkiv.length - 1; i++) {
            if (U.equals(arkiv[i])) {
                return true;
            }
        }
        return false;
    }
}




