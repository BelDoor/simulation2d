package by.darafeyeu.coordinate;

import by.darafeyeu.random_number.RandomNumber;
import by.darafeyeu.world.WorldMap;

public class WorkWithCoordinatWorld {

    public static Coordinate emptyRandomCoordinate(WorldMap world) {
        while (true) {
            int length = RandomNumber.randomParamCoordinate(world.getSizeLength());
            int height = RandomNumber.randomParamCoordinate(world.getSizeHeightY());
            Coordinate randomCoordinate = new Coordinate(length, height);
            if (!world.isOccupiedCellInWorld(randomCoordinate)) {
                return randomCoordinate;
            }
        }
    }

    public static int getCountAllCell(WorldMap world) {
        return (world.getSizeHeightY() + 1) * (world.getSizeLength() + 1);
    }
}
