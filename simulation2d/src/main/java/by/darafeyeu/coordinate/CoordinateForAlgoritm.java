package by.darafeyeu.coordinate;

public class CoordinateForAlgoritm extends Coordinate{

    public CoordinateForAlgoritm(Integer length, Integer height) {
        super(length, height);
    }

    public CoordinateForAlgoritm(Coordinate coordinate) {
        super(coordinate);
    }

    public CoordinateForAlgoritm(CoordinateForAlgoritm coordinate) {
        super(coordinate);
    }

    private int steep = 0;

    public void steepCount(int steep){
        this.steep = steep + 1;
    }

    public int getSteep() {
        return steep;
    }
}
