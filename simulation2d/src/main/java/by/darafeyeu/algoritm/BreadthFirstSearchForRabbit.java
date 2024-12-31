package by.darafeyeu.algoritm;

import by.darafeyeu.world.WorldMap;

public class BreadthFirstSearchForRabbit extends BreadthFirstSearch{
    private static final int STANDARD_STEP = 4;
    public BreadthFirstSearchForRabbit(WorldMap worldMap) {
        super(worldMap);
        optionOfStep = STANDARD_STEP;
    }
}
