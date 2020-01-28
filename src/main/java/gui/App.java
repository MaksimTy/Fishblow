
package gui;

import helper.StringHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Aquarium;
import model.InvalidValuesException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App extends Application {


    private final double topInsets = 20;
    private final double indent = 5;
    private final double buttonBoxPrefWidth = 85;
    private final String answer = "Объем воды, оставшейся между кубиками: ";
    private final String title = "Последовательная высота столбцов в аквариуме: \n";
    private final String cols = "\nКоличество столбцов = ";
    private final String rows = "\nВысота аквариума = ";
    private final String intro = "" +
            "1. Выберите файл с данными (\"open file\");\n" +
            "2. Проверьте файл (\"check file\");\n" +
            "3. Получите результат (\"calculate\"); ";


    //text box
    private VBox textBox;
    private final double textAreaHeight = 95;
    private TextArea text; // input area
    private Label label;   // answer label

    //button box;
    private VBox buttonBox;
    private FileChooser fileChooser;
    private Button buttonInput;  //"open file"
    private Button buttonCheck;  //"check data"
    private Button buttonCalc;   //"calculate"
    private Button buttonShow;   //"show"
    private Insets insets;

    // Canvas box;
    private ScrollPane canvasBox;
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private final double tile = 4;   //bricks size

    private Aquarium aquarium;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane window = new BorderPane();

        AnchorPane root = new AnchorPane(window);
        BorderPane.setAlignment(root, Pos.CENTER);

        //text box
        this.textBox = new VBox(indent);
        this.text = new TextArea();
        setOutputText(getIntro());
        this.label = new Label(this.answer);
        this.text.setPrefWidth(Double.MAX_VALUE);
        this.text.setMaxHeight(this.textAreaHeight);
        this.textBox.getChildren().add(this.text);
        this.textBox.getChildren().add(this.label);

        //button box
        this.buttonBox = new VBox();
        this.buttonBox.setPrefWidth(this.buttonBoxPrefWidth);
        this.fileChooser = new FileChooser();
        this.buttonInput = new Button("open file");
        this.buttonCheck = new Button("check data");
        this.buttonCalc = new Button("calculate");
        this.buttonShow = new Button("show");

        Button[] buttons = new Button[]{
                buttonInput,
                buttonCheck,
                buttonCalc,
                buttonShow};
        this.insets = new Insets(this.indent);
        for (Button button : buttons) {
            this.buttonBox.getChildren().add(button);
            VBox.setMargin(button, insets);
            button.setPrefWidth(buttonBoxPrefWidth);
        }
        //canvas box
        this.canvasBox = new ScrollPane(this.canvas);
        this.canvasBox.setPrefWidth(Double.MAX_VALUE);
        this.canvasBox.setPrefHeight(Double.MAX_VALUE);
        this.setAquarium();


        buttonInput.setOnAction(event -> {
            clearOutput();
            setOutputText(getIntro());
            aquarium = null;
            File file = fileChooser.showOpenDialog(stage);
            List<String> lines = null;
            try {
                lines = Files.readAllLines(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
                setOutputText(e.getMessage());
            }
            setOutputText(lines.stream().collect(Collectors.joining(" ")));
        });

        buttonCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String array;
                StringHelper stringHelper = new StringHelper();
                try {
                    stringHelper.setArray(getOutputText());
                    aquarium = new Aquarium(stringHelper.getArray());
                    array = Arrays.toString(stringHelper.getArray());
                    setOutputText(array);

                } catch (InvalidValuesException |
                        NullPointerException |
                        OutOfMemoryError |
                        NumberFormatException e) {
                    e.printStackTrace();
                    array = Arrays.toString(stringHelper.getArray());
                    setOutputText((array + "\n" + e.getMessage()));
                }
            }
        });

        buttonCalc.setOnAction(event -> setOutputLabelAfter(
                String.valueOf(aquarium.getWater()),
                String.valueOf(aquarium.getHeight()),
                String.valueOf(aquarium.getWidth())));

        buttonShow.setOnAction(event -> setAquarium());


        root.getChildren().add(this.buttonBox);
        root.getChildren().add(this.textBox);
        root.getChildren().add(this.canvasBox);

        AnchorPane.setLeftAnchor(this.buttonBox, this.indent);
        AnchorPane.setTopAnchor(this.buttonBox, this.topInsets - this.indent);

        AnchorPane.setTopAnchor(this.textBox, this.topInsets);
        AnchorPane.setLeftAnchor(this.textBox, this.buttonBox.getPrefWidth() + indent * 2);
        AnchorPane.setRightAnchor(this.textBox, this.indent);

        AnchorPane.setLeftAnchor(this.canvasBox, this.indent);
        AnchorPane.setTopAnchor(this.canvasBox, topInsets * 9);
        AnchorPane.setRightAnchor(this.canvasBox, this.indent);
        AnchorPane.setBottomAnchor(this.canvasBox, this.indent);

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


    public void clearOutput() {
        this.text.setText(this.title);
        this.label.setText(this.answer);
    }

    public void setOutputText(String array) {
        this.text.setText(this.title + array);
    }

    public String getIntro() {
        return this.intro;
    }

    public String getOutputText() {
        return this.text.getText();
    }

    public void setOutputLabelAfter(String answer, String rows, String cols) {
        String result = String.format("%s %s %s %s %s %s",
                this.answer, answer, this.rows, rows, this.cols, cols);
        this.label.setText(result);
    }

    private void fillAquarium(int width) {
        if (this.aquarium == null) {
            this.canvas.setWidth(width * this.tile);
            this.canvas.setHeight(width / 4 * this.tile);
            for (int i = 0; i < width / 4; i++) {
                for (int j = 0; j < width; j++) {
                    this.graphicsContext.setFill(Color.WHITE);
                    this.graphicsContext.setStroke(Color.GRAY);
                    this.graphicsContext.fillRect(j * tile, i * tile, tile, tile);
                    this.graphicsContext.strokeRect(j * tile, i * tile, tile, tile);
                }
            }
        } else {
            this.canvas.setWidth(this.aquarium.getWidth() * this.tile);
            this.canvas.setHeight(this.aquarium.getHeight() * this.tile);
            for (int i = 0; i < this.aquarium.getLayers().size(); i++) {
                for (int j = 0; j < aquarium.getLayers().get(i).getLayer().length; j++) {
                    this.graphicsContext.setFill(
                            Color.valueOf(
                                    this.aquarium.getLayers().get(i).getLayer()[j].getColor()));
                    this.graphicsContext.setStroke(Color.GRAY);
                    this.graphicsContext.fillRect(j * tile, i * tile, tile, tile);
                    this.graphicsContext.strokeRect(j * tile, i * tile, tile, tile);
                }
            }
        }
    }

    public void setAquarium() {
        int width = (int) (this.topInsets * 7);
        if (this.aquarium != null) {
            width = this.aquarium.getWidth();
        }
        this.canvas = new Canvas();
        this.graphicsContext = this.canvas.getGraphicsContext2D();

        this.canvasBox.setContent(this.canvas);
        this.fillAquarium(width);
    }
}

