package by.darafeyeu.action.create.entitys;

import by.darafeyeu.action.create.entity.CreateEntityAction;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.entity.Rock;
import by.darafeyeu.world.WorldMap;

public class CreateRocksAction extends CreateEntityAction {
    private static final int PARTS_OF_THE_WORLD = 10;



    public CreateRocksAction(WorldMap worldMap) {
        super(worldMap);
        partsWorld = PARTS_OF_THE_WORLD;
        setQuantityEntity();
    }

    @Override
    protected Entity getEntity() {
        return new Rock();
    }

}
