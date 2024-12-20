package by.darafeyeu;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.action.ActionAnimal;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.Rock;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.nature.animals.Bear;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.world.WorldMap;
import by.darafeyeu.world.WorldRender;


public class Main {

    public static void main(String[] agrs) {
        Coordinate coordinate = null;
        Entity entity = null;


        Coordinate coordinateRabbit = new Coordinate(9, 9);
        Coordinate coordinateRabbit2 = new Coordinate(0, 0);
        Coordinate coordinateGrass = new Coordinate(9, 7);
        Coordinate coordinateGrass1 = new Coordinate(0, 5);
        Coordinate coordinateGrass2 = new Coordinate(2, 9);
        Coordinate coordinateGrass3 = new Coordinate(6, 0);
        Coordinate coordinateBear = new Coordinate(4, 5);
        WorldMap worldMap = new WorldMap();
        WorldRender render = new WorldRender();
        BreadthFirstSearch bfs = new BreadthFirstSearch(worldMap);
        ActionAnimal actionAnimal = new ActionAnimal(worldMap);


        try {
            worldMap.putFigure(coordinateRabbit, new Rabbit(bfs));
            worldMap.putFigure(coordinateRabbit2, new Rabbit(bfs));
            worldMap.putFigure(coordinateBear, new Bear(bfs));
            worldMap.putFigure(coordinateGrass, new Grass());
            worldMap.putFigure(coordinateGrass1, new Grass());
            worldMap.putFigure(coordinateGrass2, new Grass());
            worldMap.putFigure(coordinateGrass3, new Grass());

            for (int i = 1; i <= 9; i++) {
                if (i != 3 && i != 6) {
                    worldMap.putFigure(new Coordinate(i, 3), new Rock());
                    worldMap.putFigure(new Coordinate(3, i), new Rock());
                }
            }


            render.render(worldMap);

            Entity bear = worldMap.getEntity(coordinateBear);
            Entity rabbit1 = worldMap.getEntity(coordinateRabbit);
            Entity rabbit2 = worldMap.getEntity(coordinateRabbit2);

            Animal animalBear = null;
            Animal animalRabbit1 = null;
            Animal animalRabbit2 = null;

            if (bear instanceof Animal) {
                animalBear = (Animal) bear;
            }

            if (rabbit1 instanceof Animal) {
                animalRabbit1 = (Animal) rabbit1;
            }

            if (rabbit2 instanceof Animal) {
                animalRabbit2 = (Animal) rabbit2;
            }

            for (int i = 0; i <= 5; i++) {
                actionAnimal.stepEntityInMap(animalRabbit1);
                actionAnimal.stepEntityInMap(animalRabbit2);
                actionAnimal.stepEntityInMap(animalBear);
                render.render(worldMap);
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
