package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.check_action.CheckAction;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

import java.util.List;

public abstract class Animal extends Entity {
    //добавить счетчик
    //привязать его к equals and hash
    protected int currentHP;
    protected int maxHP;
    private final int minHP = 0;
    protected int speedStep;

    protected int defensePoint;

    protected int powerHit;
    private boolean targetCell = false;
    protected AlgoritmSearchPath algoritmSearchPath;
    protected Class<? extends Entity> targetFood;

    //механика голада
    public List<Coordinate> pathSteps() {
        setTargetCell(false);
        List<Coordinate> path = algoritmSearchPath.getPath(this);

        int startIndexCoordinate = 0;
        int stepForSpeedAnimal = speedStep + 1;
        int finishIndexStep = path.size() - 1;

        List<Coordinate> coordinates;

        if (path.size() > stepForSpeedAnimal) {
            coordinates = path.subList(startIndexCoordinate, stepForSpeedAnimal);

        } else {
            isTargetCell(path.get(finishIndexStep));
            coordinates = path.subList(startIndexCoordinate, path.size());
        }
        return coordinates;
    }

    public int attackForApponent() {
        return powerHit;
    }

    public void addCurrentHP() {
        int plusHP = CheckAction.d3();
        if ((currentHP + plusHP) <= maxHP) {
            currentHP = currentHP + plusHP;
        } else {
            currentHP = maxHP;
        }
    }

    public int getAttackOnDefenseOpponent() {
        return CheckAction.d20();
    }

    public boolean isDead() {
        return currentHP == minHP;
    }

    public boolean checkMyDefense(int damageOnDefense) {
        if (defensePoint < damageOnDefense) {
            return true;
        }
        return false;
    }

    public void getDamage(int attackForAponent) {
        if ((currentHP - attackForAponent) > minHP) {
            currentHP = currentHP - attackForAponent;
        } else {
            currentHP = minHP;
        }
    }

    public int getSpeedStep() {
        return speedStep;
    }

    public Class<? extends Entity> getTargetFood() {
        return targetFood;
    }

    public boolean getTargetCell() {
        return targetCell;
    }

    public void setTargetCell(boolean b) {
        targetCell = b;
    }

    private void isTargetCell(Coordinate coordinate) {
        targetCell = algoritmSearchPath.isTargetCoordinate(coordinate);
    }
}
