package by.darafeyeu.world;

import by.darafeyeu.Exception.CellException;
import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

import java.util.Set;

public class WorldRender {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_CELL = "🏻";
    public static final String SPRITE_RABBIT = "\uD83D\uDC30";
    public static final String SPRITE_ROCK = "⛰";
    public static final String SPRITE_GRASS = "\uD83C\uDF3F";
    public static final String SPRITE_BEAR = "\uD83D\uDC3B";
    public static final String SPRITE_TREE = "\uD83C\uDF33";
    public static final String SPRITE_TRACER = "\uD83C\uDFFF";


    //убрать могичиские числа
    //реализовать через StringBilder
    public void render(WorldMap world) {
        System.out.println("-----------------------");
        Set<Coordinate> setTracers = world.getTracers();
        for (int length = world.getStartCoordinate(); length <= world.getSizeLength(); length++) {
            String line = "";
            for (int height = world.getStartCoordinate(); height <= world.getSizeHeight(); height++) {
                Coordinate coordinate = new Coordinate(length, height);
                if (!world.isFreeCell(coordinate)) {
                    try {
                        Entity entity = world.getEntity(coordinate);
                        line += getEntitySprite(entity.getClass().getSimpleName());
                    } catch (InvalidCoordinateException e) {
                        line += getSpriteForEmptySquare(coordinate);
                    } catch (OutOfWorldBoundsException e) {
                        line += getSpriteForEmptySquare(coordinate);
                    }
                } else if (setTracers.contains(coordinate)) {
                    line += getSpriteForTracerSquare(coordinate);
                } else {
                    line += getSpriteForEmptySquare(coordinate);
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
            case ("Grass"):
                return SPRITE_GRASS;
            case ("Tree"):
                return SPRITE_TREE;
            case ("Bear"):
                return SPRITE_BEAR;
            default:
                throw new IllegalStateException("Unexpected value: " + nameEntity);
        }
    }

    private String getSpriteForEmptySquare(Coordinate coordinates) {
        return EMPTY_CELL;
    }

    private String getSpriteForTracerSquare(Coordinate coordinates) {
        return SPRITE_TRACER;
    }


}
