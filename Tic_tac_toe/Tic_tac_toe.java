import java.util.Scanner;

public class Tic_tac_toe {
    static char field[] = { '_', '_', '_', '_', '_', '_', '_', '_', '_' };

    public static void main(String[] args) {
        int turn = -1, position, count = 1;
        char letter = ' ';
        boolean result = true;
        Scanner sc = new Scanner(System.in);
        do {
            turn *= -1;
            Display();
            if (turn == 1) {
                System.out.println("\n X turn \n");
                letter = 'X';
            } else {
                System.out.println("\n O turn \n");
                letter = 'O';
            }
            System.out.print("Enter position: ");
            position = sc.nextInt();
            position--;
            if (field[position] == '_') {
                field[position] = letter;
            } else {
                System.out.println("* Enter valid position *");
                turn *= -1;
                continue;
            }
            // Checking after five chances because then only a person could put a letter 3
            // times
            if (count >= 5) {
                result = HasWon(letter);
            }
            count++;
        } while (result && count < 10);

        Display();
        if (result == true) {
            System.out.println("\n * Match Tied *");
        } else {
            System.out.println("\n * " + letter + " has won the game *");
        }
        sc.close();
    }

    // Code for verifying whether the player has won the game
    private static boolean HasWon(char letter) {
        boolean flag = true;
        for (int i = 0; (i < 9 && flag); i = i + 4) {

            if (i == 0 && field[i] == letter) {
                int adjacent[][] = { { 1, 2 }, { 3, 6 } };
                flag = check(adjacent, 2, 0, letter);

            } else if (i == 4 && field[i] == letter) {
                int adjacent[][] = { { 1, -1 }, { 3, -3 }, { 2, -2 }, { 4, -4 } };
                flag = check(adjacent, 4, 4, letter);

            } else if (i == 8 && field[i] == letter) {
                int adjacent[][] = { { -1, -2 }, { -3, -6 } };
                flag = check(adjacent, 2, 8, letter);
            }
        }
        return flag;
    }

    // Code for checking the adjacent places
    private static boolean check(int[][] adjacent, int loop, int position, char letter) {
        int temp1, temp2;
        for (int i = 0; i < loop; i++) {
            temp1 = position + adjacent[i][0];
            temp2 = position + adjacent[i][1];
            if (temp1 >= 0 && temp1 < 9 && temp2 >= 0 && temp2 < 9) {
                if (field[temp1] == letter && field[temp2] == letter) {
                    return false;
                }
            }
        }
        return true;
    }

    // Code for displaying the Board
    private static void Display() {
        for (int i = 1; i < 10; i++) {
            System.out.print(field[i - 1] + " ");
            if (i % 3 == 0) {
                System.out.println();
            }
        }
    }
}
