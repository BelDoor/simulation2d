package by.darafeyeu.action;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Bear;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.world.WorldMap;

public class CreateBearAction extends CreateEntityAction {
    private static final int PARTS_OF_THE_WORLD = 50;


    public CreateBearAction(WorldMap worldMap) {
        super(worldMap);
        calculateEntity(PARTS_OF_THE_WORLD);
    }

    @Override
    protected Entity getEntity() {
        return new Bear(new BreadthFirstSearch(worldMap));
    }
}
