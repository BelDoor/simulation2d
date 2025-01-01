package by.darafeyeu;

import by.darafeyeu.simulation.Simulation;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private static final int START_SIMULATION = 1;
    private static final int ONE_ITERATION = 2;
    private static final int EXIT = 3;
    private static final int PAUSE_SIMULATION = 4;
    private static final int DEFAULT_SIZE = 9;

    private static final String GET_ACTION = "Get action";
    private static final String START_ACTION = "1 - Start simulation";
    private static final String MAKE_ONE_ACTION = "2 - Make one iteration";
    private static final String EXIT_ACTION = "3 - Exit";
    private static final String PAUSE_ACTION = "4 - Pause simulation";
    private static final String ENTER_LENGTH_SIZE = "Enter length from 10 to 100";
    private static final String ENTER_HEIGHT_SIZE = "Enter height from 10 to 100";
    private static final String ENTER_INCORRECT = "You entered incorrect information";
    private static final String ENTER_MENU = String.format("%s:\n%s.\n%s.\n%s.\n%s.\n", GET_ACTION, START_ACTION, MAKE_ONE_ACTION,
            EXIT_ACTION, PAUSE_ACTION);


    public void menu() {

        int length = enterNumber(ENTER_LENGTH_SIZE);
        int height = enterNumber(ENTER_HEIGHT_SIZE);
        Simulation simulation = new Simulation(length, height);

        while (true) {
            switch (enterNumber(ENTER_MENU)) {
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

    private int enterNumber(String message){
        enterMessage(message);
        while (!scanner.hasNextInt()){
            enterMessage(ENTER_INCORRECT);
            enterMessage(message);
            scanner.next();
        }
        return scanner.nextInt();
    }
}
