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
    private final static int SIZE_LIMIT = 100_000;  //comment: избегай однобуквенных наименований

    /**
     * последовательная высота столбцов в аквариуме.
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

    public Terrain(int[] terrain) throws NumberFormatException {
        this.terrain = terrain;
        int maxItem = Arrays.stream(this.terrain)
                            .max()
                            .getAsInt();    //comment: Idea ругается, разобраться.

        this.height = maxItem <= 0 ? 1 : maxItem;
        this.width = this.terrain.length;

        if (isTerrainValid()) {
            this.aquariumMatrix = this.getMatrix();
        } else {
            this.aquariumMatrix = null;

            throw new NumberFormatException("Invalid input data!"); //comment: Есть родной IllegalArgumentException,
            // не обязательно ради 1 исключения класс тут создавать, мне кажется

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
        return this.width <= SIZE_LIMIT
                && this.height <= SIZE_LIMIT
                && Arrays.stream(this.terrain).min().getAsInt() >= 0;   //Comment: idea warning ругается, разобраться.
    }


    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int[][] getAquariumMatrix() {
        return aquariumMatrix;
    }

    public static int getSizeLimit() {
        return SIZE_LIMIT;
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

    //comment: Пропущено возвращаемое значение в описании
    /**
     * Метод из исходного массива формирует матрицу аквариума.
     *
     * @return
     */
    private int[][] getMatrix() {
        int[] unit = new int[this.width];
        Arrays.fill(unit, 1);
        int[][] matrix = new int[this.height][this.width];
        int counter = matrix.length;
        for (int i = counter - 1; i >= 0; i--) {
            unit = divArray(unit);  //Idea пишет, что переменной уже назначено это значение
            System.arraycopy(unit, 0, matrix[i], 0, matrix[i].length);  //Comment: Idea предлагает так. Вроде, будет быстрее
        }
        return matrix;
    }

    //Comment: Пропущены параметры и возвращаемое значение
    /**
     * Метод вычитает из заданного массива натуральных чисел    //Comment: не совсем однозначное описание. что значит вычесть массив из массива?
     * единичный массив той же длины.
     *
     * @param line
     * @return
     */
    private int[] divArray(int[] line) {
        //Comment: Опиши алгоритм, если считаешь, что чтобы его понять стороннему человеку нужно больше времени без описания, чем с ним
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
