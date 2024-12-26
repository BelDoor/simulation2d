package by.darafeyeu.action;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.world.WorldMap;

import java.util.List;


public class MoveAnimalsAction extends Action {

    public MoveAnimalsAction(WorldMap worldMap) {
        super.worldMap = worldMap;
    }

    public void action() {
        worldMap.cleanTracers();
        for (Animal animal : worldMap.getAnimals()) {
            if (!animal.isDead()) {
                List<Coordinate> pathSteps = animal.pathSteps();
                worldMap.setTracers(pathSteps);
                int indexLastStep = pathSteps.size() - 1;
                int indexLastButOneStep = 0;
                if (indexLastStep > 1) {
                    indexLastButOneStep = indexLastStep - 1;
                }
                if (!animal.getTargetCell()) {
                    move(animal, pathSteps.get(indexLastStep));
                } else {
                    move(animal, pathSteps.get(indexLastButOneStep));
                    fight(animal, pathSteps.get(indexLastStep));
                }
            }
        }
    }


    //to do в общем методе итерация сделать
    //вызов всех существ есть метод в карте
    //вызвать координаты ходов
    //посмотреть последний шаг к цели или нет
    //нет идем на него
    //да начинаем проверку на цель
    //вызываем метод драка


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
        removeEntity(getEntityForCoordinate(target));
        animal.addCurrentHP();
        move(animal, target);
    }


    private void fightBear(Animal bear, Coordinate targetEntity) {
        if (isEntityRabbit(getEntityForCoordinate(targetEntity))) {
            Rabbit rabbit = (Rabbit) getEntityForCoordinate(targetEntity);
            if (rabbit.checkMyDefense(bear.getAttackOnDefenseOpponent())) {
                rabbit.getDamage(bear.attackForApponent());
                if (rabbit.isDead()) {
                    removeEntity(rabbit);
                    bear.addCurrentHP();
                    move(bear, targetEntity);
                }
            } else {
                bear.getDamage(rabbit.attackForApponent());
                if (bear.isDead()) {
                    removeEntity(bear);
                }
            }
        }
    }

    private boolean isEntityRabbit(Entity entity) {
        return entity instanceof Rabbit;
    }

    private void eat() {

    }

    public void removeEntity(Entity entity) {
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

}

