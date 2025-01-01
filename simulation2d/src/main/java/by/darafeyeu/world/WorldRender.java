package by.darafeyeu.world;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
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
    private StringBuilder line;

    public WorldRender(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void render() {
        System.out.println("-----------------");
        Set<Coordinate> setTracers = worldMap.getTracers();
        for (int length = worldMap.getStartCoordinate(); length <= worldMap.getSizeLength(); length++) {
            this.line = new StringBuilder();
            for (int height = worldMap.getStartCoordinate(); height <= worldMap.getSizeHeight(); height++) {
                Coordinate coordinate = new Coordinate(length, height);
                if (!worldMap.isFreeCell(coordinate)) {
                    try {
                        Entity entity = worldMap.getEntity(coordinate);
                        this.line.append(getEntitySprite(entity.getClass().getSimpleName()));
                    } catch (InvalidCoordinateException e) {
                        this.line.append(getSpriteForEmptySquare());
                    } catch (OutOfWorldBoundsException e) {
                        this.line.append(getSpriteForEmptySquare());
                    }
                } else if (setTracers.contains(coordinate)) {
                    this.line.append(getSpriteForTracerSquare());
                } else {
                    this.line.append(getSpriteForEmptySquare());
                }
            }
            this.line.append(ANSI_RESET);
            System.out.println(this.line);
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

    private String getSpriteForEmptySquare() {
        return EMPTY_CELL;
    }

    private String getSpriteForTracerSquare() {
        return SPRITE_TRACER;
    }


}
