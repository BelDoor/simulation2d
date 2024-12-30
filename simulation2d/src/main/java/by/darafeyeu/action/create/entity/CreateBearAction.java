package by.darafeyeu.action.create.entity;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Bear;
import by.darafeyeu.world.WorldMap;

public class CreateBearAction extends CreateEntityAction{
    private AlgoritmSearchPath algoritm;

    public CreateBearAction(WorldMap worldMap) {
        super(worldMap);
        algoritm = new BreadthFirstSearch(worldMap);
    }

    @Override
    protected Entity getEntity() {
        return new Bear(algoritm);
    }
}
