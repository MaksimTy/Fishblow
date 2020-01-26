package gui;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class TextBox extends VBox {


    private final double indent = 5;
    private final String answer = "Объем оставшейся воды между кубиками: ";
    private final String title = "Последовательная высота столбцов в аквариуме: \n";
    private final String cols = "Количество столбцов = ";
    private final String rows = "Высота аквариума = ";
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

    public Label getLabel() {
        return this.label;
    }

    public void setOutputText(String array) {
        this.text.setText(this.title + array);
    }

    public String getOutputText() {
        return this.text.getText();
    }

    public void setOutputLabel(String answer) {
        this.label.setText(this.answer + answer);
    }

    public void clearOutput() {
        this.text.setText(this.title);
        this.label.setText(this.answer);
    }

    public String getIntro() {
        return this.intro;
    }
}
