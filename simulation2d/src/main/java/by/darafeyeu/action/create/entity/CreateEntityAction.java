package by.darafeyeu.action.create.entity;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.action.Action;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.world.WorldMap;

public abstract class CreateEntityAction extends Action {

    protected int quantityEntity;
    protected int allCellInWorld;
    protected int partsWorld;

    public CreateEntityAction(WorldMap worldMap) {
        super(worldMap);
        this.allCellInWorld = worldMap.getAllCell();
        partsWorld = allCellInWorld;
    }

    public void action() {
        for (int i = quantityEntity; i > 0; i--) {
            putEntityInWorld();
        }
    }

    public void addEntitys() {
        if (isMinEntity()) {
            for (int i = 0; i < countIteration(); i++) {
                putEntityInWorld();
            }
        }
    }

    protected void putEntityInWorld() {
        Entity entity = getEntity();
        try {
            worldMap.putFigure(getEmptyCellInWorld(), entity);
            plussCountEntity();
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntityException e) {
            throw new RuntimeException(e);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    private Coordinate getEmptyCellInWorld() {
        return worldMap.emptyRandomCoordinate();
    }

    protected void setQuantityEntity() {
        quantityEntity = allCellInWorld / partsWorld;
    }

    protected abstract Entity getEntity();

    protected boolean isMinEntity() {
        switch (getEntity().getClass().getSimpleName()) {
            case ("Rabbit"):
                return CurrentEntityCount.isMinRabbit();
            case ("Grass"):
                return CurrentEntityCount.isMinGrass();
            case ("Bear"):
                return CurrentEntityCount.isMinBear();
            default:
                return false;
        }
    }

    private int countIteration() {
        switch (getEntity().getClass().getSimpleName()) {
            case ("Rabbit"):
                return CurrentEntityCount.getCountBear();
            case ("Grass"):
                return CurrentEntityCount.getCountRabbit();
            case ("Bear"):
                return 1;
        }
        return 0;
    }

    protected void plussCountEntity() {
        switch (getEntity().getClass().getSimpleName()) {
            case ("Rabbit"):
                CurrentEntityCount.addCountRabbit();
                break;
            case ("Grass"):
                CurrentEntityCount.addCountGrass();
                break;
            case ("Bear"):
                CurrentEntityCount.addCountBear();
                break;
        }
    }

}
