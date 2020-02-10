package model;

import java.util.Arrays;

/**
 * Класс описывает объект "слой" - уровень аквариума,
 * заполенный состояними из перечисления Tile.
 */
public class Layer {

    private Tile[] layer;

    public Layer(int[] array) {
        this.fillLayerByWater(array);
    }

    /**
     * Метод заполняет слой аквариума плитками:
     * кубик, вода или пусто.
     *  //comment: пропущено описание параметра (раз уж по autodoc)
     * @param array
     */
    private void fillLayerByWater(int[] array) {    //comment: по англ верно layEr
        this.layer = new Tile[array.length];
        int leftBlock = array[0] == 1 ? 0 : -1; //указатель на левый кубик слоя
        //coment: Когда идут неочевидные алгоритмы с переборами и прочим - лучше подробно описать, что, как и почему.
        //если кто-то после тебя это будет дорабатывать - не вспомнит добрым словом :)
        for (int i = 1; i < array.length; i++) {
            if (array[i] == 1) {

                if (array[i] == array[i - 1] || leftBlock == -1) {
                    leftBlock = i;
                } else if (/*leftBlock != -1 &&*/ array[i - 1] == 0) {  //comment: IDEA подчеркивает, что первое условие всегда true - проверь
                    Arrays.fill(array, leftBlock + 1, i, Tile.Water.ordinal());
                    leftBlock = i;
                }
            }
        }
        for (int i = 0; i < this.layer.length; i++) {
            this.layer[i] = Tile.values()[array[i]];
        }
    }

    //comment: Пропущено описание возвращаемого значения
    /**
     * Метод подсчёта количества воды в слое.
     *
     * @return
     */
    public int getWater() {
        //comment: При работе со стримами старайся следующий шаг переносить, так и комментировать в случае необходимости легче
        return (int) Arrays.stream(this.layer)
                            .filter(x -> x == Tile.Water)
                            .count();
    }

    @Override
    public String toString() {
        return "Layer{" +
                Arrays.toString(layer) +
                '}';
    }

    public Tile[] getLayer() {
        return layer;
    }

    public int getSize() {
        return this.layer.length;
    }
}
