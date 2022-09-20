package Ch5;

import java.util.Scanner;

import static java.lang.System.*;

public class P5_34 {
    static String[] type = {"scissor", "rock", "paper"};

    public static void main(String[] args) {
        int player = 0, computer = 0;
        while (player - computer <= 2) {
            if (go()) player++;
            else computer++;
        }
        out.print("You had won 3 times more than computer!");
    }

    public static boolean go() {
        out.print("scissor (0), rock (1), paper (2): ");
        int userInput = new Scanner(in).nextInt();
        if (userInput < 0 || userInput > 2) {
            out.print("Invalid input!");
            return false;
        }
        int rand = (int) (3 * Math.random());
        out.printf("The computer is %s. You are %s. ", type[rand], type[userInput]);
        if (rand == userInput) out.print("It is a draw");
        if (userInput == 0 || userInput == 2) {
            if (rand == 0 || rand == 2) {
                if (rand == 2) {
                    out.print("You won\n");
                    return true;
                } else {
                    out.print("You lose\n");
                    return false;
                }
            }
        }
        if (rand < userInput) {
            out.print("You won\n");
            return true;
        } else {
            out.print("You lose\n");
            return false;
        }
    }
}
