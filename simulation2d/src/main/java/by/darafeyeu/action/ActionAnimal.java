package by.darafeyeu.action;

import by.darafeyeu.Exception.InvalidCoordinateException;
import by.darafeyeu.Exception.InvalidEntityException;
import by.darafeyeu.Exception.OutOfWorldBoundsException;
import by.darafeyeu.algoritm.BreadthFirstSearch;
import by.darafeyeu.coordinate.Coordinate;
import by.darafeyeu.nature.Entity;
import by.darafeyeu.nature.animals.Animal;
import by.darafeyeu.nature.animals.Bear;
import by.darafeyeu.nature.animals.Rabbit;
import by.darafeyeu.nature.entity.Grass;
import by.darafeyeu.world.WorldMap;

import java.util.List;

public class ActionAnimal {
    private final WorldMap worldMap;

    public ActionAnimal(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    //отдедьный метод создания животных
    public void planWorld(BreadthFirstSearch algoritm) {
        int allCell = worldMap.getAllCell();
        addEntityInWorld(new Rabbit(algoritm));
        addEntityInWorld(new Rabbit(algoritm));
        addEntityInWorld(new Grass());
        addEntityInWorld(new Grass());
        addEntityInWorld(new Grass());
        addEntityInWorld(new Bear(algoritm));
/*        for (int i = allCell / 4; i > 0; i--) {
            addEntityInWorld(new Grass());

            if (i % 2 == 0) {
                addEntityInWorld(new Rock());

            } else {
                addEntityInWorld(new Tree());
            }
            if (i % 10 == 0) {
                addEntityInWorld(new Bear(algoritm));
            }
        }*/
    }


    public void move(Animal animal) {
        if (!animal.isDead()) {
            List<Coordinate> coordinatesForMove = animal.pathSteps();
            int startIndex = 0;
            int finishIndex = coordinatesForMove.size() - 1;
            int lastButOneIndex = finishIndex - 1;

            Coordinate finishCoordinate = coordinatesForMove.get(finishIndex);
            Coordinate startButOnefinishCoordinate = coordinatesForMove.get(startIndex);
            if (animal.getTargetCell()) {
                //stay last but one cell and we do attack for last Cell Entity


                if (startIndex < lastButOneIndex) {
                    startButOnefinishCoordinate = coordinatesForMove.get(lastButOneIndex);
                    moveToFinish(animal, startButOnefinishCoordinate);
                }

                Entity prey = getEntityForCoordinae(finishCoordinate);

                if (prey instanceof Rabbit) {
                    Rabbit rabbit = (Rabbit) prey;
                    fighte(rabbit, animal);
                } else if (prey instanceof Grass) {
                    stepToOccupiedCoordinate(animal, finishCoordinate);
//                    worldMap.setTracers(coordinatesForMove);
                }
                worldMap.setTracers(coordinatesForMove);
            } else {
                moveToFinish(animal, finishCoordinate);
                worldMap.setTracers(coordinatesForMove);
            }
        }
    }

    private void fighte(Rabbit rabbit, Animal animal) {
        if ((rabbit.checkMyDefense(animal.getAttackOnDefenseOpponent()))) {
            rabbit.getDamage(animal.attackForApponent());
            if (rabbit.isDead()) {
                Coordinate coordinateRabbit = getCoordinateForEntity(rabbit);
                stepToOccupiedCoordinate(animal, coordinateRabbit);
            }
        } else {
            animal.getDamage(rabbit.attackForApponent());
            if (animal.isDead()) {
                removeEntity(animal);
            }
        }
    }
    public void getDamag(Animal a){
        a.getDamage(2);
    }

    private void stepToOccupiedCoordinate(Animal animal, Coordinate finish) {
        removeEntity(finish);
        animal.addCurrentHP();
        moveToFinish(animal, finish);
    }

    public void moveToFinish(Entity animal, Coordinate finish) {
        removeEntity(animal);
        try {
            worldMap.putFigure(finish, animal);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntityException e) {
            throw new RuntimeException(e);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeEntity(Entity entity) {
        Coordinate currentCoordinate = getCoordinateForEntity(entity);
        try {
            worldMap.removeEntity(currentCoordinate);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        }
    }

    private Coordinate getCoordinateForEntity(Entity entity) {
        try {
            return worldMap.getCoordinateEntity(entity);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        }
    }

    //todo переработать обработку ошибок
    // как обработать ошибки чтоб программа не встала
    //todo двигать всех существ
    //todo наносить домаг по сущнасть распределять хп, удалять умерших и ставить на их координату сущность

    private Entity getEntityForCoordinae(Coordinate coordinateEntity) {
        try {
            return worldMap.getEntity(coordinateEntity);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeEntity(Coordinate currentCoordinate) {
        try {
            worldMap.removeEntity(currentCoordinate);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        }
    }

    public void addEntityInWorld(Entity entity) {
        try {
            worldMap.putFigure(worldMap.emptyRandomCoordinate(), entity);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntityException e) {
            throw new RuntimeException(e);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    public void addEntityInWorld(Coordinate coordinate, Entity entity) {
        try {
            worldMap.putFigure(coordinate, entity);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntityException e) {
            throw new RuntimeException(e);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        }
    }

    public void miniSimulation() {
        for (Animal animal : worldMap.getAnimals()) {
            if (animal.isDead()) {
                continue;
            }
            move(animal);
        }
    }
}
