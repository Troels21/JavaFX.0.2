package sample;

import java.io.FileWriter;
import java.io.IOException;

public class Filgenerering {
    FileWriter f1;
    public Filgenerering(String navn){
        {
            try {
                f1 = new FileWriter("PatientData/"+navn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
