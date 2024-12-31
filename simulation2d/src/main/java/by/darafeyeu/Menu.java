package by.darafeyeu;

import by.darafeyeu.simulation.Simulation;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private static final int START_SIMULATION = 1;
    private static final int ONE_ITERATION = 2;
    private static final int EXIT = 3;
    private static final int PAUSE_SIMULATION = 4;
    private static final String GET_ACTION = "Get action";
    private static final String START_ACTION = "1 - Start simulation";
    private static final String MAKE_ONE_ACTION = "2 - Make one iteration";
    private static final String EXIT_ACTION = "3 - Exit";
    private static final String PAUSE_ACTION = "4 - Pause simulation";
    private static final String ENTER_SIZE = "Enter length and height from 10 to 100";


    public void menu() {
        enterMessage(ENTER_SIZE);
        int length = scanner.nextInt();
        int height = scanner.nextInt();
        Simulation simulation = new Simulation(length, height);

        while (true) {
            enterMessage();
            switch (scanner.nextInt()) {
                case START_SIMULATION:
                    int userInput = START_SIMULATION;
                    while (true) {
                        simulation.simulation();
                        userInput = simulationControl(userInput);
                        if (userInput == PAUSE_SIMULATION)
                            break;
                        enterMessage(PAUSE_ACTION);
                    }
                    break;
                case ONE_ITERATION:
                    simulation.simulation();
                    break;
                case EXIT:
                    scanner.close();
                    System.exit(0);
                    break;
            }
        }
    }

    private int simulationControl(int current) {
        try {
            Thread.sleep(1000);
            if (System.in.available() > 0) {
                if (scanner.nextInt() == PAUSE_SIMULATION) {
                    enterMessage(START_ACTION);
                    return PAUSE_SIMULATION;

                }
                return START_SIMULATION;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return current;
    }

    private void enterMessage(String message) {
        System.out.printf("%s\n", message);
    }

    private void enterMessage() {
        System.out.printf("%s:\n%s.\n%s.\n%s.\n%s.\n", GET_ACTION, START_ACTION, MAKE_ONE_ACTION,
                EXIT_ACTION, PAUSE_ACTION);
    }
}