package by.darafeyeu.world;

import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class WorldMap {

    private Map<Coordinate, Entity> locationEntityMap = new HashMap<>();

    private static final int NULL_POINT_FOR_WORLD = 0;
    private final int sizeWidthX;
    private final int sizeHeightY;

    public WorldMap(int width, int height) {
        this.sizeWidthX = width;
        this.sizeHeightY = height;
    }

    public Optional<Coordinate> getOptionalCoordinateEntity(Entity entity)  {
        Set<Map.Entry<Coordinate, Entity>> entryLocationEntityMap = locationEntityMap.entrySet();

        for (Map.Entry<Coordinate, Entity> pair : entryLocationEntityMap) {
            if (entity == (pair.getValue())) {
                return Optional.ofNullable(new Coordinate(pair.getKey()));
            }
        }
                return Optional.empty();
    }

    public List<Entity> getEntities() {
        List<Entity> entityList = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> pair : locationEntityMap.entrySet()) {
            entityList.add(pair.getValue());
        }
        return entityList;
    }

    public Optional<Entity> getOptionalEntity(Coordinate currentCoordinate){
        if (isOccupiedCellInWorld(currentCoordinate)) {
            return Optional.ofNullable(locationEntityMap.get(currentCoordinate));
        }
        return Optional.ofNullable(null);
    }

    public void removeEntity(Coordinate currentCoordinate){
        if (isOccupiedCellInWorld(currentCoordinate)) {
            locationEntityMap.remove(currentCoordinate);
        }
    }

    public boolean addEntityInWorld(Coordinate currentCoordinate, Entity entity){
        if (checkEntity(entity) && !isOccupiedCellInWorld(currentCoordinate)) {
            locationEntityMap.put(currentCoordinate, entity);
            return true;
        }
        return false;
    }

    public boolean isOccupiedCellInWorld(Coordinate coordinate) {
        return (isValidCoordinate(coordinate) && locationEntityMap.containsKey(coordinate));
    }

    private boolean isValidCoordinate(Coordinate coordinate) {
        return isCoordinateInMap(optionalCoordinate(coordinate));
    }

    public boolean isCoordinateInMap(Coordinate coordinate) {
        int height = coordinate.getY();
        int length = coordinate.getX();
        return ((height >= NULL_POINT_FOR_WORLD && height <= this.sizeHeightY) &&
                (length >= NULL_POINT_FOR_WORLD && length <= this.sizeWidthX));
    }

    private boolean isCoordinateInMap(Optional<Coordinate> coordinate) {
        int height;
        int length;

        if (coordinate.isPresent()) {
            height = coordinate.get().getY();
            length = coordinate.get().getX();
        } else {
            return false;
        }

        return ((height >= NULL_POINT_FOR_WORLD && height <= this.sizeHeightY) &&
                (length >= NULL_POINT_FOR_WORLD && length <= this.sizeWidthX));
    }

    private Optional<Coordinate> optionalCoordinate(Coordinate currentCoordinate) {
        return Optional.ofNullable(currentCoordinate);
    }

    private boolean checkEntity(Entity entity) {
        return optionalEntity(entity).isPresent();
    }

    private Optional<Entity> optionalEntity(Entity entity) {
        return Optional.ofNullable(entity);
    }

    public int getSizeLength() {
        return sizeWidthX;
    }

    public int getSizeHeightY() {
        return sizeHeightY;
    }
}
