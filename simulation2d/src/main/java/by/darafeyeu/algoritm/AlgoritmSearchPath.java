package by.darafeyeu.algoritm;

import by.darafeyeu.Exception.CellException;
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
    private final WorldMap worldMap;

    public AlgoritmSearchPath(WorldMap worldMap){
        this.worldMap = worldMap;
    }

   // protected abstract cellNeighbours(CoordinateForAlgoritm currentCell);
    public abstract List<Coordinate> getPath(Animal animal);

    protected boolean isCellEmptyOrTarget(Coordinate coordinate, Class<? extends Entity> target) {
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
        if (target == Rabbit.class) {
            return !(entityForCell instanceof Grass || entityForCell instanceof Rock || entityForCell instanceof Tree
                    || entityForCell instanceof Bear);
        } else {
            return !(entityForCell instanceof Animal || entityForCell instanceof Rock || entityForCell instanceof Tree);
        }
    }
    public abstract boolean isTargetCoordinate(Coordinate coordinate);

    protected WorldMap getWorldMap() {
        return worldMap;
    }
}
