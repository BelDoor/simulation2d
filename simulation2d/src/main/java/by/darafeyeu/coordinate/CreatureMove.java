package by.darafeyeu.coordinate;

public enum CreatureMove {

    DOWN(new Coordinate(1, 0)),
    UP(new Coordinate(-1, 0)),
    LEFT(new Coordinate(0, -1)),
    RIGHT(new Coordinate(0, 1)),
    DOWN_RIGHT(new Coordinate(1, 1)),
    DOWN_LEFT(new Coordinate(1, -1)),
    UP_RIGHT(new Coordinate(-1, 1)),
    UP_LEFT(new Coordinate(-1, -1));
    private Coordinate coordinateMove;

    CreatureMove(Coordinate coordinate) {
        this.coordinateMove = coordinate;
    }

    public Coordinate getCoordinateMove() {
        return coordinateMove;
    }
}
