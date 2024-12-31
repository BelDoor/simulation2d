package by.darafeyeu.world;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.action.CountEntitys;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

import java.util.Set;

public class WorldRender {
    private WorldMap worldMap;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String EMPTY_CELL = "🏻";
    public static final String SPRITE_RABBIT = "\uD83D\uDC30";
    public static final String SPRITE_ROCK = "⛰";
    public static final String SPRITE_GRASS = "\uD83C\uDF3F";
    public static final String SPRITE_BEAR = "\uD83D\uDC3B";
    public static final String SPRITE_TREE = "\uD83C\uDF33";
    public static final String SPRITE_TRACER = "\uD83C\uDFFF";

    public WorldRender(WorldMap worldMap){
        this.worldMap = worldMap;
    }

    //заменить String на  buffer line  и добавить его в поле.
    //добавить метод с вводом количества существ
    public void render() {
        System.out.println("-----------------------");
        Set<Coordinate> setTracers = worldMap.getTracers();
        for (int length = worldMap.getStartCoordinate(); length <= worldMap.getSizeLength(); length++) {
            String line = "";
            for (int height = worldMap.getStartCoordinate(); height <= worldMap.getSizeHeight(); height++) {
                Coordinate coordinate = new Coordinate(length, height);
                if (!worldMap.isFreeCell(coordinate)) {
                    try {
                        Entity entity = worldMap.getEntity(coordinate);
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
        System.out.printf("Count bear - %d \nCount rabbit - %d\nCount grass - %d\n", CountEntitys.getCountBear(),
                CountEntitys.getCountRabbit(), CountEntitys.getCountGrass());
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
