package by.darafeyeu.action.create.entitys;

import by.darafeyeu.action.create.entity.CreateBearAction;
import by.darafeyeu.world.WorldMap;

public class CreateBearsAction extends CreateBearAction {
    private static final int PARTS_OF_THE_WORLD = 100;

    public CreateBearsAction(WorldMap worldMap) {
        super(worldMap);
        partsWorld = PARTS_OF_THE_WORLD;
        setQuantityEntity();
        MaxEntityCount.setMaxBear(quantityEntity);
    }

}
