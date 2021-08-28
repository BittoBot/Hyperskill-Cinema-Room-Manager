package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Getting the size of the grid and declaring it
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();
        char[][] grid = new char[rows][columns];

        // Setting values to the seats grid
        System.out.println("\nCinema:");
        for (int i = 1; i <= columns; i++) { // loop to display the seats in each row
            System.out.printf(i == 1 ? "  1 " : "%d ", i);
        }
        System.out.println();
        for (int i = 0; i < rows; i++) { // loop to create/display the grid
            System.out.printf("%d", i + 1);
            for (int j = 0; j < columns; j++) {
                grid[i][j] = 'S';
                System.out.printf(" %C", grid[i][j]);
            }
            System.out.println();
        }
        System.out.println();
        // Getting the client's seat and showing the price of the ticket
            // Getting the client's seat and determining the price of the ticket
        System.out.println("Enter a row number:");
        int customerRow = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int customerColumn = scanner.nextInt();
        System.out.println();
        int totalSeats = rows * columns;
        int income = totalSeats <= 60 ? 10 : customerRow <= rows / 2 ? 10 : 8;
        System.out.printf("Ticket price: $%d\n\n", income);
            // Showing the client's seat
        grid[customerRow - 1][customerColumn - 1] = 'B';
        displayArray(grid, columns);
    }
    public static void displayArray(char[][] grid, int columns) {
        System.out.println("Cinema:");
        for (int i = 1; i <= columns; i++) { // loop to display the columns
            System.out.printf(i == 1 ? "  1 " : "%d ", i);
        }
        System.out.println();
        for (int i = 0; i < grid.length; i++) { // loop to display the rows and seats
            System.out.printf("%d ", i + 1);
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
