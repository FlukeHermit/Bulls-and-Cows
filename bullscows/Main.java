package bullscows;

import java.util.Scanner;
import java.util.Random;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    public static void main(String[] args) {
        Game game = new Game(sc, random);
        game.start(); 
    }
}