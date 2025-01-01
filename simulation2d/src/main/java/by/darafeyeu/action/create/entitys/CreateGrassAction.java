package by.darafeyeu.action.create.entitys;

import by.darafeyeu.action.create.entity.CreateGrasAction;
import by.darafeyeu.world.WorldMap;

public class CreateGrassAction extends CreateGrasAction {
    private static final int PARTS_OF_THE_WORLD = 7;

    public CreateGrassAction(WorldMap worldMap){
        super(worldMap);
        partsWorld = PARTS_OF_THE_WORLD;
        setQuantityEntity();
        MaxEntityCount.setMaxGrass(quantityEntity);
    }
}
