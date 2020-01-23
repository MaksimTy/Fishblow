package model;

/**
 * Класс представляет набор состояний клетки (единицы объёма) аквариума.
 */
public enum Tile {

    Empty("  "),  // пустое пространство аквариума.
    Brick("[]"),  // кубик
    Water("~~");  // вода

    String sign;

    Tile(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return String.valueOf(this.ordinal());
    }
}

