package by.darafeyeu.action;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.world.WorldMap;

public class CreateGrassAction extends CreateEntityAction{
    private static final int PARTS_OF_THE_WORLD = 7;

    public CreateGrassAction(WorldMap worldMap){
        super(worldMap);
        calculateEntity(PARTS_OF_THE_WORLD);
    }

    @Override
    public void action() {
        for (int i = quantityEntity; i > 0 ; i--) {
            try {
                worldMap.putFigure(getEmptyCellInWorld(), new Grass());
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
