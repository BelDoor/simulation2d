package by.darafeyeu.algoritm;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.FreeCell;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.entity.Rock;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.nature.animals.Bear;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.nature.entity.Tree;
import by.darafeyeu.world.WorldMap;

import java.util.List;

public abstract class AlgoritmSearchPath {
    protected final WorldMap worldMap;
    protected Class<? extends Entity> target;
    protected int speedAnimal;

    protected AlgoritmSearchPath(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public abstract List<Coordinate> getPath(Animal animal);

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
