package Ch3;

import java.nio.file.attribute.UserPrincipal;
import java.util.Scanner;

public class P3_17 {

    static String[] type = {"scissor", "rock", "paper"};

    public static void main(String[] args) {
        System.out.print("scissor (0), rock (1), paper (2): ");
        int userInput = new Scanner(System.in).nextInt();
        if (userInput < 0 || userInput > 2) {
            System.out.print("Invalid input!");
            return;
        }
        int rand = (int) (3 * Math.random());
        System.out.printf("The computer is %s. You are %s. ", type[rand], type[userInput]);
        if (rand == userInput) System.out.print("It is a draw");
        if (userInput == 0 || userInput == 2) {
            if (rand == 0 || rand == 2) {
                if (rand == 2) System.out.print("You won");
                else System.out.print("You lose");
                return;
            }
        }
        if (rand < userInput) System.out.print("You won");
        else System.out.print("You lose");
    }
}
