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

    private static final int NULL_POINT_FOR_WORLD = 0;
    private final int sizeWidthX;
    private final int sizeHeightY;

    //не создавать то что не просят обработать проверку в классе симуляция но лучше в меню .
    public WorldMap(int width, int height) {
        this.sizeWidthX = width;
        this.sizeHeightY = height;
    }

    //todo in class work with work worldMap
    //в другой класс
    //todo класс работает со списком сущностей, и генерирует пустую координату.
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


    //todo перекидываем в другой класс
    //класс который работает с существами и COORDINATE
    public Coordinate getCoordinateEntity(Entity entity) throws InvalidCoordinateException {
        Set<Map.Entry<Coordinate, Entity>> entryLocationEntityMap = locationEntityMap.entrySet();

        for (Map.Entry<Coordinate, Entity> pair : entryLocationEntityMap) {
            if (entity == (pair.getValue())) {
                return new Coordinate(pair.getKey());
            }
        }
        throw new InvalidCoordinateException("Empty coordinate");
    }

    //оставляем: выдать одну сущность

    public List<Entity> getEntities() {
        List<Entity> entityList = new ArrayList<>();
        for (Map.Entry<Coordinate, Entity> pair : locationEntityMap.entrySet()) {
            entityList.add(pair.getValue());
        }
        return entityList;
    }

    //todo в BreadFirstSearch нужно переделать метод is target так чтоб при пустой координате мы обрабатываои пустой оптинал

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

    //todo сделать проверку опшион который вернет  isValidCoordinate
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


    //todo возвращает Optionsl с нулем либо объектом
    private boolean isValidCoordinate(Coordinate coordinate) throws InvalidCoordinateException, OutOfWorldBoundsException {
        if (!isCoordinateInMap(checkCoordinate(coordinate))) {
            throw new OutOfWorldBoundsException("Сoordinate is outside the map boundaries: " + coordinate);
        }
        return true;
    }


    //клон isValidCoordinate
    private boolean isValidCoordinateNotException(Coordinate coordinate) {
        return !isCoordinateInMap(optionalCoordinate(coordinate));
    }

    //todo валидация координаты проверка ее на вход в карту
    private boolean isCoordinateInMap(Coordinate coordinate) {
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


    //todo будем возвращать Optional
    private Coordinate checkCoordinate(Coordinate currentCoordinate) throws InvalidCoordinateException {
        return Optional.ofNullable(currentCoordinate)
                .orElseThrow(() -> new InvalidCoordinateException("Empty coordinate"));
    }

    private Optional<Coordinate> optionalCoordinate(Coordinate currentCoordinate) {
        return Optional.ofNullable(currentCoordinate);
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
}
