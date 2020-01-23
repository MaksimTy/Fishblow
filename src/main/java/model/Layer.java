package model;

import java.util.Arrays;

/**
 * Класс описывает объект "слой" - уровень аквариума,
 * заполенный состояними из перечисления Tile.
 */
public class Layer {

    private Tile[] layer;

    public Layer(int[] array) {
        this.fillLayrByWater(array);
    }

    /**
     * Метод заполняет слой аквариума плитками:
     * кубик, вода или пусто.
     *
     * @param array
     * @return
     */
    private void fillLayrByWater(int[] array) {
        this.layer = new Tile[array.length];
        int leftBlock = array[0] == 1 ? 0 : -1; //указатель на левый кубик слоя
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 1) {

                if (array[i] == array[i - 1] || leftBlock == -1) {
                    leftBlock = i;
                } else if (leftBlock != -1 && array[i - 1] == 0) {
                    Arrays.fill(array, leftBlock + 1, i, Tile.Water.ordinal());
                    leftBlock = i;
                }
            }
        }
        for (int i = 0; i < this.layer.length; i++) {
            this.layer[i] = Tile.values()[array[i]];
        }
    }

    /**
     * Метод подсчёта количества воды в слое.
     *
     * @return
     */
    public int getWater() {
        return (int) Arrays.stream(this.layer).filter(x -> x == Tile.Water).count();
    }

    @Override
    public String toString() {
        return "Layer{" +
                Arrays.toString(layer) +
                '}';
    }
}
