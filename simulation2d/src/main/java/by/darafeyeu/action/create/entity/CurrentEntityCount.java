package by.darafeyeu.action.create.entity;

import by.darafeyeu.action.CountEntitys;

public class CurrentEntityCount extends CountEntitys {
    protected static void addCountBear() {
        currentBear++;
    }
    protected static void addCountGrass() {
        currentGrass++;
    }

    protected static void addCountRabbit() {
        currentRabbit++;
    }

    protected static boolean isMinBear(){
        return currentBear <= (maxBear / 8);
    }

    protected static boolean isMinRabbit(){
        return currentRabbit <= (maxRabbit / 6);
    }

    protected static boolean isMinGrass(){
        return currentGrass < (maxGrass / 3);
    }
}
