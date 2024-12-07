package by.darafeyeu.world;

import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldMap {
    private Map<Coordinate, Entity> locationEntityMap = new HashMap<>();

    private static final int DEFAULT_LENGTH = 9;
    private static final int DEFAULT_HEIGHT = 9;
    private static final int START_COORDINATE = 0;

    private int sizeLength;
    private int sizeHeight;

    public WorldMap() {
        this.sizeLength = DEFAULT_LENGTH;
        this.sizeHeight = DEFAULT_HEIGHT;
    }

    public WorldMap(int length, int height) {
        this.sizeLength = DEFAULT_LENGTH;
        this.sizeHeight = DEFAULT_HEIGHT;
    }


//пробросить ошибку
    public Entity getEntity(Coordinate currentCoordinate){
            return locationEntityMap.get(currentCoordinate);
    }

    public void removeEntity(Coordinate currentCoordinate){
        locationEntityMap.remove(currentCoordinate);
    }

    public void putFigure(Coordinate currentCoordinate, Entity entity) {
        if (isValidCoordinateAndEntity(currentCoordinate, entity)) {
            locationEntityMap.put(currentCoordinate, entity);
        }
    }

    private boolean isValidCoordinateAndEntity(Coordinate currentCoordinate, Entity entity) {
        return isValidCoordinate(currentCoordinate) && !isObjectNull(entity)
                && isFreeSpace(currentCoordinate);
    }

    private boolean isValidCoordinate(Coordinate currentCoordinate){
        return !isObjectNull(currentCoordinate) && isCoordinateInMap(currentCoordinate);
    }

    private boolean isCoordinateInMap(Coordinate coordinate) {
        int heigt = coordinate.getHeight();
        int lenght = coordinate.getLength();
        return ((heigt >= START_COORDINATE && heigt <= this.sizeHeight) &&
                (lenght >= START_COORDINATE && lenght <= this.sizeLength));
    }


    private boolean isFreeSpace(Coordinate coordinate) {
        return !locationEntityMap.containsKey(coordinate);
    }

    private boolean isObjectNull(Object object) {
        return (object == null);
    }
}
