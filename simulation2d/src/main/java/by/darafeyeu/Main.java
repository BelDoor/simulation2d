package by.darafeyeu;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.Rock;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.world.WorldMap;
import by.darafeyeu.world.WorldRender;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] agrs) {
        Coordinate coordinate = null;
        Entity entity = null;
        WorldMap worldMap = new WorldMap();
        WorldRender render = new WorldRender();
        BreadthFirstSearch bfs = new BreadthFirstSearch();

        try {
            worldMap.putFigure(new Coordinate(0, 2), new Rabbit());
            //     worldMap.putFigure(new Coordinate(4, 5), new Grass());
            worldMap.putFigure(new Coordinate(1, 0), new Rock());
            worldMap.putFigure(new Coordinate(1, 1), new Rock());
            worldMap.putFigure(new Coordinate(1, 2), new Rock());
            worldMap.putFigure(new Coordinate(1, 3), new Rock());
            worldMap.putFigure(new Coordinate(1, 4), new Rock());
            worldMap.putFigure(new Coordinate(1, 5), new Rock());
            worldMap.putFigure(new Coordinate(1, 6), new Rock());
            worldMap.putFigure(new Coordinate(1, 7), new Rock());
            worldMap.putFigure(new Coordinate(1, 8), new Rock());
            worldMap.putFigure(new Coordinate(3, 9), new Rock());
            worldMap.putFigure(new Coordinate(3, 1), new Rock());
            worldMap.putFigure(new Coordinate(3, 2), new Rock());
            worldMap.putFigure(new Coordinate(3, 3), new Rock());
            worldMap.putFigure(new Coordinate(3, 4), new Rock());
            worldMap.putFigure(new Coordinate(3, 5), new Rock());
            worldMap.putFigure(new Coordinate(3, 6), new Rock());
            worldMap.putFigure(new Coordinate(3, 7), new Rock());
            worldMap.putFigure(new Coordinate(3, 8), new Rock());
            worldMap.putFigure(new Coordinate(4, 4), new Rock());
            worldMap.putFigure(new Coordinate(5, 5), new Rock());
            worldMap.putFigure(new Coordinate(5, 4), new Rock());
            worldMap.putFigure(new Coordinate(5, 6), new Rock());


            render.render(worldMap);

            List<Coordinate> path = bfs.searchPath(new Coordinate(0, 2), worldMap);
            for (Coordinate cell : path) {
                System.out.println(cell.toString());
            }
        } catch (InvalidCoordinateException e) {
            System.err.println(e);
        } catch (OutOfWorldBoundsException e) {
            System.err.println(e);
        } catch (InvalidEntityException e) {
            System.err.println(e);
        } catch (CellException e) {
            throw new RuntimeException(e);
        }


        int i = 123;
    }
}
