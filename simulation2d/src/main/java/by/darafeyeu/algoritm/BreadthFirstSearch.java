package by.darafeyeu.algoritm;

import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.coordinate.CreatureMove;
import by.darafeyeu.exception.FreeCell;
import by.darafeyeu.exception.InvalidCoordinateException;
import by.darafeyeu.exception.OutOfWorldBoundsException;
import by.darafeyeu.nature.Entity;
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

public class BreadthFirstSearch extends AlgorithmSearchPath {

    private List<Coordinate> randomCoordinate;
    private Coordinate targetCoordinate;
    private Map<Coordinate, Coordinate> beforeCells;
    private Queue<Coordinate> queueCell;
    private Coordinate currentCell;
    protected int optionOfStep;


    public BreadthFirstSearch(WorldMap worldMap, int countOfDirection) {
        super(worldMap);
        resetToDefault();
        optionOfStep = countOfDirection;
    }

    public BreadthFirstSearch(WorldMap worldMap) {
        super(worldMap);
        resetToDefault();
    }

    public List<Coordinate> getPath(Coordinate start, Class<? extends Entity> target, int speed) {
        resetToDefault();

        super.target = target;
        //будем возращать чистый путь, а в животном будем делать шаг на нужноую координату по скорости.
        super.speedAnimal = speed;
        this.currentCell = start;

        return searchPath();
    }

    private void resetToDefault() {

        Coordinate defaultCell = new Coordinate();
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
        for (Coordinate cellNeighbour : cellNeighbours(currentCell)) {
            if (beforeCells.containsKey(cellNeighbour)) {
                continue;
            }
            queueCell.add(cellNeighbour);
            beforeCells.put(cellNeighbour, currentCell);
        }
    }

    private boolean isTarget(Coordinate coordinate) {
        try {
            if (super.worldMap.getEntity(coordinate).getClass() == target) {
                return true;
            }
        } catch (FreeCell e) {
            return false;
        } catch (OutOfWorldBoundsException e) {
            return false;
        } catch (InvalidCoordinateException e) {
            return false;
        }
        return false;
    }

    //реализовать в отдельный клас для выдачи хода с рандомным путем
    private void saveRandomCoordinate(Coordinate currentCell) {
        int steepCurrentCell = currentCell.getStep();

        if (steepCurrentCell <= super.speedAnimal) {
            int steepRandomCoordinate = randomCoordinate.get(0).getStep();

            if (steepRandomCoordinate < steepCurrentCell) {
                randomCoordinate.clear();
                randomCoordinate.add(currentCell);
            } else if (steepCurrentCell == steepRandomCoordinate) {
                randomCoordinate.add(currentCell);
            }
        }
    }

    private List<Coordinate> cellNeighbours(Coordinate currentCell) {
        List<Coordinate> neighbours = new ArrayList<>();
        for (int i = 0; i < optionOfStep; i++) {
            Coordinate step = CreatureMove.values()[i].getCoordinateMove();
            step = step.addStep(currentCell);
            if (isCellEmptyOrTarget(step, target)) {
                Coordinate steepAlgorithm = new Coordinate(step);
                steepAlgorithm.steepCount(currentCell.getStep());
                neighbours.add(steepAlgorithm);
            }
        }
        return neighbours;
    }

    private List<Coordinate> pathToTarget() {
        if (isChengTargetCoordinate()) {
            return path(targetCoordinate);
        } else {
            return path(randomRoad());
        }
    }

    private List<Coordinate> path(Coordinate behindCell) {
        List<Coordinate> path = new ArrayList<>();
        while (beforeCells.get(behindCell) != null) {
            path.add(behindCell);
            behindCell = beforeCells.get(behindCell);
        }
        path.add(behindCell);
        Collections.reverse(path);
        return path;
    }

    private Coordinate randomRoad() {
        Random random = new Random();
        int numberRoad = random.nextInt(randomCoordinate.size());
        return randomCoordinate.get(numberRoad);
    }

    private boolean isChengTargetCoordinate() {
        if (targetCoordinate.getY() == -1) {
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
