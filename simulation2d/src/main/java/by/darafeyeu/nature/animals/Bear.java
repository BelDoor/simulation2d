package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.nature.entity.Grass;

public class Bear extends Animal {
    public static final int SPEED_STEP = 1;
    private static final int START_HP = 8;
    private final int MAX_HP = 15;
    private static final Class<Rabbit> TARGET_FOOD = Rabbit.class;


    public Bear(AlgoritmSearchPath algoritm) {
        super(START_HP, SPEED_STEP, TARGET_FOOD, algoritm);
    }
}
