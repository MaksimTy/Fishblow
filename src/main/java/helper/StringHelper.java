package helper;


import model.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    private static Pattern pattern = Pattern.compile("(-?\\d+)+");
    private List<Integer> integerList = new ArrayList<>();

    //Comment: пропущены описание параметров и результата
    /**
     * Метод формирует список натуральных числе из строки.
     * Разделителем являются любые символы, кроме чисел и знака минус.
     * Точки и запятые тоже являются разделителями, десятичные числа
     * возвращиются как два натуральных числа.
     *
     * @param string
     * @throws  NumberFormatException
     */
    public void setArray(String string) throws NumberFormatException {

        Matcher matcher = pattern.matcher(string);  //поле статическое, this не нужен

        while (matcher.find() && this.integerList.size() < Terrain.getSizeLimit() + 1) {
            String s = matcher.group();

            int i = Integer.parseInt(s);
            this.integerList.add(i);

            if (i > Terrain.getSizeLimit() || i < 0 || this.integerList.size() > Terrain.getSizeLimit()) {
                throw new NumberFormatException("Invalid input data!");
            }
        }
    }

    public int[] getArray() {
        return this.integerList.stream()
                                .mapToInt(x -> x)
                                .toArray();
    }
}

