package model;

import java.util.ArrayList;
import java.util.List;

//comment: не критично, но постоянное мелькание в описаниях "класс делает, метод делает..." и т.д., мне кажется, можно не указывать.
//достаточно сразу описывать суть.
/**
 * АКвариум представляет собой упорядоченный список
 * слоёв из состояний клеток, заданных матрицей
 * из класса Terrain.
 */
public class Aquarium {

    /**
     * Объект описывающий конфигурацию кубиков в аквариуме.
     */
    private Terrain terrain;

    /**
     * Список слоёв аквариума.
     */
    private List<Layer> layers;


    public Aquarium(int[] terrain) throws NumberFormatException {
        this.terrain = new Terrain(terrain);
        if (this.terrain.getAquariumMatrix() != null) {
            this.layers = new ArrayList<>();
            this.fillAquarium();
        }
    }

    /**
     * Заполняет аквариум плитками Tile.
     */
    private void fillAquarium() {
        for (int i = 0; i < this.terrain.getHeight(); i++
        ) {
            this.layers.add(i, new Layer(this.terrain.getAquariumMatrix()[i]));
        }
    }

    /**
     * Вычисляет количество воды в аквариуме.
     * @return количество воды в аквариуме.
     * Если список слоёв пуст, то возвращается -1.
     */
    public int getWater() {
        if (this.layers == null) {
            return -1;
        }
        return this.layers.stream()
                .map(Layer::getWater)
                .reduce(Integer::sum)
                .get(); //Comment: Idea пишет ворнинг - проверить
    }

//comment: я иногда отделяю геттеры и сеттеры длинной чертой, чтобы, если методов дофига - не рябило в глазах
//GET and SET=================================================

    public int getWidth() {
        return this.terrain.getWidth();
    }

    public int getHeight(){
        return this.terrain.getHeight();
    }


    public List<Layer> getLayers() {
        return layers;
    }
}
