package by.darafeyeu;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.Rock;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.world.WorldMap;
import by.darafeyeu.world.WorldRender;

public class Main {

    public static void main(String[] agrs) {
        Coordinate coordinate = null;
        Entity entity = null;
        WorldMap worldMap = new WorldMap();
        WorldRender render = new WorldRender();
        try {
            worldMap.putFigure(new Coordinate(0, 0), new Rabbit());
            worldMap.putFigure(new Coordinate(0, 1), new Rock());
            worldMap.putFigure(new Coordinate(0, 2), new Rabbit());
            worldMap.putFigure(new Coordinate(0, 3), new Rabbit());
            worldMap.putFigure(new Coordinate(9, 4), new Rabbit());
            entity = worldMap.getEntity(new Coordinate(9, 4));
            render.render(worldMap);
            worldMap.stepEntityInMap(new Coordinate(9, 4), new Coordinate(9, 3));
        } catch (InvalidCoordinateException e) {
            System.err.println(e);
        } catch (OutOfWorldBoundsException e) {
            System.err.println(e);
        } catch (InvalidEntityException e) {
            System.err.println(e);
        } catch (CellException e) {
            throw new RuntimeException(e);
        }

        render.render(worldMap);

        System.out.println(entity.toString());
        int i = 123;
    }
}
