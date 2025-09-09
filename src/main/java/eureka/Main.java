package eureka;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Eureka using FXML.
 */
public class Main extends Application {

    private Eureka eureka = new Eureka("data/record.txt");

    @Override
    public void start(Stage stage) {
        try {
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setEureka(eureka);  // inject the Eureka instance
            stage.setTitle("Eureka");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
