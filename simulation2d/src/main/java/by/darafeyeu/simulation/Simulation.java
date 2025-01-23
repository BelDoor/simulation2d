package by.darafeyeu.simulation;

import by.darafeyeu.action.Action;
import by.darafeyeu.action.create.CreateEntityAction;
import by.darafeyeu.action.move.MoveAnimalsAction;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.nature.animals.Bear;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.nature.entity.Rock;
import by.darafeyeu.nature.entity.Tree;
import by.darafeyeu.world.WorldMap;
import by.darafeyeu.world.WorldRender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Simulation {
    private WorldMap worldMap;
    private WorldRender render;

    private List<Action> createEntityAction = new ArrayList<>();
    private Action moveAction;

    private int countRound = 0;

    public Simulation(int length, int height) {
        this.worldMap = new WorldMap(length, height);
        render = new WorldRender(worldMap);

        moveAction = new MoveAnimalsAction(worldMap);

        Collections.addAll(createEntityAction,
                new CreateEntityAction(worldMap, Tree::new),
                new CreateEntityAction(worldMap, Grass::new),
                new CreateEntityAction(worldMap, Rock::new),
                new CreateEntityAction(worldMap, () -> new Rabbit(new BreadthFirstSearch(worldMap, 4))),
                new CreateEntityAction(worldMap, () -> new Bear(new BreadthFirstSearch(worldMap, 8)))
        );

        addEntity();

        render.render();
    }

    public void simulation() {
        countRound++;

        moveAction.action();

        addEntity();

        render.render();
    }

    private void addEntity() {
        if (countRound % 6 == 0 || countRound == 0) {

            for (Action action : createEntityAction) {
                action.action();
            }

        }
    }

    public int getCountRound() {
        return countRound;
    }
}
