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
        setQuantityEntity();
        for (int i = quantityEntity; i > 0; i--) {
            putEntityInWorld();
        }
    }

    protected void putEntityInWorld() {
        try {
            worldMap.putFigure(getEmptyCellInWorld(), getEntity());
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

    private void setQuantityEntity() {
        quantityEntity = allCellInWorld / partsWorld;
    }

    protected abstract Entity getEntity();

}
