package bullscows;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    static int bull = 0;
    static int cow = 0;

    public static void main(String[] args) {
        System.out.println("Please, enter the secret code's length:");
        int codeLength = sc.nextInt();

        if (codeLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            return;
        }
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        while(result.length() != codeLength) {
            int randomInt = random.nextInt(10);
            if (result.length() == 0 || !result.toString().contains((String.valueOf(randomInt)))) {
                result.append(randomInt);
            }
        }
        String res = result.toString(); 
        System.out.println(res);
        String[] secretCode = res.split("");
        int counter = 1;
        String answer;
        System.out.println("Okay, let's start a game!");
        do {
            System.out.printf("Turn %d:%n", counter);
            answer = sc.next();
            grader(secretCode, answer);
            if (answer.equals(res)) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            counter++;
        } while (res != answer);
    }

    public static void grader(String[] code, String answer) {
        bull = 0;
        cow = 0;
        for (int i = 0; i < code.length; i++) {
            if (code[i].equals(String.valueOf(answer.charAt(i)))) {
                bull++;
            } else if (answer.contains(code[i])) {
                cow++;
            }
        }

        if (bull > 0 && cow > 0) {
            System.out.printf("Grade: %d bull(s) and %d cows(s).%n", bull, cow);
        } else if (bull > 0) {
            System.out.printf("Grade: %d bull(s).%n", bull);
        } else if (cow > 0) {
            System.out.printf("Grade: %d cows(s).%n", cow);
        } else {
            System.out.printf("Grade: None.%n");

        }
    }
}