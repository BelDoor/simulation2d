package by.darafeyeu;

import by.darafeyeu.action.ActionAnimal;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.world.WorldMap;
import by.darafeyeu.world.WorldRender;


public class Main {

    public static void main(String[] agrs) {
        Coordinate coordinate = null;
        Entity entity = null;


      /*  Coordinate coordinateRabbit = new Coordinate(9, 2);
        Coordinate coordinateRabbit2 = new Coordinate(9, 9);
        Coordinate coordinateRabbit3 = new Coordinate(7, 0);
        Coordinate coordinateGrass = new Coordinate(2, 3);
        Coordinate coordinateGrass1 = new Coordinate(0, 9);
        Coordinate coordinateGrass2 = new Coordinate(4, 9);
        Coordinate coordinateGrass3 = new Coordinate(5, 7);
        Coordinate coordinateGrass4 = new Coordinate(4, 4);

        Coordinate coordinateBear = new Coordinate(4, 5);*/

        WorldMap worldMap = new WorldMap(10, 10);
        WorldRender render = new WorldRender();
        BreadthFirstSearch bfs = new BreadthFirstSearch(worldMap);
        ActionAnimal actionAnimal = new ActionAnimal(worldMap);

        actionAnimal.planWorld(bfs);
        render.render(worldMap);

        for (int i = 27; i > 0; i--) {
            actionAnimal.miniSimulation();
            render.render(worldMap);
            if(i % 3 == 0){
                actionAnimal.addEntityInWorld(new Grass());
            }
        }

//        try {
//            for (int i = 500; i > 0; i--) {
//                if (i % 2 == 0) {
//                    worldMap.putFigure(worldMap.emptyRandomCoordinate(), new Rock());
//                } else {
//                    worldMap.putFigure(worldMap.emptyRandomCoordinate(), new Tree());
//                }
//            }
//
//            for (int i = 500; i > 0; i--) {
//                worldMap.putFigure(worldMap.emptyRandomCoordinate(), new Grass());
//            }
//
//            for (int i = 200; i > 0; i--) {
//                worldMap.putFigure(worldMap.emptyRandomCoordinate(), new Rabbit(bfs));
//            }
//            worldMap.putFigure(worldMap.emptyRandomCoordinate(), new Bear(bfs));
//            worldMap.putFigure(worldMap.emptyRandomCoordinate(), new Bear(bfs));
//
//
//          /*  worldMap.putFigure(coordinateRabbit, new Rabbit(bfs));
//            worldMap.putFigure(coordinateRabbit2, new Rabbit(bfs));
//            worldMap.putFigure(coordinateRabbit3, new Rabbit(bfs));
//            worldMap.putFigure(coordinateBear, new Bear(bfs));
//            worldMap.putFigure(coordinateGrass, new Grass());
//            worldMap.putFigure(coordinateGrass1, new Grass());
//            worldMap.putFigure(coordinateGrass2, new Grass());
//            worldMap.putFigure(coordinateGrass3, new Grass());
//            worldMap.putFigure(coordinateGrass4, new Grass());
//
//            for (int j = 0; j <= 9; j++) {
//                for (int i = 0; i <= 9; i++) {
//                    if ((j == 1 || j == 5 || j == 9) && ((i > 5 && i < 9))) {
//                        worldMap.putFigure(new Coordinate(i, j), new Rock());
//                    }
//                    if ((j == 3 || j == 7) && ((i > 6 && i <= 9))) {
//                        worldMap.putFigure(new Coordinate(i, j), new Tree());
//                    }
//                    if ((j != 0 && j != 3 && j != 7) && i == 5) {
//                        worldMap.putFigure(new Coordinate(i, j), new Rock());
//                    }
//                    if (i == 0 && (j == 4)) {
//                        worldMap.putFigure(new Coordinate(i, j), new Rock());
//                    }
//                    if (i == 1 && (j != 1) && (j != 7) && (j != 3) && (j != 5)) {
//                        worldMap.putFigure(new Coordinate(i, j), new Rock());
//                    }
//                    if (i == 2 && ((j == 2) || (j == 6))) {
//                        worldMap.putFigure(new Coordinate(i, j), new Rock());
//                    }
//                    if (i == 3 && (j != 7 && j != 1)) {
//                        worldMap.putFigure(new Coordinate(i, j), new Rock());
//                    }
//
//                }
//            }*/
//
//
//            render.render(worldMap);
//
//
//            for (int i = 15; i >= 0; i--) {
//                actionAnimal.miniSimulation();
//                render.render(worldMap);
//            }
//        } catch (InvalidCoordinateException e) {
//            System.err.println(e);
//        } catch (OutOfWorldBoundsException e) {
//            System.err.println(e);
//        } catch (InvalidEntityException e) {
//            System.err.println(e);
//        } catch (CellException e) {
//            throw new RuntimeException(e);
//        }


    }
}
