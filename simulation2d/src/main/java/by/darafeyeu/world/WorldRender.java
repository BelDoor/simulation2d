package by.darafeyeu.world;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;

public class WorldRender {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_CELL = "🏻";
    public static final String SPRITE_RABBIT = "\uD83D\uDC30";
    public static final String SPRITE_ROCK = "⛰";

    //убрать могичиские числа
    //реализовать через StringBilder
    public void render(WorldMap world) {
        System.out.println("-----------------------");
        for (int length = world.getStartCoordinate(); length <= world.getSizeLength(); length++) {
            String line = "";
            for (int height = world.getStartCoordinate(); height <= world.getSizeHeight(); height++) {
                Coordinate coordinates = new Coordinate(length, height);
                if (world.isFreeCell(coordinates)) {
                    line += getSpriteForEmptySquare(coordinates);
                } else {
                    try {
                        line += getEntitySprite(world.getEntity(coordinates).getClass().getSimpleName());
                    } catch (InvalidCoordinateException e) {
                        line += getSpriteForEmptySquare(coordinates);
                    } catch (OutOfWorldBoundsException e) {
                        line += getSpriteForEmptySquare(coordinates);
                    } catch (CellException e) {
                        line += getSpriteForEmptySquare(coordinates);
                    }
                }
            }
            line += ANSI_RESET;
            System.out.println(line);
        }
    }

    private String getEntitySprite(String nameEntity) {
        switch (nameEntity) {
            case ("Rabbit"):
                return SPRITE_RABBIT;
            case ("Rock"):
                return SPRITE_ROCK;
            default:
                throw new IllegalStateException("Unexpected value: " + nameEntity);
        }
    }

    private String getSpriteForEmptySquare(Coordinate coordinates) {
        return EMPTY_CELL;
    }


}
