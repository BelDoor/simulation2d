package by.darafeyeu;

import by.darafeyeu.simulation.Simulation;


public class Main {

    public static void main(String[] agrs) {
        Menu menu = new Menu();
        menu.menu();

/*        Coordinate coordinate = null;
        Entity entity = null;






      *//*  Coordinate coordinateRabbit = new Coordinate(9, 2);
        Coordinate coordinateRabbit2 = new Coordinate(9, 9);
        Coordinate coordinateRabbit3 = new Coordinate(7, 0);
        Coordinate coordinateGrass = new Coordinate(2, 3);
        Coordinate coordinateGrass1 = new Coordinate(0, 9);
        Coordinate coordinateGrass2 = new Coordinate(4, 9);
        Coordinate coordinateGrass3 = new Coordinate(5, 7);
        Coordinate coordinateGrass4 = new Coordinate(4, 4);

        Coordinate coordinateBear = new Coordinate(4, 5);*//*

        WorldMap worldMap = new WorldMap(20, 50);
        WorldRender render = new WorldRender(worldMap);

        List<Action> startAction = new ArrayList<>();
        startAction.add(new CreateBearsAction(worldMap));
        startAction.add(new CreateGrassAction(worldMap));
        startAction.add(new CreateRocksAction(worldMap));
        startAction.add(new CreateTreesAction(worldMap));
        startAction.add(new CreateRabbitsAction(worldMap));

        for (Action action : startAction) {
            action.action();
        }


        Action move = new MoveAnimalsAction(worldMap);
        render.render();
        System.out.printf("Count bear - %d \nCount rabbit - %d\nCount grass - %d\n", CountEntity.getCountBear(),
                CountEntity.getCountRabbit(), CountEntity.getCountGrass());

        for (int i = 0; i < 10; i++) {
            move.action();
            render.render();
            System.out.printf("Count bear - %d \nCount rabbit - %d\nCount grass - %d\n", CountEntity.getCountBear(),
                     CountEntity.getCountRabbit(), CountEntity.getCountGrass());
        }*/
    }
}
