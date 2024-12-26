package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.nature.entity.Grass;

public class Rabbit extends Animal {
    private static final int SPEED_STEP = 5;
    private static final int START_HP = 4;
    private static final int MAX_HP = 10;
    private final int DEFENSE_POINT = 3;
    private static final int POWER_HIT = 1;
    private static final Class<Grass> TARGET_FOOD = Grass.class;


    public Rabbit(AlgoritmSearchPath algoritm) {
        super.algoritmSearchPath = algoritm;
        super.currentHP = START_HP;
        super.speedStep = SPEED_STEP;
        super.targetFood = TARGET_FOOD;
        super.maxHP = MAX_HP;
        super.defensePoint = DEFENSE_POINT;
        super.powerHit = POWER_HIT;
    }
}
