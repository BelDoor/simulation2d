package by.darafeyeu.exception;

import by.darafeyeu.coordinate.Coordinate;

public class FreeCell extends RuntimeException{
    public FreeCell(Coordinate coordinate){
        super("Free Cell" + coordinate);
    }
}
