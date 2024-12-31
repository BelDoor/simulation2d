package by.darafeyeu.action.create.entity;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.algoritm.BreadthFirstSearchForRabbit;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.world.WorldMap;

public class CreateRabbitAction extends CreateEntityAction {
    private final AlgoritmSearchPath algoritm;

    public CreateRabbitAction(WorldMap worldMap) {
        super(worldMap);
        algoritm = new BreadthFirstSearchForRabbit(worldMap);

    }

    @Override
    protected Entity getEntity() {
        return new Rabbit(algoritm);
    }
}