package by.darafeyeu.world;

import by.darafeyeu.exception.FreeCell;
import by.darafeyeu.exception.InvalidCoordinateException;
import by.darafeyeu.exception.InvalidEntityException;
import by.darafeyeu.exception.OutOfWorldBoundsException;
import by.darafeyeu.random_number.RandomNumber;
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

//todo - вставить, выдать одно существо и список всех хранимых существ, удалить
//оставить валидацию переработанную и методы обслуживающие туду
//все остальные разгрупировать по классам

public final class WorldMap {

    private Map<Coordinate, Entity> locationEntityMap = new HashMap<>();

    //todo задавать дефолтные значения в классе симуляция или меню
    private static final int DEFAULT_WIDTH_X = 9;
    private static final int DEFAULT_HEIGHT_Y = 9;
    private static final int MAX_WIDTH_X = 99;
    private static final int MAX_HEIGHT_Y = 99;

    private static final int NULL_POINT_FOR_WORLD = 0;


    //todo сохранять все трасеры животных в другом классе
    private Set<Coordinate> tracers = new HashSet<>();

    private final int sizeWidthX;
    private final int sizeHeightY;


    public WorldMap() {
        this(DEFAULT_WIDTH_X, DEFAULT_HEIGHT_Y);
    }

    //не создавать то что не просят обработать проверку в классе симуляция но лучше в меню .
    public WorldMap(int width, int height) {
        width = width - 1;
        height = height - 1;
        if ((width) < DEFAULT_WIDTH_X || (height) < DEFAULT_HEIGHT_Y) {
            this.sizeWidthX = DEFAULT_WIDTH_X;
            this.sizeHeightY = DEFAULT_HEIGHT_Y;
        } else if ((width) > MAX_WIDTH_X || (height) > MAX_HEIGHT_Y) {
            this.sizeWidthX = MAX_WIDTH_X;
            this.sizeHeightY = MAX_HEIGHT_Y;
        } else {
            this.sizeWidthX = width;
            this.sizeHeightY = height;
        }
    }

    //todo туда же куда и traser
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

    //todo туда же куда и traser
    protected Set<Coordinate> getTracers() {
        Set<Coordinate> coordinates = new HashSet<>();
        coordinates.addAll(tracers);
        return coordinates;
    }


    //todo in class work with work worldMap
    //в другой класс
    //класс работает со списком сущностей, и генерирует пустую координату.
    public Coordinate emptyRandomCoordinate() {
        while (true) {
            int length = RandomNumber.randomParamCoordinate(getSizeLength());
            int height = RandomNumber.randomParamCoordinate(getSizeHeightY());
            Coordinate randomCoordinate = new Coordinate(length, height);
            if (!locationEntityMap.containsKey(randomCoordinate)) {
                return randomCoordinate;
            }
        }
    }
    
    public List<Entity> getEntities() {
        List<Entity> entityList = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> pair : locationEntityMap.entrySet()) {
            entityList.add(pair.getValue());
        }
        return entityList;
    }

    //todo перекидываем в другой класс
    //класс который работает с существами и координатами
    public Coordinate getCoordinateEntity(Entity entity) throws InvalidCoordinateException {
        Set<Map.Entry<Coordinate, Entity>> entryLocationEntityMap = locationEntityMap.entrySet();

        for (Map.Entry<Coordinate, Entity> pair : entryLocationEntityMap) {
            if (entity == (pair.getValue())) {
                return new Coordinate(pair.getKey());
            }
        }
        throw new InvalidCoordinateException("Empty coordinate");
    }

    //todo создать метод возвращающей список сущностей

    //оставляем: выдать одну сущность
    public Entity getEntity(Coordinate currentCoordinate) throws OutOfWorldBoundsException,
            InvalidCoordinateException, FreeCell {
        if (isOccupiedCellInWorld(currentCoordinate)) {
            return locationEntityMap.get(currentCoordinate);
        }
        throw new FreeCell(currentCoordinate);
    }

    //оставляем тут
    public void removeEntity(Coordinate currentCoordinate) throws
            OutOfWorldBoundsException, InvalidCoordinateException {
        if (isOccupiedCellInWorld(currentCoordinate)) {
            locationEntityMap.remove(currentCoordinate);
        }
    }

    //todo мы вставляем сущность
    //остаеться в классе
    //todo избавиться от исключение
    public void putEntityInWorld(Coordinate currentCoordinate, Entity entity) throws InvalidCoordinateException,
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


    //todo переносим в другой класс
    // проверяет пуста ли наша координата

    private boolean isFreeCellInWorld(Coordinate coordinate) throws InvalidCoordinateException,
            OutOfWorldBoundsException {
        isValidCoordinate(coordinate);
        if (!isFreeCell(coordinate)) {
            return false;
        }
        return true;
    }


    //todo викидывает сообщение об ошибке переделать метод или переименовать
    public boolean isFreeCell(Coordinate coordinate) {
        return !locationEntityMap.containsKey(coordinate);
    }


    private boolean isValidCoordinate(Coordinate coordinate) throws InvalidCoordinateException, OutOfWorldBoundsException {
        if (!isCoordinateInMap(checkCoordinate(coordinate))) {
            throw new OutOfWorldBoundsException("Сoordinate is outside the map boundaries: " + coordinate);
        }
        return true;
    }


    //todo валидация координаты проверка ее на вход в карту
    private boolean isCoordinateInMap(Coordinate coordinate) {
        int height = coordinate.getY();
        int length = coordinate.getX();
        return ((height >= NULL_POINT_FOR_WORLD && height <= this.sizeHeightY) &&
                (length >= NULL_POINT_FOR_WORLD && length <= this.sizeWidthX));
    }


    //todo будем возвращать Optional
    private Coordinate checkCoordinate(Coordinate currentCoordinate) throws InvalidCoordinateException {
        return Optional.ofNullable(currentCoordinate)
                .orElseThrow(() -> new InvalidCoordinateException("Empty coordinate"));
    }

    //todo переделать на работу с Optional
    private Entity checkEntity(Entity entity) throws InvalidEntityException {
        return Optional.ofNullable(entity)
                .orElseThrow(() -> new InvalidEntityException("Empty Entity"));
    }

    public int getCountAllCell() {
        return (getSizeHeightY() + 1) * (getSizeLength() + 1);
    }

    public int getSizeLength() {
        return sizeWidthX;
    }

    public int getSizeHeightY() {
        return sizeHeightY;
    }

    //todo создать константу в render
    public int getNullPointForWorld() {
        return NULL_POINT_FOR_WORLD;
    }
}
