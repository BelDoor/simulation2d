package by.darafeyeu.algoritm;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.coordinate.CoordinateForAlgoritm;
import by.darafeyeu.coordinate.CreatureMove;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.Rock;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.nature.entity.Tree;
import by.darafeyeu.world.WorldMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class BreadthFirstSearch implements AlgoritmSearchPath {

    private Class<? extends Entity> target = Grass.class;
    private WorldMap worldMap;

    public List<Coordinate> searchPath(Coordinate start, WorldMap worldMap) {

        this.worldMap = worldMap;

        Queue<CoordinateForAlgoritm> queue = new LinkedList<>();
        //todo попробывать создать наследника coordinate с полем Сoordinate befor и заменить map одним set
        //также добавить дефолтное значение для проверки
        Set<CoordinateForAlgoritm> consideredCells = new HashSet<>();
        Map<CoordinateForAlgoritm, CoordinateForAlgoritm> beforeCells = new HashMap<>();

        CoordinateForAlgoritm targetCoordinate = new CoordinateForAlgoritm(-1, -1);
        CoordinateForAlgoritm currentCell = new CoordinateForAlgoritm(start);
        List<CoordinateForAlgoritm> randomCoordinate = new ArrayList<>(Arrays.asList(new CoordinateForAlgoritm(-1, -1)));

        queue.add(currentCell);
        consideredCells.add(currentCell);
        beforeCells.put(currentCell, null);

        while (!queue.isEmpty()) {
            //проверяем может это наша цель
            currentCell = queue.remove();

            if (isTarget(currentCell)) {
                targetCoordinate = currentCell;
                break;
            }

            if (currentCell.getSteep() <= 5) {
                if (randomCoordinate.get(0).getSteep() < currentCell.getSteep()) {
                    randomCoordinate.clear();
                    randomCoordinate.add(currentCell);
                } else if (currentCell.getSteep() == randomCoordinate.get(0).getSteep()) {
                    randomCoordinate.add(currentCell);
                }

            }

            //todo проверка на нуль если ячейка занята закидываем ее в проверенные и кидаем continue
         //   List<CoordinateForAlgoritm> neighbours = cellNeighbours(currentCell);
            //проверка была ли осмотренна
            //пометим как просмотренную
            //добавляем в очередь соседей, в мап по ключу пишим новыую координату в значение текущую
            for (CoordinateForAlgoritm cellNeighbour : cellNeighbours(currentCell)) {
                if (consideredCells.contains(cellNeighbour)) {
                    continue;
                }
                queue.add(cellNeighbour);
                //todo сделать координату с сылкой на предка упразнить мап делать все через сет
                consideredCells.add(cellNeighbour);
                beforeCells.put(cellNeighbour, currentCell);
            }
        }


        //проверка не вернулась ли координата дефолтная
        //если цель не нашлась вернуть координаты на количество ходов сущности
        //запокавать все в метод
        List<Coordinate> path = new ArrayList<>();
        if (isChengedTargeetCoordinate(targetCoordinate)) {
            CoordinateForAlgoritm beforeTargetCoordinate = targetCoordinate;
            while (beforeCells.get(beforeTargetCoordinate) != null) {
                path.add(beforeTargetCoordinate);
                beforeTargetCoordinate = beforeCells.get(beforeTargetCoordinate);
            }
            Collections.reverse(path);
            return path;
        } else {
            CoordinateForAlgoritm beforeTargetCoordinate = randomCoordinate.get(0);
            while (beforeCells.get(beforeTargetCoordinate) != null) {
                path.add(beforeTargetCoordinate);
                beforeTargetCoordinate = beforeCells.get(beforeTargetCoordinate);
            }
            Collections.reverse(path);
            return path;
        }

    }

    private boolean isTarget(Coordinate coordinate) {
        try {
            if (worldMap.getEntity(coordinate).getClass() == Grass.class) {
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

    //может вернуть пустой лист
    public List<CoordinateForAlgoritm> cellNeighbours(CoordinateForAlgoritm currentCell) {
        List<CoordinateForAlgoritm> neighbours = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Coordinate steep = CreatureMove.values()[i].getCoordinateMove();
            steep = steep.addStep(currentCell);
            if (isCellEmptyOrTarget(steep)) {
                CoordinateForAlgoritm steepAlgoritm = new CoordinateForAlgoritm(steep);
                steepAlgoritm.steepCount(currentCell.getSteep());
                neighbours.add(steepAlgoritm);
            }
        }
        return neighbours;
    }

    //todo сделать уневерсальным для хищников и трв. и вынести в абстрактный класс.
    private boolean isCellEmptyOrTarget(Coordinate coordinate) {
        Entity entityForCell = null;
        try {
            entityForCell = worldMap.getEntity(coordinate);
        } catch (CellException e) {
            return true;
        } catch (OutOfWorldBoundsException e) {
            return false;
        } catch (InvalidCoordinateException e) {
            return false;
        }
        //   if (target == Grass.class) {
        return !(entityForCell instanceof Animal || entityForCell instanceof Rock || entityForCell instanceof Tree);
        //  }
        //  return false;
    }

    //проверка targetCoordinate координаты
    boolean isChengedTargeetCoordinate(CoordinateForAlgoritm coordinate) {
        if (coordinate.getHeight() == -1) {
            return false;
        }
        return true;
    }

}
