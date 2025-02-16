package by.darafeyeu.menu;

import by.darafeyeu.nature.Entity;
import by.darafeyeu.simulation.Simulation;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);

    private static final int MIN_SIZE_WORLD = 9;
    private static final int MAX_SIZE_WORLD = 100;
    private static final int ZERO = 0;

    private static final int START_SIMULATION = 1;
    private static final int ONE_ITERATION = 2;
    private static final int EXIT = 3;
    private static final int PAUSE_SIMULATION = 4;

    private static final String GET_ACTION = "Get action";
    private static final String START_ACTION = START_SIMULATION + " - Start simulation";
    private static final String MAKE_ONE_ACTION = ONE_ITERATION + " - Make one iteration";
    private static final String EXIT_ACTION = EXIT + " - Exit";
    private static final String PAUSE_ACTION = PAUSE_SIMULATION + " - Pause simulation";
    private static final String ENTER_LENGTH_SIZE = "Enter length from 10 to 100";
    private static final String ENTER_HEIGHT_SIZE = "Enter height from 10 to 100";
    private static final String ENTER_INCORRECT = "You entered incorrect information";
    private static final String CONTINUATION_SIMULATION = "Continuation of the simulation";
    private static final String ENTER_PAUSE = "Enter -> 4  for pause of the simulation";
    private static final String INCORRECT_SIZE_FOR_MAP = "You enter incorrect size map!";
    private static final String ENTER_MENU = String.format("%s:\n%s.\n%s.\n%s.\n%s.", GET_ACTION, START_ACTION, MAKE_ONE_ACTION,
            EXIT_ACTION, PAUSE_ACTION);
    private Simulation simulation;

    public void menu() {

        int length = validationSizeWorld(ENTER_LENGTH_SIZE);
        int height = validationSizeWorld(ENTER_HEIGHT_SIZE);
        simulation = new Simulation(length, height);

        while (true) {
            switch (enterNumber(ENTER_MENU)) {
                case START_SIMULATION:
                    int userInput = START_SIMULATION;
                    while (true) {
                        simulation.simulation();
                        outPutStatistics();
                        userInput = simulationControl(userInput);
                        if (userInput == PAUSE_SIMULATION)
                            break;
                    }
                    break;
                case ONE_ITERATION:
                    simulation.simulation();
                    outPutStatistics();
                    break;
                case EXIT:
                    scanner.close();
                    System.exit(ZERO);
                    break;
            }
        }
    }

    private int simulationControl(int current) {
        outPutMessage(ENTER_PAUSE);
        try {
            Thread.sleep(1000);
            if (System.in.available() > ZERO) {
                if (enterNumber(PAUSE_ACTION) == PAUSE_SIMULATION) {
                    return PAUSE_SIMULATION;
                }
                outPutMessage(CONTINUATION_SIMULATION);
                return START_SIMULATION;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return current;
    }

    private void outPutMessage(String message) {
        System.out.printf("%s\n->", message);
    }

    private void outPutStatistics() {
        System.out.printf("Count bear - %d \nCount rabbit - %d\nCount grass - %d\nRound simulation -> %d\n",
                Entity.getEntityCount("Bear"), Entity.getEntityCount("Rabbit"),
                Entity.getEntityCount("Grass"), simulation.getCountRound());
    }

    private int validationSizeWorld(String message) {
        boolean correct = false;
        int size = ZERO;
        while (!correct) {
            size = enterNumber(message);
            if (correctSizeMap(size)) {
                correct = true;
            } else {
                outPutMessage(INCORRECT_SIZE_FOR_MAP);
            }
        }
        return size - START_SIMULATION;
    }

    private boolean correctSizeMap(int size) {
        return (size >= MIN_SIZE_WORLD && size <= MAX_SIZE_WORLD);
    }

    private int enterNumber(String message) {
        outPutMessage(message);

        while (!scanner.hasNextInt()) {
            outPutMessage(ENTER_INCORRECT);
            outPutMessage(message);
            scanner.next();
        }
        return scanner.nextInt();
    }
}
