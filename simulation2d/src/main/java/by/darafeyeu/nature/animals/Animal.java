package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgoritmSearchPath;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

import java.util.List;

public abstract class Animal extends Entity {
    //добавить счетчик
    //привязать его к equals and hash
    private int currentHp;
    private int speedStep;
    private boolean targetCell = false;
    private AlgoritmSearchPath algoritmSearchPath;
    private Class<? extends Entity> targetFood;

    public Animal(int hp, int speedStep, Class<? extends Entity> targetFood, AlgoritmSearchPath algoritm) {
        this.currentHp = hp;
        this.speedStep = speedStep;
        this.targetFood = targetFood;
        this.algoritmSearchPath = algoritm;
    }

    //вернем список на количество ходов+ (либо мап с ключем список ходов и значением boolean)-
    //в классе акшен сделать метод движенеи котороый будет вызывать из анимал классы путь атака защита-
    //можем узнать у бфс координата это это цель +
    //если да придумать механику атаки -
    //да. получаем существо из карты -
    //механика голада
    public List<Coordinate> pathSteps() {
        setTargetCell(false);

        int startIndexCoordinate = 0;
        int stepForSpeedAnimal = speedStep + 1;

        List<Coordinate> path = algoritmSearchPath.getPath(this);
        int finishIndexStep = path.size() - 1;
        List<Coordinate> coordinates;

        if (path.size() > stepForSpeedAnimal) {
            coordinates = path.subList(startIndexCoordinate, stepForSpeedAnimal);
        } else {
                coordinates = path.subList(startIndexCoordinate, path.size());
                isTargetCell(path.get(finishIndexStep));
        }
        return coordinates;
    }

    public int getCurrentHp() {
        return currentHp;
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
