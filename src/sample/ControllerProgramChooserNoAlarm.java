package sample;

import java.io.IOException;

public class ControllerProgramChooserNoAlarm extends ControllerProgramChooser {
    //Alle metoder laver ny stage med nye scene, denne klasse indeholder ikke en alarm knap
    public void pulsTemp2() throws IOException {
        pulsTempClicked();
    }

    public void arkiv2() throws IOException {
        arkivClicked();
    }

    public void ekgClicked2() throws IOException {
        ekgClicked();
    }
}
