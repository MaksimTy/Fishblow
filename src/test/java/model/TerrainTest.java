package model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class TerrainTest {

    private final int e = Tile.Empty.ordinal();
    private final int b = Tile.Brick.ordinal();
    private final int w = Tile.Water.ordinal();

    //успешные вырианты тестов
    private final ArrayList<int[]> successSetKeys = new ArrayList<>();
    private final ArrayList<int[][]> successSetValues = new ArrayList<>();
    //тесты с исключениями InvalidValuesException
    private final ArrayList<int[]> invalidValuesSetKeys = new ArrayList<>();
    private final ArrayList<int[][]> invalidValuesSetValues = new ArrayList<>();


    @Before
    public void setUpTerms() {
        //успешные вырианты тестов
        successSetKeys.add(0, new int[]{0, 0, 0, 0});
        successSetValues.add(0, new int[][]{
                {e, e, e, e}});

        successSetKeys.add(1, new int[]{0, 1, 2, 3});
        successSetValues.add(1, new int[][]{
                {e, e, e, b},
                {e, e, b, b},
                {e, b, b, b}});

        int[] arraySK2 = new int[Terrain.getN()];
        Arrays.fill(arraySK2, e);
        int[][] arraySV2 = new int[1][arraySK2.length];
        Arrays.fill(arraySV2[0], e);
        successSetKeys.add(2, arraySK2);
        successSetValues.add(2, arraySV2);

        //тесты с исключениями InvalidValuesException
        int[] arrayIK0 = new int[Terrain.getN() + 1];
        Arrays.fill(arrayIK0, e);
        invalidValuesSetKeys.add(0, arrayIK0);
        invalidValuesSetValues.add(0, null);

        int[] arrayIK1 = new int[Terrain.getN() + 1];
        Arrays.fill(arrayIK1, b);
        invalidValuesSetKeys.add(1, arrayIK1);
        invalidValuesSetValues.add(1, null);

        invalidValuesSetKeys.add(2, new int[]{-7, 0, 1, 5});
        invalidValuesSetValues.add(2, null);

        invalidValuesSetKeys.add(3, new int[]{0, 0, 0, Terrain.getN() + 1});
        invalidValuesSetValues.add(3, null);
    }

    @After
    public void tearDown() throws Exception {
        successSetKeys.clear();
        successSetValues.clear();
        invalidValuesSetKeys.clear();
        invalidValuesSetValues.clear();
    }

    @Test(expected = InvalidValuesException.class)
    public void TestGetAquriumMatrixWithInvalidValuesException() throws InvalidValuesException {
        for (int i = 0; i < invalidValuesSetKeys.size(); i++) {
            final int[] test = invalidValuesSetKeys.get(i);
            final int[][] expecteds = invalidValuesSetValues.get(i);
            Terrain terrain = new Terrain(test);
            final int[][] actual = terrain.getAquariumMatrix();
            assertArrayEquals(expecteds, actual);
        }
    }

    @Test
    public void TestGetAquriumMatrix() throws InvalidValuesException {
        for (int i = 0; i < successSetKeys.size(); i++) {
            final int[] test = successSetKeys.get(i);
            final int[][] expecteds = successSetValues.get(i);
            Terrain terrain = new Terrain(test);
            final int[][] actual = terrain.getAquariumMatrix();
            assertArrayEquals(expecteds, actual);
        }
    }
}
