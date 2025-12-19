package nl.rensen.aoc2025.days;

import java.util.Objects;

class Coord{
    static int MAX;
    int x;
    int y;
    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }
    static void setMax(int max){
        MAX = max;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x && y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}