package by.darafeyeu.action.create.entitys;

import by.darafeyeu.action.create.entity.CreateEntityAction;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.entity.Tree;
import by.darafeyeu.world.WorldMap;

public class CreateTreesAction extends CreateEntityAction {
    private static final int PARTS_OF_THE_WORLD = 11;

    public CreateTreesAction(WorldMap worldMap) {
        super(worldMap);
        partsWorld = PARTS_OF_THE_WORLD;
        setQuantityEntity();
    }

    @Override
    protected Entity getEntity() {
        return new Tree();
    }

}
