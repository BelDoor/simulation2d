package by.darafeyeu.action.create.entity.entitys;

import by.darafeyeu.action.create.entity.CreateBearAction;
import by.darafeyeu.action.create.entity.CreateEntityAction;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Bear;
import by.darafeyeu.world.WorldMap;

public class CreateBearsAction extends CreateBearAction {
    private static final int PARTS_OF_THE_WORLD = 50;

    public CreateBearsAction(WorldMap worldMap) {
        super(worldMap);
        partsWorld = PARTS_OF_THE_WORLD;
    }

}
