package by.darafeyeu;

import by.darafeyeu.action.Action;
import by.darafeyeu.action.create.entity.entitys.CreateBearsAction;
import by.darafeyeu.action.create.entity.CreateGrasAction;
import by.darafeyeu.action.create.entity.entitys.CreateGrassAction;
import by.darafeyeu.action.create.entity.entitys.CreateRabbitsAction;
import by.darafeyeu.action.create.entity.entitys.CreateRocksAction;
import by.darafeyeu.action.create.entity.entitys.CreateTreesAction;
import by.darafeyeu.action.MoveAnimalsAction;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.world.WorldMap;
import by.darafeyeu.world.WorldRender;

import java.util.ArrayList;
import java.util.List;


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

        WorldMap worldMap = new WorldMap(15, 50);
        WorldRender render = new WorldRender();

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
        render.render(worldMap);

        for (int i = 0; i < 10; i++) {
            move.action();
            render.render(worldMap);
        }
    }
}
