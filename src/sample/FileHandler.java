package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    String path;
    FileWriter f1;

    //Laver en direktory i konstruktøren
    public FileHandler(String cpr) {
        this.path = "PatientData/" + cpr;
        System.out.println(path);
        File folder = new File(path);
        folder.mkdir();
    }

    //Save date gemmer en int
    public void saveData(String type, String bogstav, int value) throws IOException {
        f1 = new FileWriter((this.path + "\\" + type), true);
        String t = type;
        f1.write(bogstav + "," + value + "," + "null" + "|");
        f1.flush();
    }

    //Save date double gemmer en double
    public void saveDataDouble(String type, String bogstav, double value) throws IOException {
        f1 = new FileWriter((this.path + "\\" + type), true);
        String t = type;
        f1.write(bogstav + "," + value + "," + "null" + "|");
        f1.flush();
    }
}


