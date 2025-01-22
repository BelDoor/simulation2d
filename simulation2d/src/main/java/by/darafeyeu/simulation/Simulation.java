package by.darafeyeu.simulation;

import by.darafeyeu.action.Action;
import by.darafeyeu.action.create.entity.CreateEntityAction;
import by.darafeyeu.action.create.entity.CreateBearAction;
import by.darafeyeu.action.create.entity.CreateEntityAction1;
import by.darafeyeu.action.create.entity.CreateGrasAction;
import by.darafeyeu.action.create.entity.CreateRabbitAction;
import by.darafeyeu.action.MoveAnimalsAction;
import by.darafeyeu.action.create.entitys.CreateBearsAction;
import by.darafeyeu.action.create.entitys.CreateGrassAction;
import by.darafeyeu.action.create.entitys.CreateRabbitsAction;
import by.darafeyeu.action.create.entitys.CreateRocksAction;
import by.darafeyeu.action.create.entitys.CreateTreesAction;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.nature.Entity;
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
    private List<Action> createWorld = new ArrayList<>();
    private List<CreateEntityAction> addEntity = new ArrayList<>();
    private List<Entity> entityClasses = new ArrayList<>();
    private List<Action> createEntityAction = new ArrayList<>();
    private Action moveAction;

    private int countRound = 0;

    public Simulation(int length, int height) {
        this.worldMap = new WorldMap(length, height);
        render = new WorldRender(worldMap);
        Collections.addAll(createWorld, new CreateRocksAction(worldMap), new CreateTreesAction(worldMap),
                new CreateGrassAction(worldMap), new CreateRabbitsAction(worldMap), new CreateBearsAction(worldMap));
        Collections.addAll(addEntity, new CreateGrasAction(worldMap), new CreateRabbitAction(worldMap),
                new CreateBearAction(worldMap));
        moveAction = new MoveAnimalsAction(worldMap);

        Collections.addAll(createEntityAction,
                new CreateEntityAction1(worldMap, Tree::new),
                new CreateEntityAction1(worldMap, Grass::new),
                new CreateEntityAction1(worldMap, Rock::new),
                new CreateEntityAction1(worldMap, () -> new Rabbit(new BreadthFirstSearch(worldMap))),
                new CreateEntityAction1(worldMap, () -> new Bear(new BreadthFirstSearch(worldMap)))
        );

        createWorld();
        render.render();
    }

    public void simulation() {
        countRound++;
        moveAction.action();
        addEntity();
        render.render();
    }

    private void createWorld() {
        for (Action action : createWorld) {
            action.action();
        }
        //todo
//        for (Action action : createEntityAction) {
//                action.action();
//        }
    }

    private void addEntity() {
        if (countRound % 5 == 0) {
            for (CreateEntityAction action : addEntity) {
                action.addEntitys();
            }
        }
    }

    public int getCountRound() {
        return countRound;
    }
}
