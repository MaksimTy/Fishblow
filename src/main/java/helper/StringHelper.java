package helper;

import model.InvalidValuesException;
import model.Terrain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {

    private static Pattern pattern = Pattern.compile("(-?\\d+)+");
    private List<Integer> integerList = new ArrayList<>();

    /**
     * Метод формирует список натуральных числе из строки.
     * Разделителем являются любые символы, кроме чисел и знака минус.
     * Точки и запятые тоже являются разделителями, десятичные числа
     * возвращиются как два натуральных числа.
     *
     * @param string
     * @throws InvalidValuesException, NumberFormatException
     */
    public void setArray(String string) throws InvalidValuesException, NumberFormatException {

        Matcher matcher = pattern.matcher(string);

        while (matcher.find() && integerList.size() < Terrain.getN() + 1) {
            String s = matcher.group();

            int i = Integer.parseInt(s);
            integerList.add(i);

            if (i > Terrain.getN() || i < 0 || integerList.size() > Terrain.getN()) {
                throw new InvalidValuesException("Invalid input data!");
            }
        }
    }

    public int[] getArray() {
        return integerList.stream().mapToInt(x -> x).toArray();
    }
}

