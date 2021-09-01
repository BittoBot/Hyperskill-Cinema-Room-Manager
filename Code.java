package cinema;

import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Cinema {
    private static int rows;
    private static int columns;
    private static int totalSeats;
    private static int clientRow;
    private static int clientColumn;
    private static int ticketsBought = 0;
    private static int income = 0;
    private static char[][] grid = new char[rows][columns];
    static allStates currentAction = allStates.GET_ROW;

    static void inputReceiver(int input) {
        if (currentAction == allStates.CHECK_INPUT) {
            if (input == 1) {
                showSeats();
                currentAction = allStates.SHOW_MENU;
            } else if (input == 2) {
                System.out.println("Enter a row number:");
                currentAction = allStates.CLIENT_ROW;
                return;
            } else if (input == 3) {
                statistics();
            } else if (input == 0) {
                 currentAction = allStates.EXIT;
                 return;
            }
        }
        switch (currentAction) {
            case CLIENT_COLUMN:
                clientColumn = input;
                ticketPrice(clientRow, clientColumn);
                break;
            case CLIENT_ROW:
                clientRow = input;
                System.out.println("Enter a seat number in that row:");
                currentAction = allStates.CLIENT_COLUMN;
                break;
            case GET_ROW:
                rows = input;
                System.out.println("Enter the number of seats in each row:");
                currentAction = allStates.GET_COLUMN;
                break;
            case GET_COLUMN:
                columns = input;
                currentAction = allStates.SHOW_MENU;
                grid = new char[rows][columns];
                totalSeats = rows * columns;
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        grid[i][j] = 'S';
                    }
                }
            case SHOW_MENU:
                menu();
                currentAction = allStates.CHECK_INPUT;
                break;
        }
    }
    static void menu() {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }
    static void statistics() {
        System.out.println("\nNumber of purchased tickets: " + ticketsBought);
        double percentage = (double) ticketsBought / totalSeats * 100;
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + income);
        System.out.print("Total income: $");
        System.out.println(totalSeats <= 60 ? totalSeats * 10 : rows % 2 == 0 ?
                (rows / 2 * columns * 10) + (rows / 2 * columns * 8) :
                (rows / 2 * columns * 10) + (rows / 2 + 1) * columns * 8);
        menu();
    }
    static void ticketPrice(int clientRow, int clientColumn){
        if ((clientRow < 1) || ((clientColumn < 1) ||
            (clientRow > rows)) || (clientColumn > columns)) {
            System.out.println("Wrong input!");
            System.out.println("Enter a row number:");
            currentAction = allStates.CLIENT_ROW;
            return;
        }
        if (grid[clientRow - 1][clientColumn - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            System.out.println("Enter a row number:");
            currentAction = allStates.CLIENT_ROW;
            return;
        }
        ticketsBought++;
        grid[clientRow - 1][clientColumn - 1] = 'B';
        if (totalSeats <= 60) {
            System.out.println("Ticket price: $10");
            income += 10;
        } else if (clientRow <= rows / 2) {
            System.out.println("Ticket price: $10");
            income += 10;
        } else {
            System.out.println("Ticket price: $8");
            income += 8;
        }
        System.out.println();
        menu();
        currentAction = allStates.CHECK_INPUT;
    }

    static void showSeats() {
        System.out.println("Cinema:");
        for (int i = 1; i <= columns; i++) { // loop to display the columns
            System.out.printf(i == 1 ? "  1 " : "%d ", i);
        }
        System.out.println();
        for (int i = 0; i < rows; i++) { // loop to display the rows and seats
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        allStates currentState = Cinema.currentAction;
        System.out.println("Enter the number of rows:");
        while (true) {
            currentState = Cinema.currentAction;
            if (currentState == allStates.EXIT) {
                break;
            }
            int getInput = read.nextInt();
            Cinema.inputReceiver(getInput);
        }
    }
}

enum allStates {
     GET_ROW, GET_COLUMN, CLIENT_ROW, CLIENT_COLUMN, SHOW_MENU, CHECK_INPUT, EXIT
}
