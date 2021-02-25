package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    // hej med dig det er mig mig mig mig naveed
    /*hej dhsccrdnscjndsjncljnds
    cnjdslncljdslcndsjlncs
    djcnsdlncsdnlcnsd
    cndsjlnclnsd
    jcndslnclsnd
    cn
    dsjcj
    dsn
    cnsdjnclsdn
    c
    dsnsd
    ncds

     */
    Stage stage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Test.fxml"));
        primaryStage.setTitle("Scene chooser");
        primaryStage.setScene(new Scene (root, 650, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
