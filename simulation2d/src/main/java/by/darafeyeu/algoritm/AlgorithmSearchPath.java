package by.darafeyeu.algoritm;

import by.darafeyeu.exception.FreeCell;
import by.darafeyeu.exception.InvalidCoordinateException;
import by.darafeyeu.exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.world.WorldMap;

import java.util.List;

public abstract class AlgorithmSearchPath {
    protected final WorldMap worldMap;
    protected Class<? extends Entity> target;
    protected int speedAnimal;

    protected AlgorithmSearchPath(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public abstract List<Coordinate> getPath(Coordinate start, Class<? extends Entity> target, int speed);

    protected boolean isCellEmptyOrTarget(Coordinate coordinate, Class<? extends Entity> target) {
        Entity entityForCell;
        try {
            entityForCell = worldMap.getEntity(coordinate);
        } catch (FreeCell e) {
            return true;
        } catch (OutOfWorldBoundsException e) {
            return false;
        } catch (InvalidCoordinateException e) {
            return false;
        }
        if (target == entityForCell.getClass()) {
            return true;
        }
        return false;
    }

    public abstract boolean isTargetCoordinate(Coordinate coordinate);
}
