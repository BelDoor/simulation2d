package by.darafeyeu.action;

import by.darafeyeu.Exception.CellException;
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
import by.darafeyeu.nature.entity.Rock;
import by.darafeyeu.nature.entity.Tree;
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

        addEntityInWorld(new Grass());
        addEntityInWorld(new Grass());
        addEntityInWorld(new Grass());
        addEntityInWorld(new Rabbit(algoritm));
        addEntityInWorld(new Bear(algoritm));

//            for (int i = allCell / 4; i > 0; i--) {
////                addEntityInWorld(new Grass());
//                addEntityInWorld(new Rabbit(algoritm));
//
//                if (i % 2 == 0) {
////                    addEntityInWorld(new Rock());
//                    addEntityInWorld(new Rabbit(algoritm));
//                    addEntityInWorld(new Rabbit(algoritm));
//                } else {
////                    addEntityInWorld(new Tree());
//                    addEntityInWorld(new Rabbit(algoritm));
//
//                }
//
//                if(i % 5 == 0){
//                    addEntityInWorld(new Bear(algoritm));
//                }
//
//            }

        }




    public void move(Animal animal) {
        if (!animal.isDead()) {
            List<Coordinate> coordinatesForMove = animal.pathSteps();
            int startIndex = 0;
            int finishIndex = coordinatesForMove.size() - 1;

            Coordinate currentCoordinate = coordinatesForMove.get(startIndex);
            Coordinate finishCoordinate = coordinatesForMove.get(finishIndex);

            if (animal.getTargetCell()) {
                //stay last but one cell and we do attack for last Cell Entity
                int lastButOneIndex = finishIndex - 1;
                Coordinate lastButOnefinishCoordinate = coordinatesForMove.get(lastButOneIndex);
                moveStartToFinish(animal, lastButOnefinishCoordinate);

                Entity prey = getEntityForCoordinae(finishCoordinate);

                if (prey instanceof Rabbit) {
                    Rabbit rabbit = (Rabbit) prey;
                    fighte(rabbit, animal);
                } else if (prey instanceof Grass) {
                    removeEntity(finishCoordinate);
                    animal.addCurrentHP();
                    moveStartToFinish(animal, finishCoordinate);
                }
            } else {
                moveStartToFinish(animal, finishCoordinate);
            }
        }
    }

    private void fighte(Rabbit rabbit, Animal animal) {
        if ((rabbit.checkMyDefense(animal.getAttackOnDefenseAponent()))) {
            rabbit.getDamage(animal.attackForApponent());
            if (rabbit.isDead()) {
                Coordinate coordinateRabbit = coordinateEntity(rabbit);
                removeEntity(coordinateRabbit);
                moveStartToFinish(animal, coordinateRabbit);
            }
        } else {
            animal.getDamage(rabbit.attackForApponent());
            if (animal.isDead()) {
                removeEntity(animal);
            }
        }
    }

    public void moveStartToFinish(Entity animal, Coordinate finish) {
        removeEntity(animal);
        try {
            worldMap.putFigure(finish, animal);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        } catch (InvalidEntityException e) {
            throw new RuntimeException(e);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        } catch (CellException e) {
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
        } catch (CellException e) {
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

    private Coordinate coordinateEntity(Entity entity) {
        try {
            return worldMap.getCoordinateEntity(entity);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        }
    }

    //todo двигать всех существ
    //todo наносить домаг по сущнасть распределять хп, удалять умерших и ставить на их координату сущность

    private Entity getEntityForCoordinae(Coordinate coordinateEntity) {
        try {
            return worldMap.getEntity(coordinateEntity);
        } catch (OutOfWorldBoundsException e) {
            throw new RuntimeException(e);
        } catch (InvalidCoordinateException e) {
            throw new RuntimeException(e);
        } catch (CellException e) {
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
        } catch (CellException e) {
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
        } catch (CellException e) {
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
