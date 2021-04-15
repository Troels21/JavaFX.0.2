package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.LineChart;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class FileHandler {

    public String savepath() {
        FileChooser fc = new FileChooser();
        File file1 = fc.showSaveDialog(null);
        file1.mkdir();
        return file1.getAbsolutePath();
    }

    public void saveAsPng(String path, LineChart lineChart, String name) { //Laver png billede
        WritableImage image = lineChart.snapshot(new SnapshotParameters(), null);
        File file = new File(path + "/" + name);
        try {
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


