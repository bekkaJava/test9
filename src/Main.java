import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final String PLAYER = "player";
    private static final String PC = "pc";
    private static final char PLAYER_SYMBOL = 'x';
    private static final char PC_SYMBOL = 'O';
    private static final int MAX_MOVES = 11;
    private static final int MIN_MOVES_TO_CHECK_WINNER = 5;


    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        start(scanner);

        scanner.close();
    }


    private static void start(Scanner scanner) {

        int counter = 1;

        char[][] gameBoard = {
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        printBoard(gameBoard);
        System.out.println("Choose a position between 1-9");

        while (true) {

            int playerPosition = 0;
            try {
                while (true) {

                    if (scanner.hasNextInt()) {
                        playerPosition = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("Please enter only a number value");
                        scanner.next();
                    }
                }
            } catch (InputMismatchException e) {
                scanner.next();
                continue;
            }

            boolean checkPlayerInput = choosePosition(gameBoard, playerPosition, PLAYER);
            while (!checkPlayerInput) {
                System.out.println("Position is taken. Please choose a correct position: ");
                playerPosition = scanner.nextInt();
                checkPlayerInput = choosePosition(gameBoard, playerPosition, PLAYER);
            }


            counter += 2;
            if (counter > MIN_MOVES_TO_CHECK_WINNER) {
                if (checkWinner(gameBoard, PLAYER_SYMBOL)) {
                    printBoard(gameBoard);
                    System.out.println("Congrats, you won");
                    return;
                } else if (counter == MAX_MOVES) {
                    printBoard(gameBoard);
                    System.out.println("Game is draw");
                    return;
                }
            }

            Random random = new Random();
            int PcPosition = random.nextInt(9) + 1;

            boolean checkPcInput = choosePosition(gameBoard, PcPosition, PC);
            while (!checkPcInput) {
                PcPosition = random.nextInt(9) + 1;
                checkPcInput = choosePosition(gameBoard, PcPosition, PC);
            }

            if (counter > MIN_MOVES_TO_CHECK_WINNER) {

                if (checkWinner(gameBoard, PC_SYMBOL)) {
                    printBoard(gameBoard);
                    System.out.println("Computer won. Try again");
                    return;
                }
            }
            printBoard(gameBoard);

        }

    }

    public static boolean checkWinner(char[][] gameBoard, char symbol) {

        for (int i = 0; i < 5; i += 2) {
            if (gameBoard[i][0] == symbol && gameBoard[i][2] == symbol && gameBoard[i][4] == symbol ||
                    gameBoard[0][i] == symbol && gameBoard[2][i] == symbol && gameBoard[4][i] == symbol) {
                return true;
            }
        }
        if (gameBoard[0][0] == symbol && gameBoard[2][2] == symbol && gameBoard[4][4] == symbol ||
                gameBoard[0][4] == symbol && gameBoard[2][2] == symbol && gameBoard[4][0] == symbol) {

            return true;
        }

        return false;

    }

    private static boolean choosePosition(char[][] gameBoard, int position, String user) {
        char symbol = user.equals(PLAYER) ? PLAYER_SYMBOL : PC_SYMBOL;

        switch (position) {
            case 1:
                if (gameBoard[0][0] == ' ') {
                    gameBoard[0][0] = symbol;
                    return true;
                }
                break;

            case 2:
                if (gameBoard[0][2] == ' ') {
                    gameBoard[0][2] = symbol;
                    return true;
                }
                break;
            case 3:
                if (gameBoard[0][4] == ' ') {
                    gameBoard[0][4] = symbol;
                    return true;
                }
                break;
            case 4:
                if (gameBoard[2][0] == ' ') {
                    gameBoard[2][0] = symbol;
                    return true;
                }
                break;
            case 5:
                if (gameBoard[2][2] == ' ') {
                    gameBoard[2][2] = symbol;
                    return true;
                }
                break;
            case 6:
                if (gameBoard[2][4] == ' ') {
                    gameBoard[2][4] = symbol;
                    return true;
                }
                break;
            case 7:
                if (gameBoard[4][0] == ' ') {
                    gameBoard[4][0] = symbol;
                    return true;
                }
                break;
            case 8:
                if (gameBoard[4][2] == ' ') {
                    gameBoard[4][2] = symbol;
                    return true;
                }
                break;
            case 9:
                if (gameBoard[4][4] == ' ') {
                    gameBoard[4][4] = symbol;
                    return true;
                }
                break;
            default:
                System.out.println("Invalid Position");
                break;
        }
        return false;
    }

    private static void printBoard(char[][] gameBoard) {
        for (char[] arrays : gameBoard) {
            for (char symbols : arrays) {
                System.out.print(symbols);
            }
            System.out.println();
        }
        System.out.println();
    }
}