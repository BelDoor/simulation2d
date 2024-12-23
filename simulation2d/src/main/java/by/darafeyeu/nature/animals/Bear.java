package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;

public class Bear extends Animal {
    public static final int SPEED_STEP = 3;
    private static final int START_HP = 8;
    private final int MAX_HP = 15;
    private final int DEFENSE_POINT = 4;
    private static final int POWER_HIT = 4;
    private static final Class<Rabbit> TARGET_FOOD = Rabbit.class;


    public Bear(AlgoritmSearchPath algoritm) {
        initialization(algoritm);
    }

    private void initialization(AlgoritmSearchPath algoritm){
        setAlgoritmSearchPath(algoritm);
        setCurrentHP(START_HP);
        setSpeedStep(SPEED_STEP);
        setTargetFood(TARGET_FOOD);
        setMaxHP(MAX_HP);
        setDefensePoint(DEFENSE_POINT);
        setPowerHit(POWER_HIT);
    }
}
