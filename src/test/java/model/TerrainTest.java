package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;


public class TerrainTest {

    private final Map<int[], int[][]> terms = new HashMap<>();

    private final int e = Tile.Empty.ordinal();
    private final int b = Tile.Brick.ordinal();
    private final int w = Tile.Water.ordinal();

    @Before
    public void setUpTerms() {
        /** если входной массив - нулевой,
         то возвращается нулевая матрица р
         высотой 1
         */
        terms.put(
                new int[]{0, 0, 0, 0},
                new int[][]{
                        {e, e, e, e}});
        terms.put(
                new int[]{0, 1, 2, 3},
                new int[][]{
                        {e, e, e, b},
                        {e, e, b, b},
                        {e, b, b, b}});
        /**
         * если
         */
        terms.put(
                new int[]{-7, 0, 1, 5},
                null);
        terms.put(
                new int[]{0, 0, 0, Terrain.getN() + 1},
                null);

        int[] array1 = new int[Terrain.getN() + 1];
        Arrays.fill(array1, e);
        terms.put(array1, null);

        int[] array2 = new int[Terrain.getN() + 1];
        Arrays.fill(array2, b);
        terms.put(array2, null);

        int[] array3 = new int[Terrain.getN() ];
        Arrays.fill(array3, e);
        int[][] array31 = new int[1][array3.length];
        Arrays.fill(array31[0], e);
        terms.put(array3, array31);
    }

    @After
    public void tearDown() throws Exception {
        terms.clear();
    }


    @Test
    public void TestGetAquriumMatrix() {
        for (Map.Entry<int[], int[][]> entry : terms.entrySet()
        ) {
            final int[] test = entry.getKey();
            final int[][] expecteds = entry.getValue();
            Terrain terrain = new Terrain(test);
            final int[][] actual = terrain.getAquariumMatrix();

            assertArrayEquals(expecteds, actual);
        }
    }
}
