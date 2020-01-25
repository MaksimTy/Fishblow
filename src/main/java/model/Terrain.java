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
           // this.turnToLeftMatrix(getTerrainMatrix(this.terrain));
            this.aquariumMatrix = this.getMatrix();
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


    /**
     * Метод из исходного массива формирует матрицу аквариума.
     * @return
     */
    private int[][] getMatrix() {
        int[] unit = new int[this.width];
        Arrays.fill(unit, 1);
        int[][] matrix = new int[this.height][this.width];
        int counter = matrix.length;
        for (int i = counter - 1; i >= 0; i--) {
            unit = divArray(unit);
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = unit[j];
            }
        }
        return matrix;
    }

    /**
     * Метод вычитает из заданного массива натруральных числе
     * единичный массив той же длины.
     * @param line
     * @return
     */
    private int[] divArray(int[] line) {
        for (int i = 0; i < this.terrain.length; i++) {
            if (this.terrain[i] == 0) {
                line[i] = 0;
            }
            if (this.terrain[i] > 0) {
                this.terrain[i] = this.terrain[i] - line[i];
            }
        }
        return line;
    }

}
