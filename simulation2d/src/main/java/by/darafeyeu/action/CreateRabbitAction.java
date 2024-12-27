package by.darafeyeu.action;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.nature.entity.Tree;
import by.darafeyeu.world.WorldMap;

public class CreateRabbitAction extends CreateEntityAction {

    private static final int PARTS_OF_THE_WORLD = 33;

    public CreateRabbitAction(WorldMap worldMap) {
        super(worldMap);
        calculateEntity(PARTS_OF_THE_WORLD);
    }

    @Override
    public void action() {
        AlgoritmSearchPath algoritm = new BreadthFirstSearch(worldMap);
        for (int i = quantityEntity; i > 0; i--) {
            try {
                worldMap.putFigure(getEmptyCellInWorld(), new Rabbit(algoritm));
            } catch (InvalidCoordinateException e) {
                throw new RuntimeException(e);
            } catch (InvalidEntityException e) {
                throw new RuntimeException(e);
            } catch (OutOfWorldBoundsException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
