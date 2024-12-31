package by.darafeyeu.action.create.entity;

import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.world.WorldMap;

public class CreateGrasAction extends CreateEntityAction {

    public CreateGrasAction(WorldMap worldMap) {
        super(worldMap);
    }

    @Override
    protected Entity getEntity() {
        return new Grass();
    }
}
