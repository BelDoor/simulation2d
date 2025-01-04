package by.darafeyeu.world;

import by.darafeyeu.exception.FreeCell;
import by.darafeyeu.exception.InvalidCoordinateException;
import by.darafeyeu.exception.InvalidEntityException;
import by.darafeyeu.exception.OutOfWorldBoundsException;
import by.darafeyeu.check_action.CheckAction;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Animal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public final class WorldMap {
    private Map<Coordinate, Entity> locationEntityMap = new HashMap<>();
    private static final int DEFAULT_LENGTH = 9;
    private static final int DEFAULT_HEIGHT = 9;
    private static final int MAX_LENGTH = 99;
    private static final int MAX_HEIGHT = 99;
    private static final int START_COORDINATE = 0;

    private Set<Coordinate> tracers = new HashSet<>();

    private int sizeLength;
    private int sizeHeight;


    public WorldMap() {
        this.sizeLength = DEFAULT_LENGTH;
        this.sizeHeight = DEFAULT_HEIGHT;
    }

    public WorldMap(int length, int height) {
        length = length - 1;
        height = height - 1;
        if ((length) < DEFAULT_LENGTH || (height) < DEFAULT_HEIGHT) {
            this.sizeLength = DEFAULT_LENGTH;
            this.sizeHeight = DEFAULT_HEIGHT;
        } else if ((length) > MAX_LENGTH || (height) > MAX_HEIGHT) {
            this.sizeLength = MAX_LENGTH;
            this.sizeHeight = MAX_HEIGHT;
        } else {
            this.sizeLength = length;
            this.sizeHeight = height;
        }
    }

    protected void cleanTracers() {
        tracers.clear();
    }

    public void setTracers(List<Coordinate> allSteps) {
        for (int i = 0; i < allSteps.size(); i++) {
            Coordinate coordinate = allSteps.get(i);

            if (!tracers.contains(coordinate)) {
                tracers.add(coordinate);
            }
        }
    }

    protected Set<Coordinate> getTracers() {
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.addAll(tracers);
        return coordinates;
    }

    public Coordinate emptyRandomCoordinate() {
        while (true) {
            int length = CheckAction.randomParamCoordinate(getSizeLength());
            int height = CheckAction.randomParamCoordinate(getSizeHeight());
            Coordinate randomCoordinate = new Coordinate(length, height);
            if (!locationEntityMap.containsKey(randomCoordinate)) {
                return randomCoordinate;
            }
        }
    }

    public List<Animal> getAnimals() {
        List<Animal> animals = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> pair : locationEntityMap.entrySet()) {
            Entity entity = pair.getValue();
            if (entity instanceof Animal) {
                animals.add((Animal) entity);
            }
        }
        return animals;
    }

    public Coordinate getCoordinateEntity(Entity entity) throws InvalidCoordinateException {
        Set<Map.Entry<Coordinate, Entity>> entryLocationEntityMap = locationEntityMap.entrySet();

        for (Map.Entry<Coordinate, Entity> pair : entryLocationEntityMap) {
            if (entity == (pair.getValue())) {
                return new Coordinate(pair.getKey());
            }
        }
        throw new InvalidCoordinateException("Empty coordinate");
    }

    public Entity getEntity(Coordinate currentCoordinate) throws OutOfWorldBoundsException,
            InvalidCoordinateException, FreeCell {
        if (isOccupiedCellInWorld(currentCoordinate)) {
            return locationEntityMap.get(currentCoordinate);
        }
        throw new FreeCell(currentCoordinate);
    }

    public void removeEntity(Coordinate currentCoordinate) throws
            OutOfWorldBoundsException, InvalidCoordinateException {
        if (isOccupiedCellInWorld(currentCoordinate)) {
            locationEntityMap.remove(currentCoordinate);
        }
    }

    public void putFigure(Coordinate currentCoordinate, Entity entity) throws InvalidCoordinateException,
            InvalidEntityException, OutOfWorldBoundsException {
        checkEntity(entity);
        if (isFreeCellInWorld(currentCoordinate)) {
            locationEntityMap.put(currentCoordinate, entity);
        }
    }

    private boolean isOccupiedCellInWorld(Coordinate coordinate) throws InvalidCoordinateException,
            OutOfWorldBoundsException {
        isValidCoordinate(coordinate);
        if (!locationEntityMap.containsKey(coordinate)) {
            return false;
        }
        return true;
    }

    private boolean isFreeCellInWorld(Coordinate coordinate) throws InvalidCoordinateException,
            OutOfWorldBoundsException {
        isValidCoordinate(coordinate);
        if (!isFreeCell(coordinate)) {
            return false;
        }
        return true;
    }

    public boolean isFreeCell(Coordinate coordinate) {
        return !locationEntityMap.containsKey(coordinate);
    }

    private boolean isValidCoordinate(Coordinate coordinate) throws InvalidCoordinateException, OutOfWorldBoundsException {
        if (!isCoordinateInMap(checkCoordinate(coordinate))) {
            throw new OutOfWorldBoundsException("Сoordinate is outside the map boundaries: " + coordinate);
        }
        return true;
    }

    private boolean isCoordinateInMap(Coordinate coordinate) {
        int heigt = coordinate.getHeight();
        int lenght = coordinate.getLength();
        return ((heigt >= START_COORDINATE && heigt <= this.sizeHeight) &&
                (lenght >= START_COORDINATE && lenght <= this.sizeLength));
    }

    private Coordinate checkCoordinate(Coordinate currentCoordinate) throws InvalidCoordinateException {
        return Optional.ofNullable(currentCoordinate)
                .orElseThrow(() -> new InvalidCoordinateException("Empty coordinate"));
    }

    private Entity checkEntity(Entity entity) throws InvalidEntityException {
        return Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Empty Entity"));
    }

    public int getAllCell() {
        return (getSizeHeight() + 1) * (getSizeLength() + 1);
    }

    public int getSizeLength() {
        return sizeLength;
    }

    public int getSizeHeight() {
        return sizeHeight;
    }

    public int getStartCoordinate() {
        return START_COORDINATE;
    }
}
