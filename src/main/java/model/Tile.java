package model;

/**
 * Класс представляет набор состояний клетки (единицы объёма) аквариума.
 */
public enum Tile {

    Empty("WHITE"),     // пустое пространство аквариума.
    Brick("BROWN"),     // кубик
    Water("BLUE");      // вода

    private String color;

    Tile(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return String.valueOf(this.ordinal());
    }
}

