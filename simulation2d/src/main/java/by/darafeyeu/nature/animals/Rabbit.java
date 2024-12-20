package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.nature.entity.Grass;

public class Rabbit extends Animal {
    private static final int SPEED_STEP = 5;
    private static final int START_HP = 5;
    private static final int MAX_HP = 10;
    private static final Class<Grass> TARGET_FOOD = Grass.class;


    public Rabbit(AlgoritmSearchPath algoritm) {
        super(START_HP, SPEED_STEP, TARGET_FOOD, algoritm);
    }
}
