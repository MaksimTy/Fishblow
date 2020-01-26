
package gui;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class App extends Application {

    private final double insets = 5;
    private TextBox textBox;
    private ButtonBox buttonBox;
    private TilesBox tiles;   //todo


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane window = new BorderPane();

        window.setMaxSize(200, 200);

        AnchorPane root = new AnchorPane(window);
        BorderPane.setAlignment(root, Pos.CENTER);


        this.textBox = new TextBox();
        this.buttonBox = new ButtonBox(stage, this.textBox);
        this.buttonBox.setTilesBox(this.buttonBox.getAquarium());
        this.tiles = this.buttonBox.getTilesBox();


        root.getChildren().add(this.buttonBox);
        root.getChildren().add(this.textBox);
        root.getChildren().add(this.tiles);

        AnchorPane.setLeftAnchor(this.buttonBox, this.insets);
        AnchorPane.setTopAnchor(this.buttonBox, 20.0 - this.insets);

        AnchorPane.setTopAnchor(this.textBox, 20.0);
        AnchorPane.setLeftAnchor(this.textBox, 80.0);
        AnchorPane.setRightAnchor(this.textBox, this.insets);

        AnchorPane.setLeftAnchor(this.tiles, this.insets);
        AnchorPane.setTopAnchor(this.tiles, 160.0);
        AnchorPane.setRightAnchor(this.tiles, this.insets);
        AnchorPane.setBottomAnchor(this.tiles, this.insets);

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

