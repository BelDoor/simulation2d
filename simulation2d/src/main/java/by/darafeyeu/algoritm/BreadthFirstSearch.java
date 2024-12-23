package by.darafeyeu.algoritm;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.coordinate.CoordinateForAlgoritm;
import by.darafeyeu.coordinate.CreatureMove;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.world.WorldMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

public class BreadthFirstSearch extends AlgoritmSearchPath {
    //todo сделать конструктор, наследовоние повтаряющиеся методы отправить в родителя.
    private Class<? extends Entity> target;

    private List<CoordinateForAlgoritm> randomCoordinate;
    private CoordinateForAlgoritm targetCoordinate;
    private Map<CoordinateForAlgoritm, CoordinateForAlgoritm> beforeCells;
    private Queue<CoordinateForAlgoritm> queueCell;
    private CoordinateForAlgoritm currentCell;
    private int speedAnimal;

    public BreadthFirstSearch(WorldMap worldMap) {
        super(worldMap);
        // this.worldMap = worldMap;
        resetToDefault();
    }

    public List<Coordinate> getPath(Animal animal) {
        resetToDefault();
        this.target = animal.getTargetFood();
        this.speedAnimal = animal.getSpeedStep();
        try {
            this.currentCell = new CoordinateForAlgoritm(getWorldMap().getCoordinateEntity(animal));
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        }
        return searchPath();
    }


    private void resetToDefault() {
        CoordinateForAlgoritm defaultCell = new CoordinateForAlgoritm();
        this.randomCoordinate = new ArrayList<>(Arrays.asList(defaultCell));
        this.targetCoordinate = defaultCell;
        this.beforeCells = new HashMap<>();
        this.queueCell = new LinkedList<>();
    }

    private List<Coordinate> searchPath() {
        queueCell.add(currentCell);
        beforeCells.put(currentCell, null);

        while (!queueCell.isEmpty()) {
            currentCell = queueCell.remove();
            if (isTarget(currentCell)) {
                targetCoordinate = currentCell;
                break;
            }
            saveRandomCoordinate(currentCell);
            saveCellNaighbour();
        }
        return pathToTarget();
    }

    private void saveCellNaighbour() {
        for (CoordinateForAlgoritm cellNeighbour : cellNeighbours(currentCell)) {
            if (beforeCells.containsKey(cellNeighbour)) {
                continue;
            }
            queueCell.add(cellNeighbour);
            beforeCells.put(cellNeighbour, currentCell);
        }
    }

    private boolean isTarget(CoordinateForAlgoritm coordinate) {
        try {
            if (getWorldMap().getEntity(coordinate).getClass() == target) {
                return true;
            }
        } catch (CellException e) {
            return false;
        } catch (OutOfWorldBoundsException e) {
            return false;
        } catch (InvalidCoordinateException e) {
            return false;
        }
        return false;
    }

    private void saveRandomCoordinate(CoordinateForAlgoritm currentCell) {
        int steepCurrentCell = currentCell.getStep();

        if (steepCurrentCell <= speedAnimal) {
            int steepRandomCoordinate = randomCoordinate.get(0).getStep();

            if (steepRandomCoordinate < steepCurrentCell) {
                randomCoordinate.clear();
                randomCoordinate.add(currentCell);
            } else if (steepCurrentCell == steepRandomCoordinate) {
                randomCoordinate.add(currentCell);
            }
        }

    }

    //может вернуть пустой лист
    public List<CoordinateForAlgoritm> cellNeighbours(CoordinateForAlgoritm currentCell) {
        List<CoordinateForAlgoritm> neighbours = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Coordinate step = CreatureMove.values()[i].getCoordinateMove();
            step = step.addStep(currentCell);
            if (isCellEmptyOrTarget(step, target)) {
                CoordinateForAlgoritm steepAlgoritm = new CoordinateForAlgoritm(step);
                steepAlgoritm.steepCount(currentCell.getStep());
                neighbours.add(steepAlgoritm);
            }
        }
        return neighbours;
    }

    //возможны проблемы с пустым массивом попробывать добавить стартовую ячейку
    //заменить нуль на проверку дефолтной координаты
    private List<Coordinate> pathToTarget() {
        if (isChengTargetCoordinate()) {
            return path(targetCoordinate);
        } else {
            return path(randomRoad());
        }
    }

    private List<Coordinate> path(CoordinateForAlgoritm behindCell) {
        List<Coordinate> path = new ArrayList<>();
        while (beforeCells.get(behindCell) != null) {
            path.add(behindCell);
            behindCell = beforeCells.get(behindCell);
        }
        path.add(behindCell);
        Collections.reverse(path);
        return path;
    }

    private CoordinateForAlgoritm randomRoad() {
        Random random = new Random();
        int numberRoad = random.nextInt(randomCoordinate.size());
        return randomCoordinate.get(numberRoad);
    }

    //проверка targetCoordinate координаты
    private boolean isChengTargetCoordinate() {
        if (targetCoordinate.getHeight() == -1) {
            return false;
        }
        return true;
    }

    public boolean isTargetCoordinate(Coordinate coordinate) {
        if (coordinate.equals(targetCoordinate)) {
            return true;
        }
        return false;
    }

}