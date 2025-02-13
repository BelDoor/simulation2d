package by.darafeyeu.nature.animals.parametr;

import by.darafeyeu.random_number.RandomNumber;

public class HealthPointsParameter {

    private static final int ZERO_POINT = 0;

    private int currentHP;
    private int maxHP;
    private int minHP;
    private int defensePoint;


    private HealthPointsParameter() {
        minHP = ZERO_POINT;
    }

    public HealthPointsParameter(int startHP, int maxHP, int defensePoint) {
        this();
        this.currentHP = startHP;
        this.maxHP = maxHP;
        this.defensePoint = defensePoint;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void addHP() {
        int plusHP = RandomNumber.d3();
        if ((currentHP + plusHP) <= maxHP) {
            currentHP = currentHP + plusHP;
        } else {
            currentHP = maxHP;
        }
    }

    public boolean isDead() {
        return currentHP <= minHP;
    }

    public void getDamage(int attackForAponent) {
        if ((currentHP - attackForAponent) > minHP) {
            currentHP = currentHP - attackForAponent;
        } else {
            currentHP = minHP;
        }
    }

    public boolean checkMyDefense(int damageOnDefense) {
        if (defensePoint < damageOnDefense) {
            return true;
        }
        return false;
    }
}
