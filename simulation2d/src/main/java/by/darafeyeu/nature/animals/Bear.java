package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;

public class Bear extends Animal {
    public static final int SPEED_STEP = 3;
    private static final int START_HP = 8;
    private final int MAX_HP = 15;
    private final int DEFENSE_POINT = 4;
    private static final int POWER_HIT = 7;
    private static final Class<Rabbit> TARGET_FOOD = Rabbit.class;


    public Bear(AlgoritmSearchPath algoritm) {
        super.algoritmSearchPath = algoritm;
        super.currentHP = START_HP;
        super.speedStep = SPEED_STEP;
        super.targetFood = TARGET_FOOD;
        super.maxHP = MAX_HP;
        super.defensePoint = DEFENSE_POINT;
        super.powerHit = POWER_HIT;
    }
}
