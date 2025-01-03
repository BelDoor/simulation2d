package by.darafeyeu.coordinate;

import java.util.Objects;

public class Coordinate {
    private final Integer length;
    private final Integer height;

    @Override
    public String toString() {
        return String.format("Coordinate:\nLength %d \nHeight %d", length, height);
    }

    public Coordinate(Integer length, Integer height) {
        this.length = length;
        this.height = height;
    }

    public Coordinate(Coordinate coordinate) {
        this.length = coordinate.length;
        this.height = coordinate.height;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getHeight() {
        return height;
    }

    public Coordinate addStep(Coordinate move) {
        return new Coordinate(this.length + move.length,
                this.height + move.height);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(length, that.length) && Objects.equals(height, that.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, height);
    }
}
