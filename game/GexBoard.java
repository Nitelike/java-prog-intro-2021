package game;

public class GexBoard extends TicTacToeBoard {
    public GexBoard(int n, int k) {
        super(n, n, k);
    }

    @Override
    protected boolean checkWin(Move move) {
        int r = move.getRow();
        int c = move.getCol();

        int topRow = Math.max(0, r - getK());
        int botRow = Math.min(getN() - 1, r + getK());

        int leftCol = Math.max(0, c - getK());
        int rightCol = Math.min(getM() - 1, c + getK());

        int rightTopShift = Math.min(r - topRow, rightCol - c);
        int leftBottomShift = Math.min(c - leftCol, botRow - r);

        return checkLine(r, leftCol, r, rightCol) ||
                checkLine(topRow, c, botRow, c) ||
                checkLine(r - rightTopShift, c + rightTopShift, r + leftBottomShift, c - leftBottomShift);
    }

//    @Override
//    public String toString() {
//        int lenTop = Integer.toString(getM()).length() + 1;
//        int lenSide = Integer.toString(getN()).length() + 1;
//
//        StringBuilder sb = new StringBuilder(alignRight("", lenSide));
//        StringBuilder shift = new StringBuilder();
//
//        for (int i = 1; i < 2 * getM(); i++) {
//            sb.append(alignRight(Integer.toString(i), lenTop));
//        }
//
//        sb.append(System.lineSeparator());
//
//        for (int r = 0; r < getN(); r++) {
//            String sideBuffer = alignRight(Integer.toString(r + 1), lenSide);
//            sb.append(sideBuffer);
//            sb.append(shift);
//            shift.append(" ".repeat(sideBuffer.length()));
//            for (int c = 0; c < getM(); c++) {
//                sb.append(alignRight(CELL_TO_STRING.get(getCell(r, c)), lenTop));
//            }
//            if (r + 1 < getN()) {
//                sb.append(System.lineSeparator());
//            }
//        }
//
//        return  sb.toString();
//    }
}
