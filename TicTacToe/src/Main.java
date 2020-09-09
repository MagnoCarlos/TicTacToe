import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPosition = new ArrayList<Integer>();

    public static void main(String[] args) {

        char [] [] gameBoard = { {' ', ' ', '|', ' ', ' ', '|', ' ', ' ',},
                                {'-','-', '+', '-','-', '+', '-','-',},
                                {' ', ' ', '|', ' ', ' ', '|', ' ', ' ',},
                                {'-','-', '+', '-','-', '+', '-','-',},
                                {' ', ' ', '|', ' ', ' ', '|', ' ',' ',} };

        PrintBoard(gameBoard);

        System.out.println("Enter your placement below (1-9): ");
        int lap = 1;
        while (true) {
            System.out.println("Lap " + lap + ", Your turn: ");
            Scanner scan = new Scanner(System.in);
            int pos = scan.nextInt();
            System.out.println(pos);
            while (playerPosition.contains(pos) || cpuPosition.contains(pos)) {
                System.out.println("Position taken! Try again");
                pos = scan.nextInt();
            }

            PlacePieace(pos, gameBoard, "player");

            String winningString = checkWinner();
            if (winningString.length() > 0) {
                System.out.println(winningString);// print a msg for the winner
                break;
            }
            // CPU
            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
                System.out.println("Position taken! Try again");
                cpuPos = rand.nextInt(9) + 1;
            }
            PlacePieace(cpuPos, gameBoard,"cpu");

            PrintBoard(gameBoard);

            winningString = checkWinner();
            if (winningString.length() > 0) {
                System.out.println(winningString);// print a msg for the winner
                break;
            }
            
            lap ++;
        } // while loop
    } // main method

    public static void PrintBoard(char [] [] gameBoard) {

        for (char [] row : gameBoard) {

            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    } // PrintBoard

    public static void PlacePieace(int pos, char [] [] gameBoard, String user) {
        char symbol = ' ';
        if (user.equals("player")) {
            symbol = 'X';
            playerPosition.add(pos);
        } else {
            symbol = 'O';
            cpuPosition.add(pos);
        }

        switch (pos) {
            case 1: gameBoard [0] [0] = symbol;
                break;

            case 2: gameBoard [0] [3] = symbol;
                break;

            case 3: gameBoard [0] [6] = symbol;
                break;

            case 4: gameBoard [2] [0] = symbol;
                break;

            case 5: gameBoard [2] [3] = symbol;
                break;

            case 6: gameBoard [2] [6] = symbol;
                break;

            case 7: gameBoard [4] [0] = symbol;
                break;

            case 8: gameBoard [4] [3] = symbol;
                break;

            case 9: gameBoard [4] [6] = symbol;
                break;

            default: break;

        } // Switch

    } // Play

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List rightCol = Arrays.asList(3, 5, 9);
        List midCol = Arrays.asList(2, 5, 8);
        List cross1 = Arrays.asList(3,5, 7);
        List cross2 = Arrays.asList(1, 5, 9);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(rightCol);
        winning.add(midCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPosition.containsAll(l)) {
                return "Winner";
            } else if (cpuPosition.containsAll(l)) {
                return "lost to cpu";
            } else if (playerPosition.size() + cpuPosition.size() == 9) {
                return "Draw";
            }
        }

        return "";

    } // checkWinner
} // End
