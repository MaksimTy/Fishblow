package model;

import java.util.Arrays;

/**
 * Класс трансформирует исходный массив высот кубиков
 * в матрицу высотой равной максимальному элементу
 * исходного массива и шириной равно длине исходного
 * массива.
 * В случае передачи массива нулевых значений будет
 * возвращена матрица высотой 1.
 * Места кубиков отмечаются порядковым номером Tile.Brick,
 * все остальные координаты - порядковым номером Tile.Empty.
 */
public class Terrain {

    /**
     * предельная ширина или высота аквариума
     */
    private final static int N = 100_000;
    /**
     * terrain последовательная высота столбцов в аквариуме.
     */
    private int[] terrain;
    /**
     * высота аквариума
     */
    private final int height;
    /**
     * ширина аквариума
     */
    private final int width;
    /**
     * aquariumMatrix матрица столбцов  в аквариуме .
     * высота матрицы height - максимум массива terrain
     * ширина матрицы width - длина массива terrain
     * место кубика отмечено порядковым номером Tile.Brick.
     */
    private int[][] aquariumMatrix;

    public Terrain(int[] terrain) {
        this.terrain = terrain;
        int maxItem = Arrays.stream(this.terrain).max().getAsInt();

        this.height = maxItem <= 0 ? 1 : maxItem;
        this.width = this.terrain.length;

        if (isTerrainValid()) {
            this.turnToLeftMatrix(getTerrainMatrix(this.terrain));
        } else {
            try {
                throw new InvalidValuesException("Invalid input data!");
            } catch (InvalidValuesException e) {
                this.aquariumMatrix = null;
            }
        }
    }

    /**
     * Метод проверки корректности исходных данных.
     * Исходные данные считаются корректным, если сумма высоты и ширины
     * аквариума не больше N, и если в исходном массиве отсутствуют
     * отрицательные число.
     *
     * @return true - если входные данные соответствуют критерию,
     * false - если не соотвтетствуют.
     */
    private boolean isTerrainValid() {
        return this.width <= N
                && this.height <= N
                && Arrays.stream(this.terrain).min().getAsInt() >= 0;
    }

    /**
     * Метод формирует матрицу расположения кубиков в аквариуме.
     * (получается аквариум, лежащий на правом боку)
     *
     * @param terrain последовательная высота столбцов в  аквариуме.
     * @return
     */
    private int[][] getTerrainMatrix(int[] terrain) {
        int[][] terrainMatrix = new int[terrain.length][this.height];

        for (int i = 0; i < this.terrain.length; i++) {
            Arrays.fill(terrainMatrix[i], Tile.Empty.ordinal());
            int count = this.terrain[i] < 0 ? 0 : this.terrain[i];
            Arrays.fill(terrainMatrix[i], 0, count, Tile.Brick.ordinal());
        }
        return terrainMatrix;
    }

    /**
     * Метод разворачивает исходную матрицу  на 90 градусов
     * против часовой стрелки и иницализирует поле "aquariumMatrix".
     * (аквариум переворачивается с правого бока на дно).
     *
     * @param matrix
     */
    private void turnToLeftMatrix(int[][] matrix) {

        this.aquariumMatrix = new int[this.height][matrix.length];
        for (int i = this.height - 1, h = 0; i >= 0; i--, h++) {
            int[] row = new int[matrix.length];
            for (int j = matrix.length - 1; j >= 0; j--) {
                row[j] = matrix[j][i];
            }
            this.aquariumMatrix[h] = row;
        }
    }


    public int getHeight() {
        return height;
    }


    public int[][] getAquariumMatrix() {
        return aquariumMatrix;
    }

    public static int getN() {
        return N;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : this.aquariumMatrix
        ) {
            stringBuilder.append(Arrays.toString(row)).append("\n");
        }
        return stringBuilder.toString();
    }
}
