package bullscows;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private Scanner sc;
    private Random random;
    private int cows;
    private int bulls;

    public Game(Scanner sc, Random random) {
        this.sc = sc;
        this.random = random;
        this.cows = 0;
        this.bulls = 0;
    }

    public void start () {
        int codeLength = getLength();
        int symbols = getSymbols();
        messageInitiate(codeLength, symbols);
        String secretCode = codeGenerator(codeLength, symbols);
        System.out.println("Okay, let's start a game!");
        System.out.println(secretCode);

        String[] code = secretCode.split("");
        int counter = 1;
        String answer;
        do {
            System.out.printf("Turn %d:%n", counter);
            answer = sc.next();
            grader(code, answer);
            if (answer.equals(secretCode)) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }

            counter++;
        } while (secretCode != answer);
    }

    private int getLength() {
        System.out.println("Input the length of the secret code: ");
        int length = Integer.valueOf(sc.nextLine());
        if (length > 36) {
            System.out.println("Error: can't generate a secret number with a length greater than 36 because there aren't enough unique alphabets and digits.");  
            getLength();
        }
        return length;
    }

    private int getSymbols() {
        System.out.println("Input the number of possible symbols in the code:");
        int possibleSymbols = Integer.valueOf(sc.nextLine());
        return possibleSymbols;
    }

    public void messageInitiate(int len, int sym) {

        if (sym <= 10) {
            System.out.printf("The secret is prepared: %s (0-%s).%n", printStars(len), Character.toString(sym + 47));
        } else {
            System.out.printf("The secret is prepared: %s (0-9, a-%s).%n", printStars(len), Character.toString(sym + 86));
        }

    }

    public String printStars(int len) {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < len; i++) {
            stars.append("*");
        }

        return stars.toString();
    }

    public String codeGenerator(int len, int sym) {
        StringBuilder result = new StringBuilder();
        char symbol;
        while(result.length() != len) {
            int random = (int) (Math.random() * len);
            if (random < 10) {
                symbol = (char) (48 + random); // start with #48 ASCII = 0 to #57 ASCII = 9
            } else {
                symbol = (char) (87 + random); // start with #97 ASCII = a to #122 ASCII = z
            }
            if (!result.toString().contains(String.valueOf(symbol))) {
                result.append(symbol);
            }  
        }
        return result.toString();
    }

    public void grader(String[] code, String answer) {
        bulls = 0;
        cows = 0;
        for (int i = 0; i < code.length; i++) {
            if (code[i].equals(String.valueOf(answer.charAt(i)))) {
                bulls++;
            } else if (answer.contains(code[i])) {
                cows++;
            }
        }
        
        if (bulls > 0 && cows > 0) {
            System.out.printf("Grade: %d bull(s) and %d cows(s).%n", bulls, cows);
        } else if (bulls > 0) {
            System.out.printf("Grade: %d bull(s).%n", bulls);
        } else if (cows > 0) {
            System.out.printf("Grade: %d cows(s).%n", cows);
        } else {
            System.out.printf("Grade: None.%n");

        }

    }
}
