package by.darafeyeu.action.create.entitys;

import by.darafeyeu.action.create.entity.CreateRabbitAction;
import by.darafeyeu.world.WorldMap;

public class CreateRabbitsAction extends CreateRabbitAction {

    private static final int PARTS_OF_THE_WORLD = 12;

    public CreateRabbitsAction(WorldMap worldMap) {
        super(worldMap);
        partsWorld = PARTS_OF_THE_WORLD;
        setQuantityEntity();
        MaxEntityCount.setMaxRabbit(quantityEntity);
    }

}
