package by.darafeyeu.world;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.check_action.CheckAction;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Animal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

//создавать чере сингелтон
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
        length = length - 1;
        height = height - 1;
        if ((length) < DEFAULT_LENGTH) {
            this.sizeLength = DEFAULT_LENGTH;
        }
        if ((height) < DEFAULT_HEIGHT) {
            this.sizeHeight = DEFAULT_HEIGHT;
        }
        this.sizeLength = length;
        this.sizeHeight = height;
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

    //метод для поиска кординаты по сущности
    public Coordinate getCoordinateEntity(Entity entity) throws InvalidCoordinateException {
        Set<Map.Entry<Coordinate, Entity>> entryLocationEntityMap = locationEntityMap.entrySet();

        for (Map.Entry<Coordinate, Entity> pair : entryLocationEntityMap) {
            if (entity.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        throw new InvalidCoordinateException("Empty coordinate");
    }

    public Entity getEntity(Coordinate currentCoordinate) throws CellException,
            OutOfWorldBoundsException, InvalidCoordinateException {
        isOccupiedCellInWorld(currentCoordinate);
        return locationEntityMap.get(currentCoordinate);
    }

    public void removeEntity(Coordinate currentCoordinate) throws CellException,
            OutOfWorldBoundsException, InvalidCoordinateException {
        if (isOccupiedCellInWorld(currentCoordinate)) {
            locationEntityMap.remove(currentCoordinate);
        }
    }

    public void putFigure(Coordinate currentCoordinate, Entity entity) throws InvalidCoordinateException,
            InvalidEntityException, OutOfWorldBoundsException, CellException {
        checkEntity(entity);
        if (isFreeCellInWorld(currentCoordinate)) {
            locationEntityMap.put(currentCoordinate, entity);
        }
    }

    private boolean isOccupiedCellInWorld(Coordinate coordinate) throws InvalidCoordinateException,
            OutOfWorldBoundsException, CellException {
        isValidCoordinate(coordinate);
        if (!locationEntityMap.containsKey(coordinate)) {
            throw new CellException("Cell Free " + coordinate);
        }
        return true;
    }

    private boolean isFreeCellInWorld(Coordinate coordinate) throws InvalidCoordinateException,
            OutOfWorldBoundsException, CellException {
        isValidCoordinate(coordinate);
        if (!isFreeCell(coordinate)) {
            throw new CellException("Cell occupied " + coordinate);
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

    public int getAllCell(){
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
