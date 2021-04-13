package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    String path;
    FileWriter f1;
    String cpr;

    //Laver en direktory i konstruktøren
    public FileHandler(String cpr) {
        this.path = "PatientData/" + cpr;
        this.cpr=cpr;
        System.out.println(path);
        File folder = new File(path);
        folder.mkdir();
    }

    //Save date gemmer en int
    public void saveData(String type, String bogstav, int value) throws IOException {
        f1 = new FileWriter((this.path + "\\" + type), true);
        f1.write(bogstav + "," + value +","+"null"+"|");
        f1.flush();
    }

    //Save date double gemmer en double
    public void saveDataDouble(String type, String bogstav, double value) throws IOException {
        f1 = new FileWriter((this.path + "\\" + type), true);
        f1.write(bogstav + "," + value + ","+"null"+"|");
        f1.flush();
    }

    public void saveAsPng(LineChart lineChart,String name) { //Laver png billede
        File file1 = new File("journal Billeder/" + cpr);
        file1.mkdir();
        WritableImage image = lineChart.snapshot(new SnapshotParameters(), null);
        File file = new File("journal Billeder/" + cpr + "/" + name);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


