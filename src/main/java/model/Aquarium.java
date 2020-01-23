package model;

import java.util.ArrayList;
import java.util.List;

public class Aquarium {

    /**
     * Объект описывающий конфигурацию кубиков в аквариуме.
     */
    private Terrain terrain;
    /**
     * Список слоёв аквариума.
     */
    private List<Layer> layers;


    public Aquarium(int[] terrain) throws InvalidValuesException {
        this.terrain = new Terrain(terrain);
        if (this.terrain.getAquariumMatrix() != null) {
            this.layers = new ArrayList<>();
            this.fillAquarium();
        }
    }

    /**
     * Метод заполняет аквариум плитками Tile.
     */
    private void fillAquarium() {
        for (int i = 0; i < this.terrain.getHeight(); i++
        ) {
            this.layers.add(i, new Layer(this.terrain.getAquariumMatrix()[i]));
        }
    }

    /**
     * Метод возвращает количество воды в аквариуме.
     *
     * @return количество воды в аквариуме.
     * Если список слоёв пуст, то возвращается -1.
     */
    public int getWater() {
        if (this.layers == null) {
            return -1;
        }
        return this.layers.stream().map(x -> x.getWater()).reduce((a, b) -> a + b).get();
    }


}
