package by.darafeyeu.world;

import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

public class WorldRender {
    private WorldMap worldMap;

    private static final int NULL_POINT_FOR_WORLD = 0;

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String EMPTY_CELL = "🏻";
    public static final String SPRITE_RABBIT = "\uD83D\uDC30";
    public static final String SPRITE_ROCK = "⛰";
    public static final String SPRITE_GRASS = "\uD83C\uDF3F";
    public static final String SPRITE_BEAR = "\uD83D\uDC3B";
    public static final String SPRITE_TREE = "\uD83C\uDF33";

    public static final String SPRITE_DELETED = "-----------------";
    private StringBuilder line;

    public WorldRender(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void render() {
        System.out.println(SPRITE_DELETED);

        for (int length = NULL_POINT_FOR_WORLD; length <= worldMap.getSizeLength(); length++) {
            this.line = new StringBuilder();
            for (int height = NULL_POINT_FOR_WORLD; height <= worldMap.getSizeHeightY(); height++) {
                paintCoordinate(new Coordinate(length, height));
            }
            this.line.append(ANSI_RESET);
            System.out.println(this.line);
        }

    }

    private void paintCoordinate(Coordinate coordinate) {
        if (worldMap.isOccupiedCellInWorld(coordinate)) {
            paintEntity(coordinate);
        } else {
            this.line.append(getSpriteForEmptySquare());
        }
    }

    private void paintEntity(Coordinate coordinate) {

        if (worldMap.getOptionalEntity(coordinate).isPresent()) {
            Entity entity = worldMap.getOptionalEntity(coordinate).get();
            this.line.append(getEntitySprite(entity.getClass().getSimpleName()));
        } else {
            this.line.append(getSpriteForEmptySquare());
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
}
