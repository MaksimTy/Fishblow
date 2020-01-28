
package gui;

import javafx.scene.paint.Color;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application{

    private final double insets = 5;
    private final double topInsets = 20;

    private TextBox textBox;
    private ButtonBox buttonBox;
    private CanvasBox canvas;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane window = new BorderPane();

        AnchorPane root = new AnchorPane(window);
        BorderPane.setAlignment(root, Pos.CENTER);


        this.textBox = new TextBox();
        this.buttonBox = new ButtonBox(stage, this.textBox);
        this.buttonBox.setCanvasBox(this.buttonBox.getAquarium());

        this.canvas = this.buttonBox.getCanvasBox();


        root.getChildren().add(this.buttonBox);
        root.getChildren().add(this.textBox);
        root.getChildren().add(this.canvas);

        AnchorPane.setLeftAnchor(this.buttonBox, this.insets);
        AnchorPane.setTopAnchor(this.buttonBox, this.topInsets - this.insets);

        AnchorPane.setTopAnchor(this.textBox, this.topInsets);
        AnchorPane.setLeftAnchor(this.textBox, this.buttonBox.getPrefWidth() + insets * 2);
        AnchorPane.setRightAnchor(this.textBox, this.insets);

        AnchorPane.setLeftAnchor(this.canvas, this.insets);
        AnchorPane.setTopAnchor(this.canvas, 180.0);
        AnchorPane.setRightAnchor(this.canvas, this.insets);
        AnchorPane.setBottomAnchor(this.canvas, this.insets);

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

