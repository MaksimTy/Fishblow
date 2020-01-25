
package gui;



import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private final double insets = 5;
    private TextBox textBox;
    private ButtonBox buttonBox;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane window = new BorderPane();

        window.setPrefSize(50, 200);

        AnchorPane root = new AnchorPane(window);
        AnchorPane.setBottomAnchor(window, this.insets);
        AnchorPane.setTopAnchor(window, this.insets);
        AnchorPane.setLeftAnchor(window, this.insets);
        AnchorPane.setRightAnchor(window, this.insets);

        this.textBox = new TextBox();
        this.buttonBox = new ButtonBox(stage,this.textBox);

        root.getChildren().add(this.buttonBox);
        root.getChildren().add(this.textBox);

        AnchorPane.setLeftAnchor(this.buttonBox, this.insets);
        AnchorPane.setTopAnchor(this.buttonBox, 20.0 - this.insets);

        AnchorPane.setTopAnchor(this.textBox, 20.0);
        AnchorPane.setLeftAnchor(this.textBox, 80.0);
        AnchorPane.setRightAnchor(this.textBox, 10.0);

        root.setBackground(new Background(
                new BackgroundFill(Color.SNOW,
                        CornerRadii.EMPTY,
                        Insets.EMPTY)));

        Scene scene = new Scene(root);
        stage.setTitle("Fishblow.");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}

