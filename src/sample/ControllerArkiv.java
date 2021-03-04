package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.ChoiceBox;

public class ControllerArkiv {
    Main m = new Main();


    ObservableList list = FXCollections.observableArrayList();
    @FXML
    private ChoiceBox <String> choiceBox;


    public void PatientChooser(){
        list.removeAll(list);
        String a = "test1";
        String b = "test2";
        list.addAll(a,b);
        choiceBox.getItems().addAll(list);
    }
}



