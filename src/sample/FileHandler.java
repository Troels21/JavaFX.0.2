package sample;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    FileWriter f1;
    public FileHandler(String navn){
        {
            try {
                f1 = new FileWriter("PatientData/"+navn);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
