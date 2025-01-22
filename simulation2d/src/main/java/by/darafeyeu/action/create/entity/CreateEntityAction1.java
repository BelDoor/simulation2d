package by.darafeyeu.action.create.entity;

import by.darafeyeu.action.Action;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.exception.InvalidCoordinateException;
import by.darafeyeu.exception.InvalidEntityException;
import by.darafeyeu.exception.OutOfWorldBoundsException;
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
import java.util.function.Supplier;

public class CreateEntityAction1 extends Action {
    private Entity entity ;
    private int quantityEntity = 1;

    public CreateEntityAction1(WorldMap worldMap, Supplier<? extends Entity> t) {
        super(worldMap);
        entity = t.get();
    }

    @Override
    public void action() {

        for (int i = 0; i < 5/* quantityEntity*/; i++) {
            try {
                worldMap.putFigure(worldMap.emptyRandomCoordinate(), entity);
            } catch (InvalidCoordinateException e) {
                throw new RuntimeException(e);
            } catch (InvalidEntityException e) {
                throw new RuntimeException(e);
            } catch (OutOfWorldBoundsException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void quantityEntity(){
        //todo с помощью сущности  находим в классе счетчик нужное значение для старта
    }

    public static void main(String[] args) {
        WorldMap w = new WorldMap();

        List<Entity> entityList = new ArrayList<>();
        Collections.addAll(entityList, new Grass(),
                new Tree(),
                new Rock(),
                new Rabbit(new BreadthFirstSearch(w)),
                new Bear(new BreadthFirstSearch(w)));

        WorldRender r = new WorldRender(w);
        r.render();

        for (Entity entity : entityList) {
            CreateEntityAction1 createEntity = new CreateEntityAction1(w, () -> entity);
            createEntity.action();
        }

        r.render();

    }

}
