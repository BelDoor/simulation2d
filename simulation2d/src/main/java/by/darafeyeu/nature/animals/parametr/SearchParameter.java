package by.darafeyeu.nature.animals.parametr;

import by.darafeyeu.algoritm.AlgorithmSearchPath;
import by.darafeyeu.nature.Entity;

public class SearchParameter {
    private Class<? extends Entity> targetFood;
    private AlgorithmSearchPath algorithmSearchPath;
    private int speedStep;

    public SearchParameter(Class<? extends Entity> targetFood, AlgorithmSearchPath algorithm, int speedStep) {
        this.targetFood = targetFood;
        this.algorithmSearchPath = algorithm;
        this.speedStep = speedStep;
    }

    public Class<? extends Entity> getTargetFood() {
        return targetFood;
    }

    public AlgorithmSearchPath getAlgorithm() {
        return algorithmSearchPath;
    }

    public int getSpeedStep() {
        return speedStep;
    }
}
