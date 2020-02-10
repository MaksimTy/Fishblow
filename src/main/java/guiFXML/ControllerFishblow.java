package guiFXML;


import helper.StringHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Aquarium;
import model.Tile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerFishblow {
    //Comment: у методов нет описания, что происходит. Не так страшно, пожалуй - вроде, тут все к графике, в основном относится и рисованию.

    private final String answer = "Объем воды, оставшейся между кубиками: ";
    private final String title = "Последовательная высота столбцов в аквариуме: \n";
    private final String cols = "\nКоличество столбцов = ";
    private final String rows = "\nВысота аквариума = ";
    private final String intro = "" +
            "1. Выберите файл с данными (\"open file\");\n" +
            "2. Проверьте файл (\"check file\");\n" +
            "3. Получите результат (\"calculate\"); ";
    private final double tile = 8;

    private Aquarium aquarium;

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button buttonInput;  //"open file"
    @FXML
    private Button buttonCheck;  //"check data"
    @FXML
    private Button buttonCalc;   //"calculate"
    @FXML
    private Button buttonShow;   //"show"
    @FXML
    private TextArea text; // input area
    @FXML
    private Label label;   // answer label
    @FXML
    private Canvas canvas;
    private GraphicsContext graphicsContext;

    //Comment: Названия методов не очень отражают их суть. Тут, например, кнопка открытия файла. Кажется, понятней будет
    //openFile? Слово-префикс set, полагаю, тут не нужно. Это ведь действие по нажатию кнопки: openButtonClick, InputButtonClick, openFile
    @FXML
    public void setButtonInputClick(ActionEvent event) {
        this.clearOutput();
        this.setOutputText(getIntro());
        this.aquarium = null;
        Stage stage = (Stage) anchorPane.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        List<String> lines = null;
        try {
            lines = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            this.setOutputText(e.getMessage());
        }
        //Comment: idea ругается - если файл не прочтется - будет null на следующей строчке
        this.setOutputText(String.join(" ", lines));    //Comment: idea предложила, так покороче будет.

    }

    @FXML
    public void setButtonCheckClick(ActionEvent event) {
        String array;
        StringHelper stringHelper = new StringHelper();
        try {
            stringHelper.setArray(this.getOutputText());
            this.aquarium = new Aquarium(stringHelper.getArray());
            array = Arrays.toString(stringHelper.getArray());
            setOutputText(array);

        } catch (NullPointerException |
                OutOfMemoryError |
                NumberFormatException e) {  //А если еще чего-нибудь выскочит? Лови уж Throwable или Exception просто
            e.printStackTrace();    //Comment: А куда он распечатает это, консоли ведь пользователь не видит?
            // Может, тогда навернуть какой-нибудь log4j2 или logback и в файл?
            //Вообще, логгеры нынче маст хэв.
            array = Arrays.toString(stringHelper.getArray());
            setOutputText((array + "\n" + e.getMessage()));
        }
        if (graphicsContext != null) {
            graphicsContext.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        }
    }

    @FXML
    public void setButtonCalcClick(ActionEvent event) {
        this.setOutputLabelAfter(
                String.valueOf(this.aquarium.getWater()),
                String.valueOf(this.aquarium.getHeight()),
                String.valueOf(this.aquarium.getWidth()));
    }

    @FXML
    public void setButtonShowClick(ActionEvent event) {
        this.setAquarium();
    }

    @FXML
    private String getIntro() {
        return this.intro;
    }

    private void clearOutput() {
        this.text.setText(this.title);
        this.label.setText(this.answer);
    }

    private void setOutputText(String array) {
        this.text.setText(this.title + array);
    }

    private String getOutputText() {
        return this.text.getText();
    }

    private void setOutputLabelAfter(String answer, String rows, String cols) {
        String result = String.format("%s %s %s %s %s %s",
                this.answer, answer, this.rows, rows, this.cols, cols);
        this.label.setText(result);
    }

    private void setAquarium() {

        this.graphicsContext = this.canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        if (this.aquarium != null) {
            this.canvas.setWidth(this.aquarium.getWidth() * this.tile);
            this.canvas.setHeight(this.aquarium.getHeight() * this.tile);
            for (int i = 0; i < this.aquarium.getLayers().size(); i++) {
                for (int j = 0; j < aquarium.getLayers().get(i).getLayer().length; j++) {
                    if (aquarium.getLayers().get(i).getLayer()[j] == Tile.Empty) {
                        continue;
                    }
                    graphicsContext.setFill(
                            Color.valueOf(
                                    this.aquarium.getLayers().get(i).getLayer()[j].getColor()));
                    graphicsContext.setStroke(Color.GRAY);
                    graphicsContext.fillRect(j * tile, i * tile, tile, tile);
                    graphicsContext.strokeRect(j * tile, i * tile, tile, tile);
                }
            }
        }
    }

}
