package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.check_action.CheckAction;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

import java.util.List;

public abstract class Animal extends Entity {
    //добавить счетчик
    //привязать его к equals and hash
    private int currentHP;
    private int maxHP;
    private final int minHP = 0;
    private int speedStep;

    private int defensePoint;

    private int powerHit;
    private boolean targetCell = false;
    private AlgoritmSearchPath algoritmSearchPath;
    private Class<? extends Entity> targetFood;

    //вернем список на количество ходов+ (либо мап с ключем список ходов и значением boolean)-
    //в классе акшен сделать метод движенеи котороый будет вызывать из анимал классы путь атака защита-
    //можем узнать у бфс координата это это цель +
    //если да придумать механику атаки -
    //да. получаем существо из карты -
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

    public int getAttackOnDefenseAponent() {
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

    protected void setCurrentHP(int currentHp) {
        this.currentHP = currentHp;
    }

    protected void setSpeedStep(int speedStep) {
        this.speedStep = speedStep;
    }

    protected void setAlgoritmSearchPath(AlgoritmSearchPath algoritmSearchPath) {
        this.algoritmSearchPath = algoritmSearchPath;
    }

    protected void setTargetFood(Class<? extends Entity> targetFood) {
        this.targetFood = targetFood;
    }

    protected void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    protected void setDefensePoint(int defensePoint) {
        this.defensePoint = defensePoint;
    }

    protected void setPowerHit(int powerHit) {
        this.powerHit = powerHit;
    }

    public int getCurrentHP() {
        return currentHP;
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
