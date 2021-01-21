package bullscows;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    static int bull = 0;
    static int cow = 0;

    public static void main(String[] args) {
        String result = "";
        System.out.println("Please, enter the secret code's length:");
        int codeLength = sc.nextInt();

        if (codeLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            return;
        }
        while(result.length() != codeLength) {
            // get nanosecond
            long pseudoRandomNumber = System.nanoTime();

            // reverse nanosecond and remove leading zeros
            String tempNumber = String.valueOf(Long.parseLong(new StringBuilder(String.valueOf(pseudoRandomNumber))
                    .reverse()
                    .toString()));

            // get unique numbers and form the result
            for (int i = 0; i < tempNumber.length(); i++) {

                if (!result.contains(String.valueOf(tempNumber.charAt(i)))) {
                    result += String.valueOf(tempNumber.charAt(i));
                }
                if (result.length() == codeLength) {
                    break;
                }
            }
        }
        System.out.println(result);
        String[] secretCode = result.split("");
        int counter = 1;
        String answer;
        System.out.println("Okay, let's start a game!");
        do {
            System.out.printf("Turn %d:%n", counter);
            answer = sc.next();
            grader(secretCode, answer);
            if (answer.equals(result)) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            grader(secretCode, answer);

            counter++;
        } while (result != answer);
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