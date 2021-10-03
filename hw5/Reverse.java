import homework.CoolIntArray;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        String[] strings = new String[1_000_001];
        int[] ints = new int[1_000_001];

        Scanner in = new Scanner(System.in);
        
        while (in.hasNextLine()) {
            Scanner line = new Scanner(in.nextLine());

            while (line.hasNextInt()) {
                int num = line.nextInt();
                numbers.add(num);

                if (numInd >= minInCol.length()) {
                    minInCol.add(num);
                } else {
                    minInCol.set(numInd, Math.min(num, minInCol.get(numInd)));
                }

                curMin = Math.min(curMin, minInCol.get(numInd));
                System.out.print(curMin + " ");
                numInd++;
            }

            indexes.add(numbers.length());
            System.out.println();
        }
    }

    private static void printInput(CoolIntArray numbers, CoolIntArray indexes) {
        int numInd = 0;
        for (int i = 0; i < indexes.length(); i++) {
            while (numInd < indexes.get(i)) {
                System.err.print(numbers.get(numInd++) + " ");
            }
            System.err.println();
        }
    }
}