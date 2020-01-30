package guiFXML;


import helper.StringHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import model.Aquarium;
import model.InvalidValuesException;

import java.util.Arrays;

public class Controller {


    private final String answer = "Объем воды, оставшейся между кубиками: ";
    private final String title = "Последовательная высота столбцов в аквариуме: \n";
    private final String cols = "\nКоличество столбцов = ";
    private final String rows = "\nВысота аквариума = ";
    private final String intro = "" +
            "1. Выберите файл с данными (\"open file\");\n" +
            "2. Проверьте файл (\"check file\");\n" +
            "3. Получите результат (\"calculate\"); ";



    private Aquarium aquarium;
    private FileChooser fileChooser;

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


    @FXML
    public void buttonInputClick(ActionEvent event){
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

    private void setOutputText(String array) {

        this.text.setText(this.title + array);
    }

    private String getOutputText() {
        return this.text.getText();
    }

}
