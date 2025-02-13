package by.darafeyeu.nature.animals;

import by.darafeyeu.algoritm.AlgorithmSearchPath;
import by.darafeyeu.nature.animals.parametr.HealthPointsParameter;
import by.darafeyeu.nature.animals.parametr.MoveParameter;
import by.darafeyeu.nature.animals.parametr.SearchParameter;
import by.darafeyeu.random_number.RandomNumber;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;

import java.util.List;

public abstract class Animal extends Entity {

    private HealthPointsParameter hp;
    private MoveParameter moveParameter;
    private SearchParameter searchParameter;

    private boolean targetCell = false;

    public Animal(HealthPointsParameter hp, MoveParameter moveParameter, SearchParameter searchParameter) {
        this.hp = hp;
        this.moveParameter = moveParameter;
        this.searchParameter = searchParameter;
    }

    public List<Coordinate> pathSteps(Coordinate start) {
        setTargetCell(false);

        List<Coordinate> path = searchParameter.getAlgorithm().getPath(start, searchParameter.getTargetFood(), searchParameter.getSpeedStep());

        int startIndexCoordinate = 0;
        int stepForSpeedAnimal = searchParameter.getSpeedStep() + 1;
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

    public int attackForOpponent() {
        return moveParameter.getPowerHit();
    }

    public void addHP() {
       hp.addHP();
    }

    public int attackOnDefenseOpponent() {
        return RandomNumber.d20();
    }

    public boolean isDead() {
       return hp.isDead();
    }

    public boolean checkMyDefense(int damageOnDefense) {
        return hp.checkMyDefense(damageOnDefense);
    }

    public void getDamage(int attackForOpponent) {
        hp.getDamage(attackForOpponent);
    }

    public boolean hasSearchTargetBeenFound() {
        return targetCell;
    }

    private void setTargetCell(boolean b) {
        targetCell = b;
    }

    private void isTargetCell(Coordinate coordinate) {
        targetCell = searchParameter.getAlgorithm().isTargetCoordinate(coordinate);
    }


    public void drainEnergyFromAnimal() {
        moveParameter.setMinusEnergy();
        getDamage(moveParameter.drainEnergyFromAnimal());
    }

    public void restoreEnergyToAnimal() {
        moveParameter.addFullEnergy();
    }

}
