package game;

import java.util.Arrays;
import java.util.Map;

public class TicTacToeBoard implements Board, Position {
    protected Cell[][] field;
    protected Cell turn;
    protected int n, m, k;
    protected int emptyCount;
    protected Map<Cell, String> CELL_TO_STRING = Map.of(
        Cell.E, ".",
        Cell.X, "X",
        Cell.O, "0"
    );

    public TicTacToeBoard(int n, int m, int k) {
        this.n = n;
        this.m = m;
        this.k = k;
        if (k > Math.max(n, m)) {
            throw new AssertionError("k cannot be greater than max board side");
        }
        field = new Cell[n][m];
        clear();
    }

    @Override
    public Position getPosition() {
        return copyPosition();
    }

    @Override
    public Cell getCell(int row, int col) {
        return field[row][col];
    }

    @Override
    public Position copyPosition() {
        return copy();
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOOSE;
        }
        field[move.getRow()][move.getCol()] = move.getValue();
        emptyCount--;
        if (checkWin(move)) {
            return GameResult.WIN;
        }
        if (checkDraw()) {
            return GameResult.DRAW;
        }
        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    @Override
    public void clear() {
        emptyCount = n * m;
        turn = Cell.X;
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
    }

    @Override
    public boolean isValid(Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getCol() && move.getCol() < m
                && field[move.getRow()][move.getCol()] == Cell.E
                && move.getValue() == turn;
    }

    protected TicTacToeBoard copy() {
        TicTacToeBoard copy = new TicTacToeBoard(n, m, k);

        for (int r = 0; r < n; r++) {
            System.arraycopy(field[r], 0, copy.field[r], 0, m);
        }

        copy.turn = turn;
        copy.emptyCount = emptyCount;

        return copy;
    }

    protected boolean checkDraw() {
        return (emptyCount == 0);
    }

    protected boolean checkWin(Move move) {
        int r = move.getRow();
        int c = move.getCol();

        int topRow = Math.max(0, r - k);
        int botRow = Math.min(n - 1, r + k);

        int leftCol = Math.max(0, c - k);
        int rightCol = Math.min(m - 1, c + k);

        int leftTopShift = Math.min(c - leftCol, r - topRow);
        int rightBottomShift = Math.min(rightCol - c, botRow - r);

        int rightTopShift = Math.min(r - topRow, rightCol - c);
        int leftBottomShift = Math.min(c - leftCol, botRow - r);

        return checkLine(r, leftCol, r, rightCol) ||
                checkLine(topRow, c, botRow, c) ||
                checkLine(r - leftTopShift, c - leftTopShift, r + rightBottomShift, c + rightBottomShift) ||
                checkLine(r - rightTopShift, c + rightTopShift, r + leftBottomShift, c - leftBottomShift);
    }

    public int getN() {
        return n;
    }

    public int getM() {
        return m;
    }

    public int getK() {
        return k;
    }

    protected boolean checkLine(int x1, int y1, int x2, int y2) {
        int dx = Integer.compare(x2, x1);
        int dy = Integer.compare(y2, y1);

        int maxSequenceLength = 0;
        int curSequenceLength = 0;

        while (true) {
            if (0 <= x1 && x1 < n && 0 <= y1 && y1 < m && field[x1][y1] == turn) {
                curSequenceLength++;
                maxSequenceLength = Math.max(maxSequenceLength, curSequenceLength);
            } else {
                curSequenceLength = 0;
            }
            if (x1 == x2 && y1 == y2) {
                break;
            }
            x1 += dx;
            y1 += dy;
        }

        return maxSequenceLength >= k;
    }

    protected String alignRight(String s, int len) {
        return (" ".repeat(len - s.length()) + s);
    }

    @Override
    public String toString() {
        int lenTop = Integer.toString(m).length() + 1;
        int lenSide = Integer.toString(n).length() + 1;

        StringBuilder sb = new StringBuilder(alignRight("", lenSide));

        for (int i = 1; i <= m; i++) {
            sb.append(alignRight(Integer.toString(i), lenTop));
        }

        sb.append(System.lineSeparator());

        for (int r = 0; r < n; r++) {
            sb.append(alignRight(Integer.toString(r + 1), lenSide));
            for (Cell cell : field[r]) {
                sb.append(alignRight(CELL_TO_STRING.get(cell), lenTop));
            }
            if (r + 1 < n) {
                sb.append(System.lineSeparator());
            }
        }

        return  sb.toString();
    }
}
