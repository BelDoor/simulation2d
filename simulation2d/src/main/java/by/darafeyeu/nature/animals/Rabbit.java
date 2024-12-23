package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.nature.entity.Grass;

public class Rabbit extends Animal {
    private static final int SPEED_STEP = 5;
    private static final int START_HP = 2;
    private static final int MAX_HP = 10;
    private final int DEFENSE_POINT = 3;
    private static final int POWER_HIT = 1;
    private static final Class<Grass> TARGET_FOOD = Grass.class;


    public Rabbit(AlgoritmSearchPath algoritm) {
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
