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

    private final Map<int[], Integer> terms = new HashMap<>();

    @Before
    public void setUp() {
        terms.put(new int[]{0, 0, 0, 0, 0}, 0);
        terms.put(new int[]{6, 6, 6, 6, 1}, 0);
        terms.put(new int[]{0, 0, 10, 0, 0}, 0);

        terms.put(new int[]{0, 0, 0, 10, 1}, 0);
        terms.put(new int[]{10, 0, 0, 0, 10}, 30);
        terms.put(new int[]{4, 2, 3, 2, 5, 0, 1, 3}, 10);
        terms.put(new int[]{-4, 2, 3, 2, 5, 0, 1, 3}, -1);

        terms.put(new int[Terrain.getN() + 1], -1);
        terms.put(new int[]{Terrain.getN() + 1}, -1);
        terms.put(new int[]{0, Terrain.getN()}, 0);

    }

    @After
    public void tearDown() {
        terms.clear();
    }

    @Test
    public void TestGetWater() throws InvalidValuesException {
        for (Map.Entry<int[], Integer> entry : terms.entrySet()
        ) {
            final int[] test = entry.getKey();
            final Integer expecteds = entry.getValue();
            final Integer actual = new Aquarium(test).getWater();
            assertEquals(expecteds, actual);
        }
    }
}