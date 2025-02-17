package by.darafeyeu.coordinate;

import java.util.Objects;

public class Coordinate {
    private final int x;
    private final int y;

    private static final int DEFAULT_STEP = -1;
    private static final int PLUS_ONE_ROUND = 1;

    private int step;

    @Override
    public String toString() {
        return String.format("Coordinate:\nLength %d \nHeight %d", x, y);
    }

    public Coordinate(){
        this(DEFAULT_STEP, DEFAULT_STEP);
        this.step = DEFAULT_STEP;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate coordinate) {
        this(coordinate.x, coordinate.y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getStep() {
        return step;
    }

    public Coordinate foldCoordinate(Coordinate move) {
        return new Coordinate(this.x + move.x,
                this.y + move.y);
    }

    public void steepCount(int step) {
        this.step = step + PLUS_ONE_ROUND;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
