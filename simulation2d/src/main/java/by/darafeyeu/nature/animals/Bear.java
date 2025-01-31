package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgorithmSearchPath;

public class Bear extends Animal {
    public static final int SPEED_STEP = 2;
    private static final int START_HP = 10;
    private final int MAX_HP = 15;
    private final int DEFENSE_POINT = 4;
    private static final int POWER_HIT = 6;
    private final int MAX_ENERGE = 20;
    private final int MINUS_ENERGE = 2;
    private final int START_ENERGE = 10;
    private static final Class<Rabbit> TARGET_FOOD = Rabbit.class;


    public Bear(AlgorithmSearchPath algoritm) {
        super.algorithmSearchPath = algoritm;
        super.currentHP = START_HP;
        super.speedStep = SPEED_STEP;
        super.targetFood = TARGET_FOOD;
        super.maxHP = MAX_HP;
        super.defensePoint = DEFENSE_POINT;
        super.powerHit = POWER_HIT;
        super.maxEnergy = MAX_ENERGE;
        super.minusEnergy = MINUS_ENERGE;
        super.energy = START_ENERGE;
    }
}
