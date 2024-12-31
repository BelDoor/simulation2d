package by.darafeyeu.algoritm;

import by.darafeyeu.world.WorldMap;

public class BreadthFirstSearchForBear extends BreadthFirstSearch{
    private static final int STANDARD_STEP = 7;
    public BreadthFirstSearchForBear(WorldMap worldMap) {
        super(worldMap);
        optionOfStep = STANDARD_STEP;
    }
}
