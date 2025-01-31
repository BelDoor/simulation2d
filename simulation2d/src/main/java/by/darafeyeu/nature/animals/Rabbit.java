package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgorithmSearchPath;
import by.darafeyeu.nature.entity.Grass;

public class Rabbit extends Animal {
    private static final int SPEED_STEP = 5;
    private static final int START_HP = 5;
    private static final int MAX_HP = 10;
    private final int DEFENSE_POINT = 4;
    private static final int POWER_HIT = 2;
    private final int MAX_ENERGE = 30;
    private final int MINUS_ENERGE = 5;
    private final int START_ENERGE = 15;

    private static final Class<Grass> TARGET_FOOD = Grass.class;

    public Rabbit(AlgorithmSearchPath algoritm) {
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
