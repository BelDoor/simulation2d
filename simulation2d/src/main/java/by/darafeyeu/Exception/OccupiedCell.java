package by.darafeyeu.Exception;

import by.darafeyeu.coordinate.Coordinate;

public class OccupiedCell extends Exception {
    public OccupiedCell(Coordinate coordinate){
        super("Occupied" + coordinate.toString());
    }
}
