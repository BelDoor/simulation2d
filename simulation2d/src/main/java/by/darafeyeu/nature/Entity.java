package by.darafeyeu.nature;

import java.util.HashMap;
import java.util.Map;

public abstract class Entity {
    private static Map<String, Integer> entityCounts = new HashMap<>();

    protected Entity(){
        entityCounts.put(this.getClass().getSimpleName(),
                entityCounts.getOrDefault(this.getClass().getSimpleName(), 0) +1);
    }

    public int getEntityCount(){
        return entityCounts.get(this.getClass().getSimpleName());
    }

    public void minusCount(){
        entityCounts.put(this.getClass().getSimpleName(),
                entityCounts.getOrDefault(this.getClass().getSimpleName(), 0) - 1);
    }


}
