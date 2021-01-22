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
        int symbols = getSymbols(codeLength);
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
        int length = 0;
        System.out.println("Input the length of the secret code: ");
        String lengthInput = sc.nextLine();

        try {
            length = Integer.parseInt(lengthInput);
        } catch (NumberFormatException e){
            System.out.println("Error: Incorrect number format");
            System.exit(0);
        }
        
        if (length < 1) {
            System.out.println("Error: 0 isn't a valid number");
            System.exit(0);
        }
        if (length > 36) {
            System.out.println("Error: maximum сode length is 36 characters");
            System.exit(0);
        }
        return length;
    }

    private int getSymbols(int length) {
        int charsCount = 0;
        System.out.println("Input the number of possible symbols in the code:");
        String charsInput = sc.nextLine();

        
        try {
            charsCount = Integer.parseInt(charsInput);
        } catch (NumberFormatException e){
            System.out.println("Error: Incorrect number format");
            System.exit(0);
        }
        
        if (charsCount < length) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.\n", length, charsCount);
            System.exit(0);
        }
        
        if (charsCount > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }
        return charsCount;

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


