package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Класс для тестирования публичных методов class Layer.
 */
public class LayerTest {

    private final int e = Tile.Empty.ordinal();
    private final int b = Tile.Brick.ordinal();
    private final int w = Tile.Water.ordinal();

    private final Map<int[], Integer> terms = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        terms.put(new int[]{e,e,e,e,e},  0);
        terms.put(new int[]{b,e,e,e,e},  0);
        terms.put(new int[]{e,e,e,e,b},  0);
        terms.put(new int[]{e,e,b,e,e},  0);
        terms.put(new int[]{e,e,b,b,e},  0);
        terms.put(new int[]{b,b,e,e,e},  0);
        terms.put(new int[]{b,b,e,e,e},  0);
        terms.put(new int[]{e,e,e,b,b},  0);
        terms.put(new int[]{b,e,e,e,b},  3);
        terms.put(new int[]{b,e,b,e,b},  2);
        terms.put(new int[]{b,e,b,e,e},  1);
        terms.put(new int[]{e,e,b,e,b},  1);

    }

    @After
    public void tearDown() throws Exception {
        terms.clear();
    }

    @Test
    public void TestGetWater() {
        for (Map.Entry<int[], Integer> entry : terms.entrySet()
        ) {
            final int[] test = entry.getKey();
            final Integer expecteds = entry.getValue();
            final Integer actual = new Layer(test).getWater();
            assertEquals(expecteds, actual);
        }
    }
}