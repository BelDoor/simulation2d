package by.darafeyeu.action;

import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.world.WorldMap;

public abstract class CreateEntityAction extends Action {

    protected WorldMap worldMap;
    protected int quantityEntity;
    protected int allCellInWorld;

    public abstract void action();

    public CreateEntityAction(WorldMap worldMap) {
        this.worldMap = worldMap;
        this.allCellInWorld = worldMap.getAllCell();
    }

    protected void calculateEntity(int partsOfTheWorld) {
        this.quantityEntity = this.allCellInWorld / partsOfTheWorld;
    }

    protected Coordinate getEmptyCellInWorld() {
        return worldMap.emptyRandomCoordinate();
    }


}
