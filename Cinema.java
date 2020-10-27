import java.util.Arrays;
import java.util.Scanner;

public class Cinema {
    private static char[][] seats;
    private static Scanner scanner = new Scanner(System.in);
    private static int totalPlaces;
    private static int occupiedSeats = 0;
    private static int income = 0;
    private static int totalIncome = 0;

    private static void initSeats() {
        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();
        seats = new char[rows][columns];
        totalPlaces = rows * columns;

        for (char[] row : seats) {
            Arrays.fill(row, 'S');
        }

        if (totalPlaces > 60) {
            totalIncome = (columns * 10 * (rows / 2)) + (columns * 8 * (rows - (rows / 2)));
        } else {
            totalIncome = totalPlaces * 10;
        }

        System.out.println();
    }

    private static void printCinema() {
        System.out.print("Cinema:\n ");
        for (int i = 0; i < seats[0].length; i++) {
            System.out.print(" " + (i + 1));
        }
        System.out.println();

        for(int i = 0; i < seats.length; i++) {
            System.out.print(i + 1);
            for(int j = 0; j < seats[i].length; j++) {
                System.out.print(" " + seats[i][j]);
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void bookSeat() {
        System.out.println("Enter a row number:");
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        int column = scanner.nextInt();
        int price = 10;
        if (row - 1 >= seats.length || row - 1 < 0 || column - 1 >= seats[0].length || column - 1 < 0) {
            System.out.println("Wrong input!");
            bookSeat();
            return;
        } else if (seats[row - 1][column - 1] == 'B') {
            System.out.println("That ticket has already been purchased!");
            bookSeat();
            return;
        }

        if (totalPlaces > 60 && row > (seats.length / 2)) {
            price = 8;
        }
        seats[row - 1][column - 1] = 'B';
        occupiedSeats++;
        income += price;

        System.out.println("\nTicket price: $" + price + "\n");
    }

    private static void printStatistics() {
        double percent = ((double) occupiedSeats / (double) totalPlaces) * 100;
        System.out.printf("Number of purchased tickets: %d\n" +
                "Percentage: %.2f%%\n" +
                "Current income: $%d\n" +
                "Total income: $%d\n\n", occupiedSeats, percent,
                                    income, totalIncome);
    }

    public static void main(String[] args) {
        initSeats();
        int choice = -1;
        while (choice != 0) {
            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    printCinema();
                    break;
                case 2:
                    bookSeat();
                    break;
                case 3:
                    printStatistics();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
}