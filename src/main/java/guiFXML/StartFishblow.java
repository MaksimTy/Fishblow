package guiFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppStart extends Application {

    private Controller controller;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/aquarium.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Fishblow");
        stage.setWidth(250);
        stage.setHeight(200);

        stage.show();
    }
}
