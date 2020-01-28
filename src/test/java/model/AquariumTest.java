package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Класс для тестирования публичных методов class Aquarium.
 */
public class AquariumTest {

    private final Map<int[], Integer> successSet = new HashMap<>();
    private final Map<int[], Integer> invalidValuesSet = new HashMap<>();

    @Before
    public void setUp() {
        successSet.put(new int[]{0, 0, 0, 0, 0}, 0);
        successSet.put(new int[]{6, 6, 6, 6, 1}, 0);
        successSet.put(new int[]{0, 0, 10, 0, 0}, 0);

        successSet.put(new int[]{0, 0, 0, 10, 1}, 0);
        successSet.put(new int[]{10, 0, 0, 0, 10}, 30);
        successSet.put(new int[]{4, 2, 3, 2, 5, 0, 1, 3}, 10);
        invalidValuesSet.put(new int[]{-4, 2, 3, 2, 5, 0, 1, 3}, -1);

        invalidValuesSet.put(new int[Terrain.getN() + 1], -1);
        invalidValuesSet.put(new int[]{Terrain.getN() + 1}, -1);
        successSet.put(new int[]{0, Terrain.getN()}, 0);

    }

    @After
    public void tearDown() {
        successSet.clear();
        invalidValuesSet.clear();
    }

    @Test(expected = InvalidValuesException.class)
    public void TestGetWaterWithInvalidValuesException() throws InvalidValuesException {
        for (Map.Entry<int[], Integer> entry : invalidValuesSet.entrySet()
        ) {
            final int[] test = entry.getKey();
            final Integer expecteds = entry.getValue();
            final Integer actual = new Aquarium(test).getWater();
            assertEquals(expecteds, actual);
        }
    }


    @Test
    public void TestGetWater() throws InvalidValuesException {
        for (Map.Entry<int[], Integer> entry : successSet.entrySet()
        ) {
            final int[] test = entry.getKey();
            final Integer expecteds = entry.getValue();
            final Integer actual = new Aquarium(test).getWater();
            assertEquals(expecteds, actual);
        }
    }
}