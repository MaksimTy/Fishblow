package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Класс для тестирования публичных методов class Layer.
 */
public class LayerTest {

    private final int e = Tile.Empty.ordinal();
    private final int b = Tile.Brick.ordinal();
    private final int w = Tile.Water.ordinal();


    private final ArrayList<int[]> successSetKeys = new ArrayList<>();
    private final ArrayList<Integer> successSetValues = new ArrayList<>();


    @Before
    public void setUp() throws Exception {

        successSetKeys.add(0, new int[]{e, e, e, e, e});
        successSetValues.add(0, 0);
        successSetKeys.add(1, new int[]{b, e, e, e, e});
        successSetValues.add(1, 0);
        successSetKeys.add(2, new int[]{e, e, e, e, b});
        successSetValues.add(2, 0);
        successSetKeys.add(3, new int[]{e, e, b, e, e});
        successSetValues.add(3, 0);
        successSetKeys.add(4, new int[]{e, e, b, b, e});
        successSetValues.add(4, 0);
        successSetKeys.add(5, new int[]{b, b, e, e, e});
        successSetValues.add(5, 0);
        successSetKeys.add(6, new int[]{e, e, e, b, b});
        successSetValues.add(6, 0);
        successSetKeys.add(7, new int[]{b, e, e, e, b});
        successSetValues.add(7, 3);
        successSetKeys.add(8, new int[]{b, e, b, e, b});
        successSetValues.add(8, 2);
        successSetKeys.add(9, new int[]{b, e, b, e, e});
        successSetValues.add(9, 1);
        successSetKeys.add(10, new int[]{e, e, b, e, b});
        successSetValues.add(10, 1);
    }

    @After
    public void tearDown() throws Exception {
        successSetKeys.clear();
        successSetValues.clear();
    }

    @Test
    public void TestGetWater() {
        for (int i = 0; i < successSetKeys.size(); i++) {
            final int[] test = successSetKeys.get(i);
            final Integer expecteds = successSetValues.get(i);
            final Integer actual = new Layer(test).getWater();
            assertEquals(expecteds, actual);
        }
    }
}