package by.darafeyeu.check_action;

import java.time.Instant;
import java.util.Random;

public class CheckAction {
    public static final int D_20 = 21;
    public static final int D_3 = 3;

    public static final int EXCLUDE_ZERO = 1;

    private static final Random random = new Random(Instant.now().getEpochSecond());

    public static int d20() {
        return random.nextInt(D_20);
    }

    public static int d3() {
        return (random.nextInt(D_3) + EXCLUDE_ZERO);
    }

    public static int randomParamCoordinate(int size) {
        return (random.nextInt(size + 1));
    }

}
