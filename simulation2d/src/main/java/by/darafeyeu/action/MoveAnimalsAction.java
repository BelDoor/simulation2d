package by.darafeyeu.action;

import by.darafeyeu.exception.InvalidCoordinateException;
import by.darafeyeu.exception.InvalidEntityException;
import by.darafeyeu.exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.world.WorldMap;

import java.util.List;


public class MoveAnimalsAction extends Action {
    List<Animal> animals;
    public MoveAnimalsAction(WorldMap worldMap) {
        super(worldMap);
    }

    public void action() {
        animals = worldMap.getAnimals();

        for (int i = 0; i < animals.size() ; i++) {
            Animal animal = animals.get(i);
            animal.drainEnergyFromAnimal();
            if (!animal.isDead()) {
                List<Coordinate> pathSteps = animal.pathSteps();
                worldMap.setTracers(pathSteps);
                int indexLastStep = pathSteps.size() - 1;
                int indexLastButOneStep = 0;
                if (indexLastStep > 1) {
                    indexLastButOneStep = indexLastStep - 1;
                }
                if (!animal.hasSearchTargetBeenFound()) {
                    move(animal, pathSteps.get(indexLastStep));
                } else {
                    move(animal, pathSteps.get(indexLastButOneStep));
                    fight(animal, pathSteps.get(indexLastStep));
                }
            }else {
                removeEntity(animal);
                animal.minusCount();
                //todo убрать старй счетчик
                minusCountEntity(animal);
            }
        }
    }


    private void move(Animal animal, Coordinate finish) {
        removeEntity(animal);
        try {
            worldMap.putFigure(finish, animal);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntityException e) {
            throw new RuntimeException(e);
        }
    }

    private void fight(Animal animal, Coordinate targetEntity) {
        if (isEntityRabbit(animal)) {
            fightRabbit(animal, targetEntity);
        } else {
            fightBear(animal, targetEntity);
        }
    }

    private void fightRabbit(Animal animal, Coordinate target) {
        Entity targetEntity = getEntityForCoordinate(target);
        removeEntityAndDecrement(targetEntity);
        eat(animal);
        move(animal, target);
    }


    private void fightBear(Animal bear, Coordinate targetEntity) {
        if (isEntityRabbit(getEntityForCoordinate(targetEntity))) {
            Rabbit rabbit = (Rabbit) getEntityForCoordinate(targetEntity);
            if (rabbit.checkMyDefense(bear.attackOnDefenseOpponent())) {
                rabbit.getDamage(bear.attackForOpponent());
                if (rabbit.isDead()) {
                    removeEntityAndDecrement(rabbit);
                    eat(bear);
                    move(bear, targetEntity);
                }
            } else {
                bear.getDamage(rabbit.attackForOpponent());
                if (bear.isDead()) {
                    removeEntityAndDecrement(bear);
                }
            }
        }
    }

    //todo убрать старый счетчик.
    private void removeEntityAndDecrement(Entity entity){
        removeEntity(entity);
        animals.remove(entity);
        entity.minusCount();
        minusCountEntity(entity);
    }

    private void eat(Animal animal){
        animal.addHP();
        animal.addEnergyToAnimal();
    }

    private boolean isEntityRabbit(Entity entity) {
        return entity instanceof Rabbit;
    }

    private void removeEntity(Entity entity) {
        try {
            Coordinate currentCoordinate = worldMap.getCoordinateEntity(entity);
            worldMap.removeEntity(currentCoordinate);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        } catch (InvalidCoordinateException e) {

        }
    }

    private Entity getEntityForCoordinate(Coordinate coordinate) {
        try {
            return worldMap.getEntity(coordinate);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        }
    }

    private void minusCountEntity(Entity entity) {
        String nameEntity = entity.getClass().getSimpleName();
        switch (nameEntity) {
            case "Bear":
                CountEntitys.minusCountBear();
                break;
            case "Rabbit":
                CountEntitys.minusCountRabbit();
                break;
            case "Grass":
                CountEntitys.minusCountGrass();
                break;
        }
    }

}

