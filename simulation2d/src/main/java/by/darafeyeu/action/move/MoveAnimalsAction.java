package by.darafeyeu.action.move;

import by.darafeyeu.action.Action;

import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.world.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class MoveAnimalsAction extends Action {

    List<Animal> animals;

    public MoveAnimalsAction(WorldMap worldMap) {
        super(worldMap);
    }

    public void action() {
        setAnimals(worldMap.getEntities());

        for (int i = 0; i < animals.size(); i++) {
            Animal animal = animals.get(i);

            animal.drainEnergyFromAnimal();

            if (!animal.isDead()) {
                List<Coordinate> pathSteps = null;

                if (worldMap.getOptionalCoordinateEntity(animal).isPresent()) {
                    pathSteps = animal.pathSteps(worldMap.getOptionalCoordinateEntity(animal).get());
                } else {
                    continue;
                }

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

            } else {
                removeEntity(animal);
                animal.minusCount();
            }
        }
    }

    private void setAnimals(List<Entity> entityList) {
        List<Animal> animals = new ArrayList<>();
        for (Entity entity : entityList) {
            if (entity instanceof Animal) {
                animals.add((Animal) entity);
            }
        }
        this.animals = animals;
    }


    private void fight(Animal animal, Coordinate targetEntity) {
        if (isEntityRabbit(animal)) {
            fightRabbit(animal, targetEntity);
        } else {
            fightBear(animal, targetEntity);
        }
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


    private void fightRabbit(Animal animal, Coordinate target) {
        Entity targetEntity = getEntityForCoordinate(target);
        removeEntityAndDecrement(targetEntity);
        eat(animal);
        move(animal, target);
    }


    private void move(Animal animal, Coordinate finish) {
        removeEntity(animal);

        worldMap.addEntityInWorld(finish, animal);
    }

    private void removeEntityAndDecrement(Entity entity) {
        removeEntity(entity);

        animals.remove(entity);
        entity.minusCount();
    }

    private void eat(Animal animal) {
        animal.regenerationHP();
        animal.restoreEnergyToAnimal();
    }

    private boolean isEntityRabbit(Entity entity) {
        return entity instanceof Rabbit;
    }

    private void removeEntity(Entity entity) {
        if (worldMap.getOptionalCoordinateEntity(entity).isPresent()) {
            Coordinate currentCoordinate = worldMap.getOptionalCoordinateEntity(entity).get();
            worldMap.removeEntity(currentCoordinate);
        }
    }

    private Entity getEntityForCoordinate(Coordinate coordinate) {
        return worldMap.getOptionalEntity(coordinate).get();
    }

}

