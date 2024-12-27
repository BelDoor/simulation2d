package by.darafeyeu.action;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.world.WorldMap;

public abstract class CreateEntityAction extends Action {

    protected WorldMap worldMap;
    protected int quantityEntity;
    protected int allCellInWorld;


    public CreateEntityAction(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.allCellInWorld = worldMap.getAllCell();
    }

    public void action() {
        for (int i = quantityEntity; i > 0; i--) {
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
    }

    protected void calculateEntity(int partsOfTheWorld) {
        this.quantityEntity = this.allCellInWorld / partsOfTheWorld;
    }

    protected Coordinate getEmptyCellInWorld() {
        return worldMap.emptyRandomCoordinate();
    }

    protected abstract Entity getEntity();

}
