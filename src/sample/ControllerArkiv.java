package sample;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ControllerArkiv {
    Puls_SpO2_Temp PatientInfo = new Puls_SpO2_Temp();
    FileWriter f1;
    public ControllerArkiv(String navn){

        {
            try {
                f1 = new FileWriter("PatientData/"+navn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void PatientFolder() throws IOException, InterruptedException {


      //  String PatientName = PatientInfo.setName();

        //
    }

/*
    public void visTemperatur() throws IOException {
        Puls_SpO2_Temp temp = new Puls_SpO2_Temp();
        FileWriter TemperaturFil = new FileWriter("Temp");

    }

    public void visIltmætning() throws IOException {
        FileWriter iltmætningFil = new FileWriter("SpO2");

    }

    public void visPuls() throws IOException {
        FileWriter pulsFil = new FileWriter("Puls");

    }

    public void visEKG () throws IOException {
        FileWriter EKGFil = new FileWriter("EKG");
 */

    }


