package by.darafeyeu.action;

import by.darafeyeu.action.create.entity.CurrentEntityCount;
import by.darafeyeu.world.WorldMap;

public abstract class Action {
    protected WorldMap worldMap;

    public Action(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public abstract void action();
}
