package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgorithmSearchPath;
import by.darafeyeu.nature.animals.parametr.HealthPointsParameter;
import by.darafeyeu.nature.animals.parametr.MoveParameter;
import by.darafeyeu.nature.animals.parametr.SearchParameter;
import by.darafeyeu.nature.entity.Grass;

public class Rabbit extends Animal {

    private static final int START_HP = 5;
    private static final int MAX_HP = 10;
    private static final int DEFENSE_POINT = 4;

    private static final int POWER_HIT = 2;
    private static final int MAX_ENERGE = 30;
    private static final int MINUS_ENERGE = 5;
    private static final int START_ENERGE = 15;

    private static final int SPEED_STEP = 5;
    private static final Class<Grass> TARGET_FOOD = Grass.class;

    public Rabbit(AlgorithmSearchPath algorithm) {
        super(
                new HealthPointsParameter(START_HP, MAX_HP, DEFENSE_POINT),
                new MoveParameter(START_ENERGE, MINUS_ENERGE, MAX_ENERGE, POWER_HIT),
                new SearchParameter(TARGET_FOOD,algorithm,SPEED_STEP)
        );
    }
}
