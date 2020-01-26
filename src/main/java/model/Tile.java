package model;

import javafx.scene.image.Image;

/**
 * Класс представляет набор состояний клетки (единицы объёма) аквариума.
 */
public enum Tile {

    Empty,  // пустое пространство аквариума.
    Brick,  // кубик
    Water;  // вода



    @Override
    public String toString() {
        return String.valueOf(this.ordinal());
    }
}

