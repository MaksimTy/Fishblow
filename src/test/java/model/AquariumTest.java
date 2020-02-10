package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Класс для тестирования публичных методов class Aquarium.
 */
public class AquariumTest {
    //успешные вырианты тестов
    private final ArrayList<int[]> successSetKeys = new ArrayList<>();
    private final ArrayList<Integer> successSetValues = new ArrayList<>();
    //тесты с исключениями NumberFormatException
    private final ArrayList<int[]> invalidValuesSetKeys = new ArrayList<>();
    private final ArrayList<Integer> invalidValuesSetValues = new ArrayList<>();


    @Before
    public void setUp() {
        //успешные вырианты тестов
        successSetKeys.add(0, new int[]{0, 0, 0, 0, 0});
        successSetValues.add(0, 0);
        successSetKeys.add(1, new int[]{6, 6, 6, 6, 1});
        successSetValues.add(1, 0);
        successSetKeys.add(2, new int[]{0, 0, 10, 0, 0});
        successSetValues.add(2, 0);
        successSetKeys.add(3, new int[]{0, 0, 0, 10, 1});
        successSetValues.add(3, 0);
        successSetKeys.add(4, new int[]{10, 0, 0, 0, 10});
        successSetValues.add(4, 30);
        successSetKeys.add(5, new int[]{4, 2, 3, 2, 5, 0, 1, 3});
        successSetValues.add(5, 10);
        successSetKeys.add(6, new int[]{0, Terrain.getSizeLimit()});
        successSetValues.add(6, 0);

        //тесты с исключениями NumberFormatException
        invalidValuesSetKeys.add(0, new int[]{-4, 2, 3, 2, 5, 0, 1, 3});
        invalidValuesSetValues.add(0, -1);
        invalidValuesSetKeys.add(1, new int[Terrain.getSizeLimit() + 1]);
        invalidValuesSetValues.add(1, -1);
        invalidValuesSetKeys.add(2, new int[]{Terrain.getSizeLimit() + 1});
        invalidValuesSetValues.add(2, -1);
    }

    @After
    public void tearDown() {
        successSetKeys.clear();
        successSetValues.clear();
        invalidValuesSetKeys.clear();
        invalidValuesSetValues.clear();
    }

    @Test(expected = NumberFormatException.class)
    public void TestGetWaterWithNumberFormatException() throws NumberFormatException {
        for (int i = 0; i < invalidValuesSetKeys.size(); i++) {
            final int[] test = invalidValuesSetKeys.get(i);
            final Integer expecteds = invalidValuesSetValues.get(i);
            final Integer actual = new Aquarium(test).getWater();
            assertEquals(expecteds, actual);
        }
    }

    @Test
    public void TestGetWater() throws NumberFormatException {
        for (int i = 0; i < successSetKeys.size(); i++) {
            final int[] test = successSetKeys.get(i);
            final Integer expecteds = successSetValues.get(i);
            final Integer actual = new Aquarium(test).getWater();
            assertEquals(expecteds, actual);
        }
    }
}