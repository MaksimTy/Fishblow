package gui;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class TextBox extends VBox {


    private final double indent = 5;
    private final String answer = "Объем воды, оставшейся между кубиками: ";
    private final String title = "Последовательная высота столбцов в аквариуме: \n";
    private final String cols = "\nКоличество столбцов = ";
    private final String rows = "\nВысота аквариума = ";
    private final String intro = "1. Выберите файл с данными (\"open file\");\n" +
            "2. Проверьте файл (\"check file\");\n" +
            "3. Получите результат (\"calculate\"); ";


    private final double textAreaHeight = 95;
    private TextArea text = new TextArea();
    private Label label = new Label(this.answer);

    public TextBox() {

        this.text.setPrefWidth(Double.MAX_VALUE);
        this.text.setMaxHeight(this.textAreaHeight);
        this.getChildren().add(this.text);
        this.getChildren().add(this.label);
    }


    public void setOutputText(String array) {
        this.text.setText(this.title + array);
    }

    public String getOutputText() {
        return this.text.getText();
    }

    public void setOutputLabelAfter(String answer, String rows, String cols) {
        String result = String.format("%s %s %s %s %s %s",
                this.answer, answer, this.rows, rows, this.cols, cols);
        this.label.setText(result);
    }

    public void clearOutput() {
        this.text.setText(this.title);
        this.label.setText(this.answer);
    }

    public String getIntro() {
        return this.intro;
    }
}
