package by.darafeyeu.action.create;

import by.darafeyeu.action.Action;
import by.darafeyeu.exception.InvalidCoordinateException;
import by.darafeyeu.exception.InvalidEntityException;
import by.darafeyeu.exception.OutOfWorldBoundsException;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.world.WorldMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class CreateEntityAction extends Action {

    private static final int TREE_OF_THE_WORLD = 11;
    private static final int ROCK_OF_THE_WORLD = 10;
    private static final int GRASS_OF_THE_WORLD = 7;
    private static final int RABBIT_OF_THE_WORLD = 12;
    private static final int BEAR_OF_THE_WORLD = 100;
    private static final double FIFTY_PERCENT_OF_THE_NUMBER = 0.5;
    private static final double SEVENTY_PERCENT_OF_THE_NUMBER = 0.7;
    private static final double EIGHTY_PERCENT_OF_THE_NUMBER = 0.8;


    private static Map<String, Integer> maxCountEntity = new HashMap<>();
    private static Map<String, Double> quantityAddedEntity = new HashMap<>();

    private Entity entity;
    private Supplier<? extends Entity> entitySupplier;
    private boolean checkCreatedEntityInWorld = false;


    public CreateEntityAction(WorldMap worldMap, Supplier<? extends Entity> entitySupplier) {
        super(worldMap);
        this.entitySupplier = entitySupplier;

        createEntity();
        entity.minusCount();

        maxCountEntity.put("Rabbit", worldMap.getCountAllCell() / RABBIT_OF_THE_WORLD);
        maxCountEntity.put("Grass", worldMap.getCountAllCell() / GRASS_OF_THE_WORLD);
        maxCountEntity.put("Bear", worldMap.getCountAllCell() / BEAR_OF_THE_WORLD);
        maxCountEntity.put("Tree", worldMap.getCountAllCell() / TREE_OF_THE_WORLD);
        maxCountEntity.put("Rock", worldMap.getCountAllCell() / ROCK_OF_THE_WORLD);

        quantityAddedEntity.put("Rabbit", SEVENTY_PERCENT_OF_THE_NUMBER);
        quantityAddedEntity.put("Grass", FIFTY_PERCENT_OF_THE_NUMBER);
        quantityAddedEntity.put("Bear", EIGHTY_PERCENT_OF_THE_NUMBER);
    }

    @Override
    public void action() {
        int quantity = quantityEntity();
        for (int i = 0; i < quantity; i++) {

            createEntity();

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
        return entity.getEntityCount() < maxCount() * FIFTY_PERCENT_OF_THE_NUMBER;
    }

    private int maxCount() {
        return maxCountEntity.get(nameEntity());
    }

    private int countRefreshEntity() {
        return (int) (maxCount() * quantityAddedEntity.get(nameEntity()));
    }

    private String nameEntity() {
        return entity.getClass().getSimpleName();
    }
}
