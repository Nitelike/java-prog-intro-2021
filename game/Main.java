package game;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    private static int n, m, k;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter m n k:");
        m = in.nextInt();
        n = in.nextInt();
        k = in.nextInt();

        if (n <= 0 || m <= 0 || k <= 0) {
            throw new AssertionError("Invalid n m k parameters");
        }

        System.out.println("\n###\nTwo player Game\n###\n");

        final int result = new TwoPlayerGame(
                new TicTacToeBoard(n, m, k),
                new RandomPlayer(n, m),
                new RandomPlayer(n, m)
        ).play(false);

        printResultMessage(result);

        System.out.println("\n###\nTournament\n###\n");

        Player[] players = new Player[5];

        for (int i = 0; i < 5; i++) {
            players[i] = new RandomPlayer(n, m);
        }

        TournamentGame tournament = new TournamentGame(new TicTacToeBoard(n, m, k), players);
        Map<String, Integer> standings = tournament.play(false, false);

        for (Map.Entry<String, Integer> entry : standings.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        System.out.println("\n###\nTwo player Game on Gex-like board\n###\n");

        final int gexResult = new TwoPlayerGame(
                new GexBoard(n, k),
                new RandomPlayer(n, n),
                new RandomPlayer(n, n)
        ).play(true);

        printResultMessage(gexResult);
    }

    public static void printResultMessage(int result) {
        switch (result) {
            case 1:
                System.out.println("First player won");
                break;
            case 2:
                System.out.println("Second player won");
                break;
            case 0:
                System.out.println("Draw");
                break;
            default:
                throw new AssertionError("Unknown result " + result);
        }
        return;
    }
}
