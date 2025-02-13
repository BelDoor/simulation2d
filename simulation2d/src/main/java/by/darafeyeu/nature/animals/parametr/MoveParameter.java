package by.darafeyeu.nature.animals.parametr;

public class MoveParameter {
    private static final int ZERO_POINT = 0;

    protected int energy;
    protected int minusEnergy;
    protected int maxEnergy;
    private int minEnergy;
    protected int powerHit;

    public MoveParameter() {
        minEnergy = ZERO_POINT;
    }

    public MoveParameter(int energy, int minusEnergy, int maxEnergy, int powerHit) {
        this();
        this.energy = energy;
        this.minusEnergy = minusEnergy;
        this.maxEnergy = maxEnergy;
        this.powerHit = powerHit;
    }

    public int getPowerHit() {
        return powerHit;
    }

    public void setMinusEnergy() {
        energy = energy - minusEnergy;
    }

    public int drainEnergyFromAnimal() {
        if (energy > minEnergy) {
            return 0;
        } else {
            return 1;
        }
    }

    public void addFullEnergy() {
        energy = maxEnergy;
    }
}
