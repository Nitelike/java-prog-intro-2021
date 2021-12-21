package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        while (true) {
            System.out.println("Current position");
            System.out.println(position);
            System.out.println("Enter your move for " + position.getTurn());
            Move move = new Move(in.nextInt() - 1, in.nextInt() - 1, position.getTurn());
            if (position.isValid(move)) {
                return move;
            } else {
                System.out.println("Your move: " + move + " is not valid. Try again");
            }
        }
    }
}
