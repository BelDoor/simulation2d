package by.darafeyeu.action.create;

import by.darafeyeu.action.Action;

import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.coordinate.WorkWithCoordinatWorld;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.world.WorldMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CreateEntityAction extends Action {

    private static final int TREE_OF_THE_WORLD = 11;
    private static final int ROCK_OF_THE_WORLD = 10;
    private static final int GRASS_OF_THE_WORLD = 5;
    private static final int RABBIT_OF_THE_WORLD = 12;
    private static final int BEAR_OF_THE_WORLD = 75;

    private static final double FIFTY_PERCENT_OF_THE_NUMBER = 0.5;
    private static final double SEVENTY_PERCENT_OF_THE_NUMBER = 0.7;
    private static final double EIGHTY_PERCENT_OF_THE_NUMBER = 0.8;

    private Map<String, Integer> maxCountEntity = new HashMap<>();
    private Map<String, Double> quantityAddedEntity = new HashMap<>();

    private Entity entity;
    private Supplier<? extends Entity> entitySupplier;
    private boolean checkCreatedEntityInWorld = false;

    {
        int countAllCell = WorkWithCoordinatWorld.getCountAllCell(worldMap);
        maxCountEntity.put("Rabbit", countAllCell / RABBIT_OF_THE_WORLD);
        maxCountEntity.put("Grass", countAllCell / GRASS_OF_THE_WORLD);
        maxCountEntity.put("Bear", countAllCell / BEAR_OF_THE_WORLD);
        maxCountEntity.put("Tree", countAllCell / TREE_OF_THE_WORLD);
        maxCountEntity.put("Rock", countAllCell / ROCK_OF_THE_WORLD);

        quantityAddedEntity.put("Rabbit", SEVENTY_PERCENT_OF_THE_NUMBER);
        quantityAddedEntity.put("Grass", FIFTY_PERCENT_OF_THE_NUMBER);
        quantityAddedEntity.put("Bear", EIGHTY_PERCENT_OF_THE_NUMBER);
    }

    public CreateEntityAction(WorldMap worldMap, Supplier<? extends Entity> entitySupplier) {
        super(worldMap);
        this.entitySupplier = entitySupplier;

        createEntity();
        entity.minusCount();
    }

    @Override
    public void action() {
        int quantity = quantityEntity();
        for (int i = 0; i < quantity; i++) {
            Coordinate coordinate = WorkWithCoordinatWorld.emptyRandomCoordinate(worldMap);

            createEntity();

            worldMap.addEntityInWorld(coordinate, entity);
        }
    }

    public int quantityEntity() {
        if (!checkCreatedEntityInWorld) {
            createdEntityInWorld();
            return maxCount();
        }

        if (checkMinEntity()) {
            return countRefreshEntity();
        }
        return 0;
    }

    private void createdEntityInWorld() {
        checkCreatedEntityInWorld = true;
    }

    private void createEntity() {
        entity = entitySupplier.get();
    }

    private boolean checkMinEntity() {
        return entity.getEntityCount() <= maxCount() * FIFTY_PERCENT_OF_THE_NUMBER;
    }

    private int maxCount() {
        return maxCountEntity.get(nameEntity());
    }

    private int countRefreshEntity() {
        return (int) Math.round(maxCount() * quantityAddedEntity.get(nameEntity()));
    }

    private String nameEntity() {
        return entity.getClass().getSimpleName();
    }
}
