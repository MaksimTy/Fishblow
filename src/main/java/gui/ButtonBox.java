package gui;


import helper.StringHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
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

public class ButtonBox extends VBox {

    private final double indent = 5;
    private final double PrefWidth = 80;

    private Insets insets = new Insets(this.indent, this.indent, this.indent, this.indent);

    private FileChooser fileChooser = new FileChooser();
    private Button buttonInput = new Button("open file"); //
    private Button buttonCheck = new Button("check data"); //
    private Button buttonCalc = new Button("calculate"); //
    private Button buttonShow = new Button("show"); //

    private Button[] buttons = new Button[]{buttonInput, buttonCheck, buttonCalc, buttonShow};

    private Aquarium aquarium;
    private TextBox text;
    private CanvasBox canvasBox;


    public ButtonBox(Stage stage, TextBox text) {

        this.text = text;
        this.text.setOutputText(this.text.getIntro());
        this.setPrefWidth(PrefWidth + indent);

        for (Button button : buttons) {
            this.getChildren().add(button);
            this.setMargin(button, insets);
            button.setPrefWidth(PrefWidth);
        }


        buttonInput.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                text.clearOutput();
                aquarium = null;
                File file = fileChooser.showOpenDialog(stage);
                List<String> lines = null;
                try {
                    lines = Files.readAllLines(file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                    text.setOutputText(e.getMessage());
                }
                text.setOutputText(lines.stream().collect(Collectors.joining(" ")));
            }
        });

        buttonCheck.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String array;
                StringHelper stringHelper = new StringHelper();
                try {
                    stringHelper.setArray(text.getOutputText());
                    aquarium = new Aquarium(stringHelper.getArray());
                    array = Arrays.toString(stringHelper.getArray());
                    text.setOutputText(array);

                } catch (InvalidValuesException |
                        NullPointerException |
                        OutOfMemoryError |
                        NumberFormatException e) {
                    e.printStackTrace();
                    array = Arrays.toString(stringHelper.getArray());
                    text.setOutputText((array + "\n" + e.getMessage()));
                }
            }
        });

        buttonCalc.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                text.setOutputLabelAfter(
                        String.valueOf(aquarium.getWater()),
                        String.valueOf(aquarium.getHeight()),
                        String.valueOf(aquarium.getWidth()));
            }
        });

        buttonShow.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                canvasBox.setAquarium(aquarium);
            }
        });
    }

    public Aquarium getAquarium() {
        return aquarium;
    }

    public CanvasBox getCanvasBox() {
        return canvasBox;
    }

    public void setCanvasBox(Aquarium aquarium) throws IOException {
        this.canvasBox = new CanvasBox(aquarium);
    }
}
