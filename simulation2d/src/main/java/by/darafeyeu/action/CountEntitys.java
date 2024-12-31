package by.darafeyeu.action;

public class CountEntitys {
    protected static int currentGrass = 0;
    protected static int currentBear = 0;
    protected static int currentRabbit = 0;
    protected static int maxBear = 0;
    protected static int maxRabbit = 0;
    protected static int maxGrass = 0;

    protected static void minusCountGrass() {
        currentGrass--;
    }

    protected static void minusCountBear() {
        currentBear--;
    }

    protected static void minusCountRabbit() {
        currentRabbit--;
    }

    public static int getCountGrass() {
        return currentGrass;
    }

    public static int getCountBear() {
        return currentBear;
    }

    public static int getCountRabbit() {
        return currentRabbit;
    }
}
