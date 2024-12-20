package by.darafeyeu.coordinate;

public class CoordinateForAlgoritm extends Coordinate {
    private static int DEFAULT_STEP = -1;
    private static int COORDINATE_STEP = 0;
    private int step;

    public CoordinateForAlgoritm() {
        super(DEFAULT_STEP, DEFAULT_STEP);
        this.step = DEFAULT_STEP;
    }

    public CoordinateForAlgoritm(Integer length, Integer height) {
        super(length, height);
        this.step = COORDINATE_STEP;
    }

    public CoordinateForAlgoritm(Coordinate coordinate) {
        super(coordinate);
        this.step = COORDINATE_STEP;
    }

    public void steepCount(int step) {
        this.step = step + 1;
    }


    public int getStep() {
        return step;
    }

}
