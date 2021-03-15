package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileHandler {
    String path;
    FileWriter f1;

    public FileHandler(String cpr) {
        this.path = "PatientData/"+cpr;
        System.out.println(path);
        File folder = new File(path);
        folder.mkdir();
        }

    public void saveData(String type,String bogstav,int value) throws IOException {
        f1 = new FileWriter((this.path+"\\"+type),true);
        String t = type;
        f1.write(bogstav+","+value+","+"null"+"|");
        f1.flush();
    }
}


