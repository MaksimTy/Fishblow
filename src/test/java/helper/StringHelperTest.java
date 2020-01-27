package helper;

import model.InvalidValuesException;
import model.Terrain;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;

public class StringHelperTest {

    private final Map<String, int[]> successSet = new HashMap<>();
    private final Map<String, int[]> invalidValuesSet = new HashMap<>();
    private final Map<String, int[]> invalidFormatSet = new HashMap<>();


    @Before
    public void setUp() {
        //успешные вырианты тестов
        successSet.put("1,2 3.4 - 5a6и7/8   9_10 100000",
                new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100000});
        int[] array1 = new int[Terrain.getN()];
        Arrays.fill(array1, Terrain.getN());
        successSet.put(Arrays.toString(array1), array1);

        //тесты с исключениями InvalidValuesException
        //1. Превышение размера массива
        int[] array2 = new int[Terrain.getN() + 1];
        Arrays.fill(array2, Terrain.getN());
        invalidValuesSet.put(Arrays.toString(array2), array2);
        //2. Превышение допустимого значения элемента массива
        invalidValuesSet.put("1, 100000", new int[]{1});
        //3. Отрицательные числа
        invalidValuesSet.put("1, -3", new int[]{1, 3});
        invalidValuesSet.put("1-3", new int[]{1, 3});

        //тесты с исключениями InvalidValuesException
        String string = "1,2,3,";
        invalidFormatSet.put(string + "2147483648", new int[]{1, 2, 3});
        invalidFormatSet.put(string + "-2147483649", new int[]{1, 2, 3});
    }

    @After
    public void tearDown() {
        successSet.clear();
        invalidValuesSet.clear();
        invalidFormatSet.clear();
    }

    @Test(expected = InvalidValuesException.class)
    public void TestSetArrayWithInvalidValuesException() throws InvalidValuesException {
        for (Map.Entry<String, int[]> instance : this.invalidValuesSet.entrySet()
        ) {
            StringHelper sh = new StringHelper();
            sh.setArray(instance.getKey());
            final int[] expecteds = instance.getValue();
            final int[] actual = sh.getArray();
            assertArrayEquals(expecteds, actual);
        }
    }

    @Test(expected = NumberFormatException.class)
    public void TestSetArrayWithNumberFormatException() throws InvalidValuesException {
        for (Map.Entry<String, int[]> instance : this.invalidFormatSet.entrySet()
        ) {
            StringHelper sh = new StringHelper();
            sh.setArray(instance.getKey());
            final int[] expecteds = instance.getValue();
            final int[] actual = sh.getArray();
            assertArrayEquals(expecteds, actual);
        }
    }


    @Test
    public void TestSetArray() throws InvalidValuesException {
        for (Map.Entry<String, int[]> instance : this.successSet.entrySet()
        ) {
            StringHelper sh = new StringHelper();
            sh.setArray(instance.getKey());
            final int[] expecteds = instance.getValue();
            final int[] actual = sh.getArray();
            assertArrayEquals(expecteds, actual);
        }
    }


}