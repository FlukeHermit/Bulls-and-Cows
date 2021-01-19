package bullscows;
import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    static int bull = 0;
    static int cow = 0;
    static String res = "S";
    public static void main(String[] args) {
        String answer = sc.nextLine();
        String[] secretCode = "9305".split("");
        grader(secretCode, answer);
    }

    public static void grader(String[] code, String answer) {
        for (int i = 0; i < 4; i++) {
            if (code[i].equals(String.valueOf(answer.charAt(i)))) {
                bull++;
            } else if (answer.contains(code[i])) {
                cow++;
            }
        }

        if (bull > 0 && cow > 0) {
            System.out.printf("Grade: %d bull(s) and %d cows(s). The secret code is 9305.", bull, cow);
        } else if (bull > 0) {
            System.out.printf("Grade: %d bull(s). The secret code is 9305.", bull);
        } else if (cow > 0) {
            System.out.printf("Grade: %d cows(s). The secret code is 9305.", cow);
        } else {
            System.out.printf("Grade: None. The secret code is 9305.");

        }
    }
}
